package jk.kamoru.db.simplemongo;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.Mongo;
import com.mongodb.MongoClient;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;

/**
 * Hello world!
 *
 */
public class App 
{
	Mongo mongo;
	MongoClient mongoClient;
	DB db;
	
	void mongoTest(String ip, int port, String dbname, String user, String pwd) throws Exception {
		
		List<MongoCredential> credentialsList = new ArrayList<MongoCredential>();
		credentialsList.add(MongoCredential.createMongoCRCredential(user, dbname, pwd.toCharArray()));
		ServerAddress serverAddress = new ServerAddress(ip, port);
		mongoClient = new MongoClient(serverAddress, credentialsList);
		db = mongoClient.getDB(dbname);
		
		
		DBCollection userTable = db.getCollection("userTable");
		/*
		 * {"name" : "MongoDB", "type" : "database", "count" : 1, "info" : {"x" : 203, "y" : 102}}
		 */
		BasicDBObject doc = new BasicDBObject("name", "MongoDB").append("type", "database").append("count", 1).append("info", new BasicDBObject("x", 203).append("y", 102));
		
		// insert
		userTable.insert(doc);
		
		// check collections
		Set<String> colls =  db.getCollectionNames();
		for (String name : colls)
			System.out.println("CollectionName : " + name);
		
		// count
		System.out.println("Collection userTable size : " + userTable.getCount());

		// select all
		DBCursor cursor = userTable.find();
		while (cursor.hasNext()) {
			System.out.println("all data : " + cursor.next());
		}
	
		cursor = userTable.find();
		while (cursor.hasNext()) {
			DBObject row = cursor.next();
			Map rowMap = row.toMap();
			Map infoMap = (Map)row.get("info");
			System.out.println("data x : " + infoMap.get("x"));
		}
	
		// select where
		BasicDBObject query = new BasicDBObject();
		query.put("type", "database");
		cursor = userTable.find(query);
		while (cursor.hasNext())
			System.out.println("where type : " + cursor.next());
		
		// select where 2
		BasicDBObject query2 = new BasicDBObject();
		query2.put("count", new BasicDBObject("$gt", 0));
		query2.put("count", new BasicDBObject("$lt", 2));
		cursor = userTable.find(query2);
		while (cursor.hasNext()) {
			System.out.println("where count : " + cursor.next().get("count"));
		}
		
		// creat index
		userTable.createIndex(new BasicDBObject("name", 1));
		userTable.createIndex(new BasicDBObject("info", 1));
		// select index
		List<DBObject> list = userTable.getIndexInfo();
		for (DBObject o : list)
			System.out.println("index : " + o);
		
	}
	
    public static void main( String[] args ) throws Exception
    {
    	App app = new App();
    	app.mongoTest("10.30.6.114", 27017, "testDB", "kamoru", "crazyjk588");
    	
        System.out.println( "Tested MongoDB" );
    }
}

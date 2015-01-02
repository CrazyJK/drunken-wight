package jk.kamoru.db.simplemongo.spring;

import java.util.List;

public interface UserDAO {

	User insert(User user);
	
	User getUser(User user);
	
	List<User> getUsers();
	
	User updateUser(User user);
	
	void deleteUser(User user);

}

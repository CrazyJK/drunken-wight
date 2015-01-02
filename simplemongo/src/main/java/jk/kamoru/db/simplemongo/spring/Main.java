package jk.kamoru.db.simplemongo.spring;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {

	public static void main(String[] args) {

		ApplicationContext context = new ClassPathXmlApplicationContext("mongo-context.xml");
 	   
        UserDAO userDAO = context.getBean(UserDAO.class);
        
        
        User user = new User();
        user.setId(0);
        user.setUserName("kamoru");
        user.setPassword("adsa");

        userDAO.deleteUser(user);

//        userDAO.insert(user);
        
        System.out.println(userDAO.getUsers());
        
        user.setPassword("26726384723");
        userDAO.updateUser(user);

        System.out.println(userDAO.getUsers());

	}

}

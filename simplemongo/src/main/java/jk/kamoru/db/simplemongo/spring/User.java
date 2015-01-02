package jk.kamoru.db.simplemongo.spring;

import java.io.Serializable;

public class User implements Serializable {

	private static final long serialVersionUID = 3306563860679319438L;

	private long id;
	private String userName;
	private String password;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return String.format("User [id=%s, userName=%s, password=%s]", id,
				userName, password);
	}
	
}

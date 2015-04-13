package demo.action;

import demo.entity.User;

public class WelcomeAction {
	//private String name;

	private User user = new User();
	public String execute() {
		if ("monster".equalsIgnoreCase(user.getName())) {
			return "fail";
		}
		return "success";
	}

//	public String getName() {
//		return name;
//	}
//
//	public void setName(String name) {
//		this.name = name;
//	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}

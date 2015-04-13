package xuxu.blog.login.action;

import xuxu.blog.entity.User;


public class LoginAction {

	private User user = new User();
	public String execute() {
		if ("monster".equalsIgnoreCase(user.getName())) {
			return "fail";
		}
		return "success";
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}

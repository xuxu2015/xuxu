package demo.action;

public class WelcomeAction {
	private String name;

	public String execute() {
		if ("monster".equalsIgnoreCase(name)) {
			return "fail";
		}
		return "success";
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}

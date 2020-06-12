package bean;

public class Comment {
	private int id;
	private String name;
	private String email;
	private String comment;
	private String dateTime;

	public Comment(String name, String email, String comment, String dateTime) {
		this.name = name;
		this.email = email;
		this.comment = comment;
		this.dateTime = dateTime;
	}

	public Comment(int id, String name, String email, String comment, String dateTime) {
		this.id = id;
		this.name = name;
		this.email = email;
		this.comment = comment;
		this.dateTime = dateTime;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public String getdateTime() {
		return dateTime;
	}

	public void setdateTime(String dateTime) {
		this.dateTime = dateTime;
	}


}

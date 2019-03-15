package myblog.bean;

public class User {
    private String username;
    private String password;
    private String avatar;
    private String gender;
    private String bio;
    public User() {
    	super();
    }
    public User(String username,String password, String avatar, String gender, String bio) {
    	super();
    	this.username = username;
    	this.password = password;
    	this.avatar = avatar;
    	this.gender = gender;
    	this.bio = bio;
    }
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getAvatar() {
		return avatar;
	}
	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getBio() {
		return bio;
	}
	public void setBio(String bio) {
		this.bio = bio;
	}
}

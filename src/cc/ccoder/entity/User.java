package cc.ccoder.entity;


/**
 * 
 * @author chencong 用户实体
 */
public class User {

	private Integer id;
	private String username;
	private String password;
	private String sex;
	private String city;
	private String phone;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public User(Integer id, String username, String password, String sex, String city, String phone) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.sex = sex;
		this.city = city;
		this.phone = phone;
	}

	public User() {
		super();
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", password=" + password + ", sex=" + sex + ", city="
				+ city + ", phone=" + phone + "]";
	}

}

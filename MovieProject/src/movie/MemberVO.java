package movie;

import java.sql.Date;

public class MemberVO {
	private String id;
	private String password;
	private String email;
	private String phone;
	private Date birth;
	public MemberVO() {
		super();
		// TODO Auto-generated constructor stub
	}
	public MemberVO(String id, String password, String email, String phone, Date birth) {
		super();
		this.id = id;
		this.password = password;
		this.email = email;
		this.phone = phone;
		this.birth = birth;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public Date getBirth() {
		return birth;
	}
	public void setBirth(Date birth) {
		this.birth = birth;
	}
	
	
}

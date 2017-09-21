package kr.spring.tiles.member.model.dto;

import java.sql.Date;

public class MemberVO {
	private String level;
	private String id;
	private String pw;
	private String name; 
	private String gender; 
	private String birth; 
	private String tel; 
	private String email; 
	private String postcode; 
	private String address; 
	private String bike_yn; 
	private String bike_type; 
	private String club_yn; 
	private String how_many; 
	private String join_root; 
	private Date userRegdate; // java.sql.Date
	private Date userUpdatedate;
	
	
	
	
	public String getLevel() {
		return level;
	}




	public void setLevel(String level) {
		this.level = level;
	}




	public String getId() {
		return id;
	}




	public void setId(String id) {
		this.id = id;
	}




	public String getPw() {
		return pw;
	}




	public void setPw(String pw) {
		this.pw = pw;
	}




	public String getName() {
		return name;
	}




	public void setName(String name) {
		this.name = name;
	}




	public String getGender() {
		return gender;
	}




	public void setGender(String gender) {
		this.gender = gender;
	}




	public String getBirth() {
		return birth;
	}




	public void setBirth(String birth) {
		this.birth = birth;
	}




	public String getTel() {
		return tel;
	}




	public void setTel(String tel) {
		this.tel = tel;
	}




	public String getEmail() {
		return email;
	}




	public void setEmail(String email) {
		this.email = email;
	}




	public String getPostcode() {
		return postcode;
	}




	public void setPostcode(String postcode) {
		this.postcode = postcode;
	}




	public String getAddress() {
		return address;
	}




	public void setAddress(String address) {
		this.address = address;
	}




	public String getBike_yn() {
		return bike_yn;
	}




	public void setBike_yn(String bike_yn) {
		this.bike_yn = bike_yn;
	}




	public String getBike_type() {
		return bike_type;
	}




	public void setBike_type(String bike_type) {
		this.bike_type = bike_type;
	}




	public String getClub_yn() {
		return club_yn;
	}




	public void setClub_yn(String club_yn) {
		this.club_yn = club_yn;
	}




	public String getHow_many() {
		return how_many;
	}




	public void setHow_many(String how_many) {
		this.how_many = how_many;
	}




	public String getJoin_root() {
		return join_root;
	}




	public void setJoin_root(String join_root) {
		this.join_root = join_root;
	}




	public Date getUserRegdate() {
		return userRegdate;
	}




	public void setUserRegdate(Date userRegdate) {
		this.userRegdate = userRegdate;
	}




	public Date getUserUpdatedate() {
		return userUpdatedate;
	}




	public void setUserUpdatedate(Date userUpdatedate) {
		this.userUpdatedate = userUpdatedate;
	}




	// toString()
	@Override
	public String toString() {
		return "MemberVO [level=" + level + "id=" + id + ", Pw=" + pw + ", Name=" + name + ", Email="
				+ email + ", userRegdate=" + userRegdate + ", userUpdatedate=" + userUpdatedate 
				+ ", gender=" + gender + ", birth=" + birth + ", tel=" + tel
				+ ", postcode=" + postcode + ", address=" + address 
				+ ", bike_yn=" + bike_yn+ ", bike_type=" + bike_type + "club_yn=" + club_yn
				+ ", join_root=" + join_root+ ", how_many=" + how_many +"]";
	}
}

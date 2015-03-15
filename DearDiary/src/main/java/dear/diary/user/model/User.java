package dear.diary.user.model;

import java.util.Set;

import dear.diary.diary.model.Diary;


public class User {    
	private int id;

	private String username;

	private String password;
	
	private String email;
	
	private Set<Diary> diaries;
	
	public int getId() {
		return id;
	} 
	public void setId(int id) {
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
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Set<Diary> getDiaries() {
		return diaries;
	}
	public void setDiaries(Set<Diary> diaries) {
		this.diaries = diaries;
	}

}

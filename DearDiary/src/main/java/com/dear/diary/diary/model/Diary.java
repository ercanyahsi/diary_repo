package com.dear.diary.diary.model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.dear.diary.diarypage.model.DiaryPage;
import com.dear.diary.user.model.User;

@Entity
@Table(name="DIARIES")
public class Diary {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="DIARY_ID")
	private int diaryId;
	
	@Column(name="DIARY_NAME")
	private String diaryName;
	
	@OneToMany(cascade=CascadeType.ALL,fetch=FetchType.LAZY)
	@JoinColumn(name="DIARY_ID")
	private Set<DiaryPage> diaryPages;
	
	@ManyToOne
	@JoinColumn(name="USER_ID")
	private User user;
	
	
	public String getDiaryName() {
		return diaryName;
	}
	public void setDiaryName(String diaryName) {
		this.diaryName = diaryName;
	}

	public int getDiaryId() {
		return diaryId;
	}
	public void setDiaryId(int diaryId) {
		this.diaryId = diaryId;
	}
	public Set<DiaryPage> getDiaryPages() {
		return diaryPages;
	}
	public void setDiaryPages(Set<DiaryPage> diaryPages) {
		this.diaryPages = diaryPages;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	
}

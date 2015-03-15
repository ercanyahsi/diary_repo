package dear.diary.diary.model;

import java.util.Set;

import dear.diary.diarypage.model.DiaryPage;
import dear.diary.user.model.User;

public class Diary {

	private int diaryId;
	private int userId;
	private String diaryName;
	
	private Set<DiaryPage> diaryPages;
	
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
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public Set<DiaryPage> getDiaryPages() {
		return diaryPages;
	}
	public void setDiaryPages(Set<DiaryPage> diaryPages) {
		this.diaryPages = diaryPages;
	}
	
}

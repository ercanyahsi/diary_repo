package dear.diary.diarypage.model;

import java.sql.Date;

import dear.diary.diary.model.Diary;

public class DiaryPage {

	private int recordId;
	private int diaryId;
	private Date pageDate;
	private int contentLanguage;
	private String content;
	
	public DiaryPage(){
		
	}
	
	public DiaryPage(Date pageDate){
		this.pageDate = pageDate;
	}
	
	public int getRecordId() {
		return recordId;
	}
	public void setRecordId(int recordId) {
		this.recordId = recordId;
	}
	public int getDiaryId() {
		return diaryId;
	}
	public void setDiaryId(int diaryId) {
		this.diaryId = diaryId;
	}
	public Date getPageDate() {
		return pageDate;
	}
	public void setPageDate(Date pageDate) {
		this.pageDate = pageDate;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}

	public int getContentLanguage() {
		return contentLanguage;
	}

	public void setContentLanguage(int contentLanguage) {
		this.contentLanguage = contentLanguage;
	}
}

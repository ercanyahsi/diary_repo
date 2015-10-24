package dear.diary.diarypage.model;

import java.sql.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import dear.diary.sharedpage.model.SharedPage;

@Entity
@Table(name="diary_pages")
public class DiaryPage {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int recordId;
	
	@Column(name="DIARY_ID")
	private int diaryId;
	
	@Column(name="PAGE_DATE")
	private Date pageDate;
	
	@Column(name="CONTENT_LANGUAGE")
	private int contentLanguage;
	
	@Column(name="CONTENT")
	private String content;
	
	@OneToOne(cascade=CascadeType.PERSIST, fetch=FetchType.LAZY)
	@JoinColumn(name="recordId")
	private SharedPage sharedPage;
	
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

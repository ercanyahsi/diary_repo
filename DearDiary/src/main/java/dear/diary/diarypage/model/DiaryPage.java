package dear.diary.diarypage.model;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

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
	
	@Column(name="CONTENT", columnDefinition="TEXT")
	private String content;
	
	@Column(name="SHARED")
	private short shared=0;
	
	
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

	public short getShared() {
		return shared;
	}

	public void setShared(short shared) {
		this.shared = shared;
	}

}

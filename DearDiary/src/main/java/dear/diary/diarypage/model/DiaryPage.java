package dear.diary.diarypage.model;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

import dear.diary.diary.model.Diary;
import javassist.expr.Instanceof;

@Entity
@Table(name = "diary_pages")
public class DiaryPage {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "RECORD_ID")
	private int recordId;

	@Column(name = "DIARY_ID")
	private int diaryId;

	@Column(name = "PAGE_DATE")
	private Date pageDate;

	@Column(name = "CONTENT_LANGUAGE")
	private int contentLanguage;

	@Column(name = "CONTENT", columnDefinition = "TEXT")
	private String content;

	@Column(name = "SHARED")
	private short shared = 0;

	@Column(name = "LIKE_COUNT")
	private int likeCount = 0;

	public DiaryPage() {

	}

	public DiaryPage(Date pageDate) {
		this.pageDate = pageDate;
	}

	public int getRecordId() {
		return recordId;
	}

	public void setRecordId(int recordId) {
		this.recordId = recordId;
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

	public int getDiaryId() {
		return diaryId;
	}

	public void setDiaryId(int diaryId) {
		this.diaryId = diaryId;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}
		if (obj == this) {
			return true;
		}
		if (obj.getClass() != getClass()) {
			return false;
		}

		DiaryPage rhs = (DiaryPage) obj;
		return rhs.getRecordId() == this.getRecordId();
	}
	
	@Override
	public int hashCode() { 
		return this.getRecordId();
	}

	public int getLikeCount() {
		return likeCount;
	}

	public void setLikeCount(int likeCount) {
		this.likeCount = likeCount;
	}

}

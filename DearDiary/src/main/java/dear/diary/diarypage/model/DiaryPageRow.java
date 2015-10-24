package dear.diary.diarypage.model;

import java.sql.Date;

import dear.diary.diary.model.Diary;

public class DiaryPageRow {

	private int recordId;
	private Date pageDate;
	private int count;
	
	public DiaryPageRow(){
		
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

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	
	
}

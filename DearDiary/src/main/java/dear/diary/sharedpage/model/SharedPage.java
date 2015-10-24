package dear.diary.sharedpage.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import dear.diary.diarypage.model.DiaryPage;

@Entity
@Table(name="sharedpage")
public class SharedPage {

	@Id
	@GeneratedValue
	private int id;
	public int getId() {
		return id;
	}

	
	@Column(name="recordId")
	private int recordId;
	
	@OneToOne(mappedBy="sharedPage")
	private DiaryPage diaryPage;
	
	

	public int getRecordId() {
		return recordId;
	}
	public void setRecordId(int recordId) {
		this.recordId = recordId;
	}
}

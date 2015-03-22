package dear.diary.diarypage.dao;

import java.sql.Date;

import dear.diary.diary.model.Diary;
import dear.diary.diarypage.model.DiaryPage;

public interface DiaryPageDAO {
	 public DiaryPage getByDate(Diary diary, Date date);
	 public void saveDiaryPage(DiaryPage diaryPage);
	 public void saveOrUpdateDiaryPage(DiaryPage diaryPage);
	 public int getMaxDiaryPageId(int diaryId);
	 public DiaryPage loadByRecordId(int diaryId, int recordId);
}

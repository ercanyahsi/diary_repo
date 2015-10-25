package dear.diary.diarypage.dao;

import java.sql.Date;
import java.util.List;

import dear.diary.diary.model.Diary;
import dear.diary.diarypage.model.DiaryPage;

public interface DiaryPageDAO {
	 public List<DiaryPage> getByDate(Diary diary, Date date);
	 public void saveDiaryPage(DiaryPage diaryPage);
	 public void saveOrUpdateDiaryPage(DiaryPage diaryPage);
	 public int getMaxDiaryPageId(int diaryId);
	 public DiaryPage loadByRecordId(int diaryId, int recordId);
	 public DiaryPage loadByDate(int diaryId, Date date);
	 public void sharePage(int diaryId, Date date);
}

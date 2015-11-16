package com.dear.diary.diarypage.dao;

import java.sql.Date;
import java.util.List;

import com.dear.diary.diary.model.Diary;
import com.dear.diary.diarypage.model.DiaryPage;

public interface DiaryPageDAO {
	 public List<DiaryPage> getByDate(Diary diary, Date date, int pageNumber, int pageSize);
	 public void saveDiaryPage(DiaryPage diaryPage);
	 public void saveOrUpdateDiaryPage(DiaryPage diaryPage);
	 public int getMaxDiaryPageId(int diaryId);
	 public DiaryPage loadByRecordId(int diaryId, int recordId);
	 public DiaryPage loadByDate(int diaryId, Date date);
	 public void sharePage(int diaryId, Date date);
	 public List<DiaryPage> getSharedList(int userId, int diaryId, int pageNumber, int pageSize);
	 public DiaryPage viewPage(int userId, int recordId) throws Exception;
	 public List<DiaryPage> getSharedUserViewedList(int userId, int diaryId);
	 public long getTotalCount(Diary diary, Date date);
	    public long getSharedListCount(int userId, int diaryId);
}

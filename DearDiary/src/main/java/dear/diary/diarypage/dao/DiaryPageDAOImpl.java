package dear.diary.diarypage.dao;

import java.sql.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import dear.diary.diary.model.Diary;
import dear.diary.diarypage.model.DiaryPage;
import dear.diary.user.model.User;
import util.common.constants.IConstants;

@Repository
public class DiaryPageDAOImpl implements DiaryPageDAO {


    private SessionFactory sessionFactory;
    
    @Autowired
    public DiaryPageDAOImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
    
    public long getTotalCount(Diary diary, Date date) {
    	Query query = currentSession().createQuery("SELECT count(p.recordId) FROM DiaryPage p where p.diaryId =:diaryId ");
    	query.setParameter("diaryId", diary.getDiaryId());
    	Long result = (Long) query.uniqueResult();
    	return result;    	
    }

    public List<DiaryPage> getByDate(Diary diary, Date date, int pageNumber, int pageSize){
    	
    	long totalCount = getTotalCount(diary, date);
    	
    	Query query = currentSession().createQuery("SELECT p FROM DiaryPage p where p.diaryId =:diaryId order by p.pageDate desc ");
        
    	int lastPageNumber = (int) ((totalCount / pageSize) + 1);

    	query.setParameter("diaryId", diary.getDiaryId());
    	
    	query.setFirstResult((pageNumber - 1) * pageSize);
    	query.setMaxResults(pageSize);
    	
        List<DiaryPage> list = (List<DiaryPage>) query.list();
        
    	return list;
    }
    

    public DiaryPage loadByDate(int diaryId, Date date){
    	DiaryPage result = null;
    	

        Criteria cr = currentSession().createCriteria(DiaryPage.class);
        cr.add(Restrictions.eq("diaryId", diaryId));
        cr.add(Restrictions.eq("pageDate", date));
        List<DiaryPage> list = (List<DiaryPage>) cr.list();
        if (list.size()>0)
        	result = (DiaryPage) list.iterator().next();
        	
    	
    	return result;
    }
    
    public void sharePage(int diaryId, Date date){
    	
    	Query queryForUser = currentSession().createQuery("select user from User user left join fetch user.diaries diaries where diaries.diaryId=:diaryId");
    	queryForUser.setParameter("diaryId", diaryId);
    	User user = (User) queryForUser.list().iterator().next();
    	
    	user.setViewRight(user.getViewRight()+IConstants.VIEW_RIGHT_MULTIPLIER);
    	user.setShareCount(user.getShareCount()+1);
    	
    	DiaryPage pageToShare = loadByDate(diaryId, date);
    	pageToShare.setShared((short)1);
    }
    
    public int getMaxDiaryPageId(int diaryId){
    	Criteria criteria = currentSession().createCriteria(DiaryPage.class).setProjection(Projections.max("recordId"));

    	return (Integer) criteria.uniqueResult();
    }

    public void saveDiaryPage(DiaryPage diaryPage) {
       currentSession().save(diaryPage);
    }


    public void saveOrUpdateDiaryPage(DiaryPage diaryPage) {
    	
    	DiaryPage pageLoaded = loadByDate(diaryPage.getDiaryId(), diaryPage.getPageDate());
    	
    	if (pageLoaded!=null) {
    		pageLoaded.setContent(diaryPage.getContent());
    	}else{
        	currentSession().save(diaryPage);    		
    	}
    	
       
    }
    

    private Session currentSession(){
    	return this.sessionFactory.getCurrentSession();
    }
    
    public DiaryPage loadByRecordId(int diaryId, int recordId){
    	Criteria cr = currentSession().createCriteria(DiaryPage.class);
    	cr.add(Restrictions.eq("diaryId", diaryId));
    	cr.add(Restrictions.eq("recordId", recordId));
    	if (cr.list().size()>0)
    		return (DiaryPage) cr.list().iterator().next();
    	else
    		return null;
    }
    
    public List<DiaryPage> getSharedList(int userId, int diaryId){
    	
    	Query query = currentSession().createQuery("select p from DiaryPage p where p.shared=1 and p.diaryId <> :diaryId and p.recordId not in (select k.recordId from User u join u.userviews k where u.id = :userId)  ");
    	query.setParameter("diaryId", diaryId);
    	query.setParameter("userId", userId);
    	
    	// user load edilecek ve user views tablosundakinler normal listeden çýkarilacak sonrasýnda 2 ayrý liste halinde gösterebiliriz
    	return (List<DiaryPage>) query.list();
    }
    

    public List<DiaryPage> getSharedUserViewedList(int userId, int diaryId){
    	
    	Query query = currentSession().createQuery("select p from DiaryPage p where p.shared=1 and p.diaryId <> :diaryId and p.recordId in (select k.recordId from User u join u.userviews k where u.id = :userId) ");
    	query.setParameter("diaryId", diaryId);
    	query.setParameter("userId", userId);
    	
    	// user load edilecek ve user views tablosundakinler normal listeden çýkarilacak sonrasýnda 2 ayrý liste halinde gösterebiliriz
    	return (List<DiaryPage>) query.list();
    }
    
    public DiaryPage viewPage(int userId, int recordId) throws Exception {
    	List<Object> list = currentSession().createQuery("select p.shared from DiaryPage p where p.recordId=:recordId").setParameter("recordId", recordId).list();
    	int shared = Integer.parseInt(list.get(0).toString());
    	if (shared==0)
    		throw new Exception("Görüntüleme yetkiniz bulunmamaktadýr.");

    	
    	DiaryPage dp = (DiaryPage) currentSession().get(DiaryPage.class, recordId);
    	
    	
    	Query queryShared = currentSession().createQuery("select u from User u left join fetch u.userviews v  where u.id=:userId ")
    			.setParameter("userId", userId);
    	
    	User usr = (User) queryShared.list().iterator().next();
    	
    	
    	if (!usr.getUserviews().contains(dp)){
    		usr.setViewRight(usr.getViewRight()-1);
    		usr.getUserviews().add(dp);
    	}
    	
    	return dp;
    }
 
}

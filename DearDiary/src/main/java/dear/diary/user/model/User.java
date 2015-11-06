package dear.diary.user.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Min;import org.hibernate.annotations.LazyCollectionOption;
import org.hibernate.validator.constraints.Email;

import dear.diary.diary.model.Diary;
import dear.diary.diarypage.model.DiaryPage;


@Entity
@Table(name="TUSERS")
public class User {    
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="USER_ID")
	private int id;

	@Min(message="{lbl.kullaniciHatasi}", value=6)
	@Column(name="USERNAME")
	private String username;

	@Min(message="{lbl.passwordHatasi}", value=6)
	@Column(name="PASSWORD")
	private String password;
	
	@Email(message="{lbl.emailHatasi}")
	@Column(name="EMAIL")
	private String email;
	
	@Column(name="VIEW_RIGHT")
	private int viewRight=0;
	
	@Column(name="SHARE_COUNT")
	private int shareCount=0;
	
	@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.LAZY)
	@JoinColumn(name="USER_ID")
	private Set<Diary> diaries = new HashSet<Diary>();
	
	@ManyToMany(fetch=FetchType.LAZY, cascade=CascadeType.ALL)
	@JoinTable(name="user_views",
			joinColumns = {@JoinColumn(name="USER_ID", nullable=false, updatable=false)},
			inverseJoinColumns = {@JoinColumn(name="RECORD_ID", nullable=false, updatable=false)}	
	)
	private Set<DiaryPage> userviews = new HashSet<DiaryPage>();
	
	@ManyToMany(fetch=FetchType.LAZY, cascade=CascadeType.ALL)
	@JoinTable(name="user_likes",
		joinColumns = {@JoinColumn(name="USER_ID", nullable=false)},
		inverseJoinColumns = {@JoinColumn(name="RECORD_ID")}
	)
	private Set<DiaryPage> userLikes = new HashSet<DiaryPage>();
	
	public int getId() {
		return id;
	} 
	public void setId(int id) {
		this.id = id;
	}
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Set<Diary> getDiaries() {
		return diaries;
	}
	public void setDiaries(Set<Diary> diaries) {
		this.diaries = diaries;
	}
	public int getViewRight() {
		return viewRight;
	}
	public void setViewRight(int viewRight) {
		this.viewRight = viewRight;
	}
	public int getShareCount() {
		return shareCount;
	}
	public void setShareCount(int shareCount) {
		this.shareCount = shareCount;
	}
	public Set<DiaryPage> getUserviews() {
		return userviews;
	}
	public void setUserviews(Set<DiaryPage> userviews) {
		this.userviews = userviews;
	}
	public Set<DiaryPage> getUserLikes() {
		return userLikes;
	}
	public void setUserLikes(Set<DiaryPage> userLikes) {
		this.userLikes = userLikes;
	}

}

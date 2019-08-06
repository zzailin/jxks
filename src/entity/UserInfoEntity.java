package entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.GenericGenerator;
@Entity
@Table(name="userInfo")
public class UserInfoEntity {
	@Id
	@GeneratedValue(generator="uuid")
	@GenericGenerator(name="uuid",strategy="uuid")
	private String id;
	
	@Column(length=1)
	private String subject="1";
	
	@Column(length=3)
	private int  score1=0;
	
	@Column(length=3)
	private int  score4=0;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date time1;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date time4;
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public int getScore1() {
		return score1;
	}

	public void setScore1(int score1) {
		this.score1 = score1;
	}

	public int getScore4() {
		return score4;
	}

	public void setScore4(int score4) {
		this.score4 = score4;
	}

	public Date getTime1() {
		return time1;
	}

	public void setTime1(Date time1) {
		this.time1 = time1;
	}

	public Date getTime4() {
		return time4;
	}

	public void setTime4(Date time4) {
		this.time4 = time4;
	}
}

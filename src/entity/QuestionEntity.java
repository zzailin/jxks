package entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "question")
public class QuestionEntity implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(generator="id")
	@GenericGenerator(name="id",strategy="identity")
	private int id;
	@Column(length=255)
	private String question;
	@Column(length=255)
	private String answer;
	@Column(length=255)
	private String item1;
	@Column(length=255)
	private String item2;
	@Column(length=255)
	private String item3;
	@Column(length=255)
	private String item4;
	@Column(length=512)
	private String explains;
	@Column(length=255)
	private String url;
	@Column(length=1)
	private String subject;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getQuestion() {
		return question;
	}
	public void setQuestion(String question) {
		this.question = question;
	}
	public String getAnswer() {
		return answer;
	}
	public void setAnswer(String answer) {
		this.answer = answer;
	}
	public String getItem1() {
		return item1;
	}
	public void setItem1(String item1) {
		this.item1 = item1;
	}
	public String getItem2() {
		return item2;
	}
	public void setItem2(String item2) {
		this.item2 = item2;
	}
	public String getItem3() {
		return item3;
	}
	public void setItem3(String item3) {
		this.item3 = item3;
	}
	public String getItem4() {
		return item4;
	}
	public void setItem4(String item4) {
		this.item4 = item4;
	}
	public String getExplains() {
		return explains;
	}
	public void setExplains(String explains) {
		this.explains = explains;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	@Override
	public String toString() {
		return "QuestionEntity [id=" + id + ", question=" + question
				+ ", answer=" + answer + ", item1=" + item1 + ", item2="
				+ item2 + ", item3=" + item3 + ", item4=" + item4
				+ ", explains=" + explains + ", url=" + url + ", subject="
				+ subject + "]";
	}
}

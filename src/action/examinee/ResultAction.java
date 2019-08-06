package action.examinee;

import java.util.List;
import java.util.Map;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionSupport;

import entity.QuestionEntity;
import entity.UserEntity;
import function.ExamerFunction;
/**
 * 处理最后的结果
 * @author LIN
 *
 */
@Controller
@ParentPackage("struts-default")
@Namespace("/")
public class ResultAction extends ActionSupport{
	@Autowired
	private ExamerFunction function;
	
	private String answer;
	private String id;
	@Action(value="doResult",results={
			@Result(name="success",location="/jsp/examinee/result.jsp")
	})
	public String doResult(){
		int score=0;
		Map<String, Object> session = ServletActionContext.getContext().getSession();
		List<QuestionEntity> queryExam = (List<QuestionEntity>) session
				.get("queryExam");
		Map<String,String> result = (Map<String, String>) session.get("result");
		result.put(id, answer);
		for (int i = 0,len =queryExam.size(); i < len; i++) {
			if(queryExam.get(i).getAnswer().equals(result.get(String.valueOf(queryExam.get(i).getId())))){
				score++;
			}
		}
		session.put("result", result);
		session.put("score", score);
		UserEntity user = (UserEntity) session.get("userEntity");
		try{
			function.saveScore(score,queryExam.size(), user);
		}catch(Exception e){
			e.printStackTrace();
		}
		return SUCCESS;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getAnswer() {
		return answer;
	}
	public void setAnswer(String answer) {
		this.answer = answer;
	}
}

package action.examinee;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionSupport;

import entity.QuestionEntity;
import entity.UserEntity;
import function.ExamerFunction;

/**
 * 考生点击同意 开始准备答题
 * 
 * @author LIN
 *
 */
@Controller
@Scope("prototype")
@ParentPackage("struts-default")
@Namespace("/")
public class TestAction extends ActionSupport {
	@Autowired
	private ExamerFunction funtion;

	@Action(value = "doExam", results = { @Result(name = "success", location = "/jsp/examinee/ajaxExamination.jsp") })
	public String doExam() {
		Map<String, Object> session = ServletActionContext.getContext().getSession();
		UserEntity user = (UserEntity) session.get("userEntity");
		try {
			int examNum = 0;
			if("1".equals(user.getUserInfo().getSubject())){
				examNum = 10;
			}else{
				examNum = 5;
			}
			List<QuestionEntity> queryExam = funtion.queryExam(examNum,user.getUserInfo().getSubject());
			Map<String,String> result = new HashMap<String, String>(examNum);
			QuestionEntity examEntity = queryExam.get(0);
			session.put("examNum",examNum);
			session.put("page", 1);
			session.put("examEntity", examEntity);
			session.put("queryExam", queryExam);
			session.put("result", result);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}
}

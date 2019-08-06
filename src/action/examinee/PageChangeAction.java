package action.examinee;

import java.util.List;
import java.util.Map;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionSupport;

import entity.QuestionEntity;
/**
 * 上下页
 * @author LIN
 *
 */
@Controller
@ParentPackage("struts-default")  
@Namespace("/") 
public class PageChangeAction extends ActionSupport {
	
	private String status;
	
	@Action(value="pageChange",results={
			@Result(name="success",location="/jsp/examinee/examination.jsp",type="redirect")
	})
	public String pageChange() {
		Map<String, Object> session = ServletActionContext.getContext().getSession();
		int page = (int)session.get("page");
		List<QuestionEntity> queryExam = (List<QuestionEntity>)session.get("queryExam");
		if("pageDown".equals(status)){
			page++;
		}else if("pageUp".equals(status)){
			page--;
		}
		QuestionEntity examEntity = queryExam.get(page-1);
		session.put("page", page);
		session.put("examEntity", examEntity);
		return SUCCESS;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
}

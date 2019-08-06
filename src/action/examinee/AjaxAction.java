package action.examinee;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.json.annotations.JSON;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionSupport;

import common.LINUtil;
import entity.QuestionEntity;

/**
 * 上下页
 * 
 * @author LIN
 *
 */
@Controller
@ParentPackage("json-default")
@Namespace("/")
public class AjaxAction extends ActionSupport {

	private String status;
	private Map<String, Object> map;
	private String answer;
	private String page;
	private String id;

	@Action(value = "ajaxAction", results = { @Result(type = "json",params={"root","map"}) })
	public String pageChange() {
		Map<String, Object> session = ServletActionContext.getContext().getSession();
		int num = LINUtil.parseInt(page, 1);
		List<QuestionEntity> queryExam = (List<QuestionEntity>) session
				.get("queryExam");
		Map<String,String> result = (Map<String, String>) session.get("result");
		result.put(id, answer);
		if ("pageDown".equals(status)) {
			num++;
		} else if ("pageUp".equals(status)) {
			num--;
		}
		QuestionEntity examEntity = new QuestionEntity();
		examEntity = queryExam.get(num - 1);
		map = new HashMap<String, Object>();
		map.put("page", num);
		map.put("examEntity", examEntity);
		return SUCCESS;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getStatus() {
		return status;
	}

	@JSON
	public Map<String, Object> getMap() {
		return map;
	}

	public void setMap(Map<String, Object> map) {
		this.map = map;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}

	public String getPage() {
		return page;
	}

	public void setPage(String page) {
		this.page = page;
	}
}

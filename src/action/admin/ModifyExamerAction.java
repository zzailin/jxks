package action.admin;

import java.util.Map;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.util.HtmlUtils;

import com.opensymphony.xwork2.ActionSupport;

import common.LINException;
import entity.UserEntity;
import function.AdminFunction;

@Controller
@ParentPackage("struts-default")
@Namespace("/")
public class ModifyExamerAction extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Autowired
	private AdminFunction function;

	private String id;

	@Action(value = "checkExamer", results = { @Result(name = "success", location = "/jsp/admin/modifyExamer.jsp") })
	public String querySingleExamer() {
		Map<String, Object> session = ServletActionContext.getContext()
				.getSession();
		try {
			UserEntity user = function.checkExamer(id);
			session.put("examerEntity", user);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}

	private String name;
	private String tel;
	private String password;
	@Action(value="modifyExamer",results={
			@Result(name="success",location="/jsp/admin/modifyExamer.jsp")
	})
	public String modifyExmaer(){
		Map<String, Object> session = ServletActionContext.getContext()
				.getSession();
		try{
			UserEntity user = new UserEntity();
			user.setId(id);
			user.setName(HtmlUtils.htmlEscape(name));
			user.setTel(tel);
			user.setPassword(password);
			user = function.modifyExamer(user);
			session.put("examerEntity", user);
			session.put("message", "修改成功！");
		}catch(LINException lin){
			session.put("message", lin.getMessage());
		}catch (Exception e) {
			e.printStackTrace();
			session.put("message", "修改失败！");
		}
		return SUCCESS;
	}
	
	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getTel() {
		return tel;
	}


	public void setTel(String tel) {
		this.tel = tel;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
}

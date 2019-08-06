package action;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionSupport;

import common.AssertUtil;
import common.LINException;
import entity.UserEntity;
import function.UserFunction;
/**
 * 用户登录
 * @author LIN
 */
@Controller
@ParentPackage("struts-default")  
@Namespace("/") 
public class LoginAction extends ActionSupport {
	@Autowired
	private UserFunction function;
	private String account;
	private String password;

	@Action(value = "userLogin", results = {
			@Result(name = "examer", location = "/jsp/examinee/test.jsp",type="redirect"),
			@Result(name = "admin", location="/jsp/admin/admin.jsp",type="redirect"),
			@Result(name = "error", location="/login.jsp",type="redirect")})
	public String login() {
		Map<String, Object> session = ServletActionContext.getContext().getSession();
		try {
			UserEntity u = function.login(account, password);
			session.put("userEntity",u);
			if("0".equals(u.getRole()))
				return "examer";
		} catch (LINException lin) {
			session.put("message", lin.getMessage());
			return ERROR;
		}catch(Exception e){
			System.out.println(e.getMessage());
			return ERROR;
		}
		return "admin";
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
}

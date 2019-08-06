package action.admin;

import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.util.HtmlUtils;

import com.opensymphony.xwork2.ActionSupport;

import common.AssertUtil;
import common.LINException;
import common.LINUtil;
import dto.PaginationQueryResultDto;
import entity.UserEntity;
import function.AdminFunction;

/**
 * 一些管理员操作
 * 
 * @author LIN
 *
 */
@Controller
@ParentPackage("struts-default")
@Namespace("/")
public class AdminAction extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Autowired
	private AdminFunction function;

	private String key;
	private String pageNo;
	private String pageSize;

	@Action(value = "queryExamer", results = { @Result(name = "success", location = "/jsp/admin/queryExamer.jsp") })
	public String queryExamer() {
		HttpServletRequest request = ServletActionContext.getRequest();
		try {
			PaginationQueryResultDto<UserEntity> result = function
					.findExamerByKey(key, pageSize, pageNo);
			request.setAttribute("result", result);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}

	private String id;
	private String name;
	private String tel;

	@Action(value = "modifyPersonInfo", results = { @Result(name = "success", location = "/jsp/admin/personInfo.jsp") })
	public String modifyExamer() {
		Map<String, Object> session = ServletActionContext.getContext()
				.getSession();
		try {
			UserEntity user = new UserEntity();
			user.setId(id);
			user.setName(HtmlUtils.htmlEscape(name));
			user.setTel(tel);
			UserEntity userEntity = function.modifyPersonInfo(user);
			session.put("userEntity", userEntity);
			session.put("message", "修改成功");
		} catch (LINException lin) {
			session.put("message", lin.getMessage());
		} catch (Exception e) {
			session.put("message", "修改失败");
			e.printStackTrace();
		}
		return SUCCESS;
	}

	private String account;

	@Action(value = "addExamer", results = {
			@Result(name = "success", location = "/jsp/admin/addExamer.jsp"),
			@Result(name = "error", location = "/jsp/admin/addExamer.jsp") })
	public String addExamer() {
		Map<String, Object> session = ServletActionContext.getContext()
				.getSession();
		try {
			UserEntity user = new UserEntity();
			user.setAccount(account);
			user.setCreateTime(new Date());
			user.setName(HtmlUtils.htmlEscape(name));
			user.setTel(tel);
			function.addExamer(user);
			session.put("message", "添加成功！");
		} catch (LINException lin) {
			session.put("message", lin.getMessage());
			return ERROR;
		} catch (Exception e) {
			e.printStackTrace();
			return ERROR;
		}
		return SUCCESS;
	}

	private String password;
	private String newPassword;
	private String newPassword1;

	@Action(value = "modifyPassword", results = {
			@Result(name = "success", location = "/jsp/admin/modifyPassword.jsp"),
			@Result(name = "error", location = "/jsp/admin/modifyPassword.jsp") })
	public String modifyPassword() {
		Map<String, Object> session = ServletActionContext.getContext()
				.getSession();
		try {
			AssertUtil.assertEquals("两次密码不相同", newPassword, newPassword1);
			function.modifyPassword(id, password, newPassword);
			session.put("message", "修改成功！");
		} catch (LINException lin) {
			session.put("message", lin.getMessage());
			return ERROR;
		} catch (Exception e) {
			e.printStackTrace();
			return ERROR;
		}
		return SUCCESS;
	}

	@Action(value = "loginOut", results = { @Result(name = "success", location = "/login.jsp", type = "redirect") })
	public String loginOut() {
		Map<String, Object> session = ServletActionContext.getContext()
				.getSession();
		session.clear();
		return SUCCESS;
	}
	
	private String action;
	@Action(value = "check", results = {
			@Result(name = "add", location = "/jsp/admin/addAdmin.jsp"),
			@Result(name = "query", location = "/queryAdmin.action",type="redirect"),
			@Result(name = "error", location = "/jsp/admin/lessPower.jsp") })
	public String check() {
		Map<String, Object> session = ServletActionContext.getContext()
				.getSession();
		try {
			UserEntity user = (UserEntity) session.get("userEntity");
			int power = LINUtil.parseInt(user.getRole(), 1);
			if (power < 2) {
				session.put("message", "您的权限不够！");
				return ERROR;
			}
			if("add".equals(action)){
				return "add";
			}else if("query".equals(action)){
				return "query";
			}
		} catch (Exception e) {
			e.printStackTrace();
			session.put("message", "session失效了");
			return ERROR;
		}
		return SUCCESS;
	}

	@Action(value = "addAdmin", results = {
			@Result(name = "success", location = "/jsp/admin/addAdmin.jsp"),
			@Result(name = "error", location = "/jsp/admin/addAdmin.jsp") })
	public String addAdmin() {
		Map<String, Object> session = ServletActionContext.getContext()
				.getSession();
		UserEntity user = new UserEntity();
		user.setAccount(account);
		user.setCreateTime(new Date());
		user.setName(HtmlUtils.htmlEscape(name));
		user.setPassword(password);
		user.setTel(tel);
		user.setRole("1");
		try {
			function.addAdmin(user);
			session.put("message", "添加成功！");
		} catch (LINException lin) {
			session.put("message", lin.getMessage());
			return ERROR;
		}catch (Exception e) {
			e.printStackTrace();
			session.put("message", "添加失败，网络原因。");
			return ERROR;
		}
		return SUCCESS;
	}
	
	@Action(value="queryAdmin",results={
			@Result(name="success",location="/jsp/admin/queryAdmin.jsp")
	})
	public String queryAdmin(){
		HttpServletRequest request = ServletActionContext.getRequest();
		try {
			PaginationQueryResultDto<UserEntity> result = function.findAdminByKey(key, pageSize, pageNo);
			request.setAttribute("result", result);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return SUCCESS;
	}
	@Action(value="deleteAdmin",results={
			@Result(name="success",location="/jsp/admin/queryAdmin.jsp")
	})
	public String deleteAdmin(){
		Map<String, Object> session = ServletActionContext.getContext().getSession();
		try {
			function.deleteAdmin(id);
			session.put("message", "删除成功");
		} catch (Exception e) {
			session.put("message","删除失败");
			e.printStackTrace();
		}
		return SUCCESS;
	}
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getNewPassword() {
		return newPassword;
	}

	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public String getNewPassword1() {
		return newPassword1;
	}

	public void setNewPassword1(String newPassword1) {
		this.newPassword1 = newPassword1;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
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

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getPageNo() {
		return pageNo;
	}

	public void setPageNo(String pageNo) {
		this.pageNo = pageNo;
	}

	public String getPageSize() {
		return pageSize;
	}

	public void setPageSize(String pageSize) {
		this.pageSize = pageSize;
	}
}

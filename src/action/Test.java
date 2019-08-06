package action;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionSupport;
@Controller
@ParentPackage("struts-default")
@Namespace("/")
public class Test extends ActionSupport{
	@Autowired
	private TestService service;
	
	@Action(value="test",results={
			@Result(name="success",location="/login.jsp")
	})
	public String test(){
		service.modify();
		return SUCCESS;
	}
}

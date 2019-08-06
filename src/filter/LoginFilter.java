package filter;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginFilter implements Filter {
	private Set<String> whiteSets=new HashSet<>(16);
	private String redirectURL="/login.jsp";
	@Override
	public void init(FilterConfig config) throws ServletException {
		String lists=config.getInitParameter("white-list");
		String contextPath=config.getServletContext().getContextPath();
		String[] listArr=lists.split(",");
		for (String url : listArr) {
			whiteSets.add(contextPath+url.trim());
		}
		String url=config.getInitParameter("redirectURL");
		if(url!=null){
			this.redirectURL=url;
		}
		this.redirectURL=contextPath+this.redirectURL;
	}
	@Override
	public void destroy() {
		
	}

	@Override
	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse,
			FilterChain fc) throws IOException, ServletException {
		HttpServletRequest req=(HttpServletRequest)servletRequest;
		HttpServletResponse resp=(HttpServletResponse)servletResponse;
		//如果未session,肯定没登录
		HttpSession sess=req.getSession(false);
		if(sess!=null){
			//判断是否登录
			Object cu=sess.getAttribute("userEntity");
			if(cu!=null){
				//是登录过的
				fc.doFilter(servletRequest, resp);
				return;
			}
		}
		//下面没登录,如果请求的url在白名单内,则通过
		String url=req.getRequestURI();
		if(whiteSets.contains(url)){
			//白名单通过
			fc.doFilter(servletRequest, resp);
		}else{
			req.getSession().setAttribute("message", "你还未登录或登录已过时,请重新登录!谢谢!");
			//没权限,重定向登录页面
			resp.sendRedirect(redirectURL);
		}
		
		
	}

	

}

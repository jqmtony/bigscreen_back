package com.gochinatv.accelarator.security;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;

public class ShiroFormAuthenticationFilter extends FormAuthenticationFilter {

	@Override
	protected boolean onAccessDenied(ServletRequest request,ServletResponse response) throws Exception {
		/*HttpServletRequest httpServletRequest = (HttpServletRequest) request;
		HttpSession session =httpServletRequest.getSession();
		String validateCode = (String) session.getAttribute("validateCode");
		String randomcode = httpServletRequest.getParameter("randomcode");
		if(randomcode!=null && validateCode!=null && !randomcode.equals(validateCode)){
			httpServletRequest.setAttribute("errorCodeFailure", "randomCodeError");
			return true; 
		}*/
		return super.onAccessDenied(request, response);
	}


	@Override
	protected void issueSuccessRedirect(ServletRequest request, ServletResponse response) throws Exception {
		HttpServletResponse rep = (HttpServletResponse)response;
		rep.sendRedirect("index");
	}
	

}

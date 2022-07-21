package com.app.tracker.security.handler;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ApiAccessDeniedHandler implements AccessDeniedHandler {
	/**
	 * @param request
	 * 		that resulted in an <code>AccessDeniedException</code>
	 * @param response
	 * 		so that the user agent can be advised of the failure
	 * @param accessDeniedException
	 * 		that caused the invocation
	 */
	@Override
	public void handle(HttpServletRequest request,
					   HttpServletResponse response,
					   AccessDeniedException accessDeniedException) throws IOException, ServletException {
		response.setHeader("Access-Control-Allow-Origin", "*");
		response.setHeader("Cache-Control", "no-cache");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/json");
		// TODO
//		response.getWriter()
//				.println(JSONUtil.parse(CommonResult.forbidden(e.getMessage())));
		response.getWriter()
				.flush();
	}

}

package xuxu.blog.common.action;

import java.io.UnsupportedEncodingException;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.interceptor.ApplicationAware;
import org.apache.struts2.interceptor.ParameterAware;
import org.apache.struts2.interceptor.RequestAware;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.apache.struts2.interceptor.SessionAware;
import org.apache.struts2.util.ServletContextAware;

import com.opensymphony.xwork2.ActionSupport;

/**
 * 通过实现Struts2的接口，访问Session，Request，Response等
 * 
 * @author shanjunpeng
 * 
 */
public class BaseAction extends ActionSupport implements SessionAware,
		RequestAware, ApplicationAware, ParameterAware, ServletRequestAware,
		ServletResponseAware, ServletContextAware {

	protected Map<String, Object> sessionMap;
	protected Map<String, Object> requestMap;
	protected Map<String, Object> applicationMap;
	protected Map<String, String[]> parameterMap;

	protected HttpServletRequest request;
	protected HttpServletResponse response;
	protected ServletContext servletContext;

	@Override
	public void setSession(Map<String, Object> sessionMap) {
		this.sessionMap = sessionMap;

	}

	@Override
	public void setRequest(Map<String, Object> requestMap) {
		this.requestMap = requestMap;

	}

	@Override
	public void setApplication(Map<String, Object> applicationMap) {
		this.applicationMap = applicationMap;

	}

	@Override
	public void setParameters(Map<String, String[]> parameterMap) {
		this.parameterMap = parameterMap;

	}

	@Override
	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
		try {
			this.request.setCharacterEncoding("utf-8");

		} catch (UnsupportedEncodingException e) {

			e.printStackTrace();
		}

	}

	@Override
	public void setServletResponse(HttpServletResponse response) {
		this.response = response;
		this.response.setContentType("text/html;charset=utf-8");

	}

	@Override
	public void setServletContext(ServletContext servletContext) {
		this.servletContext = servletContext;
	}

	public Map<String, Object> getSessionMap() {
		return sessionMap;
	}

	public void setSessionMap(Map<String, Object> sessionMap) {
		this.sessionMap = sessionMap;
	}

	public Map<String, Object> getRequestMap() {
		return requestMap;
	}

	public void setRequestMap(Map<String, Object> requestMap) {
		this.requestMap = requestMap;
	}

	public Map<String, Object> getApplicationMap() {
		return applicationMap;
	}

	public void setApplicationMap(Map<String, Object> applicationMap) {
		this.applicationMap = applicationMap;
	}

	public Map<String, String[]> getParameterMap() {
		return parameterMap;
	}

	public void setParameterMap(Map<String, String[]> parameterMap) {
		this.parameterMap = parameterMap;
	}

	public HttpServletRequest getRequest() {
		return request;
	}

	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}

	public HttpServletResponse getResponse() {
		return response;
	}

	public void setResponse(HttpServletResponse response) {
		this.response = response;
	}

	public ServletContext getServletContext() {
		return servletContext;
	}

}

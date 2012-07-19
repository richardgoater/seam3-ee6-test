package com.chilternit.web;

import java.io.IOException;
import java.io.OutputStream;
import java.lang.annotation.Annotation;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;

import org.jboss.seam.rest.templating.ResponseTemplate;
import org.jboss.seam.rest.templating.TemplatingProvider;

public class JSPTemplatingProvider implements TemplatingProvider {

	String jspPath = "WEB-INF/jsp/";
	
	@Override
	public void init(ServletContext context) { }
	
	@Override
	public void writeTo(Object result, ResponseTemplate templateAnnotation, Annotation[] annotations,
			MediaType mediaType, MultivaluedMap<String, Object> httpHeaders,
			OutputStream outputStream) throws IOException {
		
		if(result instanceof Map<?, ?>) {
			
			Map<String, Object> modelMap = (Map<String, Object>) result;
			HttpServletRequest request = (HttpServletRequest) modelMap.remove("request");
			HttpServletResponse response = (HttpServletResponse) modelMap.remove("request");
			
			request.setAttribute("model", result);
			try {
				request.getRequestDispatcher(jspPath + templateAnnotation.value()).include(request, response);
				
			} catch (ServletException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

}

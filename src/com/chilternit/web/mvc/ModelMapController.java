package com.chilternit.web.mvc;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolation;
import javax.ws.rs.core.Context;

public abstract class ModelMapController {

	protected Map<String, Object> modelWithErrors(Map<String, Object> model, Set<ConstraintViolation<Object>> errors) {
		model.put("errors", errors);
		return model;
	}
	
	@Context HttpServletRequest request;
	@Context HttpServletResponse response;
	
	protected Map<String, Object> newModel() {
		Map<String, Object> model = new HashMap<String, Object>();
//		model.put("request", request);
//		model.put("response", response);
		
		return model;
	}
	
}

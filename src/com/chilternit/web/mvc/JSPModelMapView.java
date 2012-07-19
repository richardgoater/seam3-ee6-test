package com.chilternit.web.mvc;

import java.util.Map;

public class JSPModelMapView extends com.googlecode.htmleasy.View {

	private static final String internalPath = "/WEB-INF/jsp/";	
	
	public JSPModelMapView(String path) {
		super(resolve(path));
	}
	
	public JSPModelMapView(String path, Map<String, Object> model) {
		super(resolve(path), model);
	}
	
	private static String resolve(String path) {
		if(path.startsWith(internalPath))
			return path;
		else 
			return internalPath + path;
	}
	
	@SuppressWarnings("unchecked")
	public Map<String, Object> getModelMap() {
		return (Map<String, Object>) super.getModel();
	}

}

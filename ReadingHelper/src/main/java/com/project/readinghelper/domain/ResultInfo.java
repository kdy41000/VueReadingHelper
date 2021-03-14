package com.project.readinghelper.domain;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ResultInfo {

	private Map<String, Object> result;
	
	public ResultInfo() {
		super();
		this.result = new HashMap<String, Object>();
		this.result.put("status", true);
	}
	public Map<String, Object> getResult() {
		return result;
	}
	public void setResultStatus(boolean param) {
		this.result.put("status", param);
	}
	public void setResultList(List<Object> param) {
		this.result.put("list", param);
	}
	
}

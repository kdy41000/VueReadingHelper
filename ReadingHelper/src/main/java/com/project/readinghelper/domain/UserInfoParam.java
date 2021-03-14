package com.project.readinghelper.domain;

public class UserInfoParam {

	private String selectVal;
	private String searchTxt;
	
	public UserInfoParam() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getSelectVal() {
		return selectVal;
	}
	public void setSelectVal(String selectVal) {
		String result = "";
		switch(selectVal) {
		    case "all":
		    	result = "ALL";
		    	break;
			case "userId":
				result = "USER_ID";
				break;
			case "userName":
				result = "USER_NAME";
				break;
			case "userEmail":
				result = "USER_EMAIL";
				break;
		}
		this.selectVal = result;
	}
	public String getSearchTxt() {
		return searchTxt;
	}
	public void setSearchTxt(String searchTxt) {
		this.searchTxt = searchTxt;
	}

	@Override
	public String toString() {
		return "UserInfoParam [selectVal=" + selectVal + ", searchTxt=" + searchTxt + "]";
	}
	
}

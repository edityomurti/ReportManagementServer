package com.solusi247.reportManagementApi.model;

public class Report {

	public int report_id;
	public int user_id;
	public String date;
	public String project;
	public String activity;
	public int status;
	public String desc;
	public String attachment;
	public String created_at;
	
	public Report() {}
	
	public Report(int report_id, int user_id, String date, String project, String activity, int status, String desc, String created_at) {
		super();
		this.report_id = report_id;
		this.user_id = user_id;
		this.date = date;
		this.project = project;
		this.activity = activity;
		this.status = status;
		this.desc = desc;
		this.created_at = created_at;
	}	

	public int getReport_id() {
		return report_id;
	}

	public void setReport_id(int report_id) {
		this.report_id = report_id;
	}

	public int getUser_id() {
		return user_id;
	}

	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}

	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getProject() {
		return project;
	}
	public void setProject(String project) {
		this.project = project;
	}
	public String getActivity() {
		return activity;
	}
	public void setActivity(String activity) {
		this.activity = activity;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}
	
	public String getAttachment(){
		return attachment;
	}
	
	public void setAttachment(String attachment){
		this.attachment = attachment;
	}

	public String getCreated_at() {
		return created_at;
	}

	public void setCreated_at(String created_at) {
		this.created_at = created_at;
	}	
	
}

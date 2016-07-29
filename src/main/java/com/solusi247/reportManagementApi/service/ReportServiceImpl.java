package com.solusi247.reportManagementApi.service;

import java.util.List;

import com.solusi247.reportManagementApi.dao.ReportDao;
import com.solusi247.reportManagementApi.model.Report;

public class ReportServiceImpl implements ReportService{
	
	private static ReportDao reportDao = new ReportDao();
//	private static List<Report> reports;
	
	public List<Report> getAllReportByIdPaginated(int user_id, int page, int perPage) {
		return reportDao.getAllReportByIdPaginated(user_id, page, perPage);
	}
	
	public List<Report> getAllReportById(int user_id) {
		return reportDao.getAllReportById(user_id);
	}

	public void insertReport(Report report) {
		
		reportDao.insertReport(report);	
	}

	public List<Report> getAllReport() {
		return reportDao.getAllReport();
	}

	public void insertReportWithoutImage(Report report) {
		reportDao.insertReportWithoutImage(report);	
	}

	public void deleteReport(int report_id) {
		reportDao.deleteReport(report_id);
	}

	public void updateReport(String date, String project, String activity,
			int status, String desc, String attachment, int report_id) {
		reportDao.updateReport(date, project, activity, status, desc, attachment, report_id);
		
	}

	public void updateReportWithoutImage(String date, String project,
			String activity, int status, String desc, String attachment,
			int report_id) {
		reportDao.updateReportWithoutImage(date, project, activity, status, desc, attachment, report_id);	
	}

}

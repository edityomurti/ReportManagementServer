package com.solusi247.reportManagementApi.service;

import java.util.List;

import com.solusi247.reportManagementApi.model.Report;

public interface ReportService {
	List<Report> getAllReport();
	List<Report> getAllReportById(int user_id);
	List<Report> getAllReportByIdPaginated(int user_id, int page, int perPage);
	void insertReport(Report report);
	void insertReportWithoutImage(Report report);
	void updateReport(String date, String project, String activity, int status, String desc, String attachment, int report_id);
	void updateReportWithoutImage(String date, String project, String activity, int status, String desc, String attachment, int report_id);
	void deleteReport(int report_id);
}

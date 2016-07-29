package com.solusi247.reportManagementApi.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.solusi247.reportManagementApi.model.Report;

public class ReportDao {
	
	private Connection connection;
	
	final String getAllReportByIdPaginated = "SELECT * FROM report where user_id = ? ORDER BY report_id DESC LIMIT ?,?;";
	final String getAllReportById = "SELECT * FROM report where user_id = ? ORDER BY report_id DESC;";
	final String getAllReports = "SELECT * FROM report ORDER BY report_id DESC";
	final String insertReport = "INSERT INTO report (user_id, date, project, activity, status, description, attachment) VALUES (?,?,?,?,?,?,?)";
	final String insertReportWithoutImage = "INSERT INTO report (user_id, date,project, activity, status, description) VALUES (?,?,?,?,?,?);";
	final String updateReport = "UPDATE report SET date = ?, project =?, activity = ?, status = ?, description = ?, attachment = ? WHERE report_id = ?;";
	final String updateReportWithoutImage = "UPDATE report SET date = ?, project =?, activity = ?, status = ?, description = ?, attachment = ? WHERE report_id = ?;";
	final String deleteReport = "DELETE FROM report WHERE report_id = ?;";
	
	public ReportDao(){
		connection = Database.getConnection();
	}
	
	public List<Report> getAllReport() {
		List<Report> reports = new ArrayList<Report>();
		
		try {
			Statement statement = connection.createStatement();
			ResultSet rs = statement.executeQuery(getAllReports);
			
			while(rs.next()){
				Report report = new Report();
				report.setReport_id(rs.getInt("report_id"));
				report.setUser_id(rs.getInt("user_id"));
				report.setDate(rs.getString("date"));
				report.setProject(rs.getString("project"));
				report.setActivity(rs.getString("activity"));
				report.setStatus(rs.getInt("status"));
				report.setDesc(rs.getString("description"));
				report.setAttachment(rs.getString("attachment"));
				report.setCreated_at(rs.getString("created_at"));
				reports.add(report);
			}
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
			
		return reports;
	}
	
	public List<Report> getAllReportByIdPaginated(int user_id, int page, int perPage) {
		List<Report> reports = new ArrayList<Report>();
		
		try {
//			Statement statement = connection.createStatement();
			PreparedStatement ps = connection.prepareStatement(getAllReportByIdPaginated);
			ps.setInt(1, user_id);
			ps.setInt(2, page);
			ps.setInt(3, perPage);
			ResultSet rs = ps.executeQuery();
			
			if(rs.next()){
				rs.beforeFirst();
				while(rs.next()){
					System.out.println("memuat list report..");
					Report report = new Report();
					report.setReport_id(rs.getInt("report_id"));
					report.setUser_id(rs.getInt("user_id"));
					report.setDate(rs.getString("date"));
					report.setProject(rs.getString("project"));
					report.setActivity(rs.getString("activity"));
					report.setStatus(rs.getInt("status"));
					report.setDesc(rs.getString("description"));
					report.setAttachment(rs.getString("attachment"));
					report.setCreated_at(rs.getString("created_at"));
					reports.add(report);
				}
				System.out.println("selesai list report..");
			} else {
				System.out.println("Report not listed");
				ps.close();
				rs.close();
				return null;
			}			
			ps.close();
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return reports;
	}
	
	public List<Report> getAllReportById(int user_id) {
		List<Report> reports = new ArrayList<Report>();
		
		try {
//			Statement statement = connection.createStatement();
			PreparedStatement ps = connection.prepareStatement(getAllReportById);
			ps.setInt(1, user_id);
			ResultSet rs = ps.executeQuery();
			
			if(rs.next()){
				rs.beforeFirst();
				while(rs.next()){
					Report report = new Report();
					report.setReport_id(rs.getInt("report_id"));
					report.setUser_id(rs.getInt("user_id"));
					report.setDate(rs.getString("date"));
					report.setProject(rs.getString("project"));
					report.setActivity(rs.getString("activity"));
					report.setStatus(rs.getInt("status"));
					report.setDesc(rs.getString("description"));
					report.setAttachment(rs.getString("attachment"));
					report.setCreated_at(rs.getString("created_at"));
					reports.add(report);
				}
			} else {
				ps.close();
				rs.close();
				return null;
			}			
			ps.close();
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return reports;
	}
	
	public void insertReport (Report report) {
		PreparedStatement stmt = null;
		try {
			stmt = connection.prepareStatement(insertReport);
			stmt.setInt(1, report.getUser_id());
			stmt.setString(2, report.getDate());
			stmt.setString(3, report.getProject());
			stmt.setString(4, report.getActivity());
			stmt.setInt(5, report.getStatus());
			stmt.setString(6, report.getDesc());
			stmt.setString(7, report.getAttachment());
			stmt.executeUpdate();
		} catch (SQLException ex) {
			ex.printStackTrace();
		} finally {
			try {
				stmt.close();
			} catch (SQLException ex) {
				ex.printStackTrace();
			}
		}
	}
	
	public void insertReportWithoutImage (Report report) {
		PreparedStatement stmt = null;
		try {
			stmt = connection.prepareStatement(insertReportWithoutImage);
			stmt.setInt(1, report.getUser_id());
			stmt.setString(2, report.getDate());
			stmt.setString(3, report.getProject());
			stmt.setString(4, report.getActivity());
			stmt.setInt(5, report.getStatus());
			stmt.setString(6, report.getDesc());
			stmt.executeUpdate();
		} catch (SQLException ex) {
			ex.printStackTrace();
		} finally {
			try {
				stmt.close();
			} catch (SQLException ex) {
				ex.printStackTrace();
			}
		}
	}
	
	public void updateReport(String date, String project, String activity, int status, String desc, String attachment, int report_id){
		PreparedStatement stmt = null;
		try {
			stmt = connection.prepareStatement(updateReport);
			stmt.setString(1, date);
			stmt.setString(2,  project);
			stmt.setString(3, activity);
			stmt.setInt(4, status);
			stmt.setString(5, desc);			
			stmt.setString(6, attachment);
			stmt.setInt(7, report_id);
			stmt.executeUpdate();
		} catch (SQLException ex) {
			ex.printStackTrace();
		} finally {
			try {
				stmt.close();
			} catch (SQLException ex) {
				ex.printStackTrace();
			}
		}
	}

	public void updateReportWithoutImage(String date, String project, String activity, int status, String desc, String attachment, int report_id){
		PreparedStatement stmt = null;
		try {
			stmt = connection.prepareStatement(updateReportWithoutImage);
			stmt.setString(1, date);
			stmt.setString(2,  project);
			stmt.setString(3, activity);
			stmt.setInt(4, status);
			stmt.setString(5, desc);	
			stmt.setString(6, attachment);
			stmt.setInt(7, report_id);
			stmt.executeUpdate();
		} catch (SQLException ex) {
			ex.printStackTrace();
		} finally {
			try {
				stmt.close();
			} catch (SQLException ex) {
				ex.printStackTrace();
			}
		}
	}
	
	public void deleteReport(int report_id) {
		PreparedStatement stmt = null;
		try {
			stmt = connection.prepareStatement(deleteReport);
			stmt.setInt(1, report_id);
			stmt.executeUpdate();
		} catch (SQLException ex) {
			ex.printStackTrace();
		} finally {
			try {
				stmt.close();
			} catch (SQLException ex) {
				ex.printStackTrace();
			}
		}
	}
}

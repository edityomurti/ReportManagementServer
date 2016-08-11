package com.solusi247.reportManagementApi.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Paths;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.solusi247.reportManagementApi.model.Report;
import com.solusi247.reportManagementApi.service.ReportServiceImpl;
import com.sun.jersey.core.header.FormDataContentDisposition;
import com.sun.jersey.multipart.FormDataParam;

@Path("/report")
public class ReportController {
	Report report = new Report();	
	ReportServiceImpl reportService = new ReportServiceImpl();
	
	//mendapatkan semua report yang ada di database
	@GET
	@Path("/getAllReports")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Report> listAllReport(){
		List<Report> getAllReport = reportService.getAllReport();
		return getAllReport;
	}
	
	@GET
	@Path("/getAllReportByIdPaginated")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Report> listAllReport(
			@QueryParam("user_id") int user_id,
			@QueryParam("page") int page,
			@QueryParam("perPage") int perPage){
		List<Report> getAllReport = reportService.getAllReportByIdPaginated(user_id, page, perPage);
		return getAllReport;
	}
	
	//mendapatkan report berdasarkan user id
	@GET
	@Path("/getAllReportById")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Report> listAllReport(
			@QueryParam("user_id") int user_id){
		List<Report> getAllReport = reportService.getAllReportById(user_id);
		return getAllReport;
	}
	
	//memasukan report
	@POST
	@Path("/insertReport")
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	public Response postReport(
			@QueryParam("user_id") int user_id,
			@QueryParam("date") String date,
			@QueryParam("project") String project,
			@QueryParam("activity") String activity, 
			@QueryParam("status") int status,
			@QueryParam("desc") String desc,
			@FormDataParam("attachment") InputStream attachment,
			@FormDataParam("attachment") FormDataContentDisposition attachmentDetail){
		String fileName = String.valueOf(user_id) + String.valueOf(System.currentTimeMillis());
		
		writeToFile(attachment, fileName);		
		
		String output = "Report succesfully uploaded ";
		report.setUser_id(user_id);
		System.out.println("insert report dengan fileName: " + fileName);
		report.setDate(date);
		report.setProject(project);
		report.setActivity(activity);
		report.setStatus(status);
		report.setDesc(desc);
		report.setAttachment(fileName);
		
		reportService.insertReport(report);
		return Response.status(200).entity(output).build();
	}
	
	
	@POST
	@Path("/insertReportWithoutImage")
	public Response postReportWithoutImage(
			@QueryParam("user_id") int user_id,
			@QueryParam("date") String date,
			@QueryParam("project") String project,
			@QueryParam("activity") String activity, 
			@QueryParam("status") int status,
			@QueryParam("desc") String desc){		
		String output = "File uploaded without image";
		report.setUser_id(user_id);
		report.setDate(date);
		report.setProject(project);
		report.setActivity(activity);
		report.setStatus(status);
		report.setDesc(desc);
		report.setAttachment(null);
		
		reportService.insertReport(report);
		return Response.status(200).entity(output).build();
	}
	
	//mengupdate report
	@PUT
	@Path("/updateReport")
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	public Response updateReport(
			@QueryParam("user_id") int user_id,
			@QueryParam("date") String date,
			@QueryParam("project") String project,
			@QueryParam("activity") String activity,
			@QueryParam("status") int status,
			@QueryParam("desc") String desc,
			@QueryParam("old_attachment") String old_attachment,
			@FormDataParam("attachment") InputStream attachment,
			@FormDataParam("attachment") FormDataContentDisposition attachmentDetail,
			@QueryParam("report_id") int report_id){
		String fileName = String.valueOf(user_id) + String.valueOf(System.currentTimeMillis());		

		if (old_attachment!=null) {
			deleteFile(old_attachment);
		}	
		writeToFile(attachment, fileName);
		System.out.println("insert report from user_id: " + user_id);
		
		reportService.updateReport(date, project, activity, status, desc, fileName, report_id);
		return Response.status(200).entity("Report updated").build();
	}
	
	@PUT
	@Path("/updateReportWithoutImage")
	public Response updateReportWithoutImage(
			@QueryParam("date") String date,
			@QueryParam("project") String project,
			@QueryParam("activity") String activity,
			@QueryParam("status") int status,
			@QueryParam("desc") String desc,
			@QueryParam("old_attachment") String old_attachment,
			@QueryParam("attachment") String attachment,
			@QueryParam("report_id") int report_id){
		reportService.updateReportWithoutImage(date, project, activity, status, desc, attachment, report_id);
		if (old_attachment!=null) {
			deleteFile(old_attachment);
		}
		return Response.status(200).entity("Report updated").build();
	}
	
	//menghapus report
	@DELETE
	@Path("/deleteReport")
	public Response deleteReport(
			@QueryParam("report_id") int report_id,
			@QueryParam("attachment") String attachment){
		reportService.deleteReport(report_id);
		deleteFile(attachment);
		return Response.status(200).entity("Report deleted").build();
	}
	
	@DELETE
	@Path("/deleteImage")
	public Response deleteImage(
			@QueryParam("attachment") String attachment) {
		deleteFile(attachment);
		return Response.status(200).entity("Image deleted").build();
	}
	
	private void writeToFile(InputStream attachment, String fileName) {
		try {
//			String uploadedFileLocation ="/home/usernames/Documents/KP/FileServer/";  //Laptop edityo
			String uploadedFileLocation ="/home/apps/yoedi247/upload/images/"; //Server Solusi
			OutputStream out = new FileOutputStream(new File(uploadedFileLocation+fileName));
			int read = 0;
			byte[] bytes = new byte[1024];
			
			out = new FileOutputStream(new File(uploadedFileLocation+fileName));
			while ((read = attachment.read(bytes)) != -1) {
				out.write(bytes, 0 , read);
			}
			out.flush();
			out.close();			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}	
	
	private void deleteFile(String old_attachment) {
		try {
//			java.nio.file.Path path = Paths.get("/home/usernames/Documents/KP/FileServer/" + old_attachment); //Laptop Edityo
			java.nio.file.Path path = Paths.get("/home/apps/yoedi247/upload/images/" + old_attachment); // Server Solusi
			Files.delete(path);
			System.out.println("File " + old_attachment + " deleted");
		} catch (NoSuchFileException e) {
			e.printStackTrace();
		} catch(IOException e) {
			e.printStackTrace();
		}
	}
}

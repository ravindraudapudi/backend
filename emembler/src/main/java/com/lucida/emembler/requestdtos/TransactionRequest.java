package com.lucida.emembler.requestdtos;

import java.util.List;

public class TransactionRequest {
	
	/**
	 * 
	 */
	private static long serialVersionUID = -3981263758817866505L;
	
	private String fileName;
	private String type;
	private String uploadedBy;
	private String reportedDate;
	private List<TransactionDto> content;
	
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public String getUploadedBy() {
		return uploadedBy;
	}
	public void setUploadedBy(String uploadedBy) {
		this.uploadedBy = uploadedBy;
	}
	public List<TransactionDto> getContent() {
		return content;
	}
	public void setContent(List<TransactionDto> content) {
		this.content = content;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public static void setSerialversionuid(long serialversionuid) {
		serialVersionUID = serialversionuid;
	}
	
	public String getReportedDate() {
		return reportedDate;
	}
	public void setReportedDate(String reportedDate) {
		this.reportedDate = reportedDate;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("TransactionRequest [fileName=");
		builder.append(fileName);
		builder.append(", type=");
		builder.append(type);
		builder.append(", uploadedBy=");
		builder.append(uploadedBy);
		builder.append(", content=");
		builder.append(content);
		builder.append("]");
		return builder.toString();
	}
	
}

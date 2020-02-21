package com.lucida.emembler.requestdtos;

public class ProfitAndLossRequest {
	
	private String fileName;
	private String type;
	private String uploadedBy;
	private ProfitAndLosssDto content;
	
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getUploadedBy() {
		return uploadedBy;
	}
	public void setUploadedBy(String uploadedBy) {
		this.uploadedBy = uploadedBy;
	}
	
	public ProfitAndLosssDto getContent() {
		return content;
	}
	public void setContent(ProfitAndLosssDto content) {
		this.content = content;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("ProfitAndLossRequest [fileName=");
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

package com.lucida.emembler.requestdtos;

import java.util.List;

public class SponsershipRequest {

	private String fileName;
	private String type;
	private String uploadedBy;
	private List<SponserDto> content;

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

	public List<SponserDto> getContent() {
		return content;
	}

	public void setContent(List<SponserDto> content) {
		this.content = content;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("SponsershipRequest [fileName=");
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

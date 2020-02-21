package com.lucida.emembler.requestdtos;

import java.util.List;

public class SocialMediaRequest {

	private String fileName;
	private String type;
	private String uploadedBy;
	private List<SocialMediaDto> content;

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

	public List<SocialMediaDto> getContent() {
		return content;
	}

	public void setContent(List<SocialMediaDto> content) {
		this.content = content;
	}

}

package com.lucida.emembler.responsedtos;

import java.util.List;

/**
 *  Dashboard Response
 * @author Ravindra
 *
 */
public class DashboardResponse {
	
	private Header headers;
	private List<ChapterContent> chapterContent;
	
	public Header getHeaders() {
		return headers;
	}
	public void setHeaders(Header headers) {
		this.headers = headers;
	}
	public List<ChapterContent> getChapterContent() {
		return chapterContent;
	}
	public void setChapterContent(List<ChapterContent> chapterContent) {
		this.chapterContent = chapterContent;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("DashboardResponse [headers=");
		builder.append(headers);
		builder.append(", chapterContent=");
		builder.append(chapterContent);
		builder.append("]");
		return builder.toString();
	}

	
}

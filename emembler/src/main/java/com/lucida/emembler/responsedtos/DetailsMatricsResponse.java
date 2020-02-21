package com.lucida.emembler.responsedtos;

import java.util.List;

public class DetailsMatricsResponse {
	
	private Header headers;
	private List<MatricsContent> matricsContent;
		
	public Header getHeaders() {
		return headers;
	}
	public void setHeaders(Header headers) {
		this.headers = headers;
	}
	public List<MatricsContent> getMatricsContent() {
		return matricsContent;
	}
	public void setMatricsContent(List<MatricsContent> matricsContent) {
		this.matricsContent = matricsContent;
	}
}

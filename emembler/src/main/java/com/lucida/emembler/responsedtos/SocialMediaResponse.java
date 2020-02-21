package com.lucida.emembler.responsedtos;

import java.util.List;

public class SocialMediaResponse {

	private String key;
	private boolean isPresent;
	private List<Media> values;

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public List<Media> getValues() {
		return values;
	}

	public void setValues(List<Media> values) {
		this.values = values;
	}

	public boolean isPresent() {
		return isPresent;
	}

	public void setPresent(boolean isPresent) {
		this.isPresent = isPresent;
	}
}

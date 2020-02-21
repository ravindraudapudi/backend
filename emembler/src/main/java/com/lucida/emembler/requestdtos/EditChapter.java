package com.lucida.emembler.requestdtos;

public class EditChapter {

	private String primaryGroupCode;
	private String chapterName;
	private String type;
	private String associatedWith;
	private String chapterEmail;
	private String chapterPassWord;
	private String signature;

	public String getPrimaryGroupCode() {
		return primaryGroupCode;
	}

	public void setPrimaryGroupCode(String primaryGroupCode) {
		this.primaryGroupCode = primaryGroupCode;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getAssociatedWith() {
		return associatedWith;
	}

	public void setAssociatedWith(String associatedWith) {
		this.associatedWith = associatedWith;
	}

	public String getChapterName() {
		return chapterName;
	}

	public void setChapterName(String chapterName) {
		this.chapterName = chapterName;
	}

	public String getChapterEmail() {
		return chapterEmail;
	}

	public void setChapterEmail(String chapterEmail) {
		this.chapterEmail = chapterEmail;
	}

	public String getChapterPassWord() {
		return chapterPassWord;
	}

	public void setChapterPassWord(String chapterPassWord) {
		this.chapterPassWord = chapterPassWord;
	}

	public String getSignature() {
		return signature;
	}

	public void setSignature(String signature) {
		this.signature = signature;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((associatedWith == null) ? 0 : associatedWith.hashCode());
		result = prime * result + ((chapterEmail == null) ? 0 : chapterEmail.hashCode());
		result = prime * result + ((chapterName == null) ? 0 : chapterName.hashCode());
		result = prime * result + ((chapterPassWord == null) ? 0 : chapterPassWord.hashCode());
		result = prime * result + ((primaryGroupCode == null) ? 0 : primaryGroupCode.hashCode());
		result = prime * result + ((signature == null) ? 0 : signature.hashCode());
		result = prime * result + ((type == null) ? 0 : type.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		EditChapter other = (EditChapter) obj;
		if (associatedWith == null) {
			if (other.associatedWith != null)
				return false;
		} else if (!associatedWith.equals(other.associatedWith))
			return false;
		if (chapterEmail == null) {
			if (other.chapterEmail != null)
				return false;
		} else if (!chapterEmail.equals(other.chapterEmail))
			return false;
		if (chapterName == null) {
			if (other.chapterName != null)
				return false;
		} else if (!chapterName.equals(other.chapterName))
			return false;
		if (chapterPassWord == null) {
			if (other.chapterPassWord != null)
				return false;
		} else if (!chapterPassWord.equals(other.chapterPassWord))
			return false;
		if (primaryGroupCode == null) {
			if (other.primaryGroupCode != null)
				return false;
		} else if (!primaryGroupCode.equals(other.primaryGroupCode))
			return false;
		if (signature == null) {
			if (other.signature != null)
				return false;
		} else if (!signature.equals(other.signature))
			return false;
		if (type == null) {
			if (other.type != null)
				return false;
		} else if (!type.equals(other.type))
			return false;
		return true;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("EditChapter [primaryGroupCode=");
		builder.append(primaryGroupCode);
		builder.append(", chapterName=");
		builder.append(chapterName);
		builder.append(", type=");
		builder.append(type);
		builder.append(", associatedWith=");
		builder.append(associatedWith);
		builder.append(", chapterEmail=");
		builder.append(chapterEmail);
		builder.append(", chapterPassWord=");
		builder.append(chapterPassWord);
		builder.append(", signature=");
		builder.append(signature);
		builder.append("]");
		return builder.toString();
	}
	
	
	
}

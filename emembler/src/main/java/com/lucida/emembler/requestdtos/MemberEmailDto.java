package com.lucida.emembler.requestdtos;

import java.util.Arrays;

import org.springframework.web.multipart.MultipartFile;

public class MemberEmailDto {

	private String[] to;
	private String[] cc;
	private String[] bcc;
	private String fromChapter;
	private String sentBy;
	private String message;
	private String subject;
	private MultipartFile image;

	public String[] getTo() {
		return to;
	}

	public String getSentBy() {
		return sentBy;
	}

	public void setSentBy(String sentBy) {
		this.sentBy = sentBy;
	}

	public void setTo(String[] to) {
		this.to = to;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getFromChapter() {
		return fromChapter;
	}

	public void setFromChapter(String fromChapter) {
		this.fromChapter = fromChapter;
	}

	public String[] getCc() {
		return cc;
	}

	public void setCc(String[] cc) {
		this.cc = cc;
	}

	public String[] getBcc() {
		return bcc;
	}

	public void setBcc(String[] bcc) {
		this.bcc = bcc;
	}

	public MultipartFile getImage() {
		return image;
	}

	public void setImage(MultipartFile image) {
		this.image = image;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("MemberEmailDto [to=");
		builder.append(Arrays.toString(to));
		builder.append(", cc=");
		builder.append(Arrays.toString(cc));
		builder.append(", bcc=");
		builder.append(Arrays.toString(bcc));
		builder.append(", fromChapter=");
		builder.append(fromChapter);
		builder.append(", sentBy=");
		builder.append(sentBy);
		builder.append(", message=");
		builder.append(message);
		builder.append(", subject=");
		builder.append(subject);
		builder.append(", image=");
		builder.append(image);
		builder.append("]");
		return builder.toString();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Arrays.hashCode(bcc);
		result = prime * result + Arrays.hashCode(cc);
		result = prime * result + ((fromChapter == null) ? 0 : fromChapter.hashCode());
		result = prime * result + ((image == null) ? 0 : image.hashCode());
		result = prime * result + ((message == null) ? 0 : message.hashCode());
		result = prime * result + ((sentBy == null) ? 0 : sentBy.hashCode());
		result = prime * result + ((subject == null) ? 0 : subject.hashCode());
		result = prime * result + Arrays.hashCode(to);
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
		MemberEmailDto other = (MemberEmailDto) obj;
		if (!Arrays.equals(bcc, other.bcc))
			return false;
		if (!Arrays.equals(cc, other.cc))
			return false;
		if (fromChapter == null) {
			if (other.fromChapter != null)
				return false;
		} else if (!fromChapter.equals(other.fromChapter))
			return false;
		if (image == null) {
			if (other.image != null)
				return false;
		} else if (!image.equals(other.image))
			return false;
		if (message == null) {
			if (other.message != null)
				return false;
		} else if (!message.equals(other.message))
			return false;
		if (sentBy == null) {
			if (other.sentBy != null)
				return false;
		} else if (!sentBy.equals(other.sentBy))
			return false;
		if (subject == null) {
			if (other.subject != null)
				return false;
		} else if (!subject.equals(other.subject))
			return false;
		if (!Arrays.equals(to, other.to))
			return false;
		return true;
	}

	

	

}

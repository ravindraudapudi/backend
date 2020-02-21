
package com.lucida.emembler.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * 
 * @author Chapter entity to store the chapter details
 *
 */
@Entity
@Table(name = "chapter")
public class Chapter {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(name = "chapter_name", updatable = true)
	private String chapterName;

	@Column(name = "primary_group_code")
	private String primaryGroupCode;

	@Column(name = "chapter_image")
	private String chapterImage;

	@Column(name = "associated_with")
	private String associatedWith;

	@Column(name = "type")
	private String type;

	@Column(name = "chapter_email")
	private String chapterEmail;

	@Column(name = "chapter_password")
	private String chapterPassWord;

	@Column(name = "signature", length = 3000)
	private String signature;

	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinTable(name = "CHAPTER_FISCAL", joinColumns = { @JoinColumn(name = "chapter_id") }, inverseJoinColumns = {
			@JoinColumn(name = "fy_data") })
	private List<FyData> fyData = new ArrayList<FyData>();

	@ManyToMany(mappedBy = "chapters")
	@JsonIgnore
	private List<User> users;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getChapterName() {
		return chapterName;
	}

	public void setChapterName(String chapterName) {
		this.chapterName = chapterName;
	}

	public String getPrimaryGroupCode() {
		return primaryGroupCode;
	}

	public void setPrimaryGroupCode(String primaryGroupCode) {
		this.primaryGroupCode = primaryGroupCode;
	}

	public String getChapterImage() {
		return chapterImage;
	}

	public void setChapterImage(String chapterImage) {
		this.chapterImage = chapterImage;
	}

	public String getAssociatedWith() {
		return associatedWith;
	}

	public void setAssociatedWith(String associatedWith) {
		this.associatedWith = associatedWith;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public List<FyData> getFyData() {
		return fyData;
	}

	public void setFyData(List<FyData> fyData) {
		this.fyData = fyData;
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

	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}

	public String getSignature() {
		return signature;
	}

	public void setSignature(String signature) {
		this.signature = signature;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Chapter [id=");
		builder.append(id);
		builder.append(", chapterName=");
		builder.append(chapterName);
		builder.append(", primaryGroupCode=");
		builder.append(primaryGroupCode);
		builder.append(", chapterImage=");
		builder.append(chapterImage);
		builder.append(", associatedWith=");
		builder.append(associatedWith);
		builder.append(", type=");
		builder.append(type);
		builder.append(", chapterEmail=");
		builder.append(chapterEmail);
		builder.append(", chapterPassWord=");
		builder.append(chapterPassWord);
		builder.append(", signature=");
		builder.append(signature);
		builder.append(", fyData=");
		builder.append(fyData);
		builder.append(", users=");
		builder.append(users);
		builder.append("]");
		return builder.toString();
	}

}

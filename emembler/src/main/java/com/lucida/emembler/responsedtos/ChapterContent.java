package com.lucida.emembler.responsedtos;

/**
 * 
 * @author Ravindra
 *
 */
public class ChapterContent {

	private String chapterName;
	private String primaryGroupCode;
	private double period1;
	private double period2;
	private double goal;
	private double actual;
	private double percentage;

	public String getChapterName() {
		return chapterName;
	}

	public void setChapterName(String chapterName) {
		this.chapterName = chapterName;
	}

	public double getPeriod1() {
		return period1;
	}

	public void setPeriod1(double period1) {
		this.period1 = period1;
	}

	public double getPeriod2() {
		return period2;
	}

	public void setPeriod2(double period2) {
		this.period2 = period2;
	}

	public double getGoal() {
		return goal;
	}

	public void setGoal(double goal) {
		this.goal = goal;
	}

	public double getActual() {
		return actual;
	}

	public void setActual(double actual) {
		this.actual = actual;
	}

	public double getPercentage() {
		return percentage;
	}

	public void setPercentage(double percentage) {
		this.percentage = percentage;
	}
	
	public String getPrimaryGroupCode() {
		return primaryGroupCode;
	}

	public void setPrimaryGroupCode(String primaryGroupCode) {
		this.primaryGroupCode = primaryGroupCode;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("ChapterContent [chapterName=");
		builder.append(chapterName);
		builder.append(", period1=");
		builder.append(period1);
		builder.append(", period2=");
		builder.append(period2);
		builder.append(", goal=");
		builder.append(goal);
		builder.append(", actual=");
		builder.append(actual);
		builder.append(", percentage=");
		builder.append(percentage);
		builder.append("]");
		return builder.toString();
	}
	
	

}

package com.lucida.emembler.responsedtos;

public class DashboardDetails {

	private String chapterName;
	private String primaryGroupCode;
	private int period1;
	private int period2;
	private int goal;
	private int Actual;
	private double percentage;

	public String getChapterName() {
		return chapterName;
	}

	public void setChapterName(String chapterName) {
		this.chapterName = chapterName;
	}

	public int getPeriod1() {
		return period1;
	}

	public void setPeriod1(int period1) {
		this.period1 = period1;
	}

	public int getPeriod2() {
		return period2;
	}

	public void setPeriod2(int period2) {
		this.period2 = period2;
	}

	public int getGoal() {
		return goal;
	}

	public void setGoal(int goal) {
		this.goal = goal;
	}

	public int getActual() {
		return Actual;
	}

	public void setActual(int actual) {
		Actual = actual;
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
		builder.append("DashboardResponse [chapterName=");
		builder.append(chapterName);
		builder.append(", period1=");
		builder.append(period1);
		builder.append(", period2=");
		builder.append(period2);
		builder.append(", goal=");
		builder.append(goal);
		builder.append(", Actual=");
		builder.append(Actual);
		builder.append(", percentage=");
		builder.append(percentage);
		builder.append("]");
		return builder.toString();
	}
}

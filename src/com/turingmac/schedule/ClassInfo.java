package com.turingmac.schedule;

public class ClassInfo {
	private int fromX;
	private int fromY;
	private int toX;
	private int toY;

	private String className;
	private int fromClassNum;
	private int classNumLen;
	private int weekDay;
	private String classRoom;
	private String teacher;

	public ClassInfo(String aClassNume, int aFromClassNum, int aClassNumLen,
			int aWeekday, String aClassRoom, String aTeacher) {
		className = aClassNume;
		fromClassNum = aFromClassNum;
		classNumLen = aClassNumLen;
		weekDay = aWeekday;
		classRoom = aClassRoom;
		teacher = aTeacher;
	}

	public void setPoint(int fromX, int fromY, int toX, int toY) {
		this.fromX = fromX;
		this.fromY = fromY;
		this.toX = toX;
		this.toY = toY;
	}

	public int getFromX() {
		return fromX;
	}

	public void setFromX(int fromX) {
		this.fromX = fromX;
	}

	public int getFromY() {
		return fromY;
	}

	public void setFromY(int fromY) {
		this.fromY = fromY;
	}

	public int getToX() {
		return toX;
	}

	public void setToX(int toX) {
		this.toX = toX;
	}

	public int getToY() {
		return toY;
	}

	public void setToY(int toY) {
		this.toY = toY;
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public int getFromClassNum() {
		return fromClassNum;
	}

	public void setFromClassNum(int fromClassNum) {
		this.fromClassNum = fromClassNum;
	}

	public int getClassNumLen() {
		return classNumLen;
	}

	public void setClassNumLen(int classNumLen) {
		this.classNumLen = classNumLen;
	}

	public int getWeekDay() {
		return weekDay;
	}

	public void setWeekDay(int weekDay) {
		this.weekDay = weekDay;
	}

	public String getClassRoom() {
		return classRoom;
	}

	public void setClassRoom(String classRoom) {
		this.classRoom = classRoom;
	}

	public String getTeacher() {
		return teacher;
	}

	public void setTeacher(String teacher) {
		this.teacher = teacher;
	}
}

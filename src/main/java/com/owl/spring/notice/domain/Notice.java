package com.owl.spring.notice.domain;

import java.sql.Timestamp;

public class Notice {
	private int noticeNo;
	private String noticeSubject;
	private String noticeContent;
	private String noticeWriter;
	private Timestamp nCreateDate;
	private Timestamp nUpdateDate;
	private String noticeFilename;
	private String noticeFileRename;
	private String noticeFilepath;
	private long noticeFilelength;
	
	public Notice() {}

	public int getNoticeNo() {
		return noticeNo;
	}

	public void setNoticeNo(int noticeNo) {
		this.noticeNo = noticeNo;
	}

	public String getNoticeSubject() {
		return noticeSubject;
	}

	public void setNoticeSubject(String noticeSubject) {
		this.noticeSubject = noticeSubject;
	}

	public String getNoticeContent() {
		return noticeContent;
	}

	public void setNoticeContent(String noticeContent) {
		this.noticeContent = noticeContent;
	}

	public String getNoticeWriter() {
		return noticeWriter;
	}

	public void setNoticeWriter(String noticeWriter) {
		this.noticeWriter = noticeWriter;
	}

	public Timestamp getnCreateDate() {
		return nCreateDate;
	}

	public void setnCreateDate(Timestamp nCreateDate) {
		this.nCreateDate = nCreateDate;
	}

	public Timestamp getnUpdateDate() {
		return nUpdateDate;
	}

	public void setnUpdateDate(Timestamp nUpdateDate) {
		this.nUpdateDate = nUpdateDate;
	}

	public String getNoticeFilename() {
		return noticeFilename;
	}

	public void setNoticeFilename(String noticeFilename) {
		this.noticeFilename = noticeFilename;
	}
	
	public String getNoticeFileRename() {
		return noticeFileRename;
	}
	
	public void setNoticeFileRename(String noticeFileRename) {
		this.noticeFileRename = noticeFileRename;
	}

	public String getNoticeFilepath() {
		return noticeFilepath;
	}

	public void setNoticeFilepath(String noticeFilepath) {
		this.noticeFilepath = noticeFilepath;
	}

	public long getNoticeFilelength() {
		return noticeFilelength;
	}

	public void setNoticeFilelength(long noticeFilelength) {
		this.noticeFilelength = noticeFilelength;
	}

	@Override
	public String toString() {
		return "Notice [noticeNo=" + noticeNo + ", noticeSubject=" + noticeSubject + ", noticeContent=" + noticeContent
				+ ", noticeWriter=" + noticeWriter + ", nCreateDate=" + nCreateDate + ", nUpdateDate=" + nUpdateDate
				+ ", noticeFilename=" + noticeFilename + ", noticeFileRename=" + noticeFileRename + ", noticeFilepath="
				+ noticeFilepath + ", noticeFilelength=" + noticeFilelength + "]";
	}

	

}

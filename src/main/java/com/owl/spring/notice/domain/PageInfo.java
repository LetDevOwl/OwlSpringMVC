package com.owl.spring.notice.domain;

// 페이징 처리 관련 VO
public class PageInfo {
	private int currentPage;
	private int recordCountPage;
	private int naviCountPerPage;
	private int startNavi;
	private int endNavi;
	private int totalCount;
	private int naviTotalCount;

	public PageInfo() {
	}

	public PageInfo(int currentPage, int recordCountPage, int naviCountPerPage, int startNavi, int endNavi,
			int totalCount, int naviTotalCount) {
		super();
		this.currentPage = currentPage;
		this.recordCountPage = recordCountPage;
		this.naviCountPerPage = naviCountPerPage;
		this.startNavi = startNavi;
		this.endNavi = endNavi;
		this.totalCount = totalCount;
		this.naviTotalCount = naviTotalCount;
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public int getRecordCountPage() {
		return recordCountPage;
	}

	public void setRecordCountPage(int recordCountPage) {
		this.recordCountPage = recordCountPage;
	}

	public int getNaviCountPerPage() {
		return naviCountPerPage;
	}

	public void setNaviCountPerPage(int naviCountPerPage) {
		this.naviCountPerPage = naviCountPerPage;
	}

	public int getStartNavi() {
		return startNavi;
	}

	public void setStartNavi(int startNavi) {
		this.startNavi = startNavi;
	}

	public int getEndNavi() {
		return endNavi;
	}

	public void setEndNavi(int endNavi) {
		this.endNavi = endNavi;
	}

	public int getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}

	public int getNaviTotalCount() {
		return naviTotalCount;
	}

	public void setNaviTotalCount(int naviTotalCount) {
		this.naviTotalCount = naviTotalCount;
	}

	@Override
	public String toString() {
		return "PageInfo [currentPage=" + currentPage + ", recordCountPage=" + recordCountPage + ", naviCountPerPage="
				+ naviCountPerPage + ", startNavi=" + startNavi + ", endNavi=" + endNavi + ", totalCount=" + totalCount
				+ ", naviTotalCount=" + naviTotalCount + "]";
	}
}

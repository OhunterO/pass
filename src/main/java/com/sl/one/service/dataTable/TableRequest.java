package com.sl.one.service.dataTable;


/**
 * DataTables请求的参数
 */
public class TableRequest {
	
	/**
	 * start: 其实记录位置
	 */
	private int startRecord = 0;

	/**
	 * length: 页面显示数量
	 */
	private int pageSize = 0;
	
	/**
	 * order[0][column]: 因为是二维的表格，因此只有一维需要排序，所以 order 的下标未 0. 该属性表示第几列需要排序。
	 */
	private String sortColumn = null;

	/**
	 * order[0][dir]: 排序方式 ASC | DESC
	 */
	private String sortDir = null;
	
	/**
	 * search[value]: search 输入框中的值
	 */
	private String search = null;

	public int getStartRecord() {
		return startRecord;
	}

	public void setStartRecord(int startRecord) {
		this.startRecord = startRecord;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public String getSortColumn() {
		return sortColumn;
	}

	public void setSortColumn(String sortColumn) {
		this.sortColumn = sortColumn;
	}

	public String getSortDir() {
		return sortDir;
	}

	public void setSortDir(String sortDir) {
		this.sortDir = sortDir;
	}

	public String getSearch() {
		return search;
	}

	public void setSearch(String search) {
		this.search = search;
	}
}

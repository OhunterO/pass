package com.sl.one.service.dataTable;

import java.util.List;

/**
 * DataTables返回数据对象
 */
public class TableData {
	
	/**
	 * 客户端请求次数
	 */
    private int draw = 0;

	/**
	 * 实际的行数
	 */
    private int recordsTotal = 0;
    
    /**
     * 过滤之后，实际的行数。
     */
    private int recordsFiltered = 0;
    
    /**
     * 数组的数组，表格中的实际数据。　　　　
     */
    private List<?> data = null;

	public int getRecordsTotal() {
		return recordsTotal;
	}

	public void setRecordsTotal(int recordsTotal) {
		this.recordsTotal = recordsTotal;
	}

	public int getRecordsFiltered() {
		return recordsFiltered;
	}

	public void setRecordsFiltered(int recordsFiltered) {
		this.recordsFiltered = recordsFiltered;
	}

	public List<?> getData() {
		return data;
	}

	public void setData(List<?> data) {
		this.data = data;
	}

	public int getDraw() {
		return draw;
	}

	public void setDraw(int draw) {
		this.draw = draw;
	}
}

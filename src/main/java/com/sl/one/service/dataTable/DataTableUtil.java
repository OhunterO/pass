package com.sl.one.service.dataTable;

import javax.servlet.http.HttpServletRequest;

/**
 * DataTable的工具类
 */
public class DataTableUtil {
	
	/**
	 * 从request中解析出表格请求参数
	 * @param request
	 * @return
	 */
	public static TableRequest getRequestInfo(HttpServletRequest request) {

		// For pagination
		  int pageSize = 10;
		  int startRecord = 0;
		  String size = request.getParameter("length");
		  if (!"".equals(size) && size != null) {
		      pageSize = Integer.parseInt(size);
		  }
		  String currentRecord = request.getParameter("start");
		  if (!"".equals(currentRecord) && currentRecord != null) {
		      startRecord = Integer.parseInt(currentRecord);
		  }
		  // For sortable
		  String sortOrder = request.getParameter("order[0][column]");
		  String sortDir = request.getParameter("order[0][dir]");
		  String sortColumn = request.getParameter("columns[" + sortOrder + "][data]");
		         
		  // For search
		  String searchValue = request.getParameter("search[value]");
		  
		  TableRequest tblRequest = new TableRequest();
		  tblRequest.setStartRecord(startRecord);
		  tblRequest.setPageSize(pageSize);
		  tblRequest.setSortColumn(sortColumn);
		  tblRequest.setSortDir(sortDir);
		  tblRequest.setSearch(searchValue);
		  
		  return tblRequest;
	}

}

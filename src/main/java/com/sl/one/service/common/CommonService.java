package com.sl.one.service.common;

import com.baomidou.mybatisplus.plugins.Page;
import com.sl.one.service.dataTable.DataTableUtil;
import com.sl.one.service.dataTable.TableData;
import com.sl.one.service.dataTable.TableRequest;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by shi on 2017/1/8.
 */
public class CommonService {
    /**
     * 将List转换为TableData
     *
     * @param page 分页信息
     * @param list 查询结果
     * @return TableData
     */
    public static <T> TableData convert2TableData(Page<T> page, List<T> list) {
        TableData dataResp = new TableData();
        dataResp.setRecordsTotal(page.getTotal());
        dataResp.setRecordsFiltered(page.getTotal());
        dataResp.setData(list);
        return dataResp;
    }

    /**
     * 获取DataTable请求的参数，并且将其转换为分页信息
     *
     * @return 分页信息
     */
    public static <T> Page<T> getPageInfo(HttpServletRequest request) {
        TableRequest tblRequest = DataTableUtil.getRequestInfo(request);
        Page<T> page = new Page<T>(tblRequest.getStartRecord() / tblRequest.getPageSize() + 1,
                tblRequest.getPageSize());
        return page;
    }
}

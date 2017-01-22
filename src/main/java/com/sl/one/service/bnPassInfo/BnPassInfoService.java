package com.sl.one.service.bnPassInfo;

import com.baomidou.mybatisplus.plugins.Page;
import com.sl.one.entity.BnPassInfo;
import com.sl.one.mapper.entityMapper.BnPassInfoMapper;
import com.sl.one.mapper.serviceMapper.BnPassInfoServiceMapper;
import com.sl.one.service.common.CommonService;
import com.sl.one.service.dataTable.TableData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by shi on 2017/1/8.
 */
@Component
@Transactional
public class BnPassInfoService {

    @Autowired
    private BnPassInfoMapper bnPassInfoMapper;

    @Autowired
    private BnPassInfoServiceMapper bnPassInfoServiceMapper;

    public TableData list(HttpServletRequest request) {
        // 解析request并设置分页
        Page<BnPassInfo> page = CommonService.getPageInfo(request);
        Map<String,Object> map=new HashMap<String, Object>();
        List<BnPassInfo> list = bnPassInfoServiceMapper.selectBnPassInfoList(page,map);
        TableData dataResp = CommonService.convert2TableData(page, list);
        return dataResp;
    }

    /**
     * 添加
     * @param bnPassInfo
     * @return
     */
    public int insert(BnPassInfo bnPassInfo) {
        return bnPassInfoMapper.insert(bnPassInfo);
    }

    /**
     * 删除
     * @param id
     * @return
     */
    public int delete(String id) {
        return bnPassInfoMapper.deleteById(id);
    }

    /**
     * 根据ID查询
     * @param id
     * @return
     */
    public BnPassInfo selectById(String id){
        return bnPassInfoMapper.selectById(id);
    }

    /**
     * 更新
     * @param bnPassInfo
     * @return
     */
    public int update(BnPassInfo bnPassInfo) {
        return bnPassInfoMapper.updateById(bnPassInfo);
    }
}

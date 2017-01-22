package com.sl.one.mapper.serviceMapper;

import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.plugins.Page;
import com.sl.one.entity.BnPassInfo;

/**
 * Created by shi on 2017/1/8.
 */
public interface BnPassInfoServiceMapper {

    List<BnPassInfo> selectBnPassInfoList(Page<BnPassInfo> page, Map<String,Object> map);
}

package com.sl.one.mapper.serviceMapper;

import com.baomidou.mybatisplus.plugins.Page;
import com.sl.one.entity.PUser;

import java.util.List;
import java.util.Map;

/**
 * Created by shi on 2017/1/8.
 */
public interface UserServiceMapper {

    List<PUser> selectPUserList(Page<PUser> page, Map<String, Object> map);
}

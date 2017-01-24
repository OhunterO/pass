package com.sl.one.service.user;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.toolkit.StringUtils;
import com.sl.one.entity.PUser;
import com.sl.one.mapper.entityMapper.PUserMapper;
import com.sl.one.mapper.serviceMapper.UserServiceMapper;
import com.sl.one.service.common.CommonService;
import com.sl.one.service.dataTable.TableData;
import com.sl.one.shiro.Digests;
import com.sl.one.shiro.ShiroDbRealm;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by shi on 2017/1/12.
 */
@Component
@Transactional
public class UserService {

    @Autowired
    private UserServiceMapper userServiceMapper;

    @Autowired
    private PUserMapper pUserMapper;

    public TableData list(HttpServletRequest request) {
        // 解析request并设置分页
        Page<PUser> page = CommonService.getPageInfo(request);
        Map<String, Object> map = new HashMap<String, Object>();
        List<PUser> list = userServiceMapper.selectPUserList(page, map);
        TableData dataResp = CommonService.convert2TableData(page, list);
        return dataResp;
    }

    /**
     * 添加用户
     *
     * @param pUser
     * @return
     */
    public int insert(PUser pUser) {
        initPassword(pUser);
        return pUserMapper.insert(pUser);
    }

    /**
     * 删除用户
     *
     * @param id
     * @return
     */
    public int delete(String id) {
        return pUserMapper.deleteById(id);
    }

    /**
     * 根据ID查询用户
     *
     * @param id
     * @return
     */
    public PUser selectById(String id) {
        return pUserMapper.selectById(id);
    }

    /**
     * 根据登录名查询
     * @param userName
     * @return
     */
    public PUser selectByUserName(String userName){
        Map<String,Object> paramMap=new HashMap<String,Object>();
        paramMap.put("USER_NAME",userName);
        List<PUser> list=pUserMapper.selectByMap(paramMap);
        return list.size()>0?list.get(0):null;
    }
    /**
     * 更新用户
     *
     * @param pUser
     */
    public int update(PUser pUser) {
        initPassword(pUser);
        return pUserMapper.updateById(pUser);
    }

    /**
     * 设定安全的密码，生成随机的salt并经过1024次 sha-1 hash
     *
     * @param user 用户实体类
     */
    private void initPassword(PUser user) {
        if (!StringUtils.isEmpty(user.getUserPassword())) {
            byte[] salt = Digests.generateSalt(ShiroDbRealm.SALT_SIZE);
            user.setSalt(Digests.encodeHex(salt));
            SimpleHash simpleHash = new SimpleHash(ShiroDbRealm.HASH_ALGORITHM, user.getUserPassword(), salt, ShiroDbRealm.HASH_INTERATIONS);
            user.setUserPassword(simpleHash.toString());
        }
    }
    public  static  void main(String[] args){
        byte[] salt = Digests.generateSalt(ShiroDbRealm.SALT_SIZE);
        System.out.println(Digests.encodeHex(salt));
        SimpleHash simpleHash = new SimpleHash(ShiroDbRealm.HASH_ALGORITHM,"123", salt, ShiroDbRealm.HASH_INTERATIONS);
        System.out.println(simpleHash.toString());
    }
}

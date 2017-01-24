/*
 * ====================================================================
 * 在线采购管理系统
 * ====================================================================
 */
package com.sl.one.shiro;


import com.sl.one.entity.PUser;
import com.sl.one.service.user.UserService;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 用户认证
 */
public class ShiroDbRealm extends AuthorizingRealm {

    public static final String HASH_ALGORITHM = "md5";
    public static final int HASH_INTERATIONS = 1024;
    public static final int SALT_SIZE = 8;

    @Autowired
    private UserService userService;

    private static Logger logger = LoggerFactory.getLogger(ShiroDbRealm.class);

    /**
     * 认证回调函数,登录时调用.
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authcToken) {
        UsernamePasswordToken token = (UsernamePasswordToken) authcToken;
        String username = token.getUsername();
        PUser user = userService.selectByUserName(username);

        logger.debug("登录查询,UserNo:" + token.getUsername());
        if (user != null) {
            byte[] salt = Digests.decodeHex(user.getSalt());
            return new SimpleAuthenticationInfo(user, user.getUserPassword(), ByteSource.Util.bytes(salt),
                    getName());
        } else {
            return null;
        }
    }

    /**
     * 授权查询回调函数, 进行鉴权但缓存中无用户的授权信息时调用.
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        PUser user = (PUser) principals.getPrimaryPrincipal();
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        info.addRole(user.getRole());
        logger.debug("授权成功:" + user.getUserName());
        return info;
    }

}

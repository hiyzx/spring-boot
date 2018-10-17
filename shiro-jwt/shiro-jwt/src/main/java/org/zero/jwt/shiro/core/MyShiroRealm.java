package org.zero.jwt.shiro.core;

import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.stereotype.Component;
import org.zero.jwt.shiro.entity.SysPermission;
import org.zero.jwt.shiro.entity.SysRole;
import org.zero.jwt.shiro.entity.UserInfo;
import org.zero.jwt.shiro.service.UserInfoService;

import javax.annotation.Resource;
import java.util.Map;

@Component
public class MyShiroRealm extends AuthorizingRealm {

    @Resource
    private UserInfoService userInfoService;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        UserInfo userInfo = userInfoService.findByUsername(JWTUtil.getUsername());
        for (SysRole role : userInfo.getRoleList()) {
            authorizationInfo.addRole(role.getRole());
            for (SysPermission p : role.getPermissions()) {
                authorizationInfo.addStringPermission(p.getPermission());
            }
        }
        return authorizationInfo;
    }

    /**
     * 认证
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken auth) {
        String token = (String) auth.getCredentials();
        // 解密获得username，用于和数据库进行对比

        String username = JWTUtil.getUsername(token);
        if (username == null) {
            throw new AuthenticationException("token_invalid");
        }
        Map<String, Object> map = JWTUtil.verify(token, username);
        if (!(Boolean) map.get("pass")) {
            throw new AuthenticationException("token_invalid");
        }
        Object soonExpire = map.get("soon_expire");
        if (soonExpire != null && (Boolean) soonExpire) {
            throw new ExpiredCredentialsException("soon_expire");
        }
        return new SimpleAuthenticationInfo(token, token, getName());
    }

    /**
     * 必须重写此方法，不然Shiro会报错
     */
    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof JWTToken;
    }

}
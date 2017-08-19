package com.zero.conf;

import com.zero.po.User;
import com.zero.service.UserService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author yezhaoxing
 * @date 2017/08/19
 */
@Component
public class MyShiroRealm extends AuthorizingRealm {
    @Resource
    private UserService userService;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        String userName = (String) principalCollection.fromRealm(getName()).iterator().next();
        if (userName != null) {
            List<String> permissions = userService.queryUserPermissionById(userName);
            if (!permissions.isEmpty()) {
                SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
                for (String each : permissions) {
                    // 将权限资源添加到用户信息中
                    info.addStringPermission(each);
                }
                return info;
            }
        }
        return null;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken)
            throws AuthenticationException {
        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
        // 通过表单接收的用户名
        String username = token.getUsername();
        if (username != null && !"".equals(username)) {
            User user = userService.getSelfInfo(username);
            if (user != null) {
                return new SimpleAuthenticationInfo(user.getName(), user.getPassword(), getName());
            }
        }

        return null;
    }
}

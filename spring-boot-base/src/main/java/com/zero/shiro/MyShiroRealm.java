package com.zero.shiro;

import com.zero.po.User;
import com.zero.service.LoginService;
import com.zero.service.UserService;
import com.zero.util.DateHelper;
import com.zero.vo.ShiroUserVo;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.Date;

/**
 * @author yezhaoxing
 * @date 2017/08/19
 */
@Component
public class MyShiroRealm extends AuthorizingRealm {
    @Resource
    private UserService userService;
    @Resource
    private LoginService loginService;

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken)
            throws AuthenticationException {
        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
        // 通过表单接收的用户名
        String username = token.getUsername();
        if (StringUtils.hasText(username)) {
            User user = userService.getSelfInfo(username);
            if (user != null) {
                Date now = DateHelper.getCurrentDateTime();
                loginService.login(user.getId(), user.getLastLoginTime(), now);
                ShiroUserVo rtn = new ShiroUserVo();
                rtn.setLastLoginTime(now);
                BeanUtils.copyProperties(user, rtn);
                return new SimpleAuthenticationInfo(rtn, user.getPassword(), getName());
            }
        }
        return null;
    }

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        return null;
    }
}

package org.zero.jwt.shiro.core;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;


/**
 * @author yezhaoxing
 * @since 2018/10/17
 * @description
 */
public class ShiroUtils {

    public static String getToken(HttpServletRequest req) {
        Cookie[] cookies = req.getCookies();
        String authorization = "";
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                String name = cookie.getName();
                if ("Authorization".equals(name)) {
                    authorization = cookie.getValue();
                }
            }
        }
        return authorization;
    }
}

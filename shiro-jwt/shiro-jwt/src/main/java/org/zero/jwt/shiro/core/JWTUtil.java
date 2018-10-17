package org.zero.jwt.shiro.core;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.apache.shiro.SecurityUtils;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class JWTUtil {

    // 过期时间60分钟
    private static final long EXPIRE_TIME = 30 * 60 * 1000;

    private static final String MY_SECRET = "VZ_CREDIT";

    /**
     * 校验token是否正确
     * @param token 密钥
     * @return 是否正确
     */
    public static Map<String, Object> verify(String token, String username) {
        Map<String, Object> rtn = new HashMap<>(2);
        try {
            Algorithm algorithm = Algorithm.HMAC256(MY_SECRET);
            JWTVerifier verifier = JWT.require(algorithm).withClaim("username", username).build();
            DecodedJWT jwt = verifier.verify(token);
            Date expiresAt = jwt.getExpiresAt();
            long betweenTime = expiresAt.getTime() - System.currentTimeMillis();
            if (betweenTime < 0) {
                rtn.put("pass", false);
            } else {
                if (betweenTime <= 10 * 60 * 1000) {// 离过期时间还有10分钟,更新token
                    rtn.put("soon_expire", true);
                }
                rtn.put("pass", true);
            }
        } catch (Exception exception) {
            rtn.put("pass", false);
        }
        return rtn;
    }

    /**
     * 获得token中的信息无需secret解密也能获得
     * @return token中包含的用户名
     */
    public static String getUsername(String token) {
        try {
            DecodedJWT jwt = JWT.decode(token);
            return jwt.getClaim("username").asString();
        } catch (JWTDecodeException e) {
            return null;
        }
    }

    /**
     * 获得token中的信息无需secret解密也能获得
     * @return token中包含的用户id
     */
    public static Integer getUserId(String token) {
        try {
            DecodedJWT jwt = JWT.decode(token);
            return jwt.getClaim("userId").asInt();
        } catch (JWTDecodeException e) {
            return null;
        }
    }


    /**
     * 生成签名,30min后过期
     * @param username 用户名
     * @return 加密的token
     */
    public static String sign(String username, Integer userId) {
        try {
            Date date = new Date(System.currentTimeMillis() + EXPIRE_TIME);
            Algorithm algorithm = Algorithm.HMAC256(MY_SECRET);
            // 附带username信息
            return JWT.create().withClaim("username", username).withClaim("userId", userId).withExpiresAt(date)
                    .sign(algorithm);
        } catch (UnsupportedEncodingException e) {
            return null;
        }
    }

    public static Integer getUserId() {
        String token = (String) SecurityUtils.getSubject().getPrincipal();
        return JWTUtil.getUserId(token);
    }

    public static String getUsername() {
        String token = (String) SecurityUtils.getSubject().getPrincipal();
        return JWTUtil.getUsername(token);
    }
}

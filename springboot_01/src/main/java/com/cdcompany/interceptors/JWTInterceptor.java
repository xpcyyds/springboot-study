package com.cdcompany.interceptors;

import com.auth0.jwt.JWT;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.cdcompany.controller.Result;
import com.cdcompany.utils.JWTUtils;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.security.SignatureException;
import java.util.HashMap;
import java.util.Map;

public class JWTInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        Map<String, Object> map = new HashMap<>();
        //1.获取请求头中的令牌
        String token = request.getHeader("Authorization");
        token = token.replace("Bearer ", "");
        /*String refreshToken = request.getHeader("ReAuthorization");
        refreshToken = refreshToken.replace("Bearer ", "");*/
//        String token = request.getHeader("token");
//        String refreshToken = request.getHeader("refreshToken");
        System.out.println(token);
        /*String username = JWTVerify.getClaim("username").asString();*/
        if (JWTUtils.verify(token) && JWTUtils.checkToken(token) == true) {
            System.out.println("token生效");
            long expireTime = JWT.decode(token)
                    .getExpiresAt().getTime()/1000;
            System.out.println("token剩余时间为===");
            System.out.println(expireTime - System.currentTimeMillis()/1000);
            return true;
        }else if(JWTUtils.verify(token) == true && JWTUtils.checkToken(token) == false) {
            System.out.println("token即将失效");
            DecodedJWT JWTVerify = JWTUtils.getTokenInfo(token);
            System.out.println(2);
            String username = JWTVerify.getClaim("username").asString();
            System.out.println(3);
            Map<String, String> payload = new HashMap<>();
            System.out.println(4);
            payload.put("username", username);
            //生成JWT令牌
            String newToken = JWTUtils.getToken(payload);
//            String newRefreshToken = JWTUtils.getReFreshToken(payload);
//            map.put("msg","token已失效，请重新登录");
            System.out.println("newToken===" + newToken);
//            System.out.println("newRefreshToken===" + newRefreshToken);
            response.addHeader("token", newToken);
//            response.addHeader("refreshToken", newRefreshToken);
            response.addIntHeader("code", 50000);
            return true;
        }else {
            System.out.println("token失效");
            map.put("msg","token失效，请重新登录");
            System.out.println(10);
        }
//        map.put("msg","refreshToken失效，请重新登录");
        System.out.println(11);
        response.addIntHeader("code",50001);
        System.out.println(12);
        map.put("code",108);
        System.out.println(13);
        String json = new ObjectMapper().writeValueAsString(map);
        System.out.println(14);
        response.setContentType("application/json;charset=UTF-8");
        System.out.println(15);
        response.getWriter().println(json);
        System.out.println(16);
        return false;
    }
}

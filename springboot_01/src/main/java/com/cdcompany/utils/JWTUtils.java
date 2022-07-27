package com.cdcompany.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import jdk.nashorn.internal.objects.annotations.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RestController;

import java.util.Calendar;
import java.util.Map;

@Component
@ConfigurationProperties(prefix = "jwtutils")
public class JWTUtils {

    private static final String SIGN = "!Q@W#E$R";
    private static Integer refreshTokenTime;//token刷新时间，单位：秒
    private static Integer tokenExpirationTime;//token过期时间，单位：秒

    @Value("${jwtutils.refreshTokenTime}")
    public void setRefreshTokenTime(Integer refreshTokenTime) {
        JWTUtils.refreshTokenTime = refreshTokenTime;
    }
    @Value("${jwtutils.tokenExpirationTime}")
    public void setTokenExpirationTime(Integer tokenExpirationTime) {
        JWTUtils.tokenExpirationTime = tokenExpirationTime;
    }

    /**
     * 1.生成token     header.payload.sing
     * @param map
     * @return
     */
    public static String getToken(Map<String,String> map){
        Calendar instance = Calendar.getInstance();
        instance.add(Calendar.SECOND,tokenExpirationTime);//默认过期时间;单位：秒
        //创建jwt builder
        JWTCreator.Builder builder = JWT.create();
        //payload
        map.forEach((k,v)->{
            builder.withClaim(k,v);
        });
        String token = builder.withExpiresAt(instance.getTime())//指定令牌过期时间
                .sign(Algorithm.HMAC256(SIGN));//签名
       /* String token = JWT.create()
                .withClaim("id",12)
                .withClaim("username","lihua")//payload
                .withExpiresAt(instance.getTime())
                .sign(Algorithm.HMAC256(SIGN));//签名*/
        System.out.println(token);
        return token;
    }

    //2.生成refreshToken     header.payload.sing
    public static String getReFreshToken(Map<String,String> map){
        Calendar instance = Calendar.getInstance();
        instance.add(Calendar.SECOND,20);//默认过期时间

        //创建jwt builder
        JWTCreator.Builder builder = JWT.create();

        //payload
        map.forEach((k,v)->{
            builder.withClaim(k,v);
        });

        String refreshToken = builder.withExpiresAt(instance.getTime())//指定令牌过期时间
                .sign(Algorithm.HMAC256(SIGN));//签名
       /* String token = JWT.create()
                .withClaim("id",12)
                .withClaim("username","lihua")//payload
                .withExpiresAt(instance.getTime())
                .sign(Algorithm.HMAC256(SIGN));//签名*/
        System.out.println(refreshToken);
        return refreshToken;
    }

    //验证token合法性

    /**
     * 2.验证token合法性
     * true合法 false不合法
     * @param token
     * @return
     */
    public static boolean verify(String token){
        try{
            JWT.require(Algorithm.HMAC256(SIGN)).build().verify(token);
            return true;
        }catch (Exception e){
            System.out.println("异常");
        }
        return false;
    }

    /**
     * 3.获取token信息的方法
     * @param token
     * @return
     */
    public static DecodedJWT getTokenInfo(String token){
        System.out.println(0);
        return JWT.require(Algorithm.HMAC256(SIGN)).build().verify(token);
    }

    /**
     *4.检查token是否还能继续使用
     * true可以 false不建议
     * @param token
     * @return
     */
    public static boolean checkToken(String token){
        long expireTime = JWT.decode(token)
                .getExpiresAt().getTime()/1000; 
        long surplus = expireTime - System.currentTimeMillis()/1000;
        System.out.println("token剩余时间==="+(expireTime - System.currentTimeMillis()/1000));
        return surplus < refreshTokenTime ? false : true;
    }
}

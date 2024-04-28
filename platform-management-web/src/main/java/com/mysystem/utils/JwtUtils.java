package com.mysystem.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.AlgorithmMismatchException;
import com.auth0.jwt.exceptions.InvalidClaimException;
import com.auth0.jwt.exceptions.SignatureVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.mysystem.model.UserInfo;
import lombok.Data;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

@Data
public class JwtUtils {
    private static final String SECRET = "!Q@W#E$R^Y&U";
    //token签发者
    private static final String ISSUSRE = "HZSTYGZPT";
    //token过期时间
    public static final Long EXPIRE_DATE = 1000 * 60L;

    /**
     * 生成token
     *
     * @param userInfo
     * @return
     */
    public static String Token(UserInfo userInfo) {
        //创建过期时间
        Calendar instance = Calendar.getInstance();
        instance.add(Calendar.DATE, 7);  //7天过期

        // 头部
        HashMap<String, Object> headMap = new HashMap<>();
        headMap.put("alg", "HS256");
        headMap.put("typ", "typ");

        String token = JWT.create()
                .withHeader(headMap)
                .withExpiresAt(instance.getTime())
                .withClaim("userName", userInfo.getUserName())
                .withClaim("password", userInfo.getPassword())
                .sign(Algorithm.HMAC256(SECRET));
        return token;
    }

    /**
     * 生成token
     *
     * @param map
     * @return
     */
    public static String createToken(Map<String, String> map) {
        //创建过期时间
        Calendar instance = Calendar.getInstance();
        instance.add(Calendar.DATE, 7);  //7天过期

        //创建builder对象
        JWTCreator.Builder builder = JWT.create();

        // 头部
        HashMap<String, Object> headMap = new HashMap<>();
        headMap.put("alg", "HS256");
        headMap.put("typ", "typ");
        //遍历map
        map.forEach((k, v) -> {
            builder.withClaim(k, v);
        });
        String token = builder
                .withHeader(headMap)
                .withExpiresAt(instance.getTime())
                .sign(Algorithm.HMAC256(SECRET));
        return token;
    }

    /**
     * 验证token
     * 验证过程中如果有异常，则抛出；
     * 如果没有,则返回 DecodedJWT 对象来获取用户信息;
     *
     * @param token
     */
    public static DecodedJWT verify(String token) {
        Algorithm algorithm = Algorithm.HMAC256(SECRET);
        DecodedJWT verify = null;
        try {
            JWTVerifier jwtVerifier = JWT.require(algorithm).build();
            verify = jwtVerifier.verify(token);
        } catch (SignatureVerificationException e) {
            //验证的签名不一致
            throw new SignatureVerificationException(algorithm);
        } catch (TokenExpiredException e) {
            throw new TokenExpiredException("to ken is alreadey expired", e.getExpiredOn());
        } catch (AlgorithmMismatchException e) {
            throw new AlgorithmMismatchException("签名算法不匹配");
        } catch (InvalidClaimException e) {
            throw new InvalidClaimException("校验的claims内容不匹配");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return verify;
    }

    public static void main(String[] args) {
        UserInfo yangcheng2 = UserInfo.builder().userName("yangcheng2").password("12132").build();
        String token = JwtUtils.Token(yangcheng2);
        System.out.println(token);

        DecodedJWT verify = JwtUtils.verify(token);
        System.out.println(verify.getClaim("userName"));
        System.out.println(verify.getClaim("password"));
    }
}

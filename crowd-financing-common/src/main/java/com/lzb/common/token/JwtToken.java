package com.lzb.common.token;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;

import java.io.UnsupportedEncodingException;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class JwtToken {

    public static String TOKEN="abc";
    //生产token
    public static String createJwtToken(Long id,String loginacct,int time) {
        //签发时间
        Date date = new Date();
        //过期时间，1分钟过期
        Calendar instance = Calendar.getInstance();
        instance.add(Calendar.MINUTE,time);
        Date times = instance.getTime();
        //heander
        HashMap<String, Object> map = new HashMap<>();
        map.put("alg","HS256");
        map.put("typ","JWT");
        String token= null;//加密
        try {
            token = JWT.create()
                    .withHeader(map)//heander
                    .withClaim(id.toString(),loginacct)
                    .withExpiresAt(times)//过期时间
                    .withIssuedAt(date)//签名时间
                    .sign(Algorithm.HMAC256(TOKEN));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return token;
    }

    //解密token
    public static Map<String, Claim> verifyToken(String token) throws Exception {
        JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC256(TOKEN)).build();
        DecodedJWT jwt=null;
        try {
            jwt = jwtVerifier.verify(token);
        } catch (JWTVerificationException e) {
            e.printStackTrace();
            throw new RuntimeException("令牌过期");
        }
        return jwt.getClaims();
    }
}

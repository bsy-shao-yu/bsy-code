package com.fh.jwt;

import com.alibaba.fastjson.JSON;
import com.fh.util.ResponseServer;
import com.fh.util.ServerEnum;
import io.jsonwebtoken.*;
import sun.misc.BASE64Encoder;

import java.util.HashMap;
import java.util.Map;

public class JwtUtis {

    public static String getTohen(Map<String,Object> map){


        //声明头部信息
        Map<String,Object> heandMap = new HashMap<>();
        heandMap.put("alg","HS256");
        heandMap.put("typ","JWT");
        //设置负载: 但是不要放涉及到个人隐私和机密

        Map<String,Object> payload = new HashMap<>();
        payload.putAll(map);

        Long sssj = System.currentTimeMillis(); //失效时间
        payload.put("exp",sssj+1800000000l);//  获取当前的时间戳  加上60000 秒  当作结束时间
        payload.put("iat",sssj);// //设置开始事件

        //设置签名值

        String tohen = Jwts.builder()
                .setHeader(heandMap)
                .setPayload(JSON.toJSONString(payload))
                .signWith(SignatureAlgorithm.HS256, getKey("baishaoyu"))
                .compact();

        return tohen;

    }






    public static ResponseServer analysisTohen(String tohen){

        Claims claims = null;

            try {
                claims = Jwts.parser()
                        .setSigningKey(getKey("baishaoyu"))
                        .parseClaimsJws(tohen)
                        .getBody();
            }catch (ExpiredJwtException e){
                System.out.println("token超时，token失效了");
                return ResponseServer.error(ServerEnum.TOKEN_TIMEOUT);
            }catch(SignatureException sing){
                System.out.println("解析token失败了");
                return ResponseServer.error(ServerEnum.SAFETY_INVALID);
            }

        return ResponseServer.success(claims);

    }


    private static  String getKey(String key){
        return new BASE64Encoder().encode(key.getBytes());
    }




}

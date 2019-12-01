package com.fh.controller;

import com.fh.jwt.JwtUtis;
import com.fh.model.ShopUser;
import com.fh.service.UserLoinService;
import com.fh.util.ResponseServer;
import com.fh.util.ServerEnum;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/user")
@CrossOrigin(maxAge = 3600,origins ="http://localhost:8080")
public class LoginCroller {

            @Autowired
            private UserLoinService userLoinService;


            @Autowired
            private RedisTemplate redisTemplate;


            @GetMapping
            public ResponseServer setCode(String phone) throws Exception {
                //判断手机号不能为空
                if(StringUtils.isBlank(phone)){
                        return ResponseServer.error(ServerEnum.PHONE_NULL);
                }
                //发送验证码
       //         JSONObject json = SendCode.main(phone);

//                String code = json.getString("code");
//                if(code.equals("200")){
//                    String phoneYzm = json.getString("obj");
//
//
//
//                }
                redisTemplate.opsForValue().set("code_"+phone,"123456",3000l, TimeUnit.SECONDS);
                return ResponseServer.success();
            }

            @PostMapping
            public ResponseServer loginUser(String code,String phone){


            //验证手机号验证码 不能为空
                if(StringUtils.isBlank(code) && StringUtils.isBlank(phone)){
                    return ResponseServer.error(ServerEnum.PHONE_NULL);

                }

                //获取 redis 里面的验证码
                String  codeYzm = (String) redisTemplate.opsForValue().get("code_" + phone);

                    if(! code.equals(codeYzm)){
                        return ResponseServer.error(ServerEnum.PHONYZM_ERROR);
                    }

                redisTemplate.delete("code_" + phone);
                // 判断用户是否存在  不存在就注册
                ShopUser shopUser = userLoinService.queryUserPhone(phone);

                //放入到 redis中
                redisTemplate.opsForValue().set(phone+"user",shopUser);

                redisTemplate.opsForValue().set(phone+"carid",shopUser.getCarId());
                //生成Token
                Map<String ,Object> map = new HashMap<String ,Object>();

                map.put("phone",phone);

                String tohen = JwtUtis.getTohen(map);

                return ResponseServer.success(tohen);

            }

}

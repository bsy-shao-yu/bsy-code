package com.fh.controller;

import com.fh.redis.IRedisService;
import com.fh.service.ShopTypeService;
import com.fh.util.ResponseServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/type")
@CrossOrigin(origins = "http://localhost:8080",maxAge = 3600)
public class ShopTypeController {

            @Autowired
            private ShopTypeService shopTypeService;


            @Autowired
            private IRedisService redisService;


            @GetMapping
            public ResponseServer queryShopType(){
                Boolean isExistKey = redisService.isExistKey("ShopTypeAll");
                Object shopList = null;
                if(! isExistKey){
                    shopList =shopTypeService.queryShopType();
                    redisService.setShopType("ShopTypeAll",shopList);

                }else {
                    shopList = redisService.getStringValueKey("ShopTypeAll");
                }
           return  ResponseServer.success(shopList);

            }


}

package com.fh.service.impl;

import com.fh.dao.ShopBranMappr;
import com.fh.model.ShopBrand;
import com.fh.service.ShopBranService;
import com.fh.util.ResponseServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service("shopbrand")
public class ShopBranServiceImpl implements ShopBranService {

    @Autowired
    private ShopBranMappr shopBranMappr;

    @Autowired
    private RedisTemplate redisTemplate;


    @Override
    public ResponseServer queryBrandbyTypePid(String pid) {

        Boolean isExisBrand = redisTemplate.hasKey("brand");
        List<ShopBrand> shopBrandList = new ArrayList<>();

        if(isExisBrand){
             shopBrandList = (List<ShopBrand>) redisTemplate.opsForValue().get("brand");
        }else {
            shopBrandList = shopBranMappr.queryBrandByType();
            redisTemplate.opsForValue().set("brand",shopBrandList);
        }
        List<ShopBrand> returnList =shopBrandList.stream()
                    .filter(brand->brand.getTId().equals(pid))
                    .collect(Collectors.toList());
        return ResponseServer.success(returnList);


    }

    @Override
    public ResponseServer getBrandIdCar(Integer id) {

        ShopBrand shopBrand = shopBranMappr.getBrandIdCar(id);

        return ResponseServer.success(shopBrand);
    }
}

package com.fh.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.fh.dao.UserLoinMappr;
import com.fh.model.ShopUser;
import com.fh.service.UserLoinService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.UUID;

@Service("userLogin")
public class UserLoinServiceImpl implements UserLoinService {

    @Autowired
    private UserLoinMappr userLoinMappr;

    @Override
    public ShopUser queryUserPhone(String phone) {

        QueryWrapper<ShopUser> queryWrapper = new QueryWrapper<ShopUser>();
        queryWrapper.eq("phone",phone);
        ShopUser shopUser = userLoinMappr.selectOne(queryWrapper);

        if(shopUser == null ){
             shopUser = new ShopUser();
            shopUser.setPhone(phone);
            shopUser.setIstat(1);
            shopUser.setEndDateTime(new Date());
            shopUser.setCarId(UUID.randomUUID().toString().replace("-",""));
            userLoinMappr.insert(shopUser);
        }else {
            shopUser.setEndDateTime(new Date());
            userLoinMappr.updateById(shopUser);
        }

        return  shopUser ;

    }

    @Override
    public void addShopUser(ShopUser shopUser) {
        userLoinMappr.insert(shopUser);
    }
}

package com.fh.service;

import com.fh.model.ShopUser;

public interface UserLoinService {

    ShopUser queryUserPhone(String phone);

    void addShopUser(ShopUser shopUser);
}

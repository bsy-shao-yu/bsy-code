package com.fh.service;

import com.fh.util.ResponseServer;

public interface ShopBranService {
    ResponseServer queryBrandbyTypePid(String pid);

    ResponseServer getBrandIdCar(Integer id);
}

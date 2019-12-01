package com.fh.service;

import com.fh.util.ResponseServer;

import java.util.Map;

public interface CarSevice {
    ResponseServer addCar(String phone, Integer id);

    Map<String,Object> carShow(String phone);

    ResponseServer addcar1(Integer productId,String  phone );

    ResponseServer delCar(Integer productId, String phone);

    ResponseServer delProduct(Integer productId, String phone);

    ResponseServer updateChexked(String chkvalue, String phone);
}

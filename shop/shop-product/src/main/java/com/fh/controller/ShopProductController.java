package com.fh.controller;

import com.fh.model.ShopProduct;
import com.fh.service.ShopProductService;
import com.fh.util.PageBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "http://localhost:8080",maxAge = 3600)
@RequestMapping("/product")
public class ShopProductController {

    @Autowired
    private ShopProductService shopProductService;



    @GetMapping
    public PageBean<ShopProduct> queryProduct(PageBean<ShopProduct> page){

        return shopProductService.queryProduct(page) ;
    }




}

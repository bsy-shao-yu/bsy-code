package com.fh.service.impl;

import com.fh.dao.ShopProductMappr;
import com.fh.model.ShopProduct;
import com.fh.service.ShopProductService;
import com.fh.util.PageBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("productServic")
public class ShopProductServiceImpl implements ShopProductService {

    @Autowired
    private ShopProductMappr shopProductMappr;


    @Override
    public PageBean<ShopProduct> queryProduct(PageBean<ShopProduct> page) {

        Long count = shopProductMappr.getCount();

        page.setRecordsTotal(count);
        page.setRecordsFiltered(count);
        List<ShopProduct> shopProducts = shopProductMappr.queryProduct(page);
        page.setData(shopProducts);
        return page;
    }
}

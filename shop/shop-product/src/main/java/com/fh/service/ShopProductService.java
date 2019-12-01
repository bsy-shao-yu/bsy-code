package com.fh.service;

import com.fh.model.ShopProduct;
import com.fh.util.PageBean;

public interface ShopProductService {

    PageBean<ShopProduct> queryProduct(PageBean<ShopProduct> page);

}

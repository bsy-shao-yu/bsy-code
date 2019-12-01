package com.fh.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.fh.model.ShopProduct;
import com.fh.util.PageBean;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface ShopProductMappr extends BaseMapper<ShopProduct> {

    Long getCount();

    List<ShopProduct> queryProduct(@Param("page") PageBean<ShopProduct> page);
}

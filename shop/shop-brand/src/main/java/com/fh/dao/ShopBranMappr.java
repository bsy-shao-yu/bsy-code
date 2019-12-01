package com.fh.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.fh.model.ShopBrand;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface ShopBranMappr extends BaseMapper<ShopBrand> {

    List<ShopBrand> queryBrandByType();

    ShopBrand getBrandIdCar(Integer id);


}

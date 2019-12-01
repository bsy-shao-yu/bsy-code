package com.fh.service.impl;

import com.fh.dao.ShopTypeMapper;
import com.fh.model.ShopType;
import com.fh.service.ShopTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("shopType")
public class ShopTypeServiceImpl implements ShopTypeService {

            @Autowired
            private ShopTypeMapper shopTypeMapper;




    @Override
    public List<Map<String, Object>> queryShopType() {

        List<ShopType> shopTypeListpList1 = shopTypeMapper.queryShopType();

        return getQueryShopType("0",shopTypeListpList1);

    }


    private List<Map<String, Object>> getQueryShopType(String pid,List<ShopType> shopTypeList){

        List<Map<String, Object>> list = new ArrayList<>();

        shopTypeList.forEach(type->{
            Map<String, Object> map = null;
            if(pid.equals(type.getPid())){
                map = new HashMap<>();
                map.put("id",type.getTid());
                map.put("pid",type.getPid());
                map.put("name",type.getTypeName());
                map.put("children",getQueryShopType(type.getTid(),shopTypeList));
            }
            if(map != null){
                list.add(map);
            }
        });

        return list;

    }



}

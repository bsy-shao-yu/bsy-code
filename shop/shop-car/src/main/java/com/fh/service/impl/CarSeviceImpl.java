package com.fh.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.fh.model.CarBean;
import com.fh.service.CarSevice;
import com.fh.util.HttpclientUtils;
import com.fh.util.ResponseServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("carService")
public class CarSeviceImpl implements CarSevice {

    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    public ResponseServer addCar(String phone, Integer id) {

       // 获取购物车ID
        String carid = (String) redisTemplate.opsForValue().get(phone+"carid");
        //根据id查询商品

        String url = "http://localhost:8094/brandId/"+id;

        String s = HttpclientUtils.doGet(url);
        JSONObject jsonObject = JSON.parseObject(s);

        JSONObject bean = JSON.parseObject(jsonObject.get("data").toString());

        CarBean carBean= new CarBean();

        carBean.setProductId(bean.getInteger("id"));
        carBean.setImgSrc(bean.getString("imgPath"));
        carBean.setProductName(bean.getString("name"));
        carBean.setPrice(bean.getBigDecimal("price"));

        if(redisTemplate.opsForHash().hasKey(carid,id)){
            CarBean car = (CarBean) redisTemplate.opsForHash().get(carid,id);
            carBean.setCount(car.getCount()+1);
        }else{
            carBean.setCount(1);
        }

        BigDecimal bigDecimal = BigDecimal.valueOf(0.00);
        BigDecimal count = new BigDecimal(carBean.getCount());
        BigDecimal subtatol = bigDecimal.add(carBean.getPrice()).multiply(count);
        carBean.setSubtotal(subtatol);
        carBean.setIsChecked(true);


        redisTemplate.opsForHash().put(carid,id,carBean);

        Long size = redisTemplate.opsForHash().size(carid);

        return ResponseServer.success(size);
    }


    @Override
    public Map<String,Object> carShow(String  phone) {

        // 获取购物车ID
        String carid = (String) redisTemplate.opsForValue().get(phone+"carid");

        //取出购物车的数据
        List<CarBean> carBeanList = redisTemplate.opsForHash().values(carid);
        BigDecimal bigDecimal = BigDecimal.valueOf(0.00);
        for (CarBean car:carBeanList){
            if(car.getIsChecked() ){
                bigDecimal = bigDecimal.add(car.getSubtotal());
            }
        }
        Map<String,Object> map = new HashMap<>();

        map.put("carBeanList",carBeanList);
        map.put("total",bigDecimal);

        return map;
    }

    @Override
    public ResponseServer addcar1(Integer productId,String  phone ) {
        // 获取购物车ID
        String carid = (String) redisTemplate.opsForValue().get(phone+"carid");

        CarBean carBean = (CarBean) redisTemplate.opsForHash().get(carid, productId);

        Integer count = carBean.getCount();
        carBean.setCount(count+1);

        redisTemplate.opsForHash().put(carid,productId,carBean);
        return ResponseServer.success();
    }

    @Override
    public ResponseServer delCar(Integer productId, String phone) {

        String carid = (String) redisTemplate.opsForValue().get(phone+"carid");

        CarBean carBean = (CarBean) redisTemplate.opsForHash().get(carid, productId);

        Integer count = carBean.getCount();

        if(count -1 ==0){
            redisTemplate.opsForHash().delete(carid,productId,carBean);
        }else{
            carBean.setCount(count-1);

            redisTemplate.opsForHash().put(carid,productId,carBean);
        }
        return ResponseServer.success();

    }

    @Override
    public ResponseServer delProduct(Integer productId, String phone) {
        String carid = (String) redisTemplate.opsForValue().get(phone+"carid");

        CarBean carBean = (CarBean) redisTemplate.opsForHash().get(carid, productId);

        redisTemplate.opsForHash().delete(carid,productId,carBean);
        return ResponseServer.success();
    }

    @Override
    public ResponseServer updateChexked(String chkvalue, String phone) {
        String carid = (String) redisTemplate.opsForValue().get(phone+"carid");

        List<CarBean> carBeanList = redisTemplate.opsForHash().values(carid);

        String[] split = chkvalue.split(",");

        for (String productId:split){
            carBeanList.forEach(car->{
                Integer aa = Integer.parseInt(productId);
                    if(car.getProductId() ==  aa){
                        car.setIsChecked(true);
                        redisTemplate.opsForHash().put(carid,aa,car);
                    }

            });
        }




        return ResponseServer.success();
    }

}

package com.fh.redis;

public interface IRedisService {

    void setShopType(String key, Object  value);

    Boolean isExistKey(String key);

    Object getStringValueKey(String key);
}

package com.fh.model;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("shoptype")
public class ShopType {


    private String tid;

    private String pid;

    private String typeName;

    private Integer isValid;

    private Integer sort_order;


}

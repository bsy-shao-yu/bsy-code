package com.fh.model;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Data
@TableName("t_shop_product")
public class ShopProduct implements Serializable {


        private Integer id;

        private Integer brand_id; //类型

        private String name;//名称

        private String subtitle;//宣传标题

        private String mainimg;//主题图片

        private String subimgs;//副图片

        private String detail;//商品描述

        private BigDecimal price;//价格

        private Integer stock;//库存

        private Integer status;//状态

        private Date createtime;//创建时间

        private Date updatetime;//修改时间



    }

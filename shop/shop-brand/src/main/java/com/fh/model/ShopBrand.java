package com.fh.model;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Data

public class ShopBrand implements Serializable {

    private Integer id;

    private String bid;

    private String name;


    private String phone; //dianhuahao


    private String shoptype;


    private String url;


    private String shoptatus;


    private Date updateDate;


    private String  tId;

    private String wangzhi;


    private String sname;

    private String imgPath;

    private BigDecimal price;



}

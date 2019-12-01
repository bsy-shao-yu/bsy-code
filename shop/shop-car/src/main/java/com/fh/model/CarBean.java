package com.fh.model;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
public class CarBean implements Serializable {


    private Integer productId;

    private String productName;


    private String  imgSrc;

    private BigDecimal price;

    private Integer count;

    private BigDecimal subtotal;

    private Boolean isChecked;

}

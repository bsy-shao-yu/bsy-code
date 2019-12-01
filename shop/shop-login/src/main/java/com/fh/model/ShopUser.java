package com.fh.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
@TableName("t_user")
public class ShopUser implements Serializable {


    @TableId(value = "id",type = IdType.AUTO)
    private Integer id;

    @TableField("name")
    private String name;
    @TableField("password")
    private String password;
    @TableField("istat")
    private Integer istat;
    @TableField("creaDate")
    private Date creaDate;
    @TableField("phone")
    private String phone;

    @TableField("endDateTime")
    private Date endDateTime;

    @TableField("carId")

    private String carId;

}

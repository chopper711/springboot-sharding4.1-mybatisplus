package com.example.demo.module.pojo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 订单实体
 */
@TableName("li_order")
@Data
public class Order implements Serializable {


    @TableField("order_sn")
    private String orderSn;

    @TableField("create_time")
    private Long createTime;

}

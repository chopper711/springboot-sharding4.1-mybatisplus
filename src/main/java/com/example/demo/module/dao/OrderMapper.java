package com.example.demo.module.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.demo.module.pojo.Order;
import org.apache.ibatis.annotations.Mapper;

/**
 * OrderMapper
 * @author liushuai
 */
@Mapper
public interface OrderMapper extends BaseMapper<Order> {
}

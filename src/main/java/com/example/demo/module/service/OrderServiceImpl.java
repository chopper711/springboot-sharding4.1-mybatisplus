package com.example.demo.module.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.demo.module.dao.OrderMapper;
import com.example.demo.module.pojo.Order;
import com.example.demo.module.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * OrderServiceImpl
 * @author liushuai
 */
@Service
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order> implements OrderService {
    @Autowired
    private OrderMapper orderMapper;
}

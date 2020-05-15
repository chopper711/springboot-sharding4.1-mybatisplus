package com.example.demo.module.controller;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo.module.pojo.Order;
import com.example.demo.module.service.OrderService;
import com.sun.tools.corba.se.idl.constExpr.Or;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * order Controller
 * 测试插入数据以及查询数据
 */
@RestController
@RequestMapping("/order")
@Api(description = "测试借口")
public class OrderController {

    @Autowired
    private OrderService orderService;


    @PostMapping("")
    public void save(Order order) {
        orderService.save(order);
    }

    /**
     * 获取一条制定筛选条件数据
     *
     * @return
     */
    @GetMapping("/between")
    public Object getById() {
        QueryWrapper<Order> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByDesc("create_time");
        queryWrapper.between("create_time", 1557821020, 1579063046);
        return orderService.page(new Page(1, 1),queryWrapper);
    }

    /**
     * 获取集合
     *
     * @return
     */
    @GetMapping("list")
    public List<Order> list() {
        List<Order> orders = orderService.list();
        return orders;
    }


}

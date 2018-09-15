package com.example.colordetails.service;

import com.example.colordetails.entity.OrderInfo;
import com.example.colordetails.entity.SellerInfo;

import java.util.List;

public interface OrderService {
    //根据品牌商id查询订单
    List<OrderInfo> findOrderByBrandId(Integer id);
    //根据借卖商id查询订单
    List<OrderInfo> findOrderBySellerId(Integer id);
    //保存订单状态
    OrderInfo addOrderInfo(OrderInfo orderInfo);
    //根据订单id查订单
    OrderInfo findOrderInfoById(Integer id);

}

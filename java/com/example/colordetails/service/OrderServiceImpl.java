package com.example.colordetails.service;

import com.example.colordetails.entity.OrderInfo;
import com.example.colordetails.entity.SellerInfo;
import com.example.colordetails.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderRepository orderRepository;
    @Override
    public List<OrderInfo> findOrderByBrandId(Integer id) {
        return orderRepository.findOrderByBrandId(id);
    }
    @Override
    public List<OrderInfo> findOrderBySellerId(Integer id) {
        return orderRepository.findOrderBySellerId(id);
    }
    @Override
    public OrderInfo addOrderInfo(OrderInfo orderInfo) {
        return orderRepository.save(orderInfo);
    }

    @Override
    public OrderInfo findOrderInfoById(Integer id) {
        return orderRepository.findById(id).get();
    }
}

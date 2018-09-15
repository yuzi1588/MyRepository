package com.example.colordetails.repository;

import com.example.colordetails.entity.OrderInfo;
import com.example.colordetails.entity.SellerInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import javax.persistence.Id;
import java.util.List;

public interface OrderRepository extends JpaRepository<OrderInfo,Integer> {

    //根据品牌商id查询订单
    @Query(value = "select * from order_info where  brand_info_id=?1",nativeQuery = true)
    List<OrderInfo> findOrderByBrandId(Integer id);
    //根据借卖商id查询订单
    @Query(value = "select * from order_info where  seller_info_id=?1",nativeQuery = true)
    List<OrderInfo> findOrderBySellerId(Integer id);
//    //更改订单状态
//    @Query(value = "update from order_info set ",nativeQuery = true)
//    List<OrderInfo> findOrderByBrandId(Integer id);
}

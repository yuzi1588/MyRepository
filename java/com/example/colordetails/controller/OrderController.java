package com.example.colordetails.controller;
import com.example.colordetails.entity.BrandInfo;
import com.example.colordetails.entity.OrderInfo;
import com.example.colordetails.entity.SellerInfo;
import com.example.colordetails.service.BrandService;
import com.example.colordetails.service.OrderService;

import com.example.colordetails.service.SellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.persistence.Id;
import java.util.List;

@Controller
public class OrderController {
    @Autowired
    private OrderService orderService;
    @Autowired
    private BrandService brandService;
    @Autowired
    private SellerService sellerService;
    //    品牌商查订单
    @GetMapping("/findOrderByBrandId/{userId}")
    public String findOrderByUserId(@PathVariable("userId") Integer userId,Model model){
        //根据session的user_id查品牌商id
        Integer BrandId=brandService.findBrandByUserId(userId).getId();
        List<OrderInfo> orderInfos=orderService.findOrderByBrandId(BrandId);
        model.addAttribute("list",orderInfos);
        return "BrandOrder";
    }
//    借卖商查订单
    @GetMapping("/findOrderBySellerId/{userId}")
    public String findOrderBySellerId(@PathVariable("userId") Integer userId,Model model){
        //根据session的user_id查借卖商id
        Integer SellerId=sellerService.findSellerByUser_Id(userId).getId();
        List<OrderInfo> orderInfos=orderService.findOrderBySellerId(SellerId);
        model.addAttribute("list",orderInfos);
        return "SellerOrder";
    }
    //品牌商：根据订单Id改变订单状态,id:订单id,改变订单状态的用户的user_id
    @GetMapping("/changeOrderStateByBrand/{orderId}/{userId}")
    public String changeOrderStateByBrand(@PathVariable("orderId") Integer orderId, @PathVariable("userId") Integer user_id) {
        //根据id查出订单
        OrderInfo orderInfo=orderService.findOrderInfoById(orderId);
        //修改订单状态为2 已发货
        orderInfo.setState(2);
        //存储进数据库
        orderService.addOrderInfo(orderInfo);
        return "redirect:/findOrderByBrandId/" + user_id;
    }
    //借卖商：根据订单Id改变订单状态,id:订单id,改变订单状态的用户的user_id
    @GetMapping("/changeOrderStateBySeller/{orderId}/{userId}")
    public String changeOrderStateBySeller(@PathVariable("orderId") Integer orderId, @PathVariable("userId") Integer user_id) {
        //根据id查出订单
        OrderInfo orderInfo=orderService.findOrderInfoById(orderId);
        //修改订单状态为1 请求发货
        orderInfo.setState(1);
        //存储进数据库
        orderService.addOrderInfo(orderInfo);
        return "redirect:/findOrderBySellerId/" + user_id;
    }












//    @GetMapping("Bo-total")
//    public String total(Model model){
//        List<OrderInfo> orderlist = orderService.getAllOrder();
//        model.addAttribute("list",orderlist);
//        return "BrandOrder";
//    }
//    @GetMapping("boadd")
//    public String boaddPage(){
//        return "BrandBoadd";
//    }
//    @PostMapping("boadd")
//    public String boadd1Page(OrderInfo orderInfo){
//        orderService.addOrderInfo(orderInfo);
//        return "redirect:/Bo-total";
//    }
//    @GetMapping("boupdate/{id}")
//    public String boupdatePage(@PathVariable("id") Integer id,Model model){
//        OrderInfo orderInfo = orderService.getOrdId(id);
//        model.addAttribute("bor",orderInfo);
//        return "BrandBoUpdate";
//    }
//    @PostMapping("boupdate")
//    public String boupdatePage1(OrderInfo orderInfo){
//        orderService.updateOrderInfo(orderInfo);
//        return  "redirect:/Bo-total";
//    }
//    @GetMapping("/bodelete/{id}")
//    public String deleteBoID(@PathVariable("id") Integer id){
//        orderService.deleteboById(id);
//        return  "redirect:/Bo-total";
//    }
}

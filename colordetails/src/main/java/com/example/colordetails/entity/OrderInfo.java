package com.example.colordetails.entity;

import javax.persistence.*;

@Entity
public class OrderInfo {

    @Id
    @GeneratedValue
    private Integer Id;
    @OneToOne
    private CustomerInfo customerInfo;
    @OneToOne
    private SellerInfo sellerInfo;
    private Float Total_Price;
    private Integer State;
    @OneToOne
    private CommodityInfo commodityInfo;
    @OneToOne
    private BrandInfo brandInfo;

    public BrandInfo getBrandInfo() {
        return brandInfo;
    }

    public void setBrandInfo(BrandInfo brandInfo) {
        this.brandInfo = brandInfo;
    }

    public CommodityInfo getCommodityInfo() {
        return commodityInfo;
    }

    public void setCommodityInfo(CommodityInfo commodityInfo) {
        this.commodityInfo = commodityInfo;
    }

    public OrderInfo() {
    }

    public Integer getId() {
        return Id;
    }

    public void setId(Integer id) {
        Id = id;
    }

    public CustomerInfo getCustomerInfo() {
        return customerInfo;
    }

    public void setCustomerInfo(CustomerInfo customerInfo) {
        this.customerInfo = customerInfo;
    }

    public SellerInfo getSellerInfo() {
        return sellerInfo;
    }

    public void setSellerInfo(SellerInfo sellerInfo) {
        this.sellerInfo = sellerInfo;
    }

    public Float getTotal_Price() {
        return Total_Price;
    }

    public void setTotal_Price(Float total_Price) {
        Total_Price = total_Price;
    }


    public Integer getState() {
        return State;
    }

    public void setState(Integer state) {
        State = state;
    }
}

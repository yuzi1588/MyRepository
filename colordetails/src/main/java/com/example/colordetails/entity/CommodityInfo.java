package com.example.colordetails.entity;

import javax.persistence.*;
import java.util.Date;

//商品信息
@Entity
public class CommodityInfo {
    @Id
    @GeneratedValue
    private Integer Id;
    private String Name;
    private Float Price;
    private Integer Number;
    private String Details;
    private Date Create_time;
    private String Image;
    private Integer Quality_Time;
    private Date Product_Time;
    private Integer State;
    @ManyToOne
    private CapacityTypeInfo capacityTypeInfo;
    @ManyToOne
    private PackagesTypeInfo packagesTypeInfo;
    @ManyToOne
    private WaterTypeInfo waterTypeInfo;
    @ManyToOne
    private BrandInfo brandInfo;

    public CommodityInfo() {
    }

    public Integer getId() {
        return Id;
    }

    public void setId(Integer id) {
        Id = id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public Float getPrice() {
        return Price;
    }

    public void setPrice(Float price) {
        Price = price;
    }

    public Integer getNumber() {
        return Number;
    }

    public void setNumber(Integer number) {
        Number = number;
    }

    public String getDetails() {
        return Details;
    }

    public void setDetails(String details) {
        Details = details;
    }

    public Date getCreate_time() {
        return Create_time;
    }

    public void setCreate_time(Date create_time) {
        Create_time = create_time;
    }

    public String getImage() {
        return Image;
    }

    public void setImage(String image) {
        Image = image;
    }

    public Integer getQuality_Time() {
        return Quality_Time;
    }

    public void setQuality_Time(Integer quality_Time) {
        Quality_Time = quality_Time;
    }

    public Date getProduct_Time() {
        return Product_Time;
    }

    public void setProduct_Time(Date product_Time) {
        Product_Time = product_Time;
    }

    public Integer getState() {
        return State;
    }

    public void setState(Integer state) {
        State = state;
    }

    public CapacityTypeInfo getCapacityTypeInfo() {
        return capacityTypeInfo;
    }

    public void setCapacityTypeInfo(CapacityTypeInfo capacityTypeInfo) {
        this.capacityTypeInfo = capacityTypeInfo;
    }

    public PackagesTypeInfo getPackagesTypeInfo() {
        return packagesTypeInfo;
    }

    public void setPackagesTypeInfo(PackagesTypeInfo packagesTypeInfo) {
        this.packagesTypeInfo = packagesTypeInfo;
    }

    public WaterTypeInfo getWaterTypeInfo() {
        return waterTypeInfo;
    }

    public void setWaterTypeInfo(WaterTypeInfo waterTypeInfo) {
        this.waterTypeInfo = waterTypeInfo;
    }

    public BrandInfo getBrandInfo() {
        return brandInfo;
    }

    public void setBrandInfo(BrandInfo brandInfo) {
        this.brandInfo = brandInfo;
    }
}

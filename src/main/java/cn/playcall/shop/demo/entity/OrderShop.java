package cn.playcall.shop.demo.entity;

import java.math.BigDecimal;

public class OrderShop {

    private Integer userId;
    private Integer userName;
    private Integer userAddress;
    private Integer saleId;
    private Integer productId;
    private String pro_name;
    private String pro_brand;
    private String pro_img;
    private BigDecimal pro_price_original;
    private Integer pro_num;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getUserName() {
        return userName;
    }

    public void setUserName(Integer userName) {
        this.userName = userName;
    }

    public Integer getUserAddress() {
        return userAddress;
    }

    public void setUserAddress(Integer userAddress) {
        this.userAddress = userAddress;
    }

    public Integer getSaleId() {
        return saleId;
    }

    public void setSaleId(Integer saleId) {
        this.saleId = saleId;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public String getPro_name() {
        return pro_name;
    }

    public void setPro_name(String pro_name) {
        this.pro_name = pro_name;
    }

    public String getPro_brand() {
        return pro_brand;
    }

    public void setPro_brand(String pro_brand) {
        this.pro_brand = pro_brand;
    }

    public String getPro_img() {
        return pro_img;
    }

    public void setPro_img(String pro_img) {
        this.pro_img = pro_img;
    }

    public BigDecimal getPro_price_original() {
        return pro_price_original;
    }

    public void setPro_price_original(BigDecimal pro_price_original) {
        this.pro_price_original = pro_price_original;
    }

    public Integer getPro_num() {
        return pro_num;
    }

    public void setPro_num(Integer pro_num) {
        this.pro_num = pro_num;
    }

    @Override
    public String toString() {
        return "OrderShop{" +
                "userId=" + userId +
                ", userName=" + userName +
                ", userAddress=" + userAddress +
                ", saleId=" + saleId +
                ", productId=" + productId +
                ", pro_name='" + pro_name + '\'' +
                ", pro_brand='" + pro_brand + '\'' +
                ", pro_img='" + pro_img + '\'' +
                ", pro_price_original=" + pro_price_original +
                ", pro_num=" + pro_num +
                '}';
    }
}

package cn.playcall.shop.demo.entity;

import javax.persistence.Entity;
import java.math.BigDecimal;


public class CartProduct {

    private Integer productId;
    private String pro_name;
    private String pro_brand;
    private String pro_img;
    private BigDecimal pro_price_low;
    private BigDecimal pro_price_high;
    private BigDecimal pro_price_original;
    private Integer pro_num;

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

    public BigDecimal getPro_price_low() {
        return pro_price_low;
    }

    public void setPro_price_low(BigDecimal pro_price_low) {
        this.pro_price_low = pro_price_low;
    }

    public BigDecimal getPro_price_high() {
        return pro_price_high;
    }

    public void setPro_price_high(BigDecimal pro_price_high) {
        this.pro_price_high = pro_price_high;
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
        return "CartProduct{" +
                "productId=" + productId +
                ", pro_name='" + pro_name + '\'' +
                ", pro_brand='" + pro_brand + '\'' +
                ", pro_img='" + pro_img + '\'' +
                ", pro_price_low=" + pro_price_low +
                ", pro_price_high=" + pro_price_high +
                ", pro_price_original=" + pro_price_original +
                ", pro_num=" + pro_num +
                '}';
    }
}

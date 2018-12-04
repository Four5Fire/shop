package cn.playcall.shop.demo.entity;

import javax.persistence.*;
import java.math.BigDecimal;

@Table(name = "t_sale")
@Entity
public class Sale {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer saleId;

    @Column(name = "user_id")
    private Integer userId;

    @Column(name = "shop_time")
    private String shopTime;

    @Column(name = "product_id")
    private Integer productId;

    @Column(name = "product_num")
    private Integer productNum;

    @Column(name = "sum_price")
    private BigDecimal sumPrice;

    @Column(name = "state")
    private Integer state;

    public Integer getSaleId() {
        return saleId;
    }

    public void setSaleId(Integer saleId) {
        this.saleId = saleId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getShopTime() {
        return shopTime;
    }

    public void setShopTime(String shopTime) {
        this.shopTime = shopTime;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public Integer getProductNum() {
        return productNum;
    }

    public void setProductNum(Integer productNum) {
        this.productNum = productNum;
    }

    public BigDecimal getSumPrice() {
        return sumPrice;
    }

    public void setSumPrice(BigDecimal sumPrice) {
        this.sumPrice = sumPrice;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    @Override
    public String toString() {
        return "Sale{" +
                "saleId=" + saleId +
                ", userId=" + userId +
                ", shopTime='" + shopTime + '\'' +
                ", productId=" + productId +
                ", productNum=" + productNum +
                ", sumPrice=" + sumPrice +
                ", state=" + state +
                '}';
    }
}

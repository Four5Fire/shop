package cn.playcall.shop.demo.entity;

import javax.persistence.*;
import java.math.BigDecimal;

@Table(name = "t_favor")
@Entity
public class Favor {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer userId;

    @Column(name = "product_id")
    private Integer productId;

    @Column(name = "pre_cost")
    private BigDecimal preCost;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public BigDecimal getPreCost() {
        return preCost;
    }

    public void setPreCost(BigDecimal preCost) {
        this.preCost = preCost;
    }

    @Override
    public String toString() {
        return "Favor{" +
                "userId=" + userId +
                ", productId=" + productId +
                ", preCost=" + preCost +
                '}';
    }
}

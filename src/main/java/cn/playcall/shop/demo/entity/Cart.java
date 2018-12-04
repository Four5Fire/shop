package cn.playcall.shop.demo.entity;

import javax.persistence.*;

@Table(name = "t_cart")
@Entity
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer userId;

    @Column(name = "product_id")
    private Integer productId;

    @Column(name = "product_num")
    private Integer productNum;

    @Column(name = "is_bought")
    private Integer isBought;

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

    public Integer getProductNum() {
        return productNum;
    }

    public void setProductNum(Integer productNum) {
        this.productNum = productNum;
    }

    public Integer getIsBought() {
        return isBought;
    }

    public void setIsBought(Integer isBought) {
        this.isBought = isBought;
    }

    @Override
    public String toString() {
        return "Cart{" +
                "userId=" + userId +
                ", productId=" + productId +
                ", productNum=" + productNum +
                ", isBought=" + isBought +
                '}';
    }
}

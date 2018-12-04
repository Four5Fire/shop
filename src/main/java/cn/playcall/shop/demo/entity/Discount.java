package cn.playcall.shop.demo.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;

@Table(name = "t_discount")
@Entity
public class Discount {

    @Id
    private Integer userLevel;

    @Column(name = "discount")
    private BigDecimal discount;

    public Integer getUserLevel() {
        return userLevel;
    }

    public void setUserLevel(Integer userLevel) {
        this.userLevel = userLevel;
    }

    public BigDecimal getDiscount() {
        return discount;
    }

    public void setDiscount(BigDecimal discount) {
        this.discount = discount;
    }

    @Override
    public String toString() {
        return "Discount{" +
                "userLevel=" + userLevel +
                ", discount=" + discount +
                '}';
    }
}

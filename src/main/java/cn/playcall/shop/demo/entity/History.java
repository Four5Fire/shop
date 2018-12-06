package cn.playcall.shop.demo.entity;

import javax.persistence.*;
import java.math.BigDecimal;

@Table(name = "t_history")
@Entity
public class History {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer historyId;

    @Column(name = "product_id")
    private Integer productId;

    @Column(name = "history")
    private String history;

    @Column(name = "price")
    private BigDecimal price;

    public Integer getHistoryId() {
        return historyId;
    }

    public void setHistoryId(Integer historyId) {
        this.historyId = historyId;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public String getHistory() {
        return history;
    }

    public void setHistory(String history) {
        this.history = history;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "History{" +
                "historyId=" + historyId +
                ", productId=" + productId +
                ", history='" + history + '\'' +
                ", price=" + price +
                '}';
    }
}

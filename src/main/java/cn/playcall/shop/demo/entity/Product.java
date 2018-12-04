package cn.playcall.shop.demo.entity;

import javax.persistence.*;
import java.math.BigDecimal;

@Table(name = "t_product")
@Entity
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer productId;

    @Column(name = "product_name")
    private String productName;

    @Column(name = "shop_id")
    private Integer shopId;

    @Column(name = "item_id")
    private Integer itemId;

    @Column(name = "brand")
    private String brand;

    @Column(name = "price_original")
    private BigDecimal priceOriginal;

    @Column(name = "price_high")
    private BigDecimal priceHigh;

    @Column(name = "price_low")
    private BigDecimal priceLow;

    @Column(name = "pic")
    private String pic;

    @Column(name = "intro")
    private String intro;

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Integer getShopId() {
        return shopId;
    }

    public void setShopId(Integer shopId) {
        this.shopId = shopId;
    }

    public Integer getItemId() {
        return itemId;
    }

    public void setItemId(Integer itemId) {
        this.itemId = itemId;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public BigDecimal getPriceOriginal() {
        return priceOriginal;
    }

    public void setPriceOriginal(BigDecimal priceOriginal) {
        this.priceOriginal = priceOriginal;
    }

    public BigDecimal getPriceHigh() {
        return priceHigh;
    }

    public void setPriceHigh(BigDecimal priceHigh) {
        this.priceHigh = priceHigh;
    }

    public BigDecimal getPriceLow() {
        return priceLow;
    }

    public void setPriceLow(BigDecimal priceLow) {
        this.priceLow = priceLow;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public String getIntro() {
        return intro;
    }

    public void setIntro(String intro) {
        this.intro = intro;
    }

    @Override
    public String toString() {
        return "Product{" +
                "productId=" + productId +
                ", productName='" + productName + '\'' +
                ", shopId=" + shopId +
                ", itemId=" + itemId +
                ", brand='" + brand + '\'' +
                ", priceOriginal=" + priceOriginal +
                ", priceHigh=" + priceHigh +
                ", priceLow=" + priceLow +
                ", pic='" + pic + '\'' +
                ", intro='" + intro + '\'' +
                '}';
    }
}

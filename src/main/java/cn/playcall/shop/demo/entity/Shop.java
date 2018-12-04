package cn.playcall.shop.demo.entity;

import javax.persistence.*;
import java.math.BigDecimal;

@Table(name = "t_shop")
@Entity
public class Shop {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer shopId;

    @Column(name = "shop_name")
    private String shopName;

    @Column(name = "shop_pwd")
    private String shopPwd;

    @Column(name = "tele")
    private String tele;

    @Column(name = "star")
    private BigDecimal star;

    @Column(name = "found_date")
    private String foundDate;

    @Column(name = "shop_sold")
    private Integer shopSold;

    @Column(name = "shop_pic")
    private String shopPic;

    public String getTele() {
        return tele;
    }

    public void setTele(String tele) {
        this.tele = tele;
    }

    public String getShopPwd() {
        return shopPwd;
    }

    public void setShopPwd(String shopPwd) {
        this.shopPwd = shopPwd;
    }

    public Integer getShopId() {
        return shopId;
    }

    public void setShopId(Integer shopId) {
        this.shopId = shopId;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public BigDecimal getStar() {
        return star;
    }

    public void setStar(BigDecimal star) {
        this.star = star;
    }

    public void setShopSold(Integer shopSold) {
        this.shopSold = shopSold;
    }

    public String getFoundDate() {
        return foundDate;
    }

    public void setFoundDate(String foundDate) {
        this.foundDate = foundDate;
    }

    public String getShopPic() {
        return shopPic;
    }

    public void setShopPic(String shopPic) {
        this.shopPic = shopPic;
    }

    @Override
    public String toString() {
        return "Shop{" +
                "shopId=" + shopId +
                ", shopName='" + shopName + '\'' +
                ", shopPwd='" + shopPwd + '\'' +
                ", tele='" + tele + '\'' +
                ", star=" + star +
                ", foundDate='" + foundDate + '\'' +
                ", shopSold=" + shopSold +
                ", shopPic='" + shopPic + '\'' +
                '}';
    }
}

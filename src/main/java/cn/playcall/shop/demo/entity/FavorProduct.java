package cn.playcall.shop.demo.entity;

import java.math.BigDecimal;

public class FavorProduct {

    private Integer favorId;
    private String productName;
    private BigDecimal productPrice;
    private String productPic;
    private String itemType;
    private String shopName;
    private String productApi;

    public Integer getFavorId() {
        return favorId;
    }

    public void setFavorId(Integer favorId) {
        this.favorId = favorId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public BigDecimal getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(BigDecimal productPrice) {
        this.productPrice = productPrice;
    }

    public String getItemType() {
        return itemType;
    }

    public void setItemType(String itemType) {
        this.itemType = itemType;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public String getProductPic() {
        return productPic;
    }

    public void setProductPic(String productPic) {
        this.productPic = productPic;
    }

    public String getProductApi() {
        return productApi;
    }

    public void setProductApi(String productApi) {
        this.productApi = productApi;
    }

    @Override
    public String toString() {
        return "FavorProduct{" +
                "favorId=" + favorId +
                ", productName='" + productName + '\'' +
                ", productPrice=" + productPrice +
                ", productPic='" + productPic + '\'' +
                ", itemType='" + itemType + '\'' +
                ", shopName='" + shopName + '\'' +
                ", productApi='" + productApi + '\'' +
                '}';
    }
}

package cn.playcall.shop.demo.entity;

import java.math.BigDecimal;

public class OrderShop {

    private String userName;
    private String userAddress;
    private Integer saleId;
    private String pro_name;
    private String pro_brand;
    private String pro_img;
    private BigDecimal pro_price_original;
    private BigDecimal pro_price_sum;
    private Integer pro_num;
    private String pro_status;
    private String feedback;
    private String feedbackApi;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public BigDecimal getPro_price_sum() {
        return pro_price_sum;
    }

    public void setPro_price_sum(BigDecimal pro_price_sum) {
        this.pro_price_sum = pro_price_sum;
    }

    public String getUserAddress() {
        return userAddress;
    }

    public void setUserAddress(String userAddress) {
        this.userAddress = userAddress;
    }

    public Integer getSaleId() {
        return saleId;
    }

    public void setSaleId(Integer saleId) {
        this.saleId = saleId;
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

    public String getPro_status() {
        return pro_status;
    }

    public void setPro_status(String pro_status) {
        this.pro_status = pro_status;
    }

    public String getFeedbackApi() {
        return feedbackApi;
    }

    public void setFeedbackApi(String feedbackApi) {
        this.feedbackApi = feedbackApi;
    }

    public String getFeedback() {
        return feedback;
    }

    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }

    @Override
    public String toString() {
        return "OrderShop{" +
                "userName='" + userName + '\'' +
                ", userAddress='" + userAddress + '\'' +
                ", saleId=" + saleId +
                ", pro_name='" + pro_name + '\'' +
                ", pro_brand='" + pro_brand + '\'' +
                ", pro_img='" + pro_img + '\'' +
                ", pro_price_original=" + pro_price_original +
                ", pro_price_sum=" + pro_price_sum +
                ", pro_num=" + pro_num +
                ", pro_status='" + pro_status + '\'' +
                ", feedback='" + feedback + '\'' +
                ", feedbackApi='" + feedbackApi + '\'' +
                '}';
    }
}

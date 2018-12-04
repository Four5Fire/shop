package cn.playcall.shop.demo.entity;

import javax.persistence.*;

@Table(name = "t_comment")
@Entity
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer formId;

    @Column(name = "sale_id")
    private Integer saleId;

    @Column(name = "star_togive")
    private Integer starTogive;

    @Column(name = "feedback")
    private String feedBack;

    public Integer getFormId() {
        return formId;
    }

    public void setFormId(Integer formId) {
        this.formId = formId;
    }

    public Integer getSaleId() {
        return saleId;
    }

    public void setSaleId(Integer saleId) {
        this.saleId = saleId;
    }

    public Integer getStarTogive() {
        return starTogive;
    }

    public void setStarTogive(Integer starTogive) {
        this.starTogive = starTogive;
    }

    public String getFeedBack() {
        return feedBack;
    }

    public void setFeedBack(String feedBack) {
        this.feedBack = feedBack;
    }

    @Override
    public String toString() {
        return "comment{" +
                "formId=" + formId +
                ", saleId=" + saleId +
                ", starTogive=" + starTogive +
                ", feedBack='" + feedBack + '\'' +
                '}';
    }
}

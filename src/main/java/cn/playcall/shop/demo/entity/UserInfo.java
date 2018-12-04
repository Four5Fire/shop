package cn.playcall.shop.demo.entity;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;

@Table(name = "t_user")
@Entity
public class UserInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer userId;
    @Column(name = "user_pwd")
    private String userpwd;
    @Column(name = "addr")
    private String addr;
    @Column(name = "regis_date")
    private String regisDate;
    @Column(name = "tele")
    private String tele;
    @Column(name = "gender")
    private String gender;
    @Column(name = "user_name")
    private String userName;
    @Column(name = "user_score")
    private Integer userScore;
    @Column(name = "user_level")
    private Integer userLevel;
    @Column(name = "user_pic")
    private String userPic;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUserpwd() {
        return userpwd;
    }

    public void setUserpwd(String userpwd) {
        this.userpwd = userpwd;
    }

    public String getAddr() {
        return addr;
    }

    public void setAddr(String addr) {
        this.addr = addr;
    }

    public String getRegisDate() {
        return regisDate;
    }

    public void setRegisDate(String regisDate) {
        this.regisDate = regisDate;
    }

    public String getTele() {
        return tele;
    }

    public void setTele(String tele) {
        this.tele = tele;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Integer getUserScore() {
        return userScore;
    }

    public void setUserScore(Integer userScore) {
        this.userScore = userScore;
    }

    public Integer getUserLevel() {
        return userLevel;
    }

    public void setUserLevel(Integer userLevel) {
        this.userLevel = userLevel;
    }

    public String getUserPic() {
        return userPic;
    }

    public void setUserPic(String userPic) {
        this.userPic = userPic;
    }

    @Override
    public String toString() {
        return "UserInfo{" +
                "userId=" + userId +
                ", userpwd='" + userpwd + '\'' +
                ", addr='" + addr + '\'' +
                ", regisDate='" + regisDate + '\'' +
                ", tele='" + tele + '\'' +
                ", gender='" + gender + '\'' +
                ", userName='" + userName + '\'' +
                ", userScore=" + userScore +
                ", userLevel=" + userLevel +
                ", userPic='" + userPic + '\'' +
                '}';
    }
}

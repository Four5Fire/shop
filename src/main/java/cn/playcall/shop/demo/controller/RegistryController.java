package cn.playcall.shop.demo.controller;

import cn.playcall.shop.demo.dao.ShopDao;
import cn.playcall.shop.demo.dao.UserDao;
import cn.playcall.shop.demo.entity.Shop;
import cn.playcall.shop.demo.entity.UserInfo;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@CrossOrigin
@Controller
@RequestMapping(value = "/shop/**")
public class RegistryController {

    @Autowired
    private UserDao userDao;

    @Autowired
    private ShopDao shopDao;

    @RequestMapping(value = "/registryCheck",method = RequestMethod.POST)
    public ResponseEntity<JSONObject> registryCheck(HttpServletRequest request, @RequestBody JSONObject registryJson){
        JSONObject resultJson = new JSONObject();
        int type = Integer.parseInt(registryJson.getString("user_type"));
        UserInfo userInfo;
        Shop shopUser;
        if (type == 1){
            userInfo = userDao.findByTele(registryJson.getString("tele"));
            if (!isMatchTele(registryJson.getString("tele"))){
                resultJson.put("code","000003");
                resultJson.put("desc","请输入正确的手机号");
                return new ResponseEntity<JSONObject>(resultJson, HttpStatus.OK);
            }
            if (userInfo != null){
                resultJson.put("code","000003");
                resultJson.put("desc","手机号已被注册，请重新注册");
                return new ResponseEntity<JSONObject>(resultJson, HttpStatus.OK);
            }
            userInfo = new UserInfo();
            userInfo.setUserName(registryJson.getString("user_name"));
            userInfo.setUserpwd(registryJson.getString("user_pwd"));
            userInfo.setTele(registryJson.getString("tele"));
            userInfo.setGender("男");
            userInfo.setUserScore(0);
            userInfo.setUserLevel(1);
            userInfo.setRegisDate(getCutTime());
            userInfo = userDao.save(userInfo);
        }
        else if (type == 2){
            shopUser = shopDao.findByTele(registryJson.getString("tele"));
            if (shopUser != null){
                resultJson.put("code","000003");
                resultJson.put("desc","手机号已被注册，请重新注册");
                return new ResponseEntity<JSONObject>(resultJson, HttpStatus.OK);
            }
            shopUser = new Shop();
            shopUser.setShopName(registryJson.getString("user_name"));
            shopUser.setShopPwd(registryJson.getString("user_pwd"));
            shopUser.setTele(registryJson.getString("tele"));
            shopUser.setStar(new BigDecimal(4.0));
            shopUser.setShopSold(0);
            shopUser.setFoundDate(getCutTime());
            shopDao.save(shopUser);
            System.out.println(shopUser);
        }
        resultJson.put("code","000002");
        resultJson.put("desc","注册成功");
        return new ResponseEntity<JSONObject>(resultJson, HttpStatus.OK);
    }

    @RequestMapping(value = "/registry")
    public String registry(){

        return "registry";
    }

    private String getCutTime(){
        Date date = new Date();//获得系统时间.
        String dateStr = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date);
        return dateStr;
    }

    private boolean isMatchTele(String tele){
        String regex = "^((13[0-9])|(14[5,7,9])|(15([0-3]|[5-9]))|(166)|(17[0,1,3,5,6,7,8])|(18[0-9])|(19[8|9]))\\d{8}$";
        if (tele.length() != 11){
            return false;
        }
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(tele);
        boolean isMatch = m.matches();
        return isMatch;
    }
}

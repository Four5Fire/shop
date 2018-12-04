package cn.playcall.shop.demo.controller;


import cn.playcall.shop.demo.dao.ShopDao;
import cn.playcall.shop.demo.dao.UserDao;
import cn.playcall.shop.demo.entity.Shop;
import cn.playcall.shop.demo.entity.UserInfo;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@CrossOrigin
@Controller
@RequestMapping(value = "/shop/**")
public class LoginController {

    @Autowired
    private UserDao userDao;

    @Autowired
    private ShopDao shopDao;

    @RequestMapping(value = "/loginCheck" ,method = RequestMethod.POST)
    public ResponseEntity<JSONObject> loginCheck(HttpServletRequest request, @RequestBody JSONObject loginJson){
        JSONObject resultJson = new JSONObject();
        int type = Integer.parseInt(loginJson.getString("user_type"));
        UserInfo userInfo;
        Shop shopUser;
        HttpSession session = request.getSession();
        if (type == 1){
            userInfo = userDao.findByTele(loginJson.getString("tele"));
            if (userInfo == null){
                resultJson.put("code","000001");
                resultJson.put("desc","该手机号未被注册");
                return new ResponseEntity<JSONObject>(resultJson,HttpStatus.OK);
            }
            userInfo = userDao.findByTeleAndUserpwd(loginJson.getString("tele"),loginJson.getString("user_pwd"));
            if (userInfo == null){
                resultJson.put("code","000002");
                resultJson.put("desc","用户名或密码错误");
                return new ResponseEntity<JSONObject>(resultJson, HttpStatus.OK);
            }
            session.setAttribute("type", "1");
            session.setAttribute("UserInfo",userInfo);
        }
        else if (type == 2){
            shopUser = shopDao.findByTele(loginJson.getString("tele"));
            if (shopUser == null){
                resultJson.put("code","000001");
                resultJson.put("desc","该手机号未被注册");
                return new ResponseEntity<JSONObject>(resultJson,HttpStatus.OK);
            }
            shopUser = shopDao.findByTeleAndAndShopPwd(loginJson.getString("tele"),loginJson.getString("user_pwd"));
            if (shopUser == null){
                resultJson.put("code","000002");
                resultJson.put("desc","用户名或密码错误");
                return new ResponseEntity<JSONObject>(resultJson, HttpStatus.OK);
            }
            session.setAttribute("type","2");
            session.setAttribute("ShopUser",shopUser);
        }

        resultJson.put("code","000000");
        resultJson.put("desc","登录成功");
        return new ResponseEntity<JSONObject>(resultJson,HttpStatus.OK);
    }


    @RequestMapping(value = "/login")
    public String login(){
        return "login";
    }

}

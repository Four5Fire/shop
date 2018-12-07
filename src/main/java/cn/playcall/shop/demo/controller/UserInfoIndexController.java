package cn.playcall.shop.demo.controller;

import cn.playcall.shop.demo.dao.ItemDao;
import cn.playcall.shop.demo.entity.Item;
import cn.playcall.shop.demo.entity.UserInfo;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@CrossOrigin
@Controller
@RequestMapping(value = "/shop/**")
public class UserInfoIndexController {

    @Autowired
    private ItemDao itemDao;

    @RequestMapping(value = "/userinfo")
    public String userInfoIndex(HttpServletRequest request,Model model){
        HttpSession session = request.getSession();
        if (session.getAttribute("type") == null){
            session.setAttribute("type","0");
            model.addAttribute("user","nologin");
        }
        else {
            String type = (String) session.getAttribute("type");
            if (type.equals("1")){
                model.addAttribute("user","user");
            }
            else if (type.equals("2")){
                model.addAttribute("user","shopUser");
            }else {
                model.addAttribute("user","nologin");
            }
        }
        UserInfo userInfo = (UserInfo) session.getAttribute("UserInfo");
        model.addAttribute("regisDate",userInfo.getRegisDate());
        model.addAttribute("tele",userInfo.getTele());
        model.addAttribute("address",userInfo.getAddr());
        model.addAttribute("score",userInfo.getUserScore());
        model.addAttribute("level",userInfo.getUserLevel());
        model.addAttribute("search","search");
        model.addAttribute("ulList","ulList");
        model.addAttribute("userName",userInfo.getUserName());
        model.addAttribute("userGender",userInfo.getGender());
        List<Item> itemList = itemDao.findAll();
        model.addAttribute("itemList",itemList);
        model.addAttribute("productsApi","http://127.0.0.1:7000/shop/productsIndex/");
        model.addAttribute("bottomInfo","bottomInfo");
        model.addAttribute("pic",userInfo.getUserPic());
        return "userinfo";
    }

}

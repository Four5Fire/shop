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
        UserInfo userInfo = (UserInfo) session.getAttribute("UserInfo");
        model.addAttribute("regisDate",userInfo.getRegisDate());
        model.addAttribute("tele",userInfo.getTele());
        model.addAttribute("address",userInfo.getAddr());
        model.addAttribute("score",userInfo.getUserScore());
        model.addAttribute("level",userInfo.getUserLevel());
        model.addAttribute("user","user");
        model.addAttribute("search","search");
        model.addAttribute("ulList","ulList");
        List<Item> itemList = itemDao.findAll();
        model.addAttribute("itemList",itemList);
        model.addAttribute("bottomInfo","bottomInfo");
        model.addAttribute("pic",userInfo.getUserPic());
        return "userinfo";
    }

}

package cn.playcall.shop.demo.controller;

import cn.playcall.shop.demo.dao.ItemDao;
import cn.playcall.shop.demo.entity.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@CrossOrigin
@Controller
@RequestMapping(value = "/shop/**")
public class IndexController {

    @Autowired
    private ItemDao itemDao;

    @RequestMapping(value = "/index")
    public String index(Model modle, HttpServletRequest request){
        HttpSession session = request.getSession();
        if (session.getAttribute("type") == null){
            session.setAttribute("type","0");
            modle.addAttribute("user","nologin");
        }
        else {
            String type = (String) session.getAttribute("type");
            if (type.equals("1")){
                modle.addAttribute("user","user");
            }
            else if (type.equals("2")){
                modle.addAttribute("user","shopUser");
            }else {
                modle.addAttribute("user","nologin");
            }
        }
        modle.addAttribute("ulList","ulList");
        modle.addAttribute("search","search");
        modle.addAttribute("bottomInfo","bottomInfo");
        List<Item> itemList = itemDao.findAll();
        modle.addAttribute("itemList",itemList);
        modle.addAttribute("productsApi","http://127.0.0.1:7000/shop/productsIndex/");
        return "index";
    }
}

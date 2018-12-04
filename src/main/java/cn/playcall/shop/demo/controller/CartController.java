package cn.playcall.shop.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@CrossOrigin
@Controller
@RequestMapping(value = "/shop/**")
public class CartController {

    @RequestMapping(value = "/cart")
    public String cartIndex(HttpServletRequest request, Model model){
        model.addAttribute("user","user");
        model.addAttribute("bottomInfo","bottomInfo");
        return "cart";
    }

}

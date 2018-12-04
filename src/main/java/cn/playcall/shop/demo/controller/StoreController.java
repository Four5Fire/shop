package cn.playcall.shop.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@CrossOrigin
@Controller
@RequestMapping(value = "/shop/**")
public class StoreController {

    @RequestMapping(value = "/store")
    public String storeIndex(HttpServletRequest request, Model model){
        HttpSession session = request.getSession();
        model.addAttribute("user","user");
        return "product_store";
    }
}

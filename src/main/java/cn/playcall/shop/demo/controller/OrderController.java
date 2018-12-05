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
public class OrderController {

    @RequestMapping(value = "/orderClient")
    public String orderClientIndex(HttpServletRequest request, Model model){
        HttpSession session = request.getSession();
        model.addAttribute("user","user");
        model.addAttribute("bottomInfo","bottomInfo");
        return "order_client";
    }

    @RequestMapping(value = "/orderStore")
    public String orderStoreIndex(HttpServletRequest request,Model model){
        return "order_store";
    }

}

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
public class LogoutController {

    @RequestMapping(value = "/logout")
    public String Logout(HttpServletRequest request, Model model){
        HttpSession session = request.getSession();
        session.invalidate();
        model.addAttribute("user","nologin");
        model.addAttribute("type","0");
        return "redirect:index";
    }
}

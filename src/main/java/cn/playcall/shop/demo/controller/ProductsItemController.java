package cn.playcall.shop.demo.controller;

import cn.playcall.shop.demo.dao.ItemDao;
import cn.playcall.shop.demo.dao.ProductDao;
import cn.playcall.shop.demo.entity.Item;
import cn.playcall.shop.demo.entity.Product;
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
public class ProductsItemController {

    @Autowired
    private ItemDao itemDao;

    @Autowired
    private ProductDao productDao;

    @RequestMapping(value = "/productsIndex/{itemId}")
    public String productsItemIndex(HttpServletRequest request, Model model, @PathVariable String itemId){
        HttpSession session = request.getSession();
        int type = Integer.parseInt((String) session.getAttribute("type"));
        switch (type){
            case 0:
                model.addAttribute("user","nologin");
                break;
            case 1:
                model.addAttribute("user","user");
                break;
            case 2:
                model.addAttribute("user","shopUser");
                break;
        }
        model.addAttribute("addCart","添加至购物车");
        model.addAttribute("urlApi","http://120.79.70.13:7000/shop/cartAddProduct/");
        session.setAttribute("item", itemId);
        model.addAttribute("search","search");
        model.addAttribute("ulList","ulList");
        List<Item> itemList = itemDao.findAll();
        model.addAttribute("itemList",itemList);
        model.addAttribute("bottomInfo","bottomInfo");
        List<Product> productList = productDao.findAllByItemId(Integer.parseInt(itemId));
        model.addAttribute("productList",productList);

        model.addAttribute("productsApi","http://120.79.70.13:7000/shop/productsIndex/");
        return "productsItem";

    }
}

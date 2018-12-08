package cn.playcall.shop.demo.controller;

import cn.playcall.shop.demo.dao.ItemDao;
import cn.playcall.shop.demo.dao.ProductDao;
import cn.playcall.shop.demo.entity.Item;
import cn.playcall.shop.demo.entity.Product;
import cn.playcall.shop.demo.entity.Shop;
import org.springframework.beans.factory.annotation.Autowired;
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
public class StoreController {

    @Autowired
    private ItemDao itemDao;

    @Autowired
    private ProductDao productDao;

    @RequestMapping(value = "/store")
    public String storeIndex(HttpServletRequest request, Model model){
        HttpSession session = request.getSession();
        Shop shop = (Shop) session.getAttribute("ShopUser");
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
        List<Product> productList = productDao.findAllByShopId(shop.getShopId());
        model.addAttribute("ulList","ulList");
        List<Item> itemList = itemDao.findAll();
        model.addAttribute("itemList",itemList);
        model.addAttribute("productsApi","http://120.79.70.13:7000/shop/productsIndex/");
        model.addAttribute("search","search");
        model.addAttribute("bottomInfo","bottomInfo");
        model.addAttribute("store","store");
        model.addAttribute("productList",productList);
        model.addAttribute("shopName",shop.getShopName());
        model.addAttribute("shopStar",shop.getStar());
        model.addAttribute("shopFound",shop.getFoundDate());
        return "product_store";
    }
}

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
        List<Product> productList = productDao.findAllByShopId(shop.getShopId());
        model.addAttribute("ulList","ulList");
        List<Item> itemList = itemDao.findAll();
        model.addAttribute("itemList",itemList);
        model.addAttribute("productsApi","http://127.0.0.1:7000/shop");
        model.addAttribute("user","shopUser");
        model.addAttribute("search","search");
        model.addAttribute("bottomInfo","bottomInfo");
        model.addAttribute("store","store");

        model.addAttribute("productList",productList);
        model.addAttribute("shopName",shop.getShopName());
        model.addAttribute("shopStar",shop.getStar());
        model.addAttribute("shopFound",shop.getFoundDate());
        model.addAttribute("addCart","修改商品信息");
        return "product_store";
    }
}

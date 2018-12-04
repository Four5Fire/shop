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
public class ProductsItem {

    @Autowired
    private ItemDao itemDao;

    @Autowired
    private ProductDao productDao;

    @RequestMapping(value = "/productsIndex/{itemId}")
    public String productsItemIndex(HttpServletRequest request, Model model, @PathVariable String itemId){
        System.out.println(itemId);
        HttpSession session = request.getSession();
        model.addAttribute("user","user");
        model.addAttribute("bottomInfo","bottomInfo");
        List<Item> itemList = itemDao.findAll();
        model.addAttribute("itemList",itemList);

        List<Product> productList = productDao.findAllByItemId(Integer.parseInt(itemId));
        for (Product p:productList) {
            System.out.println(p.getProductName());
        }
        model.addAttribute("productList",productList);
        model.addAttribute("productsApi","http://127.0.0.1:7000/shop/productDetail/");
        return "productsItem";

    }
}

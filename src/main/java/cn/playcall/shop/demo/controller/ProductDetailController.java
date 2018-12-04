package cn.playcall.shop.demo.controller;

import cn.playcall.shop.demo.dao.ItemDao;
import cn.playcall.shop.demo.dao.ProductDao;
import cn.playcall.shop.demo.dao.SaleDao;
import cn.playcall.shop.demo.entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@CrossOrigin
@Controller
@RequestMapping(value = "/shop/**")
public class ProductDetailController {

    @Autowired
    private ProductDao productDao;

    @Autowired
    private ItemDao itemDao;

    @Autowired
    private SaleDao saleDao;

    @RequestMapping(value = "/productDetail/{productId}")
    public String productDetailIndex(HttpServletRequest request, Model model, @PathVariable String productId){
        model.addAttribute("user","user");
        Product product = productDao.findByProductId(Integer.parseInt(productId));
        model.addAttribute("productPic",product.getPic());
        model.addAttribute("productName",product.getProductName());
        model.addAttribute("productIntro",product.getIntro());
        model.addAttribute("item",itemDao.findByItemId(product.getItemId()).getItemName());
        model.addAttribute("brand",product.getBrand());
        model.addAttribute("productCount", saleDao.countAllByProductId(Integer.parseInt(productId)));
        model.addAttribute("productPrice",product.getPriceOriginal());
        return "productDetail_Client";
    }
}

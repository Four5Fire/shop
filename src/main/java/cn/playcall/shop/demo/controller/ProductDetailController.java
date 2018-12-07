package cn.playcall.shop.demo.controller;

import cn.playcall.shop.demo.dao.HistoryDao;
import cn.playcall.shop.demo.dao.ItemDao;
import cn.playcall.shop.demo.dao.ProductDao;
import cn.playcall.shop.demo.dao.SaleDao;
import cn.playcall.shop.demo.entity.History;
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
public class ProductDetailController {

    @Autowired
    private ProductDao productDao;

    @Autowired
    private ItemDao itemDao;

    @Autowired
    private SaleDao saleDao;

    @Autowired
    private HistoryDao historyDao;

    @RequestMapping(value = "/productDetail/{productId}")
    public String productDetailIndex(HttpServletRequest request, Model model, @PathVariable String productId){
        HttpSession session = request.getSession();
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
        model.addAttribute("search","search");
        List<Item> itemList = itemDao.findAll();
        model.addAttribute("ulList","ulList");
        model.addAttribute("itemList",itemList);
        Product product = productDao.findByProductId(Integer.parseInt(productId));
        model.addAttribute("productId",product.getProductId());
        model.addAttribute("productPic",product.getPic());
        model.addAttribute("productName",product.getProductName());
        model.addAttribute("productIntro",product.getIntro());
        model.addAttribute("item",itemDao.findByItemId(product.getItemId()).getItemName());
        model.addAttribute("brand",product.getBrand());
        model.addAttribute("productCount", saleDao.countAllByProductId(Integer.parseInt(productId)));
        model.addAttribute("productPrice",product.getPriceOriginal());
        model.addAttribute("bottomInfo","bottomInfo");
        model.addAttribute("productsApi","http://120.79.70.13:7000/shop/productsIndex/");
        List<History> historyList = historyDao.findAllByProductId(product.getProductId());
        model.addAttribute("historyList",historyList);

        return "productDetail_Client";
    }
}

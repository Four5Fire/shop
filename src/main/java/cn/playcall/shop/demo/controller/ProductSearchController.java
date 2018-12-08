package cn.playcall.shop.demo.controller;

import cn.playcall.shop.demo.dao.ItemDao;
import cn.playcall.shop.demo.dao.ProductDao;
import cn.playcall.shop.demo.entity.Item;
import cn.playcall.shop.demo.entity.Product;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@CrossOrigin
@Controller
@RequestMapping(value = "/shop/**")
public class ProductSearchController {

    @Autowired
    private ItemDao itemDao;

    @Autowired
    private ProductDao productDao;

    @RequestMapping(value = "/searchProduct")
    public ResponseEntity<JSONObject> searchProduct(HttpServletRequest request, @RequestBody JSONObject searchProductJson){
        JSONObject resultJson = new JSONObject();
        String productName = searchProductJson.getString("productName");
        HttpSession session = request.getSession();
        session.setAttribute("searchProductName",productName);
        return new ResponseEntity<JSONObject>(resultJson, HttpStatus.OK);
    }

    @RequestMapping(value = "/searchIndex")
    public String searchIndex(HttpServletRequest request, Model model){
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
        model.addAttribute("addCart","添加至购物车");
        model.addAttribute("urlApi","http://120.79.70.13:7000/shop/cartAddProduct/");
        model.addAttribute("ulList","ulList");
        model.addAttribute("search","search");
        model.addAttribute("bottomInfo","bottomInfo");
        List<Item> itemList = itemDao.findAll();
        model.addAttribute("itemList",itemList);
        model.addAttribute("productsApi","http://120.79.70.13:7000/shop/productsIndex/");

        List<Product> productList = productDao.findAllByProductNameLike("%"+(String) session.getAttribute("searchProductName")+"%");

        model.addAttribute("result",session.getAttribute("searchProductName"));

        model.addAttribute("productList",productList);

        return "search";
    }
}

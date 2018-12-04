package cn.playcall.shop.demo.controller;

import cn.playcall.shop.demo.dao.ProductDao;
import cn.playcall.shop.demo.entity.Product;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@CrossOrigin
@Controller
@RequestMapping(value = "/shop/**")
public class ProductSearchController {

    @Autowired
    private ProductDao productDao;

    @RequestMapping(value = "/searchProduct")
    public String searchProduct(HttpServletRequest request, @RequestBody JSONObject searchProductJson){
        String productName = searchProductJson.getString("searchProduct");
        List<Product> listProduct = productDao.findAllByProductName(productName);
        return "searchProduct";
    }
}

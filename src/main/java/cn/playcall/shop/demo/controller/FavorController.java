package cn.playcall.shop.demo.controller;

import cn.playcall.shop.demo.dao.FavorDao;
import cn.playcall.shop.demo.dao.ItemDao;
import cn.playcall.shop.demo.dao.ProductDao;
import cn.playcall.shop.demo.dao.ShopDao;
import cn.playcall.shop.demo.entity.*;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@CrossOrigin
@Controller
@RequestMapping(value = "/shop/**")
public class FavorController {

    @Autowired
    private ItemDao itemDao;

    @Autowired
    private FavorDao favorDao;

    @Autowired
    private ProductDao productDao;

    @Autowired
    private ShopDao shopDao;

    @RequestMapping(value = "/favorIndex")
    public String favorIndex(HttpServletRequest request, Model model){
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

        UserInfo userInfo = (UserInfo) session.getAttribute("UserInfo");
        model.addAttribute("search","search");
        model.addAttribute("ulList","ulList");
        List<Item> itemList = itemDao.findAll();
        model.addAttribute("itemList",itemList);
        model.addAttribute("productsApi","http://127.0.0.1:7000/shop/productsIndex/");

        List<Favor> favorList = favorDao.findAllByUserId(userInfo.getUserId());

        ArrayList<FavorProduct> favorProductArrayList = new ArrayList<>();

        for (Favor favor:favorList) {
            Product product = productDao.findByProductId(favor.getProductId());
            Shop shop = shopDao.findByShopId(product.getShopId());
            Item item = itemDao.findByItemId(product.getItemId());
            FavorProduct favorProduct = new FavorProduct();
            favorProduct.setFavorId(favor.getFavorId());
            favorProduct.setProductName(product.getProductName());
            favorProduct.setItemType(item.getItemName());
            favorProduct.setProductPrice(favor.getPreCost());
            favorProduct.setShopName(shop.getShopName());
            favorProduct.setProductPic(product.getPic());
            favorProduct.setProductApi("http://127.0.0.1:7000/shop/productDetail/"+product.getProductId());
            favorProductArrayList.add(favorProduct);
        }

        model.addAttribute("productList",favorProductArrayList);

        model.addAttribute("bottomInfo","bottomInfo");
        return "favor";
    }

    @RequestMapping(value = "/addFavor/{productId}")
    public ResponseEntity<JSONObject> addFavor(HttpServletRequest request, @PathVariable String productId){
        JSONObject resultJson = new JSONObject();
        HttpSession session = request.getSession();
        if (session.getAttribute("type") == null){
            session.setAttribute("type","0");
            resultJson.put("desc","请登录");
            return new ResponseEntity<JSONObject>(resultJson, HttpStatus.OK);
        }
        else {
            String type = (String) session.getAttribute("type");
            if (type.equals("1")){
            }
            else if (type.equals("2")){
                resultJson.put("desc","请切换普通用户账号");
                return new ResponseEntity<JSONObject>(resultJson, HttpStatus.OK);
            }else {
                resultJson.put("desc","请登录");
                return new ResponseEntity<JSONObject>(resultJson, HttpStatus.OK);
            }
        }
        UserInfo userInfo = (UserInfo) session.getAttribute("UserInfo");
        Favor favor = favorDao.findByUserIdAndProductId(userInfo.getUserId(),Integer.parseInt(productId));
        if (favor != null){
        }else {
            favor = new Favor();
            Product product = productDao.findByProductId(Integer.parseInt(productId));
            favor.setUserId(userInfo.getUserId());
            favor.setProductId(Integer.parseInt(productId));
            favor.setPreCost(product.getPriceOriginal());
            favorDao.save(favor);
        }
        resultJson.put("desc","商品已添加至收藏夹");
        return new ResponseEntity<JSONObject>(resultJson, HttpStatus.OK);
    }
}

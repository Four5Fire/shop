package cn.playcall.shop.demo.controller;

import cn.playcall.shop.demo.dao.CartDao;
import cn.playcall.shop.demo.dao.ProductDao;
import cn.playcall.shop.demo.entity.Cart;
import cn.playcall.shop.demo.entity.CartProduct;
import cn.playcall.shop.demo.entity.Product;
import cn.playcall.shop.demo.entity.UserInfo;
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
public class CartController {

    @Autowired
    private CartDao cartDao;

    @Autowired
    private ProductDao productDao;

    @RequestMapping(value = "/cart")
    public String cartIndex(HttpServletRequest request, Model model){
        HttpSession session = request.getSession();
        UserInfo userInfo = (UserInfo) session.getAttribute("UserInfo");
        List<Cart> cartList = cartDao.findAllByUserId(userInfo.getUserId());
        ArrayList<CartProduct> cartProductArrayList = new ArrayList<>();
        for (Cart c:cartList) {
            System.out.println(c);
            System.out.println(c.getProductId());
            Product product = productDao.findByProductId(c.getProductId());
            System.out.println(product);
            CartProduct cartProduct = new CartProduct(product.getProductId(),product.getProductName(),product.getBrand(),
                    product.getPic(),product.getPriceLow(),product.getPriceHigh(),product.getPriceOriginal(),c.getProductNum());
            cartProductArrayList.add(cartProduct);
        }
        model.addAttribute("productList",cartProductArrayList);
        model.addAttribute("user","user");
        model.addAttribute("bottomInfo","bottomInfo");
        return "cart";
    }

    @RequestMapping(value = "/cartAddProduct/{productId}")
    public ResponseEntity<JSONObject> cartAddProduct(HttpServletRequest request,@PathVariable String productId){
        HttpSession session = request.getSession();
        JSONObject resultJson = new JSONObject();
        int type = Integer.parseInt((String) session.getAttribute("type"));
        if (type == 0){
            resultJson.put("desc","请先登录");
            return new ResponseEntity<JSONObject>(resultJson,HttpStatus.OK);
        }
        UserInfo userInfo = (UserInfo) session.getAttribute("UserInfo");
        resultJson.put("desc","商品添加成功");
        Product product = productDao.findByProductId(Integer.parseInt(productId));
        Cart cart = cartDao.findByProductId(Integer.parseInt(productId));
        if (cart != null){
            cart.setProductNum(cart.getProductNum()+1);
            cartDao.flush();
            resultJson.put("desc","商品添加成功");
        }else {
            cart = new Cart();
            cart.setUserId(userInfo.getUserId());
            cart.setProductId(Integer.parseInt(productId));
            cart.setProductNum(1);
            cart.setIsBought(0);
            cartDao.save(cart);
            resultJson.put("desc","商品添加成功");
        }
        return new ResponseEntity<JSONObject>(resultJson, HttpStatus.OK);
    }

}

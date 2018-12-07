package cn.playcall.shop.demo.controller;

import cn.playcall.shop.demo.dao.*;
import cn.playcall.shop.demo.entity.*;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@CrossOrigin
@Controller
@RequestMapping(value = "/shop/**")
public class CartController {

    @Autowired
    private CartDao cartDao;

    @Autowired
    private ProductDao productDao;

    @Autowired
    private DiscountDao discountDao;

    @Autowired
    private SaleDao saleDao;

    @Autowired
    private UserDao userDao;

    @RequestMapping(value = "/cart")
    public String cartIndex(HttpServletRequest request, Model model){
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
        List<Cart> cartList = cartDao.findAllByUserId(userInfo.getUserId());
        ArrayList<CartProduct> cartProductArrayList = new ArrayList<>();
        for (Cart c:cartList) {
            Product product = productDao.findByProductId(c.getProductId());
            CartProduct cartProduct = new CartProduct(product.getProductId(),product.getProductName(),product.getBrand(),
                    product.getPic(),product.getPriceLow(),product.getPriceHigh(),product.getPriceOriginal(),c.getProductNum());
            cartProductArrayList.add(cartProduct);
        }
        model.addAttribute("productList",cartProductArrayList);
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
        }else if (type == 2){
            resultJson.put("desc","请切换至普通用户账号");
            return new ResponseEntity<JSONObject>(resultJson,HttpStatus.OK);
        }
        UserInfo userInfo = (UserInfo) session.getAttribute("UserInfo");
        resultJson.put("desc","商品成功添加至购物车");
        Product product = productDao.findByProductId(Integer.parseInt(productId));
        Cart cart = cartDao.findByProductId(Integer.parseInt(productId));
        if (cart != null){
            cart.setProductNum(cart.getProductNum()+1);
            cartDao.flush();
            resultJson.put("desc","商品成功添加至购物车");
        }else {
            cart = new Cart();
            cart.setUserId(userInfo.getUserId());
            cart.setProductId(Integer.parseInt(productId));
            cart.setProductNum(1);
            cart.setIsBought(0);
            cartDao.save(cart);
            resultJson.put("desc","商品成功添加至购物车");
        }
        return new ResponseEntity<JSONObject>(resultJson, HttpStatus.OK);
    }

    @RequestMapping(value = "/addSale")
    public ResponseEntity<JSONObject> addSale(HttpServletRequest request, @RequestBody JSONObject saleJson){
        HttpSession session = request.getSession();
        UserInfo userInfo = (UserInfo) session.getAttribute("UserInfo");
        JSONObject resultJson = new JSONObject();
        resultJson.put("desc","下单失败");
        JSONArray jsonArray = saleJson.getJSONArray("order");
        Discount discount = discountDao.findByUserLevel(userInfo.getUserLevel());
        for (Object product:jsonArray) {
            Integer productId = Integer.parseInt(((JSONObject) product).getString("productId"));
            Integer productNum = Integer.parseInt(((JSONObject) product).getString("productNum"));
            Product productGood = productDao.findByProductId(productId);
            Sale sale = new Sale();
            sale.setProductId(productId);
            sale.setProductNum(productNum);
            sale.setUserId(userInfo.getUserId());
            sale.setShopTime(getCutTime());
            BigDecimal sumPrice = (productGood.getPriceOriginal().multiply(new BigDecimal(productNum))).multiply(discount.getDiscount());
            sale.setSumPrice(sumPrice);
            sale.setState(0);
            saleDao.save(sale);
            cartDao.deleteByUserIdAndProductId(userInfo.getUserId(),productId);
        }
        resultJson.put("desc","下单成功");
        return new ResponseEntity<JSONObject>(resultJson,HttpStatus.OK);
    }

    @RequestMapping(value = "/deleteCart")
    public ResponseEntity<JSONObject> deleteCart(HttpServletRequest request,@RequestBody JSONObject deleteJson){
        JSONObject resultJson = new JSONObject();
        resultJson.put("desc","从购物车删除失败");
        JSONArray jsonArray = deleteJson.getJSONArray("order");
        for (Object pro:jsonArray) {
            Integer productId = Integer.parseInt(((JSONObject)pro).getString("productId"));
            cartDao.deleteByProductId(productId);
        }
        resultJson.put("desc","从购物车删除成功");
        return new ResponseEntity<JSONObject>(resultJson,HttpStatus.OK);
    }

    @RequestMapping(value = "/changeCart")
    public void changeCart(HttpServletRequest request, @RequestBody JSONObject changeJson){
        HttpSession session = request.getSession();
        UserInfo userInfo = (UserInfo) session.getAttribute("UserInfo");
        System.out.println(changeJson);
        Integer product = Integer.parseInt(changeJson.getString("productId"));
        Integer productNum = Integer.parseInt(changeJson.getString("productNum"));
        Cart cart = cartDao.findByUserIdAndProductId(userInfo.getUserId(),product);
        cart.setProductNum(productNum);
        cartDao.flush();
    }

    private String getCutTime(){
        Date date = new Date();//获得系统时间.
        String dateStr = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date);
        return dateStr;
    }

}

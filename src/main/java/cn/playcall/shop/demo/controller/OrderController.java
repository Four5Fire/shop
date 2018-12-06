package cn.playcall.shop.demo.controller;

import cn.playcall.shop.demo.dao.ProductDao;
import cn.playcall.shop.demo.dao.SaleDao;
import cn.playcall.shop.demo.dao.UserDao;
import cn.playcall.shop.demo.entity.*;
import com.alibaba.fastjson.JSONArray;
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
import java.util.ArrayList;
import java.util.List;

@CrossOrigin
@Controller
@RequestMapping(value = "/shop/**")
public class OrderController {

    @Autowired
    private SaleDao saleDao;

    @Autowired
    private ProductDao productDao;

    @Autowired
    private UserDao userDao;

    @RequestMapping(value = "/orderClient")
    public String orderClientIndex(HttpServletRequest request, Model model){
        HttpSession session = request.getSession();
        UserInfo userInfo = (UserInfo) session.getAttribute("UserInfo");
        model.addAttribute("user","user");
        model.addAttribute("bottomInfo","bottomInfo");
        List<Sale> saleList = saleDao.findAllByUserId(userInfo.getUserId());
        ArrayList<OrderClient> orderClientArrayList = new ArrayList<>();
        Product product;
        for (Sale sale:saleList) {
            OrderClient orderClient = new OrderClient();
            product = productDao.findByProductId(sale.getProductId());
            orderClient.setProductId(sale.getSaleId());
            orderClient.setPro_name(product.getProductName());
            orderClient.setPro_brand(product.getBrand());
            orderClient.setPro_img(product.getPic());
            orderClient.setPro_price_low(product.getPriceLow());
            orderClient.setPro_price_high(product.getPriceHigh());
            orderClient.setPro_price_original(product.getPriceOriginal());
            orderClient.setPro_num(sale.getProductNum());
            if (sale.getState() == 0){
                orderClient.setPro_status("等待卖家发货");
            }else if (sale.getState() == 1){
                orderClient.setPro_status("待签收");
            }else if (sale.getState() == 2){
                orderClient.setPro_status("已签收");
            }
            orderClientArrayList.add(orderClient);
        }
        model.addAttribute("productList",orderClientArrayList);
        return "order_client";
    }

    @RequestMapping(value = "/orderStore")
    public String orderStoreIndex(HttpServletRequest request,Model model){
        HttpSession session = request.getSession();
        Shop shop = (Shop) session.getAttribute("ShopUser");
        model.addAttribute("user","shopUser");
        model.addAttribute("bottomInfo","bottomInfo");
        ArrayList<OrderShop> orderShopArrayList = new ArrayList<>();
        List<Product> productList = productDao.findAllByShopId(shop.getShopId());
        Product p;
        UserInfo userInfo;
        for (Product product:productList) {
            List<Sale> saleList = saleDao.findAllByProductId(product.getProductId());
            for (Sale s:saleList) {
                OrderShop orderShop = new OrderShop();
                userInfo = userDao.findByUserId(s.getUserId());
                orderShop.setUserName(userInfo.getUserName());
                orderShop.setUserAddress(userInfo.getAddr());
                orderShop.setSaleId(s.getSaleId());
                orderShop.setPro_brand(product.getBrand());
                orderShop.setPro_img(product.getPic());
                orderShop.setPro_name(product.getProductName());
                orderShop.setPro_price_original(product.getPriceOriginal());
                orderShop.setPro_price_sum(s.getSumPrice());
                orderShop.setPro_num(s.getProductNum());
                if (s.getState() == 0){
                    orderShop.setPro_status("待发货");
                }else if (s.getState() == 1){
                    orderShop.setPro_status("等待卖家签收");
                }else if (s.getState() == 2){
                    orderShop.setPro_status("卖家已签收");
                }
                orderShopArrayList.add(orderShop);
            }
        }
        model.addAttribute("productList",orderShopArrayList);
        return "order_store";
    }

    @RequestMapping(value = "/receiveOrder")
    public ResponseEntity<JSONObject> deleteOrder(HttpServletRequest request, @RequestBody JSONObject receiveOrder){
        JSONObject resultJson = new JSONObject();
        HttpSession session = request.getSession();
        UserInfo userInfo = (UserInfo) session.getAttribute("UserInfo");
        Integer saleId = Integer.parseInt(receiveOrder.getString("saleId"));
        Sale sale = saleDao.findBySaleId(saleId);
        if (sale.getState() == 0){
            resultJson.put("code","000000");
            resultJson.put("desc","签收失败，请等待卖家发货");
        }else if (sale.getState() == 1){
            resultJson.put("code","000001");
            resultJson.put("desc","签收成功，请给五星好评");
            sale.setState(2);
            saleDao.flush();
            userInfo = userDao.findByUserId(userInfo.getUserId());
            Integer score = sale.getSumPrice().intValue();
            userInfo.setUserScore(userInfo.getUserScore()+score);
            if (userInfo.getUserScore() < 7000){
                userInfo.setUserLevel(userInfo.getUserScore()/1000+1);
            }else {
                userInfo.setUserLevel(8);
            }
            userDao.flush();
        }else if (sale.getState() == 2){
            resultJson.put("code","000002");
            resultJson.put("desc","商品已签收，请勿重复签收");
        }
        return new ResponseEntity<JSONObject>(resultJson, HttpStatus.OK);
    }

    @RequestMapping(value = "/payoutOrder")
    public ResponseEntity<JSONObject> payoutOrder(HttpServletRequest request,@RequestBody JSONObject payoutJson){
        JSONObject resultJson = new JSONObject();
        Integer saleId = Integer.parseInt(payoutJson.getString("saleId"));
        Sale sale = saleDao.findBySaleId(saleId);
        if (sale.getState() == 0){
            Product product = productDao.findByProductId(sale.getProductId());
            if (product.getStock() - sale.getProductNum() >= 0){
                sale.setState(1);
                saleDao.flush();
                product.setStock(product.getStock()-1);
                productDao.flush();
                resultJson.put("code","000000");
                resultJson.put("desc","发货成功");
            }else {
                resultJson.put("code","000003");
                resultJson.put("desc","商品库存不足，请补充货物");
            }
        }else if (sale.getState() == 1){
            resultJson.put("code","000001");
            resultJson.put("desc","发货失败，请等待卖家签收");
        }else if (sale.getState() == 2){
            resultJson.put("code","000002");
            resultJson.put("desc","发货失败，订单已签收");
        }
        return new ResponseEntity<JSONObject>(resultJson,HttpStatus.OK);
    }

}

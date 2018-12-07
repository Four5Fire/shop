package cn.playcall.shop.demo.controller;

import cn.playcall.shop.demo.dao.*;
import cn.playcall.shop.demo.entity.*;
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
import java.util.List;

@CrossOrigin
@Controller
@RequestMapping(value = "/shop/**")
public class CommentController {

    @Autowired
    private ItemDao itemDao;

    @Autowired
    private SaleDao saleDao;

    @Autowired
    private ProductDao productDao;

    @Autowired
    private CommentDao commentDao;

    @Autowired
    private ShopDao shopDao;

    @RequestMapping(value = "/commentClient/{saleId}")
    public String commentClient(HttpServletRequest request, Model model, @PathVariable String saleId){
        Sale sale = saleDao.findBySaleId(Integer.parseInt(saleId));
        Product product = productDao.findByProductId(sale.getProductId());
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
        model.addAttribute("ulList","ulList");
        List<Item> itemList = itemDao.findAll();
        model.addAttribute("itemList",itemList);
        model.addAttribute("productsApi","http://127.0.0.1:7000/shop/productsIndex/");
        model.addAttribute("productDetail","http://127.0.0.1:7000/shop/productDetail/"+product.getProductId());
        model.addAttribute("productPic",product.getPic());
        model.addAttribute("productNum",sale.getProductNum());
        model.addAttribute("productSumPrice",sale.getSumPrice());
        model.addAttribute("productName",product.getProductName());
        model.addAttribute("bottomInfo","bottomInfo");
        model.addAttribute("saleId",saleId);
        return "comment";
    }

    @RequestMapping(value = "/addComment")
    public ResponseEntity<JSONObject> addComment(HttpServletRequest request, @RequestBody JSONObject commentJson){
        HttpSession session = request.getSession();
        JSONObject resultJson = new JSONObject();
        System.out.println(commentJson);
        Integer saleId = Integer.parseInt(commentJson.getString("saleId"));
        Integer startToGive = Integer.parseInt(commentJson.getString("starNum"));
        String feedBack = commentJson.getString("feedback");
        Sale sale = saleDao.findBySaleId(saleId);
        Shop shop = shopDao.findByShopId(productDao.findByProductId(sale.getProductId()).getShopId());
        Comment comment = commentDao.findBySaleId(saleId);
        if (comment != null){
            resultJson.put("desc","该订单已提交过评价，请勿重复提交");
        }else {
            comment = new Comment();
            comment.setSaleId(saleId);
            comment.setStarTogive(startToGive);
            comment.setFeedBack(feedBack);
            commentDao.save(comment);
            BigDecimal star = shop.getStar().multiply(new BigDecimal(shop.getShopSold()-1)).add(new BigDecimal(startToGive));
            System.out.println(star);
            star = star.divide(new BigDecimal(shop.getShopSold()),2);
            System.out.println(star);
            shop.setStar(star);
            shopDao.flush();
            resultJson.put("desc","评价提交成功");
        }
        return new ResponseEntity<JSONObject>(resultJson, HttpStatus.OK);
    }

    @RequestMapping(value = "/commentLook/{saleId}")
    public String commentLook(HttpServletRequest request, @PathVariable String saleId,Model model){
        HttpSession session = request.getSession();
        String type = (String) session.getAttribute("type");
        if (type.equals("1")){
            model.addAttribute("user","user");
        }else if (type.equals("2")){
            model.addAttribute("user","shopUser");
        }
        Sale sale = saleDao.findBySaleId(Integer.parseInt(saleId));
        Comment comment = commentDao.findBySaleId(sale.getSaleId());
        Product product = productDao.findByProductId(sale.getProductId());
        model.addAttribute("search","search");
        model.addAttribute("ulList","ulList");
        List<Item> itemList = itemDao.findAll();
        model.addAttribute("itemList",itemList);
        model.addAttribute("productsApi","http://127.0.0.1:7000/shop/productsIndex/");
        model.addAttribute("productDetail","http://127.0.0.1:7000/shop/productDetail/"+product.getProductId());
        model.addAttribute("productPic",product.getPic());
        model.addAttribute("productNum",sale.getProductNum());
        model.addAttribute("productSumPrice",sale.getSumPrice());
        model.addAttribute("productName",product.getProductName());
        model.addAttribute("bottomInfo","bottomInfo");
        model.addAttribute("saleId",saleId);
        String star = "";
        for (int i = 0; i < comment.getStarTogive(); i++) {
            star += "★";
        }
        model.addAttribute("star",star);
        model.addAttribute("feedback",comment.getFeedBack());

        return "commentLook";
    }
}

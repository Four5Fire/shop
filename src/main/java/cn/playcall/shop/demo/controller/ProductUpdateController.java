package cn.playcall.shop.demo.controller;

import cn.playcall.shop.demo.dao.HistoryDao;
import cn.playcall.shop.demo.dao.ItemDao;
import cn.playcall.shop.demo.dao.ProductDao;
import cn.playcall.shop.demo.entity.History;
import cn.playcall.shop.demo.entity.Item;
import cn.playcall.shop.demo.entity.Product;
import cn.playcall.shop.demo.entity.Shop;
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
import java.util.Date;
import java.util.List;

@CrossOrigin
@Controller
@RequestMapping(value = "/shop/**")
public class ProductUpdateController {

    @Autowired
    private ItemDao itemDao;

    @Autowired
    private ProductDao productDao;

    @Autowired
    private HistoryDao historyDao;

    @RequestMapping(value = "/productInfoUpdateIndex/{productId}")
    public String ProductInfoUpdateIndex(HttpServletRequest request, Model model, @PathVariable String productId){
        HttpSession session = request.getSession();
        Shop shop = (Shop) session.getAttribute("UserInfo");
        Product product = productDao.findByProductId(Integer.parseInt(productId));
        model.addAttribute("user","shopUser");
        model.addAttribute("search","search");
        model.addAttribute("ulList","ulList");
        List<Item> itemList = itemDao.findAll();
        model.addAttribute("itemType","itemType");
        model.addAttribute("itemList",itemList);
        model.addAttribute("productPic",product.getPic());
        model.addAttribute("productName",product.getProductName());
        model.addAttribute("productPrice",product.getPriceOriginal());
        model.addAttribute("productBrand",product.getBrand());
        model.addAttribute("productNum",product.getStock());
        model.addAttribute("productIntro",product.getIntro());
        model.addAttribute("productId",product.getProductId());
        model.addAttribute("bottomInfo","bottomInfo");
        return "updateProductInfo";
    }

    @RequestMapping(value = "/updateProduct/{productId}")
    public ResponseEntity<JSONObject> updateProduct(HttpServletRequest request, @PathVariable String productId, @RequestBody JSONObject updateJson){
        JSONObject resultJson = new JSONObject();
        resultJson.put("desc","商品信息修改失败");
        HttpSession session = request.getSession();
        Shop shop = (Shop) session.getAttribute("ShopUser");
        Product product = productDao.findByProductId(Integer.parseInt(productId));
        product.setProductName(updateJson.getString("productName"));
        if (product.getPriceOriginal().compareTo(new BigDecimal(updateJson.getString("productPrice"))) != 0){
            History history = new History();
            history.setProductId(product.getProductId());
            history.setPrice(new BigDecimal(updateJson.getString("productPrice")));
            history.setHistory(getCutTime());

            product.setPriceOriginal(new BigDecimal(updateJson.getString("productPrice")));
            product.setBrand(updateJson.getString("productBrand"));
            product.setItemId(Integer.parseInt(updateJson.getString("productItem")));
            product.setStock(Integer.parseInt(updateJson.getString("productNum")));
            product.setIntro(updateJson.getString("productIntro"));
            productDao.flush();
            historyDao.save(history);
        }else {
            product.setPriceOriginal(new BigDecimal(updateJson.getString("productPrice")));
            product.setBrand(updateJson.getString("productBrand"));
            product.setItemId(Integer.parseInt(updateJson.getString("productItem")));
            product.setStock(Integer.parseInt(updateJson.getString("productNum")));
            product.setIntro(updateJson.getString("productIntro"));
            productDao.flush();
        }
        resultJson.put("desc","商品信息修改成功");
        return new ResponseEntity<JSONObject>(resultJson, HttpStatus.OK);
    }

    private String getCutTime(){
        Date date = new Date();//获得系统时间.
        String dateStr = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date);
        return dateStr;
    }
}

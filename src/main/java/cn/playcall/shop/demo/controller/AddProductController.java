package cn.playcall.shop.demo.controller;

import cn.playcall.shop.demo.dao.ItemDao;
import cn.playcall.shop.demo.dao.ProductDao;
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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.math.BigDecimal;
import java.util.List;

@CrossOrigin
@Controller
@RequestMapping(value = "/shop/**")
public class AddProductController {

    @Autowired
    private ProductDao productDao;

    @Autowired
    private ItemDao itemDao;

    @RequestMapping(value = "/addProduct")
    public String indexAddProduct(HttpServletRequest request, Model model){
        model.addAttribute("user","shopUser");
        model.addAttribute("search","search");
        model.addAttribute("bottomInfo","bottomInfo");
        model.addAttribute("ulList","ulList");
        List<Item> itemList = itemDao.findAll();
        model.addAttribute("itemList",itemList);
        return "addProduct";
    }

    @RequestMapping(value = "/uploadProduct")
    public ResponseEntity<JSONObject> receiveProduct(HttpServletRequest request) throws IOException {
        HttpSession session = request.getSession();
        Shop shop = (Shop) session.getAttribute("ShopUser");
        JSONObject resultJson = new JSONObject();
        MultipartHttpServletRequest params = ((MultipartHttpServletRequest)request);
        List<MultipartFile> files = ((MultipartHttpServletRequest)request).getFiles("file");
        MultipartFile file = files.get(0);
        String fileName = params.getParameter("fileName");
        String productName = params.getParameter("name");
        String brand = params.getParameter("brand");
        String intro = params.getParameter("DESCRIBE");
        Product product = productDao.findByShopIdAndProductNameAndBrandAndIntro(shop.getShopId(),productName,brand,intro);
        if (product != null){
            resultJson.put("desc","商品已存在,请勿重复添加");
        }
        else {
            product = new Product();
            product.setShopId(shop.getShopId());
            product.setProductName(productName);
            product.setBrand(brand);
            product.setPriceOriginal(new BigDecimal(params.getParameter("price")));
            product.setIntro(intro);
            product.setItemId(Integer.parseInt(params.getParameter("selection")));
            product.setPriceLow(product.getPriceOriginal());
            product.setPriceHigh(product.getPriceOriginal());
            File shopUserPath = new File("./src/main/resources/static/shopUser");
            if (!shopUserPath.exists()){
                shopUserPath.mkdirs();
            }

            long uTime = System.currentTimeMillis();

            String filePath = shopUserPath+"/shopPro"+shop.getShopId()+uTime+"."+fileName.split("\\.")[1];

            BufferedOutputStream outputStream = new BufferedOutputStream(new FileOutputStream(new File(filePath)));
            outputStream.write(file.getBytes());
            outputStream.flush();
            outputStream.close();
            product.setPic("/shopUser/shopPro"+shop.getShopId()+uTime+"."+fileName.split("\\.")[1]);
            productDao.save(product);
            resultJson.put("desc","商品添加成功");
        }
        System.out.println(resultJson);
        return new ResponseEntity<JSONObject>(resultJson, HttpStatus.OK);
    }


}

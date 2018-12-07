package cn.playcall.shop.demo.controller;

import cn.playcall.shop.demo.dao.ItemDao;
import cn.playcall.shop.demo.dao.UserDao;
import cn.playcall.shop.demo.entity.Item;
import cn.playcall.shop.demo.entity.UserInfo;
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
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

@CrossOrigin
@Controller
@RequestMapping(value = "/shop/**")
public class UserUpdateController {

    @Autowired
    private UserDao userDao;

    @Autowired
    private ItemDao itemDao;

    @RequestMapping(value = "/userUpdateIndex")
    public String userUpdateIndex(HttpServletRequest request,Model model){
        HttpSession session = request.getSession();
        UserInfo userInfo = (UserInfo) session.getAttribute("UserInfo");
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
        model.addAttribute("username",userInfo.getUserName());
        model.addAttribute("tele",userInfo.getTele());
        model.addAttribute("userPwd",userInfo.getUserpwd());
        model.addAttribute("address",userInfo.getAddr());
        model.addAttribute("bottomInfo","bottomInfo");
        return "userupdate";
    }

    @RequestMapping(value = "/userUpdate")
    public ResponseEntity<JSONObject> update(HttpServletRequest request) throws IOException {
        JSONObject resultJson = new JSONObject();
        HttpSession session = request.getSession();
        UserInfo userInfo = (UserInfo) session.getAttribute("UserInfo");

        userInfo = userDao.findByUserId(userInfo.getUserId());

        MultipartHttpServletRequest params = ((MultipartHttpServletRequest)request);
        List<MultipartFile> files = ((MultipartHttpServletRequest)request).getFiles("file");
        MultipartFile file = files.get(0);
        String fileName = params.getParameter("fileName");

        String tempName = params.getParameter("name");

        userInfo.setUserName(params.getParameter("name"));

        if (params.getParameter("selection").equals("1")){
            userInfo.setGender("男");
        }else {
            userInfo.setGender("女");
        }

        userInfo.setUserpwd(params.getParameter("password"));
        userInfo.setAddr(params.getParameter("address"));

        File userPath = new File("./src/main/resources/static/user");
        if (!userPath.exists()){
            userPath.mkdirs();
        }

        long uTime = System.currentTimeMillis();

        String filePath = userPath+"/"+"user"+userInfo.getUserId()+uTime+"."+fileName.split("\\.")[1];

        BufferedOutputStream outputStream = new BufferedOutputStream(new FileOutputStream(new File(filePath)));
        outputStream.write(file.getBytes());
        outputStream.flush();
        outputStream.close();
        userInfo.setUserPic("/user/user"+userInfo.getUserId()+uTime+"."+fileName.split("\\.")[1]);
        userDao.flush();
        session.setAttribute("UserInfo",userInfo);
        return new ResponseEntity<JSONObject>(resultJson, HttpStatus.OK);
    }
}

package cn.playcall.shop.demo.dao;

import cn.playcall.shop.demo.entity.Shop;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShopDao extends JpaRepository<Shop,Integer> {

    public Shop findByTele(String tele);

    public Shop findByTeleAndAndShopPwd(String tele, String shopPwd);
}

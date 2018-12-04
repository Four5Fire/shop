package cn.playcall.shop.demo.dao;

import cn.playcall.shop.demo.entity.Sale;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SaleDao extends JpaRepository<Sale,Integer> {

    public int countAllByProductId(Integer productId);
}

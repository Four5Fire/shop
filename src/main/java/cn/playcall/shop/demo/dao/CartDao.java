package cn.playcall.shop.demo.dao;

import cn.playcall.shop.demo.entity.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartDao extends JpaRepository<Cart,Integer> {
}

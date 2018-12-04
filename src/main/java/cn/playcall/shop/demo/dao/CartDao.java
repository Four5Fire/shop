package cn.playcall.shop.demo.dao;

import cn.playcall.shop.demo.entity.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartDao extends JpaRepository<Cart,Integer> {

    public List<Cart> findAllByUserId(Integer userId);

    public Cart findByProductId(Integer productId);
}

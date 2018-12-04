package cn.playcall.shop.demo.dao;

import cn.playcall.shop.demo.entity.Discount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DiscountDao extends JpaRepository<Discount,Integer> {

}

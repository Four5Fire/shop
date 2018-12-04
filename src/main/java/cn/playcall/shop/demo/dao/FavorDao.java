package cn.playcall.shop.demo.dao;

import cn.playcall.shop.demo.entity.Favor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FavorDao extends JpaRepository<Favor,Integer> {

}

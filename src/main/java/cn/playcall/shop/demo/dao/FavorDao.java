package cn.playcall.shop.demo.dao;

import cn.playcall.shop.demo.entity.Favor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FavorDao extends JpaRepository<Favor,Integer> {

    public Favor findByUserIdAndProductId(Integer userId, Integer productId);

    public List<Favor> findAllByProductId(Integer productId);

    public List<Favor> findAllByUserId(Integer userId);
}

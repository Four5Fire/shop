package cn.playcall.shop.demo.dao;

import cn.playcall.shop.demo.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemDao extends JpaRepository<Item,Integer> {

    public List<Item> findAll();

    public Item findByItemId(Integer itemId);
}

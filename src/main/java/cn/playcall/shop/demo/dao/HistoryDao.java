package cn.playcall.shop.demo.dao;

import cn.playcall.shop.demo.entity.History;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HistoryDao extends JpaRepository<History,Integer> {
}

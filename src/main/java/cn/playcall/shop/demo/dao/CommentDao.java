package cn.playcall.shop.demo.dao;

import cn.playcall.shop.demo.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentDao extends JpaRepository<Comment,Integer> {

    public Comment findBySaleId(Integer saleId);
}

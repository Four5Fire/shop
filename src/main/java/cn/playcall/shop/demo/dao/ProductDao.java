package cn.playcall.shop.demo.dao;

import cn.playcall.shop.demo.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductDao extends JpaRepository<Product,Integer> {

    public List<Product> findAllByProductName(String productName);

    public List<Product> findAllByItemId(Integer itemId);

    public Product findByProductId(Integer productId);

    public List<Product> findAllByShopId(Integer shopId);
}

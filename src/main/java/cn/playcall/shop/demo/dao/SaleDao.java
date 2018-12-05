package cn.playcall.shop.demo.dao;

import cn.playcall.shop.demo.entity.Sale;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface SaleDao extends JpaRepository<Sale,Integer> {

    public int countAllByProductId(Integer productId);

    public List<Sale> findAllByUserId(Integer userId);

    public List<Sale> findAllByProductId(Integer productId);

    @Transactional
    public void deleteBySaleId(Integer saleId);

    public Sale findBySaleId(Integer salId);
}

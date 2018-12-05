package cn.playcall.shop.demo.dao;


import cn.playcall.shop.demo.entity.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Repository
public interface UserDao extends JpaRepository<UserInfo,Integer> {

    /**
     * 根据tele进行查询
     * @param tele
     * @return
     */
    public UserInfo findByTele(String tele);

    /**
     * 根据tele和user_pwd进行查询
     * @param tele
     * @param userPwd
     * @return
     */
    public UserInfo findByTeleAndUserpwd(String tele, String userPwd);

    public UserInfo findByUserId(Integer userId);

    public UserInfo getByUserId(Integer userId);
}

package org.zero.jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.zero.jpa.entity.User;

import java.util.List;

/**
 * @author 水寒
 * @date 2020/4/26
 * @description 用户持久化工具
 */
public interface UserRepository extends JpaRepository<User, Long> {

    /**
     * 查询用户列表
     * @param name 名称
     * @param mobile 手机号
     * @return 用户列表
     */
    List<User> findByNameAndMobile(String name, String mobile);

    /**
     *  根据名称来统计
     * @param name 名称
     * @return 数量
     */
    Long countByName(String name);

    /**
     *  不用写mapper.xml
     * @param mobile 手机号
     * @return 数量
     */
    @Query("SELECT COUNT(User) FROM User WHERE mobile = ?1 ")
    Long countByMobilePrefix(String mobile);
}
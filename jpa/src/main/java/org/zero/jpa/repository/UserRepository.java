package org.zero.jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.zero.jpa.entity.User;

import java.util.List;

/**
 * 用户持久化操作
 *
 */
public interface UserRepository extends JpaRepository<User, Long> {

    List<User> findByNameAndMobile(String name, String mobile);

}
package org.zero.jpa.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.zero.jpa.entity.User;
import org.zero.jpa.repository.UserRepository;

import java.util.List;

/**
 * @author 水寒
 * @date 2020/4/17
 */
@RestController
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/user")
    public void create(@RequestBody User user) {
        userRepository.save(user);
    }

    @DeleteMapping("/user/{id}")
    public void delete(@PathVariable Long id) {
        userRepository.deleteById(id);
    }

    @GetMapping("/user/{id}")
    public User get(@PathVariable Long id) {
        return userRepository.findById(id).orElse(new User());
    }

    @PutMapping("/user")
    public void update(@RequestBody User user) {
        userRepository.save(user);
    }

    @GetMapping("/user/search")
    public List<User> search(@RequestParam String name, @RequestParam String mobile) {
        return userRepository.findByNameAndMobile(name, mobile);
    }
}

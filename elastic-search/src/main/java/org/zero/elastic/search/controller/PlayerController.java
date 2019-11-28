package org.zero.elastic.search.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.zero.elastic.search.dto.Player;
import org.zero.elastic.search.repository.PlayerService;
import org.zero.elastic.search.util.PageResultVO;

import javax.annotation.Resource;

/**
 * @author yezhaoxing
 * @date 2019/11/24
 */
@RestController
@RequestMapping("/player")
public class PlayerController {

    @Resource
    private PlayerService playerService;

    @GetMapping("/save")
    public String add() {
        playerService.add();
        return "";
    }

    @GetMapping("/findByName")
    public PageResultVO<Player> findByName(@RequestParam String name, @RequestParam Integer from, @RequestParam Integer size) {
        return playerService.findByName(name, from, size);
    }

    @GetMapping("/findByCountry")
    public String findByCountry() {
        playerService.add();
        return "";
    }

}

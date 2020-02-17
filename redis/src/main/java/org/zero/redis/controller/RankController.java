package org.zero.redis.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.zero.redis.util.RedisZsetHelper;

import javax.annotation.Resource;

/**
 * @author yezhaoxing
 * @date 2020/1/2
 */
@RestController
@RequestMapping("/rank")
public class RankController {

    @Resource
    private RedisZsetHelper<String, Object> redisZsetHelper;

    private static final String key = "math_score";

    /**
     * @author zero
     * @date 2020/1/2
     * @description 添加数据
     */
    @GetMapping("/add")
    public void add(@RequestParam Integer score, @RequestParam String id) {
        redisZsetHelper.add(key, id, score);
    }

    /**
     * @author zero
     * @date 2020/1/2
     * @description 删除数据
     */
    @GetMapping("/remove")
    public void remove(@RequestParam String id) {
        redisZsetHelper.remove(key, id);
    }

    /**
     * @author zero
     * @date 2020/1/2
     * @description 查询分数值
     */
    @GetMapping("/score")
    public Double score(@RequestParam String id) {
        return redisZsetHelper.getScore(key, id);
    }

    /**
     * @author zero
     * @date 2020/1/2
     * @description 修改分数值
     */
    @GetMapping("/updateScore")
    public void updateScore(@RequestParam String id, @RequestParam Integer addScore) {
        redisZsetHelper.incrScore(key, id, addScore);
    }

    /**
     * @author zero
     * @date 2020/1/2
     * @description 查询名词
     */
    @GetMapping("/rank")
    public Long zRank(@RequestParam String id) {
        return redisZsetHelper.getRank(key, id);
    }
}

package org.zero.redis.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.zero.redis.util.RedisSetHelper;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * @author yezhaoxing
 * @date 2020/1/2
 */
@RestController
@RequestMapping("/relation")
public class FollowController {

    @Autowired
    private RedisSetHelper<String, String> redisSetHelper;

    /**
     * @author zero
     * @date 2020/1/2
     * @description 关注某人
     */
    @GetMapping("/follow")
    public void follow(@RequestParam String followId, @RequestParam String fansId) {
        String key1 = "follow:" + fansId;
        String key2 = "fans:" + followId;
        // 将被关注者添加到关注者关注列表
        redisSetHelper.add(key1, followId);
        // 将关注者添加添加到被关注者粉丝列表
        redisSetHelper.add(key2, fansId);
    }

    /**
     * @author zero
     * @date 2020/1/2
     * @description 取消关注某人
     */
    @GetMapping("/unfollow")
    public void unfollow(@RequestParam String followId, @RequestParam String fansId) {
        String key1 = "follow:" + fansId;
        String key2 = "fans:" + followId;
        // 将被关注者从关注者关注列表移除
        redisSetHelper.remove(key1, followId);
        // 将关注者从被关注者粉丝列表移除
        redisSetHelper.remove(key2, fansId);
    }

    /**
     * @author zero
     * @date 2020/1/2
     * @description 查询关注列表
     */
    @GetMapping("/follows")
    public Set<String> follows(@RequestParam String fansId) {
        String key = "follow:" + fansId;
        return redisSetHelper.members(key);
    }

    /**
     * @author zero
     * @date 2020/1/2
     * @description 查询粉丝列表
     */
    @GetMapping("/fanses")
    public Set<String> fanses(@RequestParam String followId) {
        String key = "fans:" + followId;
        return redisSetHelper.members(key);
    }

    /**
     * @author zero
     * @date 2020/1/2
     * @description 是否互相关注
     */
    @GetMapping("/mutualFollow")
    public String mutualFollow(@RequestParam String myId, @RequestParam String hisId) {
        String key1 = "fans:" + myId;
        String key2 = "follow:" + myId;
        Boolean b1 = redisSetHelper.isMember(key1, hisId);
        Boolean b2 = redisSetHelper.isMember(key2, hisId);
        if (b1) {
            if (b2) {
                return "互相关注";
            } else {
                return "他单方面关注我";
            }
        } else {
            if (b2) {
                return "我单方面关注他";
            } else {
                return "互不关注";
            }
        }
    }

    /**
     * @author zero
     * @date 2020/1/2
     * @description 查询和我互粉的人
     */
    @GetMapping("/mutualFollowList")
    public Set<String> mutualFollowList(@RequestParam String myId) {
        String key1 = "fans:" + myId;
        String key2 = "follow:" + myId;
        return redisSetHelper.intersect(key1, key2);
    }

    /**
     * @author zero
     * @date 2020/1/2
     * @description 共同关注的人
     */
    @GetMapping("/commonFollowList")
    public Set<String> commonFollowList(@RequestParam String myId, @RequestParam String hisId) {
        String key1 = "follow:" + myId;
        String key2 = "follow:" + hisId;
        return redisSetHelper.intersect(key1, key2);
    }

    /**
     * @author zero
     * @date 2020/1/2
     * @description 查询关注数和粉丝数
     */
    @GetMapping("/size")
    public Map<String, Long> size(@RequestParam String myId) {
        String key1 = "fans:" + myId;
        String key2 = "follow:" + myId;
        Map<String, Long> rtn = new HashMap<>(2);
        rtn.put(key1, redisSetHelper.size(key1));
        rtn.put(key2, redisSetHelper.size(key2));
        return rtn;
    }
}

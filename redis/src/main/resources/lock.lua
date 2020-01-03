--[[ keys[1] 锁名称
     ARGV[1] 过期时间
     ARGV[2] 值,一般是uuid或者线程id
]]


--[[如果不存在value,就设值=1 并设置过期时间]]
if (redis.call('exists', KEYS[1]) == 0) then
    redis.call('hset', KEYS[1], ARGV[2], 1);
    redis.call('pexpire', KEYS[1], ARGV[1]);
    return -1;
end ;
--[[如果存在value, 就设值+1, 并重新设置过期时间]]
if (redis.call('hexists', KEYS[1], ARGV[2]) == 1) then
    redis.call('hincrby', KEYS[1], ARGV[2], 1);
    redis.call('pexpire', KEYS[1], ARGV[1]);
    return -1;
end ;
--[[返回过期时间--]]
return redis.call('pttl', KEYS[1]);
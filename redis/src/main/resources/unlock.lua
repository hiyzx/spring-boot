--[[ keys[1] 锁名称
     keys[2] 频道名称
     ARGV[1] 过期时间
     ARGV[2] 值,一般是uuid或者线程id
     ARGV[3] 发送频道消息,其他监听线程判断锁释放了
]]

--[[ 如果不是等待锁,这段可以不用]]
--[[if (redis.call('exists', KEYS[1]) == 0) then
    redis.call('publish', KEYS[2], ARGV[3]);
    return 1;
end ;]]

--[[ value不存在,直接返回 ]]
if (redis.call('hexists', KEYS[1], ARGV[2]) == 0) then
    return nil;
end ;

--[[ 对value进行 -1操作 ]]
local counter = redis.call('hincrby', KEYS[1], ARGV[2], -1);

--[[ 如果counter>0, 说明是重入锁,等待 ]]
if (counter > 0) then
    redis.call('pexpire', KEYS[1], ARGV[1]);
    return 0;

-- [[如果counter <= 0, 直接删除 ]]
else
    redis.call('del', KEYS[1]);
--[[    redis.call('publish', KEYS[2], ARGV[3]);]]
    return 1;
end ;
return nil;
package org.zero.redis.conf;

import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.client.codec.Codec;
import org.redisson.config.Config;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.ClassUtils;

import io.netty.channel.nio.NioEventLoopGroup;

@Configuration
public class RedissonConfig {

    @Value("${spring.redis.host}")
    private String host;
    @Value("${spring.redis.port}")
    private String port;
    @Value("${spring.redis.password}")
    private String password;
    @Value("${spring.redis.database}")
    private Integer database;

    @Bean(destroyMethod = "shutdown")
    RedissonClient redisson() throws Exception {
        Config config = new Config();
        String prefix = "redis://";
        config.useSingleServer().setAddress(prefix + host + ":" + port).setDatabase(database).setPassword(password);
        Codec codec = (Codec)ClassUtils
            .forName("org.redisson.codec.JsonJacksonCodec", ClassUtils.getDefaultClassLoader()).newInstance();
        config.setCodec(codec);
        config.setEventLoopGroup(new NioEventLoopGroup());
        config.setUseLinuxNativeEpoll(false);
        return Redisson.create(config);
    }
}
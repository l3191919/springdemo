package com.lyz.config;

import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Redisson如果配置过Redis，
 * 通常不需要另行配置，但具体情况取决于Redisson与Redis的集成方式以及项目的具体需求。
 * Redisson是一个基于Redis的分布式和高性能Java对象框架，
 * 它提供了许多有用的特性，如分布式锁、分布式集合、分布式对象等，可以很方便地与Redis进行集成。
 *
 * 在配置类中，我们创建了一个RedissonClient的Bean，并通过Config对象设置了Redis的连接地址为redis://127.0.0.1:6379。
 * 一旦这个配置被加载，Redisson就可以使用这个连接信息来操作Redis了。
 *
 * 结论
 * 综上所述，如果Redisson已经配置过Redis的连接信息，并且这些信息是正确的，那么通常不需要再另行配置Redis。
 * 然而，如果项目中有特殊的Redis配置需求，如连接多个Redis实例或配置Redis的高级特性，那么可能需要在Redisson的配置中额外指定这些信息。
 *
 * 请注意，以上信息是基于Redisson的一般用法和配置方式。在实际项目中，可能需要根据项目的具体需求和Redis的版本选择合适的Redisson版本，
 * 并参考Redisson的官方文档进行配置。
 */
//@Configuration 注掉不加入Configuration中 正常使用放开
public class RedissonConfig {

    //@Bean 注掉不加入bean中 正常使用放开
    public RedissonClient redissonClient() {
        Config config = new Config();
        config.useSingleServer().setAddress("redis://127.0.0.1:6379"); // 这里指定了Redis的连接地址
        return Redisson.create(config);
    }
}
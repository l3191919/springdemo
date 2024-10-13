package com.lyz.example.demo;
import com.lyz.entities.Payment;
import org.junit.jupiter.api.Test;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * 在Java中，当你需要将大量数据批量写入MySQL数据库时，
 * 使用JDBC的PreparedStatement配合addBatch()和executeBatch()方法是一个高效的选择。
 * 这种方法减少了与数据库的连接次数，因为你可以将多个SQL语句打包成一个批次发送到数据库执行。
 */
@SpringBootTest
public class RedissonTest {
    @Autowired
    RedissonClient redissonClient;

    @Test
    public void Rlock(){
        RLock lock = redissonClient.getLock("anyLock");
        lock.lock();
        try {
            // 执行业务代码
            /**
             * 共享资源的并发访问：当多个服务实例或线程需要同时访问同一个共享资源（如数据库记录、缓存项、文件等）时，
             * 使用分布式锁可以确保同一时间只有一个服务实例或线程能够访问该资源，从而避免数据损坏或不一致。
             *
             * 分布式任务调度：在分布式系统中，如果有多个节点需要执行同一任务，并且这个任务在同一时间只能由一个节点执行，
             * 那么可以使用分布式锁来协调节点的执行。xxl-job?
             *
             * 分布式缓存的更新：在更新分布式缓存中的数据时，为了确保数据的一致性和准确性，
             * 可以使用分布式锁来防止多个节点同时更新同一数据项。 不能同时更新的一些业务
             *
             * 数据库记录的修改：在修改数据库中的记录时，如果涉及到多个步骤或需要保证原子性，
             * 可以使用分布式锁来确保这些步骤在同一时间只被一个服务实例执行。
             *
             * 防止资源耗尽：在某些情况下，为了防止资源（如文件描述符、数据库连接等）被过度使用而导致系统崩溃，
             * 可以使用分布式锁来限制对资源的并发访问。
             *
             * 分布式会话管理：在分布式系统中，如果需要在多个服务实例之间共享用户会话信息，并且需要确保会话信息的同步和一致性
             * ,可以使用分布式锁来保护会话信息的更新。
             *
             * 防止重复提交：在处理表单提交或API请求时，为了防止由于网络延迟或重试机制导致的重复提交，
             * 可以使用分布式锁来标记已经处理过的请求。
             */







            System.out.println("Locked and executing business logic...");
        } finally {
            lock.unlock(); // 确保锁被释放
        }


    }

}

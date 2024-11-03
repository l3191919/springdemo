package com.lyz.shiwushixiao;

import com.alibaba.fastjson.JSON;
import org.springframework.aop.framework.AopContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeoutException;

/**
 * 事务失效
 * 循环依赖解决
 */

@Service("shiwushixiao")
public class Shiwushixiao implements ShiwushixiaoService{
    @Autowired
    JdbcTemplate jdbcTemplate;


    /***
     *
     * @throws TimeoutException
     */

    @Transactional(rollbackFor = {TimeoutException.class})
    public void add () throws TimeoutException {
        //是否有@Service("paymentService")
        //是否有@Transactional
        //是否有try catch 自己处理了异常
        //TimeoutException 使用 @Transactional 不会回滚
        // 只会帮我们捕捉 RuntimeException
        //TimeoutException RuntimeException是同一级别的报错所以不回滚
        //@Transactional(rollbackFor = {TimeoutException.class}) 就可以回滚

        jdbcTemplate.execute("insert into "+
                " test_paper (cname,score,name) values ('语文',22,'张三');"
        );

        throw new TimeoutException("出错");
    }
    @Transactional(rollbackFor = {TimeoutException.class})
    public void add1 () throws TimeoutException {

        jdbcTemplate.execute("insert into "+
                " test_paper (cname,score,name) values ('语文',22,'张三');"
        );
        //直接调用query()不行,这个是不通过spring的所以不行
        //需要经过AOP增强,通过增强自带的try catch 进行处理
        // @Autowired
        // PaymentService paymentService;
        //使用 paymentService.query(); 是可以的但是会导致循环依赖

        //使用AopContext 调用了ThreadLocal
        ((Shiwushixiao) AopContext.currentProxy()).query();

    }

    @Transactional(propagation = Propagation.NEVER)
    public void query (){
        List<Map<String,Object>> selectData = jdbcTemplate.queryForList("select * from test_paper");
        System.out.println(JSON.toJSONString(selectData));
    }


    /**
     * 目前不能实现主线程和子线程的同时回滚(在子线程出问题的情况下,不能使主线程也回滚)
     * 这两个方法各自使用自己的ThreadLoacl ,如果共享一个数据库连接 2pc 3pc 等等
     * 解决方案:编程式事务 分布式事务
     *
     */

    @Transactional
    public void add2 (){
        jdbcTemplate.execute("insert into "+
                " test_paper (cname,score,name) values ('语文123',22,'张三213');"
        );
        Thread thread = new Thread(()->{
            try {
                ((Shiwushixiao) AopContext.currentProxy()).threadAdd();
            } catch (TimeoutException e) {
                e.printStackTrace();
            }
        });
        thread.start();
        try{
            thread.join();
        }catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    @Transactional(rollbackFor = {TimeoutException.class})
    public void threadAdd () throws TimeoutException {
        jdbcTemplate.execute("insert into "+
                " test_paper (cname,score,name) values ('语文',22,'张三');"
        );

        throw new TimeoutException("出错");
    }
}

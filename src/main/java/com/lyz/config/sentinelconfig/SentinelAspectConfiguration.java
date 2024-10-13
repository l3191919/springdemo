package com.lyz.config.sentinelconfig;

import com.alibaba.csp.sentinel.annotation.aspectj.SentinelResourceAspect;
import com.alibaba.csp.sentinel.slots.block.RuleConstant;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRule;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRuleManager;
import com.lyz.properties.FlowControlProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Filter;

@Configuration
public class SentinelAspectConfiguration {

    @Autowired
    FlowControlProperties flowControlProperties;

    @Bean
    public SentinelResourceAspect sentinelResourceAspect() {
        return new SentinelResourceAspect();
    }

    //你可以创建一个配置类，在这个类中定义限流规则，
    // 并使用 @PostConstruct 注解来确保在 Spring 容器中的所有 bean 都初始化之后执行限流规则的初始化。
    @PostConstruct
    public void initFlowQpsRule() {
        List<FlowRule> rules = new ArrayList<>();
        FlowRule rule = new FlowRule(flowControlProperties.getRules().get(0).getRefResource());
        rule.setCount(flowControlProperties.getRules().get(0).getCount());
        rule.setGrade(flowControlProperties.getRules().get(0).getGrade());
        rule.setLimitApp(flowControlProperties.getRules().get(0).getLimitApp());
        rules.add(rule);
        FlowRuleManager.loadRules(rules);
    }


}

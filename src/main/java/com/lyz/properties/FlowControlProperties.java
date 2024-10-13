package com.lyz.properties;

import com.alibaba.csp.sentinel.slots.block.flow.FlowRule;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRuleManager;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

// 定义一个配置属性类来映射 YAML 中的 flow-control.rules
@Configuration
@ConfigurationProperties(prefix = "flow-control")
//@RefreshScope 实时同步刷新nacos配置
@Data
public class FlowControlProperties {

    private List<FlowRule> rules = new ArrayList<>();

    // 假设你有一个 FlowRule 类来映射单个限流规则


    // getter 和 setter
    public List<FlowRule> getRules() {
        return rules;
    }

    public void setRules(List<FlowRule> rules) {
        this.rules = rules;
    }

    // 在配置类加载后，将规则加载到 FlowRuleManager
//    @PostConstruct
//    public void loadRules() {
//        FlowRuleManager.loadRules(this.rules);
//    }
}
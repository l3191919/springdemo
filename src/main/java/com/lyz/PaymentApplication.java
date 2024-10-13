package com.lyz;

import com.alibaba.csp.sentinel.slots.block.RuleConstant;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRule;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRuleManager;
import com.lyz.controller.PaymentController1;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import java.util.ArrayList;
import java.util.List;


@SpringBootApplication
@Slf4j
public class PaymentApplication {

	public static void main(String[] args) {
		SpringApplication.run(PaymentApplication.class, args);

//		int i = 0;
//		while (true){
//			try {
//				Thread.sleep(1000);
//			} catch (InterruptedException e) {
//				e.printStackTrace();
//			}
//			log.info("启动JAVA:{}秒",i++);
//		}
		//用来OOM测试
//		List<user> list = new ArrayList<>();
//		int i = 0;
//		while (true){
//			i++;
//			user u = user.builder().name(i+"").age(i).id(System.currentTimeMillis()+"").build();
//			System.out.println(u.getId());
//			list.add(u);
//		}
	}


}


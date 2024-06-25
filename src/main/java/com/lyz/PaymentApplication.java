package com.lyz;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.lyz.dao"})
public class PaymentApplication {

	public static void main(String[] args) {
		SpringApplication.run(PaymentApplication.class, args);
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


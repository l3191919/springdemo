package com.lyz.example.demo;

import com.lyz.service.impl.MyCompletableFuture;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class DemoApplicationTests {
	@Autowired
	MyCompletableFuture myCompletableFuture;
	@Test
	void contextLoads() {
		myCompletableFuture.AsyncDemo04();
	}

}

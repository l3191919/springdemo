package com.lyz.example.demo;

import com.lyz.service.impl.MyCompletableFuture;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

@SpringBootTest
class DemoApplicationTests {
	@Autowired
	MyCompletableFuture myCompletableFuture;
	@Test
	void contextLoads() {
		myCompletableFuture.AsyncDemo04();
	}
	@Test
	void contextLoads1() {
		String timestamp1 = "2023-10-01 12:00:00";
		String timestamp2 = "2023-10-05 15:30:00";

		// 定义日期时间格式化器
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

		// 将字符串转换为 LocalDateTime 对象
		LocalDateTime dateTime1 = LocalDateTime.parse(timestamp1, formatter);
		LocalDateTime dateTime2 = LocalDateTime.parse(timestamp2, formatter);

		// 计算两个日期时间之间的差异（以天为单位）
		long daysBetween = ChronoUnit.DAYS.between(dateTime1.toLocalDate(), dateTime2.toLocalDate());

		// 输出结果
		System.out.println("两个时间戳之间相差 " + daysBetween + " 天");
		/**
		 * 我们定义了两个时间戳字符串 timestamp1 和 timestamp2。
		 * 使用 DateTimeFormatter 来指定时间戳的格式。
		 * 使用 LocalDateTime.parse 方法将字符串解析为 LocalDateTime 对象。
		 * 使用 ChronoUnit.DAYS.between 方法计算两个 LocalDate（只包含日期部分）之间的天数差异。注意，这里我们将 LocalDateTime 转换为 LocalDate，因为我们只关心日期部分。
		 * 最后，打印出两个时间戳之间相差的天数。
		 * 如果你想要考虑时间部分（即如果两个日期在同一天但时间不同，也计算为一天差异），你需要使用不同的方法，因为 ChronoUnit.DAYS 只考虑日期。在这种情况下，你可以使用 Duration 来计算总的时间差，然后将其转换为天数：
		 */

	}

	@Test
	void contextLoads2() {
		String timestamp1 = "2023-10-01T12:00:00+02:00";
		String timestamp2 = "2023-10-05T15:30:00+02:00";

		DateTimeFormatter formatter = DateTimeFormatter.ISO_OFFSET_DATE_TIME;

		OffsetDateTime dateTime1 = OffsetDateTime.parse(timestamp1, formatter);
		OffsetDateTime dateTime2 = OffsetDateTime.parse(timestamp2, formatter);

		// 转换为UTC时间（如果需要），或者直接使用OffsetDateTime进行比较
		// 这里我们直接使用OffsetDateTime，因为时区已经考虑在内
		Duration duration = Duration.between(dateTime1, dateTime2);
		double daysBetween = duration.getSeconds()/ (24.0 * 60 * 60);

		System.out.println("两个时间戳之间相差 " + daysBetween + " 天");
	}
}

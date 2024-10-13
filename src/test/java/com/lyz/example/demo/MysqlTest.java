package com.lyz.example.demo;
import com.lyz.entities.Payment;

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

public class MysqlTest {



        public static void main(String[] args) {
            // 数据库连接信息
            String url = "jdbc:mysql://localhost:3306/cloud?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC";
            String user = "root";
            String password = "2579494";

            // 准备SQL语句
            String sql = "INSERT INTO payment (serial,company_id,moneey) VALUES (?,?,?)";

            // 假设这是你要插入的数据列表
            //List<YourDataModel> dataList = ...; // 假设你已经有了一个包含数据的列表
            List<Payment> dataList = new ArrayList<>();
            for (int i =0;i>10000 ;i++) {
                Payment payment1 = Payment.builder().serial("2024年7月15日19:09:47").companyId(123455L+i).moneey(BigDecimal.valueOf(123.123+1)).build();
                dataList.add(payment1);
            }

            // 加载数据库驱动（对于JDBC 4.0及以上版本，这一步通常是可选的）
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
                return;
            }

            // 建立数据库连接
            try (Connection conn = DriverManager.getConnection(url, user, password);
                 PreparedStatement pstmt = conn.prepareStatement(sql)) {
                int count = 0;
                // 遍历数据列表，并添加到批次中
                for (Payment data : dataList) {
                    pstmt.setString(1, data.getSerial());
                    pstmt.setLong(2, data.getCompanyId());
                    pstmt.setBigDecimal(3,data.getMoneey());
                    // 设置其他列的值...
                    pstmt.addBatch();
                    count ++;
                    // 可选：如果批次大小太大，可以提前执行批次以避免内存溢出
                    // 例如，每1000条记录执行一次批次
                    if (count % 1000 == 0) {
                        pstmt.executeBatch();
                        pstmt.clearBatch();
                    }
                }

                // 执行剩余的批次
                if(count>0){
                    pstmt.executeBatch();
                }
                // 关闭PreparedStatement（在try-with-resources中自动完成）

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }


}

package com.lyz.util;

import javax.mail.*;
import javax.mail.internet.*;
import java.io.IOException;
import java.util.Properties;

public class EmailSenderUtil {


    public static void sendEmail(String host, String port,
                                 final String userName, final String password, String toAddress,
                                 String subject, String content) {

        // 设置邮件服务器的配置
        Properties properties = new Properties();
        properties.put("mail.smtp.host", host);
        properties.put("mail.smtp.port", port);
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true"); // 如果需要TLS

        // 获取默认的 Session 对象
        Session emailSession = Session.getInstance(properties,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(userName, password);
                    }
                });

        try {
            // 创建默认的 MimeMessage 对象
            Message message = new MimeMessage(emailSession);

            // 设置发件人
            message.setFrom(new InternetAddress(userName));

            // 设置收件人
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(toAddress));

            // 设置邮件主题
            message.setSubject(subject);

            // 设置邮件内容
            message.setText(content);

            // 发送邮件
            Transport.send(message);

            System.out.println("邮件发送成功....");

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        // 邮件服务器配置
        String host = "smtp.your-email-provider.com"; // 替换为你的邮件服务器地址 smtp.your-email-provider.com
        String port = "587"; // SMTP端口

        // 邮件账户信息
        final String userName = "your-email@example.com"; // 替换为你的邮件地址
        final String password = "your-email-password"; // 替换为你的邮件密码或应用专用密码

        // 收件人地址
        String toAddress = "recipient@example.com"; // 替换为收件人地址

        // 邮件主题和内容
        String subject = "Word Document Email";
        String content = "";

        try {
            // 读取Word文档内容
            content = ShipPlanEmailTemplateUtil.readTemplateFile("path/to/your/document.docx");
        } catch (IOException e) {
            e.printStackTrace();
        }

        // 发送邮件
        sendEmail(host, port, userName, password, toAddress, subject, content);
    }
}

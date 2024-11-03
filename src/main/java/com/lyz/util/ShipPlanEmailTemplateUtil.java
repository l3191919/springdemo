package com.lyz.util;

import javax.mail.*;
import javax.mail.internet.*;
import java.util.*;
import java.util.regex.*;
import java.io.*;
import org.springframework.stereotype.Component;


public class ShipPlanEmailTemplateUtil {

    // 读取模板文件内容
    public static String readTemplateFile(String filePath) throws IOException {
        StringBuilder content = new StringBuilder();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                content.append(line).append("\n");
            }
        }
        return content.toString();
    }

    // 替换模板中的变量
    public static String replaceVariables(String template, Map<String, String> variables) {
        Pattern pattern = Pattern.compile("\\{\\{(\\w+)\\}\\}");
        Matcher matcher = pattern.matcher(template);
        StringBuffer sb = new StringBuffer();

        while (matcher.find()) {
            String variableName = matcher.group(1);
            String replacement = variables.getOrDefault(variableName, "");
            matcher.appendReplacement(sb, Matcher.quoteReplacement(replacement));
        }
        matcher.appendTail(sb);
        return sb.toString();
    }


}

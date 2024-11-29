package com.lyz.entities;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserImportVO {
    @ExcelProperty("姓名")
    private String names;
    @ExcelProperty("年龄")
    private Long ages;
    @ExcelProperty("身份证")
    private Long idCode;
    @ExcelProperty("性别")
    private String sex;

}

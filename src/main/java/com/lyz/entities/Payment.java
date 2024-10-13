package com.lyz.entities;

import com.lyz.annotation.NotEmptyOrNull;
import com.lyz.annotation.NotNullNumber;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Payment implements Serializable {
    private Long id;
    //@NotEmptyOrNull 自定义的注解 message不能进行变量报错不能指定到哪个属性,貌似不好实现

    @NotNull(message = "不能为空") //javax.validation.constraints 自带的
    @NotEmpty(message = "不能为空字符串")//javax.validation.constraints 自带的
    private String serial;
    //@NotNullNumber
    private Long companyId;
    //@NotNullNumber
    private BigDecimal moneey;
//
//    public Long getId() {
//        return id;
//    }
//
//    public void setId(Long id) {
//        this.id = id;
//    }
//
//    public String getSerial() {
//        return serial;
//    }
//
//    public void setSerial(String serial) {
//        this.serial = serial;
//    }
//
//    public Long getCompanyId() {
//        return companyId;
//    }
//
//    public void setCompanyId(Long companyId) {
//        this.companyId = companyId;
//    }
//
//    public BigDecimal getMoneey() {
//        return moneey;
//    }
//
//    public void setMoneey(BigDecimal moneey) {
//        this.moneey = moneey;
//    }
}

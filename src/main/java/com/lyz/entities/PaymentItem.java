package com.lyz.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PaymentItem implements Serializable {
    private Long paymentId1;
    private String serial;
    private Long companyId1;
    private BigDecimal moneey;

    private List<Company> companies = new ArrayList<>();

    public Long getPaymentId1() {
        return paymentId1;
    }

    public void setPaymentId1(Long paymentId1) {
        this.paymentId1 = paymentId1;
    }

    public String getSerial() {
        return serial;
    }

    public void setSerial(String serial) {
        this.serial = serial;
    }

    public Long getCompanyId1() {
        return companyId1;
    }

    public void setCompanyId1(Long companyId1) {
        this.companyId1 = companyId1;
    }

    public BigDecimal getMoneey() {
        return moneey;
    }

    public void setMoneey(BigDecimal moneey) {
        this.moneey = moneey;
    }

    public List<Company> getCompanies() {
        return companies;
    }

    public void setCompanies(List<Company> companies) {
        this.companies = companies;
    }
}

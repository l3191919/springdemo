package com.lyz.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Date;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
class UserReport implements Serializable {
    private Long repoetId;
    private BigDecimal report;
    private Date date;
    private Long userId;

}
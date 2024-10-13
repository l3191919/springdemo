package com.lyz.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserItem implements Serializable {
    private Long userId;
    private String name;
    private Integer age;
    private String sex;
    private List<UserReport> userReportList;


}
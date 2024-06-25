package com.lyz.util;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashMap;

/**
 * 线程交换参数实体类
 *
 * @author xiaojw
 * @since 2020/4/11
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ThreadSwapParameter {

    /**
     * 品牌编码
     */
    private String brandId;

    /**
     * 企业ID
     */
    private String enterPriseId;
    /**
     * 用户ID
     */
    private String beanId;
    /**
     * 品牌
     */
    private String appId;

    /**
     * 设备标识
     */
    private String terminal;

    /**
     * 通用key
     */
    private String commonKey;

    /**
     * 通用map
     */
    private HashMap commonMap;

    /**
     * 四维版本号
     */
    private String  cVer;

    private String rs;

    private String ip;

    private String brand;

    private String methodName;

}

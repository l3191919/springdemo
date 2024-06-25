package com.lyz.util;


import com.lyz.enums.AppErrorEnum;
import com.lyz.util.error.NEException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.bouncycastle.util.encoders.Hex;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.security.MessageDigest;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;
import java.util.concurrent.ConcurrentHashMap;


/**
 * App公共类
 *
 * @author xiaojw
 * @since 2020/1/14
 */
@Component
@Slf4j
public class AppUtil {

    @Resource
    private HttpServletRequest priRequest;


    private static HttpServletRequest request;

    @PostConstruct
    public void init() {
        request = priRequest;

    }

    /**
     * 获取品牌标识
     *
     * @return
     */
    public static String getBrand() {
        return getHeader("brand");
    }

    /**
     * 获取品牌编码
     *
     * @return
     */
    public static String getBrandId() {
        return getHeader("brandId");
    }

    /**
     * 获取设备标识
     *
     * @return
     */
    public static String getTerminal() {
        return getHeader("terminal");
    }

    /**
     * 获取渠道
     *
     * @return
     */
    public static String getChannel() {
        return getHeader("channel");
    }

    /**
     * 获取用户ID
     *
     * @return
     */
    public static String getBeanId() {
        return getHeader("brandId");
    }

    /**
     * 获取版本号
     *
     * @return
     */
    public static String getCVer() {
        return getHeader( "cVer");
    }

    /**
     * 获取企业ID
     *
     * @return
     */
    public static String getEnterPriseId() {
        return getHeader("enterPriseId");
    }

    /**
     * 获取登录版本
     *
     * @return
     */
    public static String getVersion() {
        return getHeader("cVer");
    }

    /**
     * 获取用户accessToken
     *
     * @return
     */
    public static String getAccessToken() {
        return getHeader("accessToken");
    }

    /**
     * 获取请求头信息
     *
     * @param headerName
     * @return
     */
    public static String getHeader(String headerName) {
        return getHeader(headerName, true);
    }

    /**
     * 获取请求头信息
     *
     * @param headerName
     * @param enableNull 是否允许空值
     * @return
     */
    public static String getHeader(String headerName, boolean enableNull) {
        String headerValue;
        try {
            headerValue = request.getHeader(headerName);
        } catch (IllegalStateException ex) {
            //子线程中调用该方法会报IllegalStateException错误{No thread-bound request found}，从本地线程中拿一下
            headerValue = getHeaderFromSwapThreadLocal(headerName);
        }
        if (StringUtils.isBlank(headerValue) && !enableNull) {
            throw new NEException(AppErrorEnum.EX_HEADER_IS_NOT_EXIST);
        } else {
            return StringUtils.isBlank(headerValue) ? "" : headerValue;
        }
    }

    public static ThreadSwapParameter getSwapThreadLocalParameter() {
        return getSwapThreadLocal().get();
    }

    public static String getHeaderFromSwapThreadLocal(String header) {
        switch (header) {
            case "beanId":
                return getSwapThreadLocalParameter().getBeanId();
            case "terminal":
                return getSwapThreadLocalParameter().getTerminal();
            case "brandId":
                return getSwapThreadLocalParameter().getBrandId();
            case "enterPriseId":
                return getSwapThreadLocalParameter().getEnterPriseId();
            case "appId":
                return getSwapThreadLocalParameter().getAppId();
            case "commonKey":
                return getSwapThreadLocalParameter().getCommonKey();
            case "cVer":
                return getSwapThreadLocalParameter().getCVer();
            default:
                return "";
        }
    }
    /**
     * Rsa解密
     *
     * @param data
     * @return
     * @remark 解密前台传递的密码等信息
     */
/*    public static String decryptWithRsa(String data) {
        try {
            return RSAUtil.decrypt(data, RSAUtil.getPrivateKey(configuration.getRsaPrivateKey()));
        } catch (Exception e) {
            log.info("rsa解密失败，密文信息：{}", data);
            throw new NEException(AppErrorEnum.EX_RSA_DECRYPT_FAIL);
        }
    }*/

    /**
     * Rsa加密
     *
     * @param data
     * @return
     * @remark 加密前台传递的密码等信息
     */
    /*public static String encryptWithRsa(String data) {
        try {
            return RSAUtil.encrypt(data, RSAUtil.getPublicKey(configuration.getRsaPublicKey()));
        } catch (Exception e) {
            log.info("rsa加密失败，data信息：{}", data);
            throw new NEException(AppErrorEnum.EX_RSA_DECRYPT_FAIL);
        }
    }*/


    public static volatile Map<String, String> headerMap = new ConcurrentHashMap();

    public static String putMapHeader(String key, String value) {
        return headerMap.put(key, value);
    }

    public static String getMapHeader(String key) {
        return headerMap.get(key);
    }


    public static String getSign(String timestamp, Map<String, Object> paramMap, String appKey, String appSecret) {
        TreeMap<String, Object> map = new TreeMap<>();
        map.put("appKey", appKey);
        map.put("timestamp", timestamp);
        if (null != paramMap) {
            map.putAll(paramMap);
        }
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<String, Object> entry : map.entrySet()) {
            if (entry.getValue() != null && !"".equals(entry.getValue()) && !"null".equals(entry.getValue())) {
                sb.append(entry.getKey() + "=" + entry.getValue());
            }
        }

        String result = appSecret + sb.toString() + appSecret;
        log.info("签名前：result:" + result);
        result = Md5(result);
        log.info("MD5加密后result:" + result);

        return result.toUpperCase();
    }

    /**
     * MD5加密
     *
     * @param s
     * @return
     */
    public final static String Md5(String s) {
        try {
            byte[] btInput = s.getBytes();
            MessageDigest mdInst = MessageDigest.getInstance("MD5");
            mdInst.update(btInput);
            byte[] md = mdInst.digest();
            return new String(Hex.encode(md));
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String dateToStrLong(String dateDate) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-ddHH:mm:ss");
        Date date = null;
        try {
            date = formatter.parse(dateDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateString = format.format(date);
        return dateString;
    }


    private static final ThreadLocal<ThreadSwapParameter> swapThreadLocal = new ThreadLocal<>();

    public static ThreadLocal<ThreadSwapParameter> getSwapThreadLocal() {
        return swapThreadLocal;
    }

    public static void clearSwapThreadLocalParameter() {
        getSwapThreadLocal().remove();
    }

    public static Map<String, Object> getMap() {
        return new HashMap<>();
    }

    /**
     * 添加社区的本地线程参数
     *
     * @return
     */
    public static void addSwapThreadLocalParameter(ThreadSwapParameter parameter) {
        ThreadLocal<ThreadSwapParameter> threadLocal = getSwapThreadLocal();
        threadLocal.set(parameter);
    }

    public static ThreadSwapParameter getCommunityThreadSwapParameter() {
        return ThreadSwapParameter.builder()
                .beanId(AppUtil.getBeanId())
                .enterPriseId(AppUtil.getEnterPriseId())
                .brandId(AppUtil.getBrandId())
                .terminal(AppUtil.getTerminal())
                .cVer(AppUtil.getVersion())
                .build();
    }

    public static ThreadSwapParameter getUserAccountThreadSwapParameter() {
        return ThreadSwapParameter.builder()
                .beanId(AppUtil.getBeanId())
                .enterPriseId(AppUtil.getEnterPriseId())
                .brandId(AppUtil.getBrandId())
                .terminal(AppUtil.getTerminal())
                .cVer(AppUtil.getVersion())
                .build();
    }


}




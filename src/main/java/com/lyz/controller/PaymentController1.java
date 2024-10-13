package com.lyz.controller;

import com.alibaba.csp.sentinel.Entry;
import com.alibaba.csp.sentinel.SphU;
import com.alibaba.csp.sentinel.Tracer;
import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.support.ExcelTypeEnum;
import com.alibaba.fastjson.JSON;
import com.lyz.entities.*;
import com.lyz.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@RestController
public class PaymentController1 {
    @Autowired
    PaymentService paymentService;

    @GetMapping(value = "payment1/get/{id}")
    public Payment getPaymentById(@PathVariable("id") Long id) {
        return paymentService.getPaymentById(id);
    }

    @GetMapping(value = "payment1/threadget/{id}")
    public Payment ThreadGetPaymentById(@PathVariable("id") Long id) {
        return paymentService.getThreadPaymentById(id);
    }

//    @GetMapping(value = "payment1/pushMessage")
//    public void sendMessage() {
//        paymentService.sendMessage();
//    }

    @PostMapping(value = "payment1/uploadExcel", headers = "content-type=multipart/form-data")
    public CommonResult uploadExcel(MultipartFile file) throws Exception {
        String fileName = file.getOriginalFilename(); //获取文件名
        CommonResult commonResult = new CommonResult();
        //ExcelReaderBuilder e = EasyExcel.read(file.getInputStream());
        Long size = file.getSize();
        log.info("文件大小:{}", JSON.toJSON(size));
        String fileXlsx = fileName.substring(fileName.length() - 5);       //获取文件的后缀名为xlsx
        String fileXls = fileName.substring(fileName.length() - 4);
        if (!(fileXlsx.equals(".xlsx") || fileXls.equals(".xls"))) {   //如果不是excel文件
            commonResult.setCode(500);
            commonResult.setMessage("文件格式错误");
            return commonResult;
        }
        ExcelTypeEnum type = null;
        if (fileXlsx.equals(".xlsx")) {
            type = ExcelTypeEnum.XLSX;
        } else if (fileXls.equals(".xls")) {
            type = ExcelTypeEnum.XLS;
        }


        // 存储读取到的数据
        List<UserLoadData> userLoadData = EasyExcel.read(file.getInputStream()).excelType(type).doReadAllSync();
        for (UserLoadData u : userLoadData) {
            log.info("姓名:{}", u.getName());
            log.info("年龄:{}", JSON.toJSONString(u.getAge()));
            log.info("性别:{}", u.getAge());
        }


        commonResult.setCode(400);
        commonResult.setMessage("成功");
        return commonResult;
    }

    @PostMapping(value = "payment1/setredisdata")
    public CommonResult setRedisData(@RequestBody UserData userData) {
        String key = paymentService.setRedisData(userData);
        CommonResult commonResult = new CommonResult();
//        if (!b){
//            commonResult.setCode(500);
//            commonResult.setMessage("失败");
//        }else {
//            commonResult.setCode(400);
//            commonResult.setMessage("成功");
//        }
        Map<String, String> map = new HashMap<>();
        map.put("key", key);
        commonResult.setData(map);
        commonResult.setCode(400);
        commonResult.setMessage("成功");
        return commonResult;
    }


    @PostMapping(value = "payment1/setredisdata1")
    public CommonResult setRedisData1(@RequestBody Map<Object,Object> map) {
        String key = paymentService.setRedisData1( map);
        CommonResult commonResult = new CommonResult();
//        if (!b){
//            commonResult.setCode(500);
//            commonResult.setMessage("失败");
//        }else {
//            commonResult.setCode(400);
//            commonResult.setMessage("成功");
//        }
        Map<String, String> mapreturn = new HashMap<>();
        map.put("key", key);
        commonResult.setData(mapreturn);
        commonResult.setCode(400);
        commonResult.setMessage("成功");
        return commonResult;
    }

    @PostMapping(value = "payment1/getredisdata")
    public CommonResult getRedisData(@RequestBody Map<String, String> map) {
        UserData userData = paymentService.getRedisData(map.get("key"));

        CommonResult commonResult = new CommonResult();
        commonResult.setData(userData);
        commonResult.setCode(400);
        commonResult.setMessage("成功");
        return commonResult;
    }
    @PostMapping(value = "payment1/getredisdata1")
    public CommonResult getRedisData1(@RequestBody Map<String, String> map) {
        Map<Object,Object> userData = paymentService.getRedisData1(map.get("Rediskey"),map.get("Hashkey"));

        CommonResult commonResult = new CommonResult();
        commonResult.setData(userData);
        commonResult.setCode(400);
        commonResult.setMessage("成功");
        return commonResult;
    }



    @PostMapping(value = "payment1/getRedisDistance")
    public CommonResult getRedisDistance(@RequestBody Map<String, RedisCityData> redisCityDataMap) {
        String redisLength = paymentService.redisLength(redisCityDataMap.get("originCity"), redisCityDataMap.get("targetCity"));
        CommonResult commonResult = new CommonResult();
        commonResult.setData(redisLength);
        commonResult.setCode(400);
        commonResult.setMessage("成功");
        return commonResult;
    }

    /**
     * 默认值 defaultValue
     * key名 name.
     * 这个例子中，如果客户端请求 /payment1/selectById?id=1，则 greet 方法会接收到 id 参数的值 "1"，
     * 并返回 。如果没有提供 name 参数，则使用默认值 "defaultValue"。
     *
     * @param id
     * @return
     */
    @GetMapping(value = "payment1/selectById")
    public CommonResult selectById(@RequestParam(name = "id", defaultValue = "defaultValue") Long id) {
        List<Payment> payments = paymentService.selectById(id);

        CommonResult commonResult = new CommonResult();
        commonResult.setData(payments);
        commonResult.setCode(400);
        commonResult.setMessage("成功");
        return commonResult;
    }


    @GetMapping(value = "payment1/selectPaymentItemById/{id}")
    public CommonResult selectPaymentItemById(@PathVariable("id") Long id) {
        PaymentItem paymentItem = paymentService.selectPaymentItemById(id);

        CommonResult commonResult = new CommonResult();
        commonResult.setData(paymentItem);
        commonResult.setCode(400);
        commonResult.setMessage("成功");
        return commonResult;
    }

    @GetMapping(value = "payment1/selectUserItemById/{id}")
    public UserItem selectUserItemById(@PathVariable("id") Long id) {
        UserItem paymentItem = paymentService.selectUserItemById(id);
        return paymentItem;
    }


    //资源名称
    public static final String RESOURCE_NAME = "userItemById1";

    @GetMapping(value = "payment1/selectUserItemById1/{id}")
    public CommonResult selectUserItemById1(@PathVariable("id") Long id) {
        CommonResult commonResult = new CommonResult();
        Entry entry = null;
        try {
            // 被保护的业务逻辑
            entry = SphU.entry(RESOURCE_NAME);
            UserItem paymentItem = paymentService.selectUserItemById(id);
            commonResult.setData(paymentItem);
            commonResult.setCode(400);
            commonResult.setMessage("成功");
        } catch (BlockException e) {
            commonResult.setCode(500);
            commonResult.setMessage("资源访问被限流");
        } catch (Exception e) {
            // 若需要配置降级规则，需要通过这种方式记录业务异常
            Tracer.traceEntry(e, entry);
        } finally {
            // 务必保证 exit，务必保证每个 entry 与 exit 配对
            if (entry != null) {
                entry.exit();
            }
        }
        return commonResult;
    }

    // blockHandler = "selectUserItemById2Block" 绑定的是下面的方法名
    @GetMapping(value = "payment1/selectUserItemById2/{id}")
    //@SentinelResource(value = RESOURCE_NAME, blockHandler = "selectUserItemById2Block")
    //blockHandler = "selectUserItemById2Block" 是下面的方法
    @SentinelResource(value = "payment1/selectUserItemById2/{id}")
    public CommonResult selectUserItemById2(@PathVariable("id") Long id) {
        CommonResult commonResult = new CommonResult();
        UserItem paymentItem = paymentService.selectUserItemById(id);
        commonResult.setData(paymentItem);
        commonResult.setCode(400);
        commonResult.setMessage("成功");
        return commonResult;
    }

    //注意细节，一定要跟原函数的返回值和形参一致，并且形参最后要加个BlockException参数
    //否则会报错，FlowException: null
    public CommonResult selectUserItemById2Block( Long id, BlockException ex) {
        //打印异常
        ex.printStackTrace();
        CommonResult commonResult = new CommonResult();
        commonResult.setCode(500);
        commonResult.setMessage("资源访问被限流");
        return commonResult;
    }
}

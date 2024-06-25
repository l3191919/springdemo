package com.lyz.controller;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.support.ExcelTypeEnum;
import com.alibaba.fastjson.JSON;
import com.lyz.entities.*;
import com.lyz.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@RestController
public class PaymentController1 {
    @Autowired
    PaymentService paymentService;

    @GetMapping(value = "payment1/get/{id}")
    public  Payment getPaymentById(@PathVariable("id") Long id){
        return    paymentService.getPaymentById(id);
    }

    @GetMapping(value = "payment1/threadget/{id}")
    public Payment ThreadGetPaymentById(@PathVariable("id") Long id){
        return    paymentService.getThreadPaymentById(id);
    }

    @GetMapping(value = "payment1/pushMessage")
    public  void sendMessage(){
       paymentService.sendMessage();
    }

    @PostMapping(value = "payment1/uploadExcel",headers = "content-type=multipart/form-data")
    public CommonResult uploadExcel(MultipartFile file)throws Exception{
        String fileName = file.getOriginalFilename(); //获取文件名
        CommonResult commonResult = new CommonResult();
        //ExcelReaderBuilder e = EasyExcel.read(file.getInputStream());
        Long size = file.getSize();
        log.info("文件大小:{}", JSON.toJSON(size));
        String fileXlsx = fileName.substring(fileName.length()-5);       //获取文件的后缀名为xlsx
        String fileXls = fileName.substring(fileName.length()-4);
        if(!(fileXlsx.equals(".xlsx") || fileXls.equals(".xls"))){   //如果不是excel文件
            commonResult.setCode(500);
            commonResult.setMessage("文件格式错误");
            return commonResult;
        }
        ExcelTypeEnum type = null;
        if(fileXlsx.equals(".xlsx")){
            type = ExcelTypeEnum.XLSX;
        }else if( fileXls.equals(".xls")){
            type =ExcelTypeEnum.XLS;
        }


        // 存储读取到的数据
        List<UserLoadData> userLoadData = EasyExcel.read(file.getInputStream()).excelType(type).doReadAllSync();
        for (UserLoadData u: userLoadData) {
            log.info("姓名:{}",u.getName());
            log.info("年龄:{}",JSON.toJSONString(u.getAge()));
            log.info("性别:{}",u.getAge());
        }


        commonResult.setCode(400);
        commonResult.setMessage("成功");
        return commonResult;
    }
    @PostMapping(value = "payment1/setredisdata")
    public CommonResult setRedisData(@RequestBody UserData userData){
       String key = paymentService.setRedisData( userData);
        CommonResult commonResult = new CommonResult();
//        if (!b){
//            commonResult.setCode(500);
//            commonResult.setMessage("失败");
//        }else {
//            commonResult.setCode(400);
//            commonResult.setMessage("成功");
//        }
        Map<String,String> map = new HashMap<>();
        map.put("key",key);
        commonResult.setData(map);
        commonResult.setCode(400);
        commonResult.setMessage("成功");
        return commonResult;
    }
    @PostMapping(value = "payment1/getredisdata")
    public CommonResult getRedisData (@RequestBody Map<String,String> map){
        UserData userData = paymentService.getRedisData(map.get("key"));

        CommonResult commonResult = new CommonResult();
        commonResult.setData(userData);
        commonResult.setCode(400);
        commonResult.setMessage("成功");
        return commonResult;
    }

    @PostMapping(value = "payment1/getRedisDistance")
    public CommonResult getRedisDistance (@RequestBody Map<String, RedisCityData> redisCityDataMap){
        String redisLength = paymentService.redisLength(redisCityDataMap.get("originCity"),redisCityDataMap.get("targetCity"));
        CommonResult commonResult = new CommonResult();
        commonResult.setData(redisLength);
        commonResult.setCode(400);
        commonResult.setMessage("成功");
        return commonResult;
    }

}

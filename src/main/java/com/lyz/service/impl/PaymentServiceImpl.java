package com.lyz.service.impl;




import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.enums.WriteDirectionEnum;
import com.alibaba.excel.write.builder.ExcelWriterBuilder;
import com.alibaba.excel.write.metadata.WriteSheet;
import com.alibaba.excel.write.metadata.fill.FillConfig;
import com.alibaba.excel.write.metadata.fill.FillWrapper;
import com.alibaba.excel.write.metadata.style.WriteCellStyle;
import com.alibaba.excel.write.metadata.style.WriteFont;
import com.alibaba.excel.write.style.HorizontalCellStyleStrategy;
import com.alibaba.excel.write.style.column.LongestMatchColumnWidthStyleStrategy;
import com.alibaba.excel.write.style.row.SimpleRowHeightStyleStrategy;
import com.alibaba.fastjson.JSON;
import com.lyz.alibaba.pattern.zerenlian.InvestChainPatternService;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lyz.dao.PaymentDao;
import com.lyz.dao.UserDao;
import com.lyz.entities.*;
import com.lyz.service.PaymentService;
import com.lyz.service.ThreadPoolService;

import com.lyz.util.ObjectToMapConverterUtil;
import com.lyz.util.RedisService;
import com.lyz.util.excel.RwhzCustemhandler;
import jxl.Workbook;
import lombok.extern.slf4j.Slf4j;

import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.springframework.aop.framework.AopContext;
import org.springframework.beans.factory.annotation.Autowired;


import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.util.*;

@Service("paymentService")
@Slf4j
public class PaymentServiceImpl implements PaymentService {
    @Autowired
    RedisService redisService;

    @Autowired
    PaymentDao paymentDao;
     @Autowired
    UserDao userDao;

    @Autowired
    InvestChainPatternService investChainPatternService;
//    @Autowired
//    KafkaProducer kafkaProducer;
    //@Autowired
    //ThreadPoolService threadPoolService;


    @Autowired
    private SqlSessionFactory sqlSessionFactory;
    @Autowired
    private ResourceLoader resourceLoader;

    @Override
    public int create(Payment payment){
        return paymentDao.create(payment);
    }

    @Override
    public int insertPayment(Payment payment){
        return paymentDao.insertPayment(payment);
    }

    @Override
    public  Payment getPaymentById(Long id){
        return paymentDao.getPaymentById(id);
    }
    @Override
    public  Payment getThreadPaymentById(Long id){
        //多线程
        //threadPoolService.getPaymentData(id); 循环依赖了 修改成下面的方法
        return     ((ThreadPoolService) AopContext.currentProxy()).getPaymentData(id);

    }

    @Override
    public IPage<Payment> getPaymentByCompanyId (Long companyId){
        IPage<Payment> page = paymentDao.getPaymentByCompanyId(new Page<>(1,5),companyId);
        System.out.println("总页数： " + page.getPages());
        System.out.println("总记录数： " + page.getTotal());
        page.getRecords().forEach(System.out::println);

        return page;
    }

    @Override
    public IPage<Payment> getPaymentByOr (HashMap<String,String> map){
        IPage<Payment> page = paymentDao.getPaymentByOr(new Page<>(1,5),map);
        System.out.println("总页数： " + page.getPages());
        System.out.println("总记录数： " + page.getTotal());
        page.getRecords().forEach(System.out::println);

        return page;
    }
    @PostConstruct
    public void getMethod(){


        System.out.println("aaaaa");
    }

    @Override
    public void go() {
        investChainPatternService.go("EWO",123456L,"IMPORT");
    }

//    @Override
//    public void sendMessage() {
//        kafkaProducer.sendMessage();
//    }

    @Override
    public String setRedisData(UserData userData) {
        Map<String,String> data = new HashMap<>();
        try {
            data.putAll(ObjectToMapConverterUtil.toObjectMap(userData));
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        String key = JSON.toJSONString(System.currentTimeMillis());
        Boolean b = redisService.putHash(key,data);
        return key;
    }
    public String setRedisData1(Map<Object,Object> map) {


        Map<String,String> data = new HashMap<>();
//        try {
//            data.putAll(ObjectToMapConverterUtil.toObjectMap(userData));
//        } catch (IllegalAccessException e) {
//            e.printStackTrace();
//        }

        data.put("aa","aa");
        data.put("aa-a","aa-a");
        data.put("aa-b","aa-b");
        data.put("aa-c","aa-c");
        data.put("aa-d","aa-d");
        data.put("bb","bb");
        data.put("bb-a","bb-a");

        String key = JSON.toJSONString(System.currentTimeMillis());
        Boolean b = redisService.putHash(key,data);
        return key;
    }



    @Override
    public UserData getRedisData(String key) {
        Map<Object, Object> map = redisService.getHashValues(key);

        UserData userData = UserData.builder()
                .name(map.get("name").toString())
                .type(map.get("type").toString())
                .aa(map.get("aa").toString())
                .bb(map.get("bb").toString())
                .cc(map.get("cc").toString()).build();

        return userData;
    }






    /**
     * 修改相关redisHash中的内容
     * 并且从新查询一遍
     * @param
     * @return
     */
    @Override
    public Map <Object,Object> getRedisData1(String Rediskey,String HashKey) {
        Map<Object, Object> map = redisService.getHashValues(Rediskey);

        //String aa="aa";
        Map <String,String> map1 = new HashMap<>();
        for(Map.Entry<Object,Object> e:map.entrySet()){
            //需要模糊筛选的key 不符合的不添加进去
            if(!HashKey.contains(String.valueOf(e.getKey()))){
                map1.put(String.valueOf(e.getKey()),String.valueOf(e.getValue()));

            }
        }

        redisService.putHash(Rediskey,map1);

        //取出看看效果
        return redisService.getHashValues(Rediskey);
    }


    @Override
    public String redisLength(RedisCityData originCity, RedisCityData targetCity){
        //写入redis中的数据
        redisService.addGeoLocation("cities",originCity.getLongitude(),originCity.getLatitude(),originCity.getCityName());
        redisService.addGeoLocation("cities",targetCity.getLongitude(),targetCity.getLatitude(),targetCity.getCityName());
        //根据写入的数据进行调用 并且得出数值
        Double distance = redisService.distance("cities",originCity.getCityName(),targetCity.getCityName());
        return distance.toString();
    }


    @Override
    public String redisLengthTest(){
        RedisCityData originCity =  RedisCityData.builder().cityName("北京").latitude(39.916527).longitude(116.397128).build();

        RedisCityData targetCity = RedisCityData.builder().cityName("洛阳").latitude(34.70431).longitude(112.51078).build();;


        //写入redis中的数据
        redisService.addGeoLocation("cities",originCity.getLongitude(),originCity.getLatitude(),originCity.getCityName());
        redisService.addGeoLocation("cities",targetCity.getLongitude(),targetCity.getLatitude(),targetCity.getCityName());
        //根据写入的数据进行调用 并且得出数值
        Double distance = redisService.distance("cities",originCity.getCityName(),targetCity.getCityName());
        log.info("距离是:{}",distance);

        return distance.toString();
    }

    /**
     * 批量写入数据 方法1
     * @param users
     */
    public void insertUsersBatch(List<Payment> users) {
        try (SqlSession session = sqlSessionFactory.openSession(ExecutorType.BATCH, false)) {
            PaymentDao mapper = session.getMapper(PaymentDao.class);
            for (Payment user : users) {
                mapper.insertBatch(Collections.singletonList(user)); // 注意：这里实际上并不高效，只是为了演示
                // 注意：在实际应用中，你应该将整个用户列表传递给mapper的单个方法，如上XML配置所示
            }
            session.commit(); // 提交事务
        }
    }
    /**
     * 批量写入数据 方法2
     * @param users
     */
    // 更高效的做法是直接传递整个列表给mapper
    public void insertUsersBatchEfficiently(List<Payment> users) {
        try (SqlSession session = sqlSessionFactory.openSession(ExecutorType.BATCH, false)) {
            PaymentDao mapper= session.getMapper(PaymentDao.class);
            mapper.insertBatch(users); // 假设insertBatch方法已经修改为接受List<User>
            session.commit(); // 提交事务
        }
    }



    public static void main(String[] args) {
        InvestChainPatternService i = new InvestChainPatternService();
        i.go("EWO",123456L,"IMPORT");
    }


    public void selectByIdExcel(HttpServletResponse response) throws IOException {
        List<Payment> payments = paymentDao.selectPaymentLimit();
        //1，找出模板文件，并转化为输入流：templateFileInputStream；
        InputStream templateFileInputStream = this.getClass().getResourceAsStream("/static/import/Import_payment.xlsx");

        //2，outputStream：要导出的文件的输出流；
        OutputStream outputStream = response.getOutputStream();
        ExcelWriterBuilder excelWriterBuilder = EasyExcel.write(outputStream).withTemplate(templateFileInputStream);

        ExcelWriter excelWriter = excelWriterBuilder.build();
        //设置列表横向还是纵向-----默认是纵向，HORIZONTAL就是水平填充
        FillConfig fillConfig = FillConfig.builder().direction(WriteDirectionEnum.HORIZONTAL).build();
        try {
            WriteSheet writeSheet = EasyExcel.writerSheet().build();
            //3，往sheet表中填充数据：
            excelWriter.fill(new FillWrapper("data", payments),fillConfig , writeSheet);
            //可以多个
            //excelWriter.fill(new FillWrapper("site", paramValueMapListNew), fillConfig, writeSheet0);

            //4，设置输出流格式以及文件名：
            response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
            response.setCharacterEncoding("utf-8");
            response.setHeader("Content-disposition", "attachment;filename*=utf-8''" + "Import_payment" + ".xlsx");
        } finally {
            //5，最后，千万别忘记finish，关闭IO流；
            if (excelWriter != null) {
                excelWriter.finish();
            }
        }


    }

    public List<Payment> selectById(Long id){
        List<Payment> payments = paymentDao.selectById(id);
        return payments;
    }


    public PaymentItem selectPaymentItemById(Long id){
        PaymentItem paymentItem= paymentDao.selectPaymentItemById(id);
        return paymentItem;
    }

    public UserItem selectUserItemById(Long id){
        UserItem paymentItem= userDao.selectUserItemById(id);
        return paymentItem;
    }

    /**
     * 动态导出
     * @param response
     * @throws IOException
     */

    public void excelDynamicExport(HttpServletResponse response) throws IOException {
        // 示例数据
        Map<String, List<UserVO>> dataMap = new HashMap<>();


        dataMap.put("Group1", Arrays.asList(
                new UserVO("Alice", 30),
                new UserVO("Bob", 25),
                new UserVO("Bob", 25)));
        dataMap.put("Group2", Arrays.asList(new UserVO("Charlie", 35), new UserVO("David", 28),new UserVO("Charlie", 35), new UserVO("David", 28)));
        dataMap.put("Group3", Arrays.asList(new UserVO("Charlie", 35), new UserVO("David", 28)));
        // 导出文件路径

        String fileName = "测试";
        //判断那个Map数据最长  拿到key
        Integer size= 0;
        String sizeKey = "";
        for(Map.Entry<String, List<UserVO>> listEntry:dataMap.entrySet()){
            if(size<listEntry.getValue().size()){
                size=listEntry.getValue().size();
                sizeKey = listEntry.getKey();
            }
        }

        //写入头
        List<List<String>> list = new ArrayList<>();
        for(Map.Entry<String, List<UserVO>> listEntry:dataMap.entrySet()){
            List<String> head0 = new ArrayList<>();
            head0.add("名称-名称-名称"+listEntry.getKey());
            list.add(head0);
            List<String> head1 = new ArrayList<>();
            head1.add("年龄-年龄-年龄"+listEntry.getKey());
            list.add(head1);
        }

        /**
         * 获得数据
         */
        List<List<Object>> dataList = new ArrayList<>();
        for (int i = 0;size>i;i++){
            List<Object> data = new ArrayList<>();
            for (Map.Entry<String, List<UserVO>> listEntry:dataMap.entrySet()){
                if(listEntry.getValue().size()>i) {
                    UserVO vo =  listEntry.getValue().get(i);
                    data.add(vo.getNames());
                    data.add(vo.getAges());
                }else{
                    data.add(null);
                    data.add(null);
                }
            }
            dataList.add(data);
        }







        // 头的策略
        WriteCellStyle headWriteCellStyle = new WriteCellStyle();
        // 背景设置为白色
        headWriteCellStyle.setFillForegroundColor(IndexedColors.WHITE.getIndex());
        WriteFont headWriteFont = new WriteFont();
        headWriteFont.setFontHeightInPoints((short)16);
        headWriteCellStyle.setWriteFont(headWriteFont);

        // 内容的策略
        WriteCellStyle contentWriteCellStyle = new WriteCellStyle();
        // 这里需要指定 FillPatternType 为FillPatternType.SOLID_FOREGROUND 不然无法显示背景颜色.头默认了 FillPatternType所以可以不指定
        contentWriteCellStyle.setFillPatternType(FillPatternType.SOLID_FOREGROUND);
        //设置边框
        contentWriteCellStyle.setBorderTop(BorderStyle.THIN);
        contentWriteCellStyle.setBorderBottom(BorderStyle.THIN);
        contentWriteCellStyle.setBorderRight(BorderStyle.THIN);
        contentWriteCellStyle.setBorderLeft(BorderStyle.THIN);

        //居中
        contentWriteCellStyle.setHorizontalAlignment(HorizontalAlignment.CENTER);
        // 背景白色
        contentWriteCellStyle.setFillForegroundColor(IndexedColors.WHITE.getIndex());
        WriteFont contentWriteFont = new WriteFont();

        // 字体大小
        contentWriteFont.setFontHeightInPoints((short)12);
        contentWriteCellStyle.setWriteFont(contentWriteFont);
        // 这个策略是 头是头的样式 内容是内容的样式 其他的策略可以自己实现
        HorizontalCellStyleStrategy horizontalCellStyleStrategy = new HorizontalCellStyleStrategy(headWriteCellStyle, contentWriteCellStyle);


        // 设置响应头
        // 这里注意 有同学反应使用swagger 会导致各种问题，请直接用浏览器或者用postman
        response.setContentType("application/vnd.ms-excel");
        response.setCharacterEncoding("utf-8");
        // 这里URLEncoder.encode可以防止中文乱码 当然和 easyexcel 没有关系
        String URLfileName = URLEncoder.encode(fileName, "UTF-8").replaceAll("\\+", "%20");
        response.setHeader("Content-disposition", "attachment;filename*=utf-8''" + URLfileName + ".xlsx");

        // 先仅仅写入头，再以不写入头的方式写入数据
        EasyExcel.write(response.getOutputStream()).head(list)
                .registerWriteHandler(horizontalCellStyleStrategy)
                //列宽自适应
                .registerWriteHandler(new RwhzCustemhandler())
                //设置头部和内容的行高
                .registerWriteHandler(new SimpleRowHeightStyleStrategy((short)50,(short)30))
                .sheet("sheet1").doWrite(dataList);


    }




}

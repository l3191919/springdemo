package com.lyz.util.excel;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.metadata.CellExtra;
import com.alibaba.excel.metadata.data.ReadCellData;
import com.alibaba.excel.read.listener.ReadListener;
import com.alibaba.excel.read.metadata.holder.ReadRowHolder;
import com.alibaba.fastjson.JSON;
import com.lyz.entities.UserImportVO;
import lombok.extern.slf4j.Slf4j;
import org.mybatis.logging.Logger;
import org.mybatis.logging.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Component
@Slf4j
public class DataListener1 implements ReadListener<UserImportVO> {
    //日志
    private static final Logger LOGGER = LoggerFactory.getLogger(DataListener.class);
    //每次最多导入条数
    private static final int BATCH_COUNT = 2000;


    //数据集合
    List<UserImportVO> poList = new ArrayList<>();
    //读取失败的Excel文件索引行数集合
    List<Integer> failedRowIndexList = new ArrayList<>();
    private int startRow = 2; // 假设您想从第三行开始读取数据（注意：行号通常从0开始，但这里我们按Excel的实际行号来思考）
    private int currentRow = 0; // 当前处理的行号（从0开始）
    //无参构造方法
//    public DataListener() {
//
//    }

    //使用这个构造方法，Listener是不被spring所管理的
    //所以我们自己在外面new Listener的时候把service传进来
//    public MailDataListener(SysUserService sysUserService) {
//        this.sysUserService = sysUserService;
//    }

    //@Override
    public void onException(Exception exception, AnalysisContext context) throws Exception {

    }

    //@Override
    public void invokeHead(Map<Integer, ReadCellData<?>> headMap, AnalysisContext context) {

    }

    //这个是按照Excel表格来进行每行读取的 一行一行读取
    //@Override
    public void invoke(UserImportVO userImportVO, AnalysisContext context) {
//        currentRow++; // 递增当前行号
//        if (currentRow < startRow) {
//            // 跳过不需要的行
//            return;
//        }


        ReadRowHolder readRowHolder = context.readRowHolder();
        //Excel的行索引
        Integer rowIndex = readRowHolder.getRowIndex() + 1;
        try {
            //对应数据库的实体类
            //SysUser sysUser = new SysUser();
            //通过get获取Excel中的数据
            //可以在这做一些数据处理，看自己需求
            /*Long id = excelModel.getId();
            String name = excelModel.getName();

            sysUser.setId(id);
            sysUser.setName(name);*/


            //把实体类存到集合中
            poList.add(userImportVO);

            //读取数超过2000进行一次数据写入
            //防止一次读取的数据量过大做的一个限制
            //因为在doAfterAllAnalysed()方法里面执行批量导入，这个方法是在读取完Excel才会执行
            /*if (poList.size() >= BATCH_COUNT) {
                sysUserService.insertByPoListCheck(poList);
                poList.clear();//清除list中的数据
            }
            LOGGER.info("行索引数: [%s] -> Excel数据: [%s]", rowIndex,
                    JsonUtil.toJsonString(excelModel));*/
            //try catch 用的Exception这样不管什么异常都捕获得到出错的Excel行数
       /* } catch (Exception e) {
            LOGGER.error("行索引数: [%s] -> 失败的Excel数据: [%s]", rowIndex,
                    JsonUtil.toJsonString(excelModel));
            //收集失败的Excel行索引 通过getFailedRows()返回
            failedRowIndexList.add(rowIndex);
        }*/
        }catch (Exception e){
            failedRowIndexList.add(rowIndex);
            log.error("行索引数: [%s] -> 失败的Excel数据: [%s]", rowIndex,
                    JSON.toJSON(userImportVO));
        }

        //最后执行，读取完整个Excel执行
        //先打印下日志，然后直接批量导入操作
//    @Override
//        public void doAfterAllAnalysed(AnalysisContext analysisContext)  {
//         log.info(JSON.toJSONString(poList));
//    }

        //获取读取失败的Excel行索引数集合
//    public List<Integer> getFailedRows() {
//        return failedRowIndexList;
//    }
    }


    public void extra(CellExtra extra, AnalysisContext context) {

    }


    public void doAfterAllAnalysed(AnalysisContext analysisContext) {
        log.info(JSON.toJSONString(poList));
    }


    public boolean hasNext(AnalysisContext context) {
        return true;
    }

    public List<Integer> getFailedRows(){
        return failedRowIndexList;
    }
}

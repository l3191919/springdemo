package com.lyz.util.excel;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.alibaba.excel.metadata.data.ReadCellData;
import com.alibaba.excel.read.metadata.holder.ReadRowHolder;
import com.alibaba.excel.util.ConverterUtils;
import com.alibaba.fastjson.JSON;
import com.lyz.entities.UserImportVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Slf4j
@Component
public class DataListener  extends AnalysisEventListener<UserImportVO> {
    private List<UserImportVO> dataList = new ArrayList<>();
    List<Integer> failedRowIndexList =new ArrayList<>();

//    public void invokeHead(Map<Integer, ReadCellData<?>> headMap, AnalysisContext context) {
//        this.invokeHeadMap(ConverterUtils.convertToStringMap(headMap, context), context);
//    }

    @Override
    public void invoke(UserImportVO userData, AnalysisContext context) {
        ReadRowHolder readRowHolder = context.readRowHolder();
        //Excel的行索引
        Integer rowIndex = readRowHolder.getRowIndex() + 1;
        try {

            dataList.add(userData);
        }catch (Exception e){
            failedRowIndexList.add(readRowHolder.getRowIndex() + 1);
            log.error("行索引数: [%s] -> 失败的Excel数据: [%s]", rowIndex,
                    JSON.toJSON(failedRowIndexList));
        }
        // 每读取一行数据，这个方法就会被调用一次


    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext context) {
        // 所有数据解析完成后的回调
    }

    // 获取读取到的数据列表
    public List<UserImportVO> getDataList() {
        return dataList;
    }
    public List<Integer> getFailedRowIndexList() {
        return failedRowIndexList;
    }
}

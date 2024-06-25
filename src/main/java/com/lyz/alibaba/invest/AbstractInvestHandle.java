package com.lyz.alibaba.invest;


import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;


@Component
@Slf4j
public abstract class AbstractInvestHandle{
    //责任链
    static List<AbstractInvestHandle> getChainPattern(){
        List<AbstractInvestHandle> list = new ArrayList<>();
        list.add(new InvestEwoHandleI(AbstractInvestHandle.EWO));
        list.add(new InvestPriceChangeHandleI(AbstractInvestHandle.PRICE_CHANGE));
        list.add(new InvestCOPHandleI(AbstractInvestHandle.COP));
        return list;
    }

    //ewo类型
    public static String EWO = "EWO";
    //价格变更
    public static String PRICE_CHANGE = "PRICE_CHANGE";
    //cop
    public static String COP ="COP";

    //导入或者批量添加
    public static String IMPORT = "IMPORT";
    //ewo 台账添加
    public static String ADD = "ADD";

    //对应的数字
    protected String type;

    public static void investMessage(String type,Long appFormId, String function){
        for(AbstractInvestHandle abstractInvestHandle:getChainPattern()){
            abstractInvestHandle.handle(type,appFormId,function);
        }
    }

    //处理的抽象类
    abstract protected void handle(String type,Long appFormId,String function);
}

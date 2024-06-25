package com.lyz.alibaba.invest;

import lombok.extern.slf4j.Slf4j;

/**
 * 责任链的一环
 * COP
 */
//@Component
    @Slf4j
public class InvestCOPHandleI extends AbstractInvestHandle{
    public  InvestCOPHandleI(String type){
        this.type = type;
    }
    //@Autowired
    //AppFormInvestDataPriceChange appFormInvestDataPriceChange;



    //手动注入
    //AppFormInvestDataCop appFormInvestDataCop = SpringContextUtil.getBean(AppFormInvestDataCop.class);

    //public void getType(String type){
    //    this.type = type;
    //}


    @Override
    protected void handle(String type,Long appFormId, String function) {
        //批量新增 或者导入的
        if(this.type.equals(type)) {
            log.info("COP 开始处理invest相关数据");
            if (IMPORT.equals(function)) {
                log.info(type+"----"+function);
            }
            //台账添加 同步 支付阶段 继承分摊
            if (ADD.equals(function)) {
                log.info(type+"----"+function);
            }
        }
    }
}

package com.lyz.alibaba.pattern.zerenlian;

import lombok.extern.slf4j.Slf4j;

/**
 * 责任链的一环
 * ewo
 */
//@Component
    @Slf4j
public class InvestEwoHandleI extends AbstractInvestHandle {

    public InvestEwoHandleI(String type){
        this.type = type;
    }


    @Override
    protected void handle(String type,Long appFormId, String function) {
        if(this.type.equals(type)) {
            log.info("EWO 开始处理invest相关数据");
            //批量新增 或者导入的
            if (this.type.equals(type)) {
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
}

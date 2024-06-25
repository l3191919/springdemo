package com.lyz.alibaba.invest;

import org.springframework.stereotype.Component;

@Component
public class InvestChainPatternService {

    public void go(String type,Long appFormId, String function){
        AbstractInvestHandle.investMessage( type, appFormId, function);
    }

}

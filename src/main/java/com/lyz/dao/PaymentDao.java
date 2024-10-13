package com.lyz.dao;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;


import com.lyz.entities.Payment;
import com.lyz.entities.PaymentItem;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.HashMap;
import java.util.List;

@Mapper
public interface PaymentDao {
    /**
     * 新增
     *
     * @param payment
     * @return
     */
    int create(Payment payment);

    int insertPayment(Payment payment);
    /**
     * 根据Id查询
     *
     * @param id
     * @return
     */
    Payment getPaymentById(@Param("id") Long id);

    /**
     * 根据companyId查询
     *
     * @param companyId
     * @return
     */
    IPage<Payment> getPaymentByCompanyId(Page<Payment> page, @Param("companyId") Long companyId);


    IPage<Payment> getPaymentByOr(Page<Payment> page, @Param("map") HashMap<String, String> map);

    /**
     * insertBatch
     *  批量写入
     */
    void insertBatch (List<Payment> payment);


    List<Payment> selectById(@Param("id") Long id);


    PaymentItem selectPaymentItemById(@Param("id") Long id);
}

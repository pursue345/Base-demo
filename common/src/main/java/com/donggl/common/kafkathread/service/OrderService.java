package com.donggl.common.kafkathread.service;


import com.donggl.common.kafkathread.model.OrderDTO;

/**
 * 测试订单服务，模拟业务处理成功与失败场景
 *
 * @author donggl
 * @date 2022/01/19
 */
public interface OrderService {

    /**
     * 处理订单数据
     *
     * @param order
     */
    void solveRetry(OrderDTO order);
}

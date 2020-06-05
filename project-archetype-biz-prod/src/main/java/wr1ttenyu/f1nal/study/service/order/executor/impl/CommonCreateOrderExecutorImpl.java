package wr1ttenyu.f1nal.study.service.order.executor.impl;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import wr1ttenyu.f1nal.study.project.archetype.model.OrderModel;
import wr1ttenyu.f1nal.study.project.archetype.model.enums.OrderType;
import wr1ttenyu.f1nal.study.service.order.executor.CreateOrderExecutor;

@Service
public class CommonCreateOrderExecutorImpl implements CreateOrderExecutor {

    private static Logger LOGGER = LoggerFactory.getLogger(CancelMPlusOrderExecutorImpl.class);

    @Override
    public OrderModel createOrder(OrderModel order) {
        LOGGER.info("{}订单，被创建了，订单类型为：{}", order.getTitle(), order.getOrderType());
        return order;
    }

    @Override
    public Boolean support(OrderType orderType) {
        if (OrderType.M_PLUS_CREATE.equals(orderType) ||
                OrderType.TICKET_CHARGE.equals(orderType)) return true;
        return false;
    }
}

package wr1ttenyu.f1nal.study.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import wr1ttenyu.f1nal.study.project.archetype.model.OrderModel;
import wr1ttenyu.f1nal.study.service.MPlusOrderService;
import wr1ttenyu.f1nal.study.service.order.OrderLifeManager;

@Service
public class MPlusOrderServiceImpl implements MPlusOrderService {

    @Autowired
    private OrderLifeManager orderLifeManager;

    @Override
    public OrderModel createMPlusOrder(OrderModel orderModel) {
        return orderLifeManager.createOrder(orderModel);
    }

    @Override
    public OrderModel cancelMPlusOrder(OrderModel orderModel) {
        return orderLifeManager.cancelOrder(orderModel);
    }

    @Override
    public OrderModel discardMPlusOrder(OrderModel orderModel) {
        return orderLifeManager.discardOrder(orderModel);
    }


}

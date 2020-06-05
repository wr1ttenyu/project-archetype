package wr1ttenyu.f1nal.study.service;

import wr1ttenyu.f1nal.study.project.archetype.model.OrderModel;

public interface MPlusOrderService {

    OrderModel createMPlusOrder(OrderModel orderModel);

    OrderModel cancelMPlusOrder(OrderModel orderModel);

    OrderModel discardMPlusOrder(OrderModel orderModel);
}

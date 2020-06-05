package wr1ttenyu.f1nal.study.service.order;


import wr1ttenyu.f1nal.study.project.archetype.model.OrderModel;

public interface OrderLife {

    OrderModel createOrder(OrderModel orderModel);

    OrderModel discardOrder(OrderModel orderModel);

    OrderModel cancelOrder(OrderModel orderModel);

    OrderModel paySuccess(OrderModel orderModel);

    OrderModel inverseOrder(OrderModel orderModel);

    OrderModel refundOrder(OrderModel orderModel);

}

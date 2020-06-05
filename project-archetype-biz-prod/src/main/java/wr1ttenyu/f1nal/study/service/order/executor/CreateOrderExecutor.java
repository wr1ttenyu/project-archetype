package wr1ttenyu.f1nal.study.service.order.executor;


import wr1ttenyu.f1nal.study.project.archetype.model.OrderModel;

public interface CreateOrderExecutor extends Executor {

   OrderModel createOrder(OrderModel order);
}

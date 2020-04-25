package wr1ttenyu.f1nal.study.project.archetype.service.order.executor;

import wr1ttenyu.f1nal.study.project.archetype.model.OrderModel;


public interface CancelOrderExecutor extends Executor {

    OrderModel cancelOrder(OrderModel order);
}

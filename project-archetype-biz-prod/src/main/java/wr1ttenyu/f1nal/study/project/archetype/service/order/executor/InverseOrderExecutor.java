package wr1ttenyu.f1nal.study.project.archetype.service.order.executor;


import wr1ttenyu.f1nal.study.project.archetype.model.OrderModel;

public interface InverseOrderExecutor extends Executor {

   OrderModel inverseOrder(OrderModel order);
}

package wr1ttenyu.f1nal.study.service.order.executor;

import wr1ttenyu.f1nal.study.project.archetype.model.enums.OrderType;

public interface Executor {

    Boolean support(OrderType orderType);
}

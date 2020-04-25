package wr1ttenyu.f1nal.study.project.archetype.model;

import wr1ttenyu.f1nal.study.project.archetype.entity.UUser;
import wr1ttenyu.f1nal.study.project.archetype.model.enums.OrderType;
import wr1ttenyu.f1nal.study.project.archetype.model.request.AddOrderRequest;

public class OrderModel {

    private String orderId;

    private String title;

    private Long price;

    private OrderType orderType;

    public static OrderModel convertReqToModel(AddOrderRequest addOrderRequest) {
        if (addOrderRequest == null) return null;
        OrderModel model = new OrderModel();
        model.setOrderId(addOrderRequest.getOrderId());
        model.setOrderType(addOrderRequest.getOrderType());
        model.setPrice(addOrderRequest.getPrice());
        model.setTitle(addOrderRequest.getTitle());
        return model;
    }


    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public OrderType getOrderType() {
        return orderType;
    }

    public void setOrderType(OrderType orderType) {
        this.orderType = orderType;
    }
}

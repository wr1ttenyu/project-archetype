package wr1ttenyu.f1nal.study.project.archetype.model.request;

import wr1ttenyu.f1nal.study.project.archetype.model.enums.OrderType;

import javax.validation.constraints.NotNull;

public class AddOrderRequest {

    private String orderId;

    @NotNull(message = "title不能为空")
    private String title;

    @NotNull(message = "price不能为空")
    private Long price;

    @NotNull(message = "orderType不能为空")
    private OrderType orderType;

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

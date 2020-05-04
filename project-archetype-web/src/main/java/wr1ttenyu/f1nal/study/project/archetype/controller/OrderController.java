package wr1ttenyu.f1nal.study.project.archetype.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import wr1ttenyu.f1nal.study.project.archetype.model.OrderModel;
import wr1ttenyu.f1nal.study.project.archetype.model.UserModel;
import wr1ttenyu.f1nal.study.project.archetype.model.request.AddOrderRequest;
import wr1ttenyu.f1nal.study.project.archetype.service.MPlusOrderService;
import wr1ttenyu.f1nal.study.project.archetype.util.UUIDGenerator;
import wr1ttenyu.f1nal.study.project.archetype.util.common.response.CommonResponse;
import wr1ttenyu.f1nal.study.project.archetype.web.utils.token.TokenValid;

import javax.validation.Valid;

@TokenValid
@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private MPlusOrderService mPlusOrderService;

    @RequestMapping(method = RequestMethod.POST, path = "/createMPlusOrder")
    public CommonResponse<OrderModel> createMPlusOrder(@Valid @RequestBody AddOrderRequest request, UserModel user) {
        OrderModel orderModel = OrderModel.convertReqToModel(request);
        orderModel.setOrderId(UUIDGenerator.generate());
        mPlusOrderService.createMPlusOrder(orderModel);
        return CommonResponse.successResponse(orderModel);
    }

    @RequestMapping(method = RequestMethod.POST, path = "/cancelMPlusOrder")
    public CommonResponse<OrderModel> cancelMPlusOrder(@Valid @RequestBody AddOrderRequest request) {
        OrderModel orderModel = OrderModel.convertReqToModel(request);
        orderModel.setOrderId(UUIDGenerator.generate());
        mPlusOrderService.cancelMPlusOrder(orderModel);
        return CommonResponse.successResponse(orderModel);
    }

    @RequestMapping(method = RequestMethod.POST, path = "/discardMPlusOrder")
    public CommonResponse<OrderModel> discardMPlusOrder(@Valid @RequestBody AddOrderRequest request) {
        OrderModel orderModel = OrderModel.convertReqToModel(request);
        orderModel.setOrderId(UUIDGenerator.generate());
        mPlusOrderService.discardMPlusOrder(orderModel);
        return CommonResponse.successResponse(orderModel);
    }
}

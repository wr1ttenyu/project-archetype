package wr1ttenyu.f1nal.study.service.order;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;
import wr1ttenyu.f1nal.study.project.archetype.model.OrderModel;
import wr1ttenyu.f1nal.study.service.order.executor.*;
import wr1ttenyu.f1nal.study.util.common.constant.enums.BusinessExceptionEnum;

import java.util.ArrayList;
import java.util.List;

@Component
public class OrderLifeManager implements BeanPostProcessor, OrderLife {

    private static Logger LOGGER = LoggerFactory.getLogger(OrderLifeManager.class);

    private List<CreateOrderExecutor> CREATE_EXECUTOR_CONTAINER = new ArrayList<>();
    private List<CancelOrderExecutor> CANCEL_EXECUTOR_CONTAINER = new ArrayList<>();
    private List<DiscardOrderExecutor> DISCARD_EXECUTOR_CONTAINER = new ArrayList<>();
    private List<InverseOrderExecutor> INVERSE_EXECUTOR_CONTAINER = new ArrayList<>();
    private List<PaySuccessExecutor> PAY_SUCCESS_EXECUTOR_CONTAINER = new ArrayList<>();
    private List<RefundOrderExecutor> REFUND_EXECUTOR_CONTAINER = new ArrayList<>();

    @Override
    public OrderModel createOrder(OrderModel orderModel) {
        CreateOrderExecutor target = null;
        for (CreateOrderExecutor createOrderExecutor : CREATE_EXECUTOR_CONTAINER) {
            if (createOrderExecutor.support(orderModel.getOrderType())) {
                target = createOrderExecutor;
            }
        }
        BusinessExceptionEnum.ORDER_EXECUTOR_NOT_FOUND.assertNotNull(target, orderModel.getOrderId(),
                orderModel.getOrderType(), "create");
        return target.createOrder(orderModel);
    }

    @Override
    public OrderModel discardOrder(OrderModel orderModel) {
        DiscardOrderExecutor target = null;
        for (DiscardOrderExecutor discardOrderExecutor : DISCARD_EXECUTOR_CONTAINER) {
            if (discardOrderExecutor.support(orderModel.getOrderType())) {
                target = discardOrderExecutor;
            }
        }
        BusinessExceptionEnum.ORDER_EXECUTOR_NOT_FOUND.assertNotNull(target, orderModel.getOrderId(),
                orderModel.getOrderType(), "discard");
        return target.discardOrder(orderModel);
    }

    @Override
    public OrderModel cancelOrder(OrderModel orderModel) {
        CancelOrderExecutor target = null;
        for (CancelOrderExecutor cancelOrderExecutor : CANCEL_EXECUTOR_CONTAINER) {
            if (cancelOrderExecutor.support(orderModel.getOrderType())) {
                target = cancelOrderExecutor;
            }
        }
        BusinessExceptionEnum.ORDER_EXECUTOR_NOT_FOUND.assertNotNull(target, orderModel.getOrderId(),
                orderModel.getOrderType(), "cancel");
        return target.cancelOrder(orderModel);
    }

    @Override
    public OrderModel paySuccess(OrderModel orderModel) {
        BusinessExceptionEnum.ORDER_EXECUTOR_NOT_SUPPORT.assertFail();
        return orderModel;
    }

    @Override
    public OrderModel inverseOrder(OrderModel orderModel) {
        BusinessExceptionEnum.ORDER_EXECUTOR_NOT_SUPPORT.assertFail();
        return orderModel;
    }

    @Override
    public OrderModel refundOrder(OrderModel orderModel) {
        BusinessExceptionEnum.ORDER_EXECUTOR_NOT_SUPPORT.assertFail();
        return orderModel;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        if (bean instanceof CreateOrderExecutor) {
            CREATE_EXECUTOR_CONTAINER.add((CreateOrderExecutor) bean);
        } else if (bean instanceof CancelOrderExecutor) {
            CANCEL_EXECUTOR_CONTAINER.add((CancelOrderExecutor) bean);
        } else if (bean instanceof DiscardOrderExecutor) {
            DISCARD_EXECUTOR_CONTAINER.add((DiscardOrderExecutor) bean);
        } else if (bean instanceof InverseOrderExecutor) {
            INVERSE_EXECUTOR_CONTAINER.add((InverseOrderExecutor) bean);
        } else if (bean instanceof PaySuccessExecutor) {
            PAY_SUCCESS_EXECUTOR_CONTAINER.add((PaySuccessExecutor) bean);
        } else if (bean instanceof RefundOrderExecutor) {
            REFUND_EXECUTOR_CONTAINER.add((RefundOrderExecutor) bean);
        } else if (bean instanceof Executor) {
            LOGGER.error("未知订单生命周期处理器，请检查，beanName:{} bean:{}", beanName, bean);
            BusinessExceptionEnum.NOT_KNOWN_ORDER_EXECUTOR.assertFail();
        }
        return bean;
    }
}

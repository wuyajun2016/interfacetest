package com.dfire.testcase.function.bean.cash;

import java.io.Serializable;
import java.util.List;


/**
 * 预付款信息
 * <pre>包括订单信息和支付信息
 * </pre>
 *
 * @author silence
 */
public class CustomerPayOrder implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    private CustomerOrder customerOrder;

    private List<PayOrder> payOrders;

    private CardDto cardDto;


    public CustomerOrder getCustomerOrder() {
        return customerOrder;
    }

    public void setCustomerOrder(CustomerOrder customerOrder) {
        this.customerOrder = customerOrder;
    }

    public List<PayOrder> getPayOrders() {
        return payOrders;
    }

    public void setPayOrders(List<PayOrder> payOrders) {
        this.payOrders = payOrders;
    }

    public CardDto getCardDto() {
        return cardDto;
    }

    public void setCardDto(CardDto cardDto) {
        this.cardDto = cardDto;
    }
}

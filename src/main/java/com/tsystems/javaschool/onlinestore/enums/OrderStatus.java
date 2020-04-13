package com.tsystems.javaschool.onlinestore.enums;
import java.util.EnumSet;

public enum OrderStatus {

    NOTSHIPPED("Order is waiting for shipping"),
    SHIPPED("Order is shipped"),
    DELIVERED("Order is delivered");

    private String code;

    OrderStatus (String code) {
        this.code=code;
    }
    public String getCode() {
        return code;
    }
    static public OrderStatus getOrderStatus(String code) {
        for(OrderStatus e:EnumSet.allOf(OrderStatus.class)) {
            if(e.getCode().equals(code)) {
                return e;
            }
        }
        return null;
    }



}
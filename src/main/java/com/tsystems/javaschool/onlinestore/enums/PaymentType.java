package com.tsystems.javaschool.onlinestore.enums;

import java.util.EnumSet;

public enum PaymentType {

    CASH("Cash payment upon receipt of order"),
    CARD("Payment by card upon receipt of order"),
    ONLINE("Pay online with card");

    private String code;

    PaymentType(String code){
        this.code=code;
    }
    public String getCode() {
        return code;
    }
    static public PaymentType getPaymentType(String code) {
        for(PaymentType e:EnumSet.allOf(PaymentType.class)) {
            if(e.getCode().equals(code)) {
                return e;
            }
        }
        return null;
    }

}
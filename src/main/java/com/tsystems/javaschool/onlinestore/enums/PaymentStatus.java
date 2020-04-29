package com.tsystems.javaschool.onlinestore.enums;

import java.util.EnumSet;

/**
 * Enum represents payment statuses for order
 */
public enum PaymentStatus {

    NOTPAID("Order is not paid"),
    PAID("Order is paid");

    private String code;

    PaymentStatus(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    /**
     * Method returns PaymentStatus by code
     * @param code
     * @return PaymentStatus
     */
    public static PaymentStatus getPaymentStatus(String code) {
        for (PaymentStatus e : EnumSet.allOf(PaymentStatus.class)) {
            if (e.getCode().equals(code)) {
                return e;
            }
        }
        return null;
    }
}
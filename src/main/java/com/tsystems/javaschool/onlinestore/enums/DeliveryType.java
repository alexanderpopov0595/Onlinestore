package com.tsystems.javaschool.onlinestore.enums;

import java.util.EnumSet;

/**
 * Enum represents delivery type for order
 */
public enum DeliveryType {

    PICKUP("Pickup from the pickup point"),
    DELIVERY("Delivery by courier to the specified address");

    private String code;

    DeliveryType(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    /**
     * Method returns DeliveryType by code
     * @param code
     * @return DeliveryType
     */
    public static  DeliveryType getDeliveryType(String code) {
        for (DeliveryType e : EnumSet.allOf(DeliveryType.class)) {
            if (e.getCode().equals(code)) {
                return e;
            }
        }
        return null;
    }
}
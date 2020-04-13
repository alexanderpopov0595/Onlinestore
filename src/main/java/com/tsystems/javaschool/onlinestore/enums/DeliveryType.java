package com.tsystems.javaschool.onlinestore.enums;

import java.util.EnumSet;

public enum DeliveryType {

    PICKUP("Pickup from the pickup point"),
    DELIVERY("Delivery by courier to the specified address");

    private String code;


    DeliveryType(String code) {
        this.code=code;
    }
    public String getCode(){
        return code;
    }

    static public DeliveryType getDeliveryType(String code) {
        for(DeliveryType e:EnumSet.allOf(DeliveryType.class)) {
            if(e.getCode().equals(code)) {
                return e;
            }
        }
        return null;
    }


}
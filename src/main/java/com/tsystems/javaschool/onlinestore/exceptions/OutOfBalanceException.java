package com.tsystems.javaschool.onlinestore.exceptions;

/**
 * Exception thrown when user doesn't have enough balance for pusrchase order
 */
public class OutOfBalanceException extends RuntimeException {

    /**
     * Total order price - user balance
     */
    private long missmatch;

    public OutOfBalanceException(long missmatch) {
        this.missmatch=missmatch;
    }
    public long getMissmatch() {
        return missmatch;
    }
}

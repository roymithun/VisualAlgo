package com.peto.visualalgo.model;

public class NumberItem {
    private Integer val; // face value
    private boolean isPrime;
    private boolean isTraversed;

    public NumberItem(Integer val, boolean isPrime) {
        this.val = val;
        this.isPrime = isPrime;
        this.isTraversed = false;
    }

    public void setVal(Integer val) {
        this.val = val;
    }

    public void setPrime(boolean prime) {
        isPrime = prime;
    }

    public void setTraversed() {
        isTraversed = true;
    }

    public Integer getValue() {
        return val;
    }

    public boolean isPrime() {
        return isPrime;
    }

    public boolean isTraversed() {
        return isTraversed;
    }
}

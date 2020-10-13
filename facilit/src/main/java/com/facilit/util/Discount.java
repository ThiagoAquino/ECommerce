package com.facilit.util;

public enum Discount {
    START(5, 1000, 4999), MEDIUM(7, 5000, 9999), EXTREME(10, 10000, (int) Double.POSITIVE_INFINITY);

    private int discount;
    private int from;
    private int to;

    Discount(int discount, int from, int to) {
        this.discount = discount;
        this.from = from;
        this.to = to;
    }

    public int getDiscount() {
        return discount;
    }

    public int getFrom() {
        return from;
    }

    public int getTo() {
        return to;
    }

}

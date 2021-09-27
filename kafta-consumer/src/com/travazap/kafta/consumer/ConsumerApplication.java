package com.travazap.kafta.consumer;

public class ConsumerApplication {
    private int offset;
    private final

    public static void main(String[] args) {
        System.out.println("consumer");
    }

    public int getOffset() {
        return this.offset;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }
}

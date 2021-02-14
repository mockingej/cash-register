package com.rocketmiles.model;

public class Cash {

    private int twenties = 0;
    private int tens = 0;
    private int fives = 0;
    private int twos = 0;
    private int ones = 0;

    public int getTwenties() {
        return twenties;
    }

    public void setTwenties(int twenties) {
        this.twenties = twenties;
    }

    public int getTens() {
        return tens;
    }

    public void setTens(int tens) {
        this.tens = tens;
    }

    public int getFives() {
        return fives;
    }

    public void setFives(int fives) {
        this.fives = fives;
    }

    public int getTwos() {
        return twos;
    }

    public void setTwos(int twos) {
        this.twos = twos;
    }

    public int getOnes() {
        return ones;
    }

    public void setOnes(int ones) {
        this.ones = ones;
    }

    public int getTotal() {
        int total = 0;

        total += this.twenties * 20;
        total += this.tens * 10;
        total += this.fives * 5;
        total += this.twos * 2;
        total += this.ones;

        return total;
    }
}

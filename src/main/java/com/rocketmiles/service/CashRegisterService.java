package com.rocketmiles.service;

import com.rocketmiles.model.Cash;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CashRegisterService {

    private static final String NO_CHANGE_ERROR = "Sorry. No change can be made";
    private static final String INSUFFICIENT_ERROR = "Sorry. Insufficient Funds";

    private final Cash cash = new Cash();

    public String show() {
        String output = "";
        output += "$" + cash.getTotal() + " ";
        output += cash.getTwenties() + " ";
        output += cash.getTens() + " ";
        output += cash.getFives() + " ";
        output += cash.getTwos() + " ";
        output += cash.getOnes();
        return output;
    }

    public String put(int twenties, int tens, int fives, int twos, int ones) {
        cash.setTwenties(cash.getTwenties() + twenties);
        cash.setTens(cash.getTens() + tens);
        cash.setFives(cash.getFives() + fives);
        cash.setTwos(cash.getTwos() + twos);
        cash.setOnes(cash.getOnes() + ones);
        return show();
    }

    public String take(int twenties, int tens, int fives, int twos, int ones) {

        int twenty = cash.getTwenties() - twenties;
        int ten = cash.getTens() - tens;
        int five = cash.getFives() - fives;
        int two = cash.getTwos() - twos;
        int one = cash.getOnes() - ones;

        if (checker(twenty, ten, five, two, one)) {
            cash.setTwenties(twenty);
            cash.setTens(ten);
            cash.setFives(five);
            cash.setTwos(two);
            cash.setOnes(one);
            return show();
        }

        return INSUFFICIENT_ERROR;
    }

    public String change(int change) {

        Map<Integer, Integer> availableBills = new HashMap<>();
        availableBills.put(20, cash.getTwenties());
        availableBills.put(10, cash.getTens());
        availableBills.put(5, cash.getFives());
        availableBills.put(2, cash.getTwos());
        availableBills.put(1, cash.getOnes());

        Map<Integer, Integer> counter = new HashMap<>();
        counter.put(20, 0);
        counter.put(10, 0);
        counter.put(5, 0);
        counter.put(2, 0);
        counter.put(1, 0);


        List<Integer> bills = new ArrayList<>();
        if (change / 20 > 0 && availableBills.get(20) > 0) {
            bills.add(20);
        }
        if (change / 10 > 0 && availableBills.get(10) > 0) {
            bills.add(10);
        }
        if (change / 5 > 0 && availableBills.get(5) > 0) {
            bills.add(5);
        }
        if (change / 2 > 0 && availableBills.get(2) > 0) {
            bills.add(2);
        }
        if (change > 0 && availableBills.get(1) > 0) {
            bills.add(1);
        }

        if (change > 0 && countChange(change, bills, availableBills, counter) == -1)
            return NO_CHANGE_ERROR;

        System.out.println(counter.get(20) + " " + counter.get(10) + " " + counter.get(5) + " " + counter.get(2) + " " + counter.get(1));
        return take(counter.get(20), counter.get(10), counter.get(5), counter.get(2), counter.get(1));
    }

    private int countChange(int change, List<Integer> bills, Map<Integer, Integer> availableBills, Map<Integer, Integer> counter) {

        if (change == 0) {
            return 0;
        }

        if (change < 0) {
            return -1;
        }

        for (int bill: bills) {
            if (availableBills.get(bill) > 0) {
                counter.put(bill, counter.get(bill) + 1);
                availableBills.put(bill, availableBills.get(bill) - 1);
                int result = countChange(change - bill, bills, availableBills, counter);

                if (result < 0) {
                    counter.put(bill, counter.get(bill) - 1);
                    availableBills.put(bill, availableBills.get(bill) + 1);
                }

                if (result == 0) {
                    return 0;
                }
            }
        }

        return - 1;
    }



    private boolean checker(int twenties, int tens, int fives, int twos, int ones) {
        return twenties >= 0 && tens >= 0 && fives >= 0 && twos >= 0 && ones >= 0;
    }

}

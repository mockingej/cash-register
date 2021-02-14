package com.rocketmiles.service;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class CashRegisterServiceTest {

    CashRegisterService sut = new CashRegisterService();

    @Test
    public void show() {
        String response = sut.show();
        assertEquals("$0 0 0 0 0 0 ", response);
    }

    @Test
    public void put() {
        sut.put(1, 1, 1, 1, 1);
        String response = sut.put(1, 2, 3, 0, 5);
        assertEquals("$98 2 3 4 1 6 ", response);
    }

    @Test
    public void take_inSufficientFundsError() {
        sut.put(1, 1, 1, 1, 1);
        String response = sut.take(2, 0, 0, 0, 5);
        assertEquals("Sorry. Insufficient Funds", response);
    }

    @Test
    public void take() {
        sut.put(3, 3, 3, 3, 3);
        String response = sut.take(2, 0, 0, 0, 0);
        assertEquals("$74 1 3 3 3 3 ", response);
    }

    @Test
    public void change_noChangeError() {
        sut.put(1, 0, 2, 1, 0);
        String response = sut.change(14);
        assertEquals("Sorry. No change can be made", response);
    }

    @Test
    public void change() {
        sut.put(0, 1, 0, 0, 1);
        sut.put(1, 0, 4, 3, 0);
        String response = sut.change(31);
        assertEquals("$26 0 0 4 3 0 ", response);
    }
}
package com.rocketmiles;

import com.rocketmiles.service.CashRegisterService;

import java.util.Scanner;

public class CashRegisterApplication {

    private static final String INVALID_INPUT_ERROR = "Invalid Input!";
    private static final String QUIT = "quit";
    private static final String SHOW = "show";
    private static final String PUT = "put";
    private static final String TAKE = "take";
    private static final String CHANGE = "change";
    private static final String BYE = "Bye!";


    private static final Scanner scanner = new Scanner(System.in);
    private static final CashRegisterService cashRegisterService = new CashRegisterService();

    public static void main(String[] args) {
        System.out.println("Ready...");

        while (true) {
            String in = scanner.nextLine();

            String[] input = in.split(" ");

            if (input.length == 1) {
                if (in.equals(QUIT)) {
                    System.out.println(BYE);
                    break;
                } else if (in.equals(SHOW)){
                    System.out.println(cashRegisterService.show());
                } else {
                    System.out.println(INVALID_INPUT_ERROR);
                }
            } else if (input.length == 6){
                try {
                    int twenties = Integer.parseInt(input[1]);
                    int tens = Integer.parseInt(input[2]);
                    int fives = Integer.parseInt(input[3]);
                    int twos = Integer.parseInt(input[4]);
                    int ones = Integer.parseInt(input[5]);

                    if (input[0].equals(PUT)) {
                        System.out.println(cashRegisterService.put(twenties, tens, fives, twos, ones));
                    } else if (input[0].equals(TAKE)) {
                        System.out.println(cashRegisterService.take(twenties, tens, fives, twos, ones));
                    } else {
                        System.out.println(INVALID_INPUT_ERROR);
                    }

                } catch (NumberFormatException exception) {
                    System.out.println(INVALID_INPUT_ERROR);
                }
            } else if (input.length == 2) {
                try {
                    int change = Integer.parseInt(input[1]);

                    if (input[0].equals(CHANGE) && change >= 0) {
                        System.out.println(cashRegisterService.change(change));
                    } else {
                        System.out.println(INVALID_INPUT_ERROR);
                    }
                } catch (NumberFormatException exception) {
                    System.out.println(INVALID_INPUT_ERROR);
                }
            }

            else {
                System.out.println(INVALID_INPUT_ERROR);
            }
        }
    }
}

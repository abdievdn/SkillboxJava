package main.java;

import java.util.Map;
import java.util.Random;
import java.util.TreeMap;

public class Main {
    public static void main(String[] args) {

        Map<String, Account> accounts = new TreeMap<>();

        for (int i = 1; i < 101; i++) {
            accounts.put(String.valueOf(i), new Account(String.valueOf(i), 100000));
        }

        Bank bank = new Bank(accounts);

        System.out.println("Total bank cash: " + bank.NUMBER_FORMAT.format(bank.getSumAllAccounts()) + '\n');

        for (int i = 0; i < 100; i++) {
            try {
                int from = randomNumber(1, 100);;
                int to = randomNumber(1, 100);
                int amount = randomNumber(10000, 60000);
                new Thread(() -> {
                    bank.transfer(String.valueOf(from), String.valueOf(to), amount);
                }).start();
            }
            catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }

        try {
            Thread.sleep(25000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("=======================================\nTotal bank cash: " + bank.NUMBER_FORMAT.format(bank.getSumAllAccounts()));
    }

    private static int randomNumber(int min, int max) {
        int diff = max - min;
        Random random = new Random();
        int i = random.nextInt(diff + 1);
        return i += min;
    }

}

package main.java;

import java.util.Map;
import java.util.Random;
import java.util.TreeMap;
import java.util.Vector;

public class Main {

    public static final int NUMBER_OF_THREADS = 10000;
    public static final int MIN_ACC_NUMBER = 1;
    public static final int MAX_ACC_NUMBER = 100;
    public static final int MIN_ACC_AMOUNT = 1000;
    public static final int MAX_ACC_AMOUNT = 100000;
    public static final String STRING_TAB = "\t\t\t\t";

    public static void main(String[] args) {

        Map<String, Account> accounts = new TreeMap<>();

        for (int i = 1; i < 101; i++) {
            accounts.put(String.valueOf(i), new Account(String.valueOf(i), 100000));
        }

        Bank bank = new Bank(accounts);

        System.out.println("Total bank cash: " + bank.NUMBER_FORMAT.format(bank.getSumAllAccounts()) + '\n');

        Vector<Thread> threads = new Vector<>();

        for (int i = 0; i < NUMBER_OF_THREADS; i++) {
                int from = randomNumber(MIN_ACC_NUMBER, MAX_ACC_NUMBER);
                int to = randomNumber(MIN_ACC_NUMBER, MAX_ACC_NUMBER);
                int amount = randomNumber(MIN_ACC_AMOUNT, MAX_ACC_AMOUNT);
                threads.add(new Thread(() -> bank.transfer(String.valueOf(from), String.valueOf(to), amount)));
        }

        threads.forEach(Thread::start);
        threads.forEach(thread -> {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        System.out.println("AccountNumber | Balance | BlockedStatus");
        accounts
                .forEach((key, value) -> {
                    System.out.print(value.getAccNumber() + STRING_TAB);
                    System.out.print(value.getMoney() + STRING_TAB);
                    System.out.println(value.isBlock());
                });
        System.out.println("=======================================\nTotal bank cash: " + bank.NUMBER_FORMAT.format(bank.getSumAllAccounts()));
    }



    private static int randomNumber(int min, int max) {
        int diff = max - min;
        Random random = new Random();
        int i = random.nextInt(diff + 1);
        i += min;
        return i;
    }

}

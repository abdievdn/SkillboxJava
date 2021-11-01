package main.java;

import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class Bank {

    private Map<String, Account> accounts;
    private final Random random = new Random();

    public final DecimalFormat NUMBER_FORMAT = new DecimalFormat( "###,###" );

    public Bank(Map<String, Account> accounts) {
        this.accounts = accounts;
    }

    public synchronized boolean isFraud(String fromAccountNum, String toAccountNum, long amount)
            throws InterruptedException {
        Thread.sleep(1000);
        return random.nextBoolean();
    }

    /**
     * TODO: реализовать метод. Метод переводит деньги между счетами. Если сумма транзакции > 50000,
     * то после совершения транзакции, она отправляется на проверку Службе Безопасности – вызывается
     * метод isFraud. Если возвращается true, то делается блокировка счетов (как – на ваше
     * усмотрение)
     */
    public void transfer(String fromAccountNum, String toAccountNum, long amount) {
        Account from = getAccountValue(fromAccountNum);
        Account to = getAccountValue(toAccountNum);

        synchronized (from) {
            if ((from != null && to != null) && (from != to) && (from.getMoney() >= amount)) {
                if (!from.isBlock() && !to.isBlock()) {
                    try {
                        if (amount > 50000 && isFraud(fromAccountNum, toAccountNum, amount)) {
                            from.setBlock(true);
                            to.setBlock(true);
                            System.out.println("Transfer from " + fromAccountNum + " to " + toAccountNum
                                    + " with amount " + amount
                                    + "\nFraud operation. Transaction blocked!\n");
                            return;
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    from.setMoney(getBalance(fromAccountNum) - amount);
                    to.setMoney(getBalance(toAccountNum) + amount);
                    System.out.println("Transfer from " + fromAccountNum + " to " + toAccountNum
                            + " with amount " + amount
                            + "\nBalance for account " + fromAccountNum + " is: "
                            + NUMBER_FORMAT.format(getBalance(String.valueOf(fromAccountNum)))
                            + "\nBalance for account " + toAccountNum + " is: "
                            + NUMBER_FORMAT.format(getBalance(String.valueOf(toAccountNum))) + '\n');
                } else {
                    System.out.println("Account " + fromAccountNum + " blocked: " + from.isBlock()
                            + ". Account " + toAccountNum + " blocked: " + to.isBlock()
                            + ".\nTransaction canceled!\n");
                }
            }
        }
    }

    /**
     * TODO: реализовать метод. Возвращает остаток на счёте.
     */
    public long getBalance(String accountNum) {
        return getAccountValue(accountNum)
                .getMoney();
    }

    public long getSumAllAccounts() {
        return accounts
                .entrySet()
                .parallelStream()
                .mapToLong(acc -> acc.getValue().getMoney())
                .sum();
    }

    private Account getAccountValue(String accountNum) {
        return accounts
                .entrySet()
                .parallelStream()
                .filter(acc -> acc.getValue().getAccNumber().equals(accountNum))
                .findFirst()
                .get().getValue();
    }
}

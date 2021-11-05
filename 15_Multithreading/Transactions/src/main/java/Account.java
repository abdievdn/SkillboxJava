package main.java;

public class Account implements Comparable<Account> {

    private volatile long money;
    private String accNumber;
    private volatile boolean block;

    public Account(String accNumber, long money) {
        this.accNumber = accNumber;
        this.money = money;
        block = false;
    }

    public long getMoney() {
        return money;
    }

    public void setMoney(long money) {
        this.money = money;
    }

    public String getAccNumber() {
        return accNumber;
    }

    public void setAccNumber(String accNumber) {
        this.accNumber = accNumber;
    }

    public boolean isBlock() {
        return block;
    }

    public void setBlock(boolean block) {
        this.block = block;
    }

    @Override
    public int compareTo(Account o) {
        return this.getAccNumber().compareTo(o.getAccNumber());
    }
}

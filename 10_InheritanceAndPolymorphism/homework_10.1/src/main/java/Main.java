public class Main {
    public static void main(String[] args) {
        BankAccount account = new BankAccount();
        account.put(1000);
        System.out.println("First put on BankAccount: " + account.getAmount());

        CardAccount card = new CardAccount();
        card.put(500);
        System.out.println("First put on CardAccount: " + card.getAmount());

        DepositAccount deposit = new DepositAccount();
        deposit.put(500);
        System.out.println("First put on DepositAccount: " + deposit.getAmount());

        account.send(deposit, 500);
        System.out.println("-500 from BankAccount to DepositAccount, BankAccount balance: " + account.getAmount());
        System.out.println("DepositAccount balance: " + deposit.getAmount());
        account.send(card, 400);
        System.out.println("-400 from BankAccount to CardAccount, BankAccount balance: " + account.getAmount());
        System.out.println("CardAccount balance: " + card.getAmount());
        card.send(deposit, 600);
        System.out.println("-600 from CardAccount to DepositAccount, CardAccount balance: " + card.getAmount());
        System.out.println("DepositAccount balance: " + deposit.getAmount());
        deposit.send(account, 600);
        System.out.println("-600 from DepositAccount to BankAccount, DepositAccount balance: " + deposit.getAmount());
        System.out.println("BankAccount balance: " + account.getAmount());
    }


}

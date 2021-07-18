public class BankAccount {

  private double amount;

  public double getAmount() {
    return amount;
  }

  public void put(double amountToPut) {
    if (amountToPut < 0 ) return;
    amount += amountToPut;
  }

  public boolean take(double amountToTake) {
    if (amount < amountToTake) return false;
    amount -= amountToTake;
    return true;
  }

  public boolean send(BankAccount receiver, double amount) {
    return (receiver.take(amount));
  }
}

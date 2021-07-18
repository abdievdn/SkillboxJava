public class CardAccount extends BankAccount {
    @Override
    public boolean take(double amountToTake) {
        amountToTake += amountToTake * 0.01;
        return (super.take(amountToTake));
    }
}

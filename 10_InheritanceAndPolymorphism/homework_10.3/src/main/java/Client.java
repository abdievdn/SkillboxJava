public abstract class Client {

    private double amount;

    public double getAmount() {
        return amount;
    }

    public void put(double amountToPut) {
        if (amountToPut < 1) return;
        amount += amountToPut;
    }

    public void take(double amountToTake) {
        if (amount < amountToTake) return;
        amount -= amountToTake;
    }
}

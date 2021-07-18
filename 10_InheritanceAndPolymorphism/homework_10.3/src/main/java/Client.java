public abstract class Client {

    protected double amount;

    public abstract double getAmount();
    public abstract void put(double amountToPut);
    public abstract void take(double amountToTake);

}

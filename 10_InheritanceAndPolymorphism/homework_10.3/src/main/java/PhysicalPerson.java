public class PhysicalPerson extends Client {

    @Override
    public double getAmount() {
        return amount;
    }

    @Override
    public void put(double amountToPut) {
        if (amountToPut < 1) return;
        amount += amountToPut;
    }

    @Override
    public void take(double amountToTake) {
        if (amount < amountToTake) return;
        amount -= amountToTake;
    }
}

public class LegalPerson extends Client {

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
        amountToTake += amountToTake * 0.01;
        if (amount < amountToTake) return;
        amount -= amountToTake;
    }
}

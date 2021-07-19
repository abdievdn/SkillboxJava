public class LegalPerson extends Client {

    @Override
    public void take(double amountToTake) {
        amountToTake += amountToTake * 0.01;
        super.take(amountToTake);
    }
}

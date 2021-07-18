import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class DepositAccount extends BankAccount {

    private LocalDate lastIncome;

    @Override
    public void put(double amountToPut) {
        super.put(amountToPut);
        lastIncome = LocalDate.now();
    }

    @Override
    public boolean take(double amountToTake) {
        if (ChronoUnit.MONTHS.between(lastIncome, LocalDate.now()) < 0) return false;
        return super.take(amountToTake);
    }
}

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class BankStatement {

    public static final String RUB = " руб.";
    DecimalFormat df = new DecimalFormat("###,###.##");

    private double expenseSum;
    private double incomeSum;
    List<String> organizationsExpense;

    public BankStatement() {
        organizationsExpense = new ArrayList<>();
    }

    public List<String> getOrganizationsExpense() {
        return organizationsExpense;
    }

    public void setOrganizationsExpense(List<String> organizationsExpense) {
        this.organizationsExpense = organizationsExpense;
    }

    public double getExpenseSum() {
        return expenseSum;
    }

    public void setExpenseSum(double expenseSum) {
        this.expenseSum = expenseSum;
    }

    public double getIncomeSum() {
        return incomeSum;
    }

    public void setIncomeSum(double incomeSum) {
        this.incomeSum = incomeSum;
    }

    private String listOrganizationsExpense() {
        String orgList = "";
        for (String org : organizationsExpense) {
            orgList += org + '\n';
        }
        return orgList;
    }

    @Override
    public String toString() {
        return "Сумма расходов: " + df.format(expenseSum) + RUB +
                "\nСумма доходов: " + df.format(incomeSum) + RUB +
                "\n\nСуммы расходов по организациям:\n" +listOrganizationsExpense();
    }
}

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class Main {

  public static void main(String[] args) {
    System.out.println(getPeriodFromBirthday(LocalDate.of(1995, 5, 23)));
  }

  private static String getPeriodFromBirthday(LocalDate birthday) {
    LocalDate today = LocalDate.now();
    return birthday.until(today, ChronoUnit.YEARS) + " years, " +
            birthday.until(today).getMonths() + " months, " +
            birthday.until(today).getDays() + " days";
  }

}

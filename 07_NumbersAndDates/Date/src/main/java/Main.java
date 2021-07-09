import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Locale;

public class Main {

    public static void main(String[] args) {

        int day = 31;
        int month = 12;
        int year = 1990;

        System.out.println(collectBirthdays(year, month, day));
    }

    public static String collectBirthdays(int year, int month, int day) {

        //TODO реализуйте метод для построения строки в следующем виде
        //0 - 31.12.1990 - Mon
        //1 - 31.12.1991 - Tue

        LocalDate birthday = LocalDate.of(year, month, day);
        LocalDate today = LocalDate.now();
        long datesDifference = birthday.until(today, ChronoUnit.YEARS);
        DateTimeFormatter dateToString = DateTimeFormatter.ofPattern("dd.MM.yyyy - EEE", Locale.ENGLISH);
        String result = "";

        if (today.compareTo(birthday) >= 0)
        {
            for (int i = 0; i <= datesDifference; i++) {
                result += i + " - " + dateToString.format(birthday.plusYears(i)) + '\n';
            }
        }
        return result;
    }
}

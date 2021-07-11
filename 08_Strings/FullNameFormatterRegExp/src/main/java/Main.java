import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {

  public static void main(String[] args) {

    Scanner scanner = new Scanner(System.in);
    while (true) {
      String input = scanner.nextLine();
      if (input.equals("0")) {
        break;
      }
      //TODO:напишите ваш код тут, результат вывести в консоль.
      //При невалидном ФИО вывести в консоль: Введенная строка не является ФИО
      String surname = "";
      String name ="";
      String patronymic = "";
      String regex = "[а-яА-Я]+[-]?[а-яА-Я]+";
      Pattern pattern = Pattern.compile(regex);
      Matcher matcher = pattern.matcher(input);
      int matcherCount = 0;

      while (matcher.find()) {
        matcherCount++;
        if (surname.isEmpty()) surname = input.substring(matcher.start(), matcher.end());
        else if (name.isEmpty()) name = input.substring(matcher.start(), matcher.end());
        else if (patronymic.isEmpty()) patronymic = input.substring(matcher.start(), matcher.end());
      }
      if (surname.isEmpty() || name.isEmpty() || patronymic.isEmpty() || matcherCount > 3)
        System.out.println("Введенная строка не является ФИО");
      else System.out.println("Фамилия: " + surname +
              "\nИмя: " + name +
              "\nОтчество: " + patronymic);
    }
  }

}
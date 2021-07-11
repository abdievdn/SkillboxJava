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
      String regex = "[^\\d]";
      String number = input.replaceAll(regex, "");

        if (number.length() == 10) number = "7" + number;
        if (number.charAt(0) == '8')
          number = number.replaceFirst("[8]","7");
        if (number.length() > 11 || number.length() < 10 || number.charAt(0) != '7') {
          System.out.println("Неверный формат номера\n");
        }
        else System.out.println(number);
    }
  }

}

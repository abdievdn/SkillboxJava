import java.util.Scanner;

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

      String surname = "",
             name = "",
             patronymic = "";
      boolean isFIO = true;
      int spaceCount = 0;

      for (int i = 0; i < input.length(); i++) {
        if (Character.isDigit(input.charAt(i)) || spaceCount > 2) {
          isFIO = false;
          break;
        }
        if (Character.isSpaceChar(input.charAt(i))) spaceCount++;
      }
      int pos1 = input.indexOf(' ');
      int pos2 = input.lastIndexOf(' ');
      if (pos1 != pos2) {
          surname = input.substring(0, pos1);
          name = input.substring(pos1 + 1, pos2);
          patronymic = input.substring(pos2 + 1, input.length());
      }


      if (surname.isEmpty() || name.isEmpty() || patronymic.isEmpty() || !isFIO)
        System.out.println("Введенная строка не является ФИО");
      else System.out.println("Фамилия: " + surname + "\nИмя: " + name + "\nОтчество: " + patronymic);
    }
  }

}
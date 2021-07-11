public class Main {

  public static void main(String[] args) {

    String text = "Вася заработал 5000 рублей, Петя - 7563 рубля, а Маша - 30000 рублей";
    //TODO: напишите ваш код, результат вывести в консоль

    int earningsSum = 0;
    int i = 0;
    text.trim();

    while (i != text.length()) {
      if (Character.isDigit(text.charAt(i))) {
        int start = i;
        while (Character.isDigit(text.charAt(i))) {
          i++;
        }
        int end = i;
        earningsSum += Integer.parseInt(text.substring(start, end));
      }
      else i++;
    }

    System.out.println(earningsSum);

  }

}
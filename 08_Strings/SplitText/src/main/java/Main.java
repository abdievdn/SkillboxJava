import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {

  public static void main(String[] args) {

  }

  public static String splitTextIntoWords(String text) {
    //TODO реализуйте метод
    String result = "";
    String regex = "[^\\d\\s\\r\\n\\t,.!?:;+\\-<>]+";
    Pattern pattern = Pattern.compile(regex);
    Matcher matcher = pattern.matcher(text);
    while (matcher.find()) {
      result += text.substring(matcher.start(), matcher.end()) + '\n';
    }
    return result.trim();
  }

}
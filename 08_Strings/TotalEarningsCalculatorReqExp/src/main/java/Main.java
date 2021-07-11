import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {

  public static void main(String[] args) {

  }

  public static int calculateSalarySum(String text){
    //TODO: реализуйте метод
    int earningsSum = 0;
    String regex = "\\d+";
    Pattern pattern = Pattern.compile(regex);
    Matcher matcher = pattern.matcher(text);
    while (matcher.find()) {
      earningsSum += Integer.parseInt(text.substring(matcher.start(), matcher.end()));
    }
    return earningsSum;
  }

}
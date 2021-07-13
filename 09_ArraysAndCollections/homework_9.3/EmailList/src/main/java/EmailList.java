import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EmailList {

    private final TreeSet<String> emailList = new TreeSet<>();
    private final String regex = "\\w+([\\.-]?\\w+)@\\w+([\\.-]?\\w+)\\.\\w{2,4}";
    private final Pattern pattern = Pattern.compile(regex);

    public void add(String email) {
        // TODO: валидный формат email добавляется
        Matcher matcher = pattern.matcher(email);
        if (matcher.find()) {
            if (emailList.contains(email.toLowerCase())) {
                System.out.println("Такой email уже существует в базе");
            }
            else {
                emailList.add(email.toLowerCase());
                System.out.println("Email добавлен");
            }
        }
        else System.out.println(Main.WRONG_EMAIL_ANSWER);
    }

    public List<String> getSortedEmails() {
        // TODO: возвращается список электронных адресов в алфавитном порядке
        ArrayList<String> sortedEmails = new ArrayList<>();
        for (String email : emailList)
            sortedEmails.add(email);
        return sortedEmails;
    }

}

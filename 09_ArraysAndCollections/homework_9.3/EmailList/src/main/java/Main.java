import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
    public static final String WRONG_EMAIL_ANSWER = "Неверный формат email";
    
    /* TODO:
        Пример вывода списка Email, после ввода команды LIST в консоль:
        test@test.com
        hello@mail.ru
        - каждый адрес с новой строки
        - список должен быть отсортирован по алфавиту
        - email в разных регистрах считается одинаковыми
           hello@skillbox.ru == HeLLO@SKILLbox.RU
        - вывод на печать должен быть в нижнем регистре
           hello@skillbox.ru
        Пример вывода сообщения об ошибке при неверном формате Email:
        "Неверный формат email"
    */

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        final String commandGroup = "command";
        final String textGroup = "text";
        final String addCommand = "ADD";
        final String listCommand = "LIST";
        final String regex = String.format("(?<%s>%s|%s)(\\s(?<%s>.*))?", commandGroup, addCommand, listCommand, textGroup);
        final Pattern pattern = Pattern.compile(regex);
        EmailList emailList = new EmailList();

        while (true) {
            String input = scanner.nextLine();
            if (input.equals("0")) {
                break;
            }
            Matcher matcher = pattern.matcher(input);
            if (matcher.find()) {
                switch (matcher.group(commandGroup)) {
                    case addCommand:
                        if (matcher.group(textGroup) != null)
                            emailList.add(matcher.group(textGroup));
                        break;
                    case listCommand:
                        System.out.println("LIST");
                        for (String email : emailList.getSortedEmails())
                            System.out.println(email);
                        break;
                    default:
                        break;
                }
            }
        }
    }
}

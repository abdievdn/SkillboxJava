import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
    private static TodoList todoList = new TodoList();

    public static void main(String[] args) {
        // TODO: написать консольное приложение для работы со списком дел todoList

        System.out.println("Доступные команды: LIST, ADD, EXIT;\n и команды с указанием номера через пробел: ADD, EDIT, DELETE");
        while (true) {
            Scanner input = new Scanner(System.in);
            String line = input.nextLine();
            String regex = "[A-Z]{3,}(\\s\\d)?";
            Pattern pattern = Pattern.compile(regex);
            Matcher matcher = pattern.matcher(line);

            if (matcher.find()) {

                if (line.substring(matcher.start(), matcher.end()).equals("LIST")) {
                    for (int i = 0; i < todoList.getTodos().size(); i++) {
                        System.out.println(i + " - " + todoList.getTodos().get(i));
                    }
                }

                else if (line.substring(matcher.start(), matcher.end()).equals("EXIT")) break;

                else if (line.substring((matcher.start()), matcher.end()).equals("ADD")) {
                    todoList.add(line.substring(matcher.end(), line.length()).trim());
                }

                else if (line.substring((matcher.start()), matcher.end()).contains("ADD")) {
                    int index = Integer.parseInt(line.substring(4, matcher.end()));
                    todoList.add(index, line.substring(matcher.end(), line.length()).trim());
                }

                else if (line.substring((matcher.start()), matcher.end()).contains("EDIT")) {
                    int index = Integer.parseInt(line.substring(5, matcher.end()));
                    todoList.edit(line.substring(matcher.end(), line.length()).trim(), index);
                }

                else if (line.substring((matcher.start()), matcher.end()).contains("DELETE")) {
                    int index = Integer.parseInt(line.substring(7, matcher.end()));
                    todoList.delete(index);
                }

                else System.out.println("Неверная команда");
            }
        }
    }
}

import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
    private static final TodoList todoList = new TodoList();
    private static final Pattern pattern = Pattern.compile("(?<command>ADD|EDIT|DELETE|LIST|EXIT)(\\s(?<index>\\d+))?(\\s(?<text>.+))?");

    public static void main(String[] args) {
        // TODO: написать консольное приложение для работы со списком дел todoList

        boolean isExit = false;


        System.out.println("Доступные команды: LIST, ADD, EXIT;\n и команды с указанием номера через пробел: ADD, EDIT, DELETE");
        Scanner input = new Scanner(System.in);
        while (true) {
            String line = input.nextLine();
            Matcher matcher = pattern.matcher(line);
            if (matcher.find()) {
                switch (matcher.group("command")) {
                    case "EXIT":
                        isExit = true;
                        break;
                    case "LIST":
                        for (int i = 0; i < todoList.getTodos().size(); i++) {
                            System.out.println(i + " - " + todoList.getTodos().get(i));
                        }
                        break;
                    case "ADD":
                        if (isEmptyText(matcher)) break;
                        if (isIndexNull(matcher, false)) todoList.add(matcher.group("text").trim());
                        else {
                            int index = Integer.parseInt(matcher.group("index"));
                            todoList.add(index, matcher.group("text").trim());
                        }
                        break;
                    case "EDIT":
                        if (isIndexNull(matcher, true) || isEmptyText(matcher)) break;
                        int index = Integer.parseInt(matcher.group("index"));
                        todoList.edit(matcher.group("text").trim(), index);
                        break;
                    case "DELETE":
                        if (isIndexNull(matcher, true)) break;
                        index = Integer.parseInt(matcher.group("index"));
                        todoList.delete(index);
                        break;
                    default:
                        break;
                }
                if (isExit) break;
            }
        }
    }

    private static boolean isEmptyText(Matcher matcher) {
        if (matcher.group("text") == null) {
            System.out.println("Введите информацию о деле");
            return true;
        }
        return false;
    }

    private static boolean isIndexNull(Matcher matcher, boolean isMessage) {
        if (matcher.group("index") == null) {
            if (isMessage == true) System.out.println("Не введен индекс");
            return true;
        }
        return false;
    }
}

package Main;

import java.util.ArrayList;
import java.util.TreeMap;

public class Storage {

    private static int currentNumber = 1;
    private static TreeMap<Integer, Todo> todoList = new TreeMap<>();

    public static int addTodo(Todo todo) {
        int number = currentNumber++;
        todo.setNumber(number);
        todoList.put(number, todo);
        return number;
    }

    public static Todo getTodo(int todoNumber) {
        if (todoList.containsKey(todoNumber)) {
            return todoList.get(todoNumber);
        }
        return null;

    }

    public static ArrayList<Todo> getTodoList() {
        ArrayList<Todo> caseList = new ArrayList<>();
        caseList.addAll(todoList.values());
        return caseList;
    }
}

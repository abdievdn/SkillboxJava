package Main;

import java.util.ArrayList;
import java.util.TreeMap;
import java.util.concurrent.atomic.AtomicInteger;

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

    public static void deleteTodo(int number) {
        todoList.remove(number);
        //refreshing todoList
        int todoListSize = todoList.size();
        for (int i = number; i < todoListSize + 1; i++) {
            todoList.put(i, todoList.get(i + 1));
            todoList.get(i + 1).setNumber(i);
            try {
                todoList.remove(i + 1);
            }
            catch (Exception e) {
            }
        }
        currentNumber--;
    }

    public static void editTodo(int number,Todo todo) {
        todoList.replace(number, todo);
    }

}

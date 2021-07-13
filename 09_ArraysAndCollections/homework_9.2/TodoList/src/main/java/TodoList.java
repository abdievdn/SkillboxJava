import java.util.ArrayList;

public class TodoList {

    private ArrayList<String> todoList = new ArrayList<>();

    public void add(String todo) {
        // TODO: добавьте переданное дело в конец списка
        System.out.println("Добавлено дело \"" + todo + "\"");
        todoList.add(todo);
    }

    public void add(int index, String todo) {
        // TODO: добавьте дело на указаный индекс,
        //  проверьте возможность добавления
        if (todoList.size() == 0 || index - todoList.size() == 1) {
            todoList.add(todo);
        }
        else if (index <= todoList.size() && index >= 0) {
            System.out.println("Добавлено дело \"" + todo + "\" по номеру \"" + index + "\"");
            todoList.add(index, todo);
        }

        else System.out.println("Такого номера не существует в списке");
    }

    public void edit(String todo, int index) {
        // TODO: заменить дело на index переданным todo индекс,
        //  проверьте возможность изменения
        if (index < todoList.size()  && index >= 0) {
            System.out.println("Дело \"" + todoList.get(index) + "\" заменено на \"" + todo + "\"");
            todoList.set(index, todo);
        }
        else System.out.println("Дело с таким номером не существует");
    }

    public void delete(int index) {
        // TODO: удалить дело находящееся по переданному индексу,
        //  проверьте возможность удаления дела
        if (index < todoList.size()  && index >= 0) {
            System.out.println("Дело \"" + todoList.get(index) + "\" удалено");
            todoList.remove(index);
        }
        else System.out.println("Дело с таким номером не существует");
    }

    public ArrayList<String> getTodos() {
        // TODO: вернуть список дел
        return todoList;
    }

}
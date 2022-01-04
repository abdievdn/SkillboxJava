package Main;

import Main.model.Todo;
import Main.model.TodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
public class TodoController {

    @Autowired
    private TodoRepository todoRepository;

    @GetMapping("/todolist/")
    public List<Todo> list() {
        Iterable<Todo> todoIterable = todoRepository.findAll();
        ArrayList<Todo> todos = new ArrayList<>();
        for (Todo todo: todoIterable) {
            todos.add(todo);
        }
        return todos;
    }

    @GetMapping("/todolist/{number}")
    public ResponseEntity get(@PathVariable int number) {
        Optional<Todo> optionalTodo = todoRepository.findById(number);
        if (!optionalTodo.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        return new ResponseEntity(optionalTodo.get(), HttpStatus.OK);
    }

    @PostMapping("/todolist/")
    public int add(Todo todo) {
        Todo newTodo = todoRepository.save(todo);
        return newTodo.getNumber();
    }

    @PutMapping("/todolist/{number}")
    public void edit(@PathVariable int number, Todo todo) {
        Optional<Todo> optionalTodo = todoRepository.findById(number);
        if (optionalTodo.isPresent()) {
            todoRepository.save(todo);
        }
    }

    @DeleteMapping("/todolist/{number}")
    public void delete(@PathVariable int number) {
        todoRepository.deleteById(number);
    }

    @DeleteMapping("/todolist/delete_all")
    public void deleteAll() {
        todoRepository.deleteAll();
    }
}

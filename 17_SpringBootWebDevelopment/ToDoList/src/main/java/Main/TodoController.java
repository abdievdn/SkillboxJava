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

    @GetMapping("/todolist/get/{number}")
    public ResponseEntity get(@PathVariable int number) {
        Optional<Todo> optionalTodo = todoRepository.findById(number);
        if (!optionalTodo.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        return new ResponseEntity(optionalTodo.get(), HttpStatus.OK);
    }

    @PostMapping("/todolist/add")
    public int add(Todo todo) {
        Todo newTodo = todoRepository.save(todo);
        return newTodo.getNumber();
    }

    @PutMapping("/todolist/edit/{number}")
    public void edit(@PathVariable int number, Todo todo) {
        Optional<Todo> optionalTodo = todoRepository.findById(number);
        if (optionalTodo.isPresent()) {
            todoRepository.save(todo);
        }
    }

    @DeleteMapping("/todolist/delete/{number}")
    public ResponseEntity delete(@PathVariable int number) {
        Optional<Todo> optionalTodo = todoRepository.findById(number);
        if (!optionalTodo.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        todoRepository.deleteById(number);
        return new ResponseEntity(optionalTodo.get(), HttpStatus.OK);
    }

    @DeleteMapping("/todolist/delete/all")
    public void deleteAll() {
        todoRepository.deleteAll();
    }
}

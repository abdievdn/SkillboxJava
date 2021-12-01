package Main;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TodoController {

    @GetMapping("/todolist/")
    public List<Todo> list() {
        return Storage.getTodoList();
    }

    @GetMapping("/todolist/{number}")
    public ResponseEntity get(@PathVariable int number) {
        Todo todo = Storage.getTodo(number);
        if (todo == null ) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        return new ResponseEntity(todo, HttpStatus.OK);
    }

    @RequestMapping("/todolist/")
    public synchronized int add(Todo todo) {
        return Storage.addTodo(todo);
    }

    @DeleteMapping("/todolist/")
    public void delete(int number) {

    }

}

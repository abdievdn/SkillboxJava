package Main;

import Main.model.Todo;
import Main.data.TodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;

@Controller
public class DefaultController {

    @Autowired
    private TodoRepository todoRepository;

    @Value("${revision.value}")
    private Integer revision;

    @RequestMapping("/")
    public String index(Model model) {
        Iterable<Todo> todoIterable = todoRepository.findAll();
        ArrayList<Todo> todoList = new ArrayList<>();
        todoIterable.forEach(todo -> todoList.add(todo));
        model.addAttribute("todoList", todoList);
        model.addAttribute("todoCount", todoList.size());
        model.addAttribute("revision", revision);

        return "index";
    }
}

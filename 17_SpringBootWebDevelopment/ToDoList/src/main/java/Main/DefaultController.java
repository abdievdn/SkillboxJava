package Main;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

@RestController
public class DefaultController {

    @RequestMapping("/")
    public String index() {
        int number;
        number = (int) (Math.random()*100 + 1);
        return String.format("ToDoList number #%s for date %s", number, LocalDate.now());
    }
}

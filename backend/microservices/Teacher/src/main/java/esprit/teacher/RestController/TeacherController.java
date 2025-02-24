package esprit.teacher.RestController;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/teacher")
@RestController
public class TeacherController {
    private String title = "Hello,i'm the teacher micro-service";

    @RequestMapping("/hello")
    public String sayHello() {
        System.out.println(title);
        return title;
    }
}

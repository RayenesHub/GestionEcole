package esprit.event;

import org.springframework.web.bind.annotation.RequestMapping;

public class EventRestAPI {
    private String title="Hello,i'm the event micro-service";
    @RequestMapping("/hello")
    public String sayHello(){
        System.out.println(title);
        return title;
    }
}

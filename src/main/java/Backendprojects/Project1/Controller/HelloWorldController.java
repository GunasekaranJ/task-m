package Backendprojects.Project1.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorldController {

    @GetMapping("/h")
    String helloWorld(){
        return "Hello World";
    }
}


package projectJEE.sw.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MonstersController {
    @GetMapping("/html/monsters")
    public String monsters() {
        return "/html/monsters";
    }
}

package projectJEE.sw.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ArtifactsController {
    @GetMapping("/artifacts")
    public String artifacts() {
        return "/html/artifacts";
    }
}

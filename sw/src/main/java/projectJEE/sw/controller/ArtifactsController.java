package projectJEE.sw.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import projectJEE.sw.dbRepository.ArtifactRepository;

@Controller
public class ArtifactsController {
    @Autowired
    ArtifactRepository artifactRepository;
    @GetMapping("/html/artifacts")
    public String artifacts(Model model) {
        model.addAttribute("artifact",artifactRepository.findAll());
        return "/html/artifacts";
    }
}

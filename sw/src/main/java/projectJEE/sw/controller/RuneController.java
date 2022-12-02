package projectJEE.sw.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import projectJEE.sw.dbEntity.Rune;
import projectJEE.sw.dbRepository.RuneRepository;

import javax.persistence.NamedQuery;
import java.util.List;

@Controller
public class RuneController {
    @GetMapping("/html/runes")
    public String artifacts() {
        return "/html/runes";
    }
    @Autowired
    RuneRepository runeRepository;

    @GetMapping("runes")
    public String triRune(){
        System.out.println(runeRepository.findAllByOrderByIdRuneDesc());
        return "redirect:/";
    }


}

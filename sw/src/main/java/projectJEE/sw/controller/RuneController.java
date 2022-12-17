package projectJEE.sw.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import projectJEE.sw.dbEntity.Rune;
import projectJEE.sw.dbRepository.RuneRepository;

import javax.persistence.NamedQuery;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

@Controller
public class RuneController {
    @Autowired
    RuneRepository runeRepository;
    @GetMapping("/runes")
    public String runes(Model model) {
        List<Rune> runes = runeRepository.findAllByOrderByEfficiencyDesc();
        float[] efficiency = new float[400];
        int i=0;
        Iterator<Rune> it = runes.iterator();
        while(it.hasNext() && i<400){
            Rune rune = it.next();
            efficiency[i] = rune.getEfficiency();
            i++;
        }

        /*model.addAttribute("runes",runes);*/
        model.addAttribute("eff",efficiency);
        return "/html/runes";
    }
}

package projectJEE.sw.controller;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
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
    @PostMapping("/runes/filter")
    public ResponseEntity<JSONObject> runeFilter(@Param("set") int set,@Param("ancient") int ancient,@Param("nbRunes") int nbRunes){
        JSONObject filter = new JSONObject();
        List<Rune> runes = runeRepository.findAllByOrderByEfficiencyDesc();
        if(ancient==1){
            runes=runeRepository.findAllNonAncient();
        }else if(ancient==2){
            runes=runeRepository.findAllAncient();
        }
        if(set!=0){
            runes=runeRepository.findAllBySet(set);
            if(ancient==1){
                runes = runeRepository.findAllNonAncientBySet(set);
            }else if(ancient==2){
                runes = runeRepository.findAllAncientBySet(set);
            }
        }
        Float[] efficiency = new Float[nbRunes];
        int i=0;
        Iterator<Rune> it = runes.iterator();
        while(it.hasNext() && i<nbRunes){
            Rune rune = it.next();
            efficiency[i] = rune.getEfficiency();
            i++;
        }
        filter.put("efficiency",efficiency);
        filter.put("totalRunes",nbRunes);
        return ResponseEntity.status(HttpStatus.OK).body(filter);
    }
}

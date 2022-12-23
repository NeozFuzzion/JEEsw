package projectJEE.sw.controller;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.JpaSort;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import projectJEE.sw.dbEntity.*;
import projectJEE.sw.dbRepository.RuneRepository;
import projectJEE.sw.dbRepository.RuneSetRepository;
import projectJEE.sw.dbRepository.UserRepository;
import projectJEE.sw.model.RuneId;

import javax.persistence.NamedQuery;
import java.lang.reflect.InvocationTargetException;
import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

@Controller
public class RuneController {
    @Autowired
    RuneRepository runeRepository;
    @Autowired
    RuneSetRepository runeSetRepository;
    @Autowired
    UserRepository userRepository;

    @GetMapping("/runes")
    public String runes(Model model) {
        User user=userRepository.findUserByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
        List<String> jsons=runeRepository.findAllJson(user);
        if(!jsons.isEmpty()) {
            String json1 = jsons.get(0);
            List<Rune> runes = runeRepository.findAll(Sort.by("efficiency").descending(),json1,user);
            List<Rune> runes2 = runeRepository.findAll(Sort.by("effMaxHero").descending(),json1,user);
            List<Rune> runes3 = runeRepository.findAll(Sort.by("effMaxLegend").descending(),json1,user);
            float[] efficiency = new float[400];
            float[] effMaxHero = new float[400];
            float[] effMaxLegend = new float[400];
            int i=0;
            Iterator<Rune> it = runes.iterator();
            while(it.hasNext() && i<400){
                Rune rune = it.next();
                efficiency[i] = rune.getEfficiency();
                i++;
            }
            Iterator<Rune> it2 = runes2.iterator();
            int j =0;
            while(it2.hasNext() && j<400){
                Rune rune = it2.next();
                effMaxHero[j] = rune.getEffMaxHero();
                j++;
            }
            Iterator<Rune> it3 = runes3.iterator();
            int k=0;
            while(it3.hasNext() && k<400){
                Rune rune = it3.next();
                effMaxLegend[k] = rune.getEffMaxLegend();
                k++;
            }
            model.addAttribute("jsons",jsons);
            model.addAttribute("runes",runes);
            model.addAttribute("eff",efficiency);
            model.addAttribute("effMaxHero",effMaxHero);
            model.addAttribute("effMaxLegend",effMaxLegend);
        }
        model.addAttribute("data",!jsons.isEmpty());
        return "/html/runes";

    }
    @PostMapping("/runes/filter")
    public ResponseEntity<JSONObject> runeFilter(@Param("jsonChosen") String jsonChosen,@Param("set") int set,@Param("ancient") int ancient,@Param("nbRunes") int nbRunes){
        User user=userRepository.findUserByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
        RuneSet setRune = runeSetRepository.getReferenceById(set);
        JSONObject filter = new JSONObject();
        List<Rune> runes = runeRepository.findAll(Sort.by("efficiency").descending(),jsonChosen,user);
        List<Rune> runes2 = runeRepository.findAll(Sort.by("effMaxHero").descending(),jsonChosen,user);
        List<Rune> runes3 = runeRepository.findAll(Sort.by("effMaxLegend").descending(),jsonChosen,user);
        if(ancient==1){
            runes=runeRepository.findAllNonAncient(Sort.by("efficiency").descending(),jsonChosen,user);
            runes2=runeRepository.findAllNonAncient(Sort.by("effMaxHero").descending(),jsonChosen,user);
            runes3=runeRepository.findAllNonAncient(Sort.by("effMaxLegend").descending(),jsonChosen,user);
        }else if(ancient==2){
            runes=runeRepository.findAllAncient(Sort.by("efficiency").descending(),jsonChosen,user);
            runes2=runeRepository.findAllAncient(Sort.by("effMaxHero").descending(),jsonChosen,user);
            runes3=runeRepository.findAllAncient(Sort.by("effMaxLegend").descending(),jsonChosen,user);
        }
        if(set!=0){
            runes=runeRepository.findAllBySet(setRune,Sort.by("efficiency").descending(),jsonChosen,user);
            runes2=runeRepository.findAllBySet(setRune,Sort.by("effMaxHero").descending(),jsonChosen,user);
            runes3=runeRepository.findAllBySet(setRune,Sort.by("effMaxLegend").descending(),jsonChosen,user);
            if(ancient==1){
                runes = runeRepository.findAllNonAncientBySet(setRune,Sort.by("efficiency").descending(),jsonChosen,user);
                runes2=runeRepository.findAllNonAncientBySet(setRune,Sort.by("effMaxHero").descending(),jsonChosen,user);
                runes3=runeRepository.findAllNonAncientBySet(setRune,Sort.by("effMaxLegend").descending(),jsonChosen,user);
            }else if(ancient==2){
                runes = runeRepository.findAllAncientBySet(setRune, Sort.by("efficiency").descending(),jsonChosen,user);
                runes2=runeRepository.findAllAncientBySet(setRune,Sort.by("effMaxHero").descending(),jsonChosen,user);
                runes3=runeRepository.findAllAncientBySet(setRune,Sort.by("effMaxLegend").descending(),jsonChosen,user);
            }
        }
        Float[] efficiency = new Float[nbRunes];
        Float[] effMaxHero = new Float[nbRunes];
        Float[] effMaxLegend = new Float[nbRunes];
        int i=0;
        Iterator<Rune> it = runes.iterator();
        while(it.hasNext() && i<nbRunes){
            Rune rune = it.next();
            efficiency[i] = rune.getEfficiency();
            i++;
        }
        int j=0;
        Iterator<Rune> it2 = runes2.iterator();
        while(it2.hasNext() && j<nbRunes){
            Rune rune = it2.next();
            effMaxHero[j] = rune.getEffMaxHero();
            j++;
        }
        int k=0;
        Iterator<Rune> it3 = runes3.iterator();
        while(it3.hasNext() && k<nbRunes){
            Rune rune = it3.next();
            effMaxLegend[k] = rune.getEffMaxLegend();
            k++;
        }
        filter.put("jsonToUse",jsonChosen);
        filter.put("efficiency",efficiency);
        filter.put("effMaxHero",effMaxHero);
        filter.put("effMaxLegend",effMaxLegend);
        filter.put("totalRunes",nbRunes);
        JSONArray runesJSON = new JSONArray();
        runes.forEach(rune -> {runesJSON.add(rune.toJSON());});
        filter.put("runes",runesJSON);

        return ResponseEntity.status(HttpStatus.OK).body(filter);
    }

    @GetMapping(value = "/runesBook/{id}")
    public String runeBook(@PathVariable("id") long id, Model model){
        User user=userRepository.findUserByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
        Rune rune = runeRepository.findFirstByIdRune(new RuneId(user,id));
        model.addAttribute("rune",rune);
        model.addAttribute("format",new DecimalFormat("0.00"));
        return "/html/runesBook";
    }
}

package projectJEE.sw.controller;

import org.apache.commons.io.IOUtils;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import projectJEE.sw.dbEntity.*;
import projectJEE.sw.dbRepository.*;
import projectJEE.sw.model.ArtifactId;
import projectJEE.sw.model.MonsterId;
import projectJEE.sw.model.RuneId;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

@Controller
public class ExtractController {
    @Autowired
    MonsterRepository monsterRepository;
    @Autowired
    StatRuneRepository statRuneRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    GameMonsterRepository gameMonsterRepository;
    @Autowired
    RuneRepository runeRepository;
    @Autowired
    ArtifactRepository artifactRepository;
    @Autowired
    StatArtifactRepository statArtifactRepository;

    @Autowired
    GrindstoneRepository grindstoneRepository;

    @Autowired
    RuneSetRepository runeSetRepository;


    @GetMapping("/uploadJSON")
    public String index() {
        return "/html/uploadJSON";
    }

    @PostMapping("/upload")
    public String singleFileUpload(@RequestParam("file") MultipartFile file,
                                   RedirectAttributes redirectAttributes) throws IOException, ParseException, NoSuchMethodException, InvocationTargetException, IllegalAccessException {

        if (file.isEmpty() || !Objects.requireNonNull(file.getOriginalFilename()).matches("^.*\\.json$")) {
            redirectAttributes.addFlashAttribute("message", "Please select a correct jSON file to upload");
            return "redirect:/uploadJSON";
        }
        User user =userRepository.findUserByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
        // Get the file and save it somewhere
        ByteArrayInputStream stream = new   ByteArrayInputStream(file.getBytes());
        String myString = IOUtils.toString(stream, "UTF-8");


        JSONParser jsonP = new JSONParser();

        JSONObject jsonO = (JSONObject)jsonP.parse(myString);
        JSONArray runes = (JSONArray) jsonO.get("runes");
        JSONArray units = (JSONArray) jsonO.get("unit_list");
        JSONArray artifacts = (JSONArray) jsonO.get("artifacts");



        long i =0;
        List<Monster> saveMonster = new ArrayList<>();
        for (JSONObject element : (Iterable<JSONObject>) units) {
            JSONArray occupiedRunes = (JSONArray) element.get("runes");
            JSONArray occupiedArtifacts = (JSONArray) element.get("artifacts");

            Monster tmp_mstr = new Monster();
            MonsterId tmp_id = new MonsterId(user, (Long) element.get("unit_id"));
            tmp_mstr.setIdMonster(tmp_id);
            tmp_mstr.setGameMonster(gameMonsterRepository.getReferenceById((Long) element.get("unit_master_id")));

            saveMonster.add(tmp_mstr);
            runes.addAll(occupiedRunes);
            artifacts.addAll(occupiedArtifacts);
        }
        monsterRepository.saveAll(saveMonster);


        Iterator<JSONObject> itRunes = runes.iterator();
        List<Rune> saveRune=new ArrayList<>();
        while(itRunes.hasNext()) {

            JSONObject element = itRunes.next();
            Rune rune = new Rune();
            RuneId runeId = new RuneId(user,(Long) element.get("rune_id"));
            rune.setIdRune(runeId);
            rune.setOccupied_type((long) ((Long) element.get("occupied_type")).intValue());
            MonsterId tmp_id = new MonsterId(user, (Long) element.get("occupied_id"));
            if((Long) element.get("occupied_id")!=0)
                rune.setOccupied_id(monsterRepository.getReferenceById(tmp_id));


            rune.setSlot_no(((Long) element.get("slot_no")).intValue());
            rune.setClasse(((Long) element.get("class")).intValue());
            rune.setRang(((Long) element.get("rank")).intValue());
            rune.setSet_id(runeSetRepository.getReferenceById (Math.toIntExact((Long) element.get("set_id"))));
            rune.setUpgrade_curr(((Long) element.get("upgrade_curr")).intValue());

            JSONArray pri_eff = (JSONArray) element.get("pri_eff");
            JSONArray prefix_eff = (JSONArray) element.get("prefix_eff");
            JSONArray sec_eff = (JSONArray) element.get("sec_eff");

            rune.setStatPri(statRuneRepository.getReferenceById((Long) pri_eff.get(0)));
            rune.setPri((Long) pri_eff.get(1));


            long maxmain = (long)(StatRune.class.getMethod("getMaxMain" + rune.getClasse()%10)).invoke(rune.getStatPri());
            long maxmain6 = rune.getStatPri().getMaxMain6();

            float efficiency = (float) ((((float) maxmain)/maxmain6) /2.8);

            if ((Long) prefix_eff.get(0) != 0) {
                rune.setStatInnate(statRuneRepository.getReferenceById((Long) prefix_eff.get(0)));
                rune.setInnate((Long) prefix_eff.get(1));
                StatRune temp_statinnate=rune.getStatInnate();
                float pre_eff_substat = (temp_statinnate.getIdStat()==1 || temp_statinnate.getIdStat()==3 ||temp_statinnate.getIdStat()==5 ) ? (float) ((float) (((rune.getStatInnate().getMaxSub6()*2.8))) * 2) : (float) ((rune.getStatInnate().getMaxSub6() * 2.8));
                efficiency += (rune.getInnate() / pre_eff_substat);
            }
            float effMaxHero = efficiency;
            float effMaxLegend = efficiency;

            i=0;
            for (Object e : sec_eff) {
                if (((JSONArray) e).size() > 0) {
                    StatRune temp_SR = statRuneRepository.getReferenceById((Long) ((JSONArray) e).get(0));

                    long subValue = (Long) ((JSONArray) e).get(1) ;
                    long subGemme = (Long) ((JSONArray) e).get(2);
                    long subMeule = (Long) ((JSONArray) e).get(3) ;
                    Method method1 = Rune.class.getMethod("setSubStat" + (i + 1), StatRune.class);
                    Method method2 = Rune.class.getMethod("setSubStat" + (i + 1) + "Value", Long.class);
                    Method method3 = Rune.class.getMethod("setSubStat" + (i + 1) + "Gemme", Long.class);
                    Method method4 = Rune.class.getMethod("setSubStat" + (i + 1) + "Meule", Long.class);
                    method1.invoke(rune, temp_SR);
                    method2.invoke(rune, subValue);
                    method3.invoke(rune, subGemme);
                    method4.invoke(rune, subMeule);
                    float sommesub = (temp_SR.getIdStat()==1 || temp_SR.getIdStat()==3 ||temp_SR.getIdStat()==5 ) ? (float) ((float) ((subValue) + (subMeule)) * 0.5) : (subValue) + (subMeule);
                    float maxsub =  temp_SR.getMaxSub6();
                    efficiency += ( sommesub / (maxsub * 2.8));
                    if(((Long) ((JSONArray) e).get(0)).intValue()<=8){
                        Grindstone grindstone = grindstoneRepository.getReferenceById(((Long) ((JSONArray) e).get(0)).intValue());

                        float sommesubMaxHero =  (temp_SR.getIdStat()==1 || temp_SR.getIdStat()==3 ||temp_SR.getIdStat()==5 ) ? (float) ((float) ((subValue) + (grindstone.getMaxHero())) * 0.5) : (subValue) + (grindstone.getMaxHero());
                        float sommesubMaxLegend =  (temp_SR.getIdStat()==1 || temp_SR.getIdStat()==3 ||temp_SR.getIdStat()==5 ) ? (float) ((float) ((subValue) + (grindstone.getMaxLegend())) * 0.5) : (subValue) + (grindstone.getMaxLegend());


                        effMaxLegend += ( sommesubMaxLegend / (maxsub * 2.8));
                        effMaxHero += ( sommesubMaxHero / (maxsub * 2.8));
                    }else{
                        effMaxLegend+=( sommesub / (maxsub * 2.8));
                        effMaxHero+=( sommesub / (maxsub * 2.8));
                    }
                }
                i++;
            }

            rune.setEfficiency(efficiency*100);
            rune.setEffMaxLegend(effMaxLegend*100);
            rune.setEffMaxHero(effMaxHero*100);
            rune.setjSON(file.getOriginalFilename());
            saveRune.add(rune);
        }

        runeRepository.saveAll(saveRune);

        List<Artifact> saveArti = new ArrayList<>();
        for (JSONObject element : (Iterable<JSONObject>) artifacts) {
            Artifact artifact = new Artifact();
            ArtifactId atId = new ArtifactId(user,(Long) element.get("rid") );
            artifact.setIdArtifact(atId);
            artifact.setOccupied_id((Long) element.get("occupied_id"));
            artifact.setSlot(((Long) element.get("slot")).intValue());

            if(Integer.parseInt(element.get("type").toString())==1){
                artifact.setType("Attribute");
                switch (Integer.parseInt(element.get("attribute").toString())){
                    case 1:
                        artifact.setRestriction("Water");
                        break;
                    case 2:
                        artifact.setRestriction("Fire");
                        break;
                    case 3:
                        artifact.setRestriction("Wind");
                        break;
                    case 4:
                        artifact.setRestriction("Light");
                        break;
                    case 5:
                        artifact.setRestriction("Dark");
                        break;
                }
            } else{
                artifact.setType("Archetype");
                switch (Integer.parseInt(element.get("unit_style").toString())){
                    case 1:
                        artifact.setRestriction("Attack");
                        break;
                    case 2:
                        artifact.setRestriction("Defense");
                        break;
                    case 3:
                        artifact.setRestriction("HP");
                        break;
                    case 4:
                        artifact.setRestriction("Support");
                        break;
                }
            }

            artifact.setNatural_rank(((Long) element.get("natural_rank")).intValue());
            artifact.setRang(((Long) element.get("rank")).intValue());
            artifact.setLevel(((Long) element.get("level")).intValue());

            JSONArray pri_eff = (JSONArray) element.get("pri_effect");
            switch (Integer.parseInt(pri_eff.get(0).toString())){
                case 100:
                    artifact.setStatPri("HP flat");
                    break;
                case 101:
                    artifact.setStatPri("ATK flat");
                    break;
                case 102:
                    artifact.setStatPri("DEF flat");
                    break;
            }
            artifact.setPri(Long.parseLong(pri_eff.get(1).toString()));
            JSONArray sec_eff = (JSONArray) element.get("sec_effects");

            float efficiency = 0;
            i=0;
            for (Object e : sec_eff) {
                if (((JSONArray) e).size() > 0) {
                    StatArtifact temp_SR = statArtifactRepository.getReferenceById((Long) ((JSONArray) e).get(0));

                    float subValue = Float.parseFloat(((JSONArray) e).get(1).toString()) ;

                    if (Integer.parseInt(((JSONArray) e).get(3).toString())!=0 || Integer.parseInt(((JSONArray) e).get(4).toString())!=0)
                        artifact.setSubStatChange(i+1);

                    Method method1 = Artifact.class.getMethod("setSubStat" + (i + 1), StatArtifact.class);
                    Method method2 = Artifact.class.getMethod("setSubStat" + (i + 1) + "Value", Float.class);
                    Method method3 = Artifact.class.getMethod("setSubStat" + (i + 1) + "Proc", Long.class);

                    method1.invoke(artifact, temp_SR);
                    method2.invoke(artifact, subValue);
                    method3.invoke(artifact,((JSONArray) e).get(2));

                    efficiency += ( subValue / (temp_SR.getMaxValue() * 1.6));
                }
                i++;
            }
            artifact.setjSON(file.getOriginalFilename());
            artifact.setEfficiency(efficiency*100);
            saveArti.add(artifact);

        }
        artifactRepository.saveAll(saveArti);

        saveMonster = new ArrayList<>();
        for (JSONObject element : (Iterable<JSONObject>) units) {
            Monster monster = new Monster();
            MonsterId id = new MonsterId(user, (Long) element.get("unit_id"));
            monster.setIdMonster(id);
            monster.setGameMonster(gameMonsterRepository.getReferenceById((Long) element.get("unit_master_id")));
            monster.setSkills( (element.get("skills").toString()));

            JSONArray occupiedRunes = (JSONArray) element.get("runes");

            for (JSONObject e : (Iterable<JSONObject>) occupiedRunes){
                RuneId ri = new RuneId(user,(Long) e.get("rune_id"));
                Rune r=runeRepository.getReferenceById(ri);
                Method method = Monster.class.getMethod("setRune" + (e.get("slot_no")), Rune.class);
                method.invoke(monster, r);
            }

            JSONArray occupiedArtifacts = (JSONArray) element.get("artifacts");
            for (JSONObject e : (Iterable<JSONObject>) occupiedArtifacts){
                Method method = Monster.class.getMethod("setArtifact" + (e.get("slot")), Artifact.class);
                method.invoke(monster, artifactRepository.getReferenceById(new ArtifactId(user,(Long) e.get("rid"))));
            }
            monster.setjSON(file.getOriginalFilename());
            monster.setUnit_level((Long) element.get("unit_level"));
            saveMonster.add(monster);
        }

        monsterRepository.saveAll(saveMonster);
        redirectAttributes.addFlashAttribute("message",
                "You successfully uploaded '" + file.getOriginalFilename() + "'");

        return "redirect:/monsters";
    }

}
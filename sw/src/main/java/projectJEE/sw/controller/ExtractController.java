package projectJEE.sw.controller;

import org.apache.commons.io.IOUtils;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import projectJEE.sw.dbEntity.*;
import projectJEE.sw.dbRepository.*;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

@Controller
public class ExtractController {
    @Autowired
    MonsterRepository monsterRepository;
    @Autowired
    StatRuneRepository statRuneRepository;
    @Autowired
    GameMonsterRepository gameMonsterRepository;
    @Autowired
    RuneRepository runeRepository;
    @Autowired
    ArtifactRepository artifactRepository;

    @Autowired
    GrindstoneRepository grindstoneRepository;


    @GetMapping("/uploadJSON")
    public String index() {
        return "/html/uploadJSON";
    }

    @PostMapping("/upload")
    public String singleFileUpload(@RequestParam("file") MultipartFile file,
                                   RedirectAttributes redirectAttributes) throws IOException, ParseException, NoSuchMethodException, InvocationTargetException, IllegalAccessException {

        if (file.isEmpty()) {
            redirectAttributes.addFlashAttribute("message", "Please select a file to upload");
            return "redirect:/monsters";
        }

        // Get the file and save it somewhere
        ByteArrayInputStream stream = new   ByteArrayInputStream(file.getBytes());
        String myString = IOUtils.toString(stream, "UTF-8");


        JSONParser jsonP = new JSONParser();
        JSONObject jsonDataO = (JSONObject)jsonP.parse(new FileReader(new ClassPathResource("data/monster.json").getFile()));
        File fileData = new ClassPathResource("data/monster.json").getFile();
        JSONObject jsonO = (JSONObject)jsonP.parse(myString);
        JSONArray runes = (JSONArray) jsonO.get("runes");
        JSONArray units = (JSONArray) jsonO.get("unit_list");
        JSONArray artifacts = (JSONArray) jsonO.get("artifacts");
        JSONObject maxMain = (JSONObject)((JSONObject)((JSONObject) jsonDataO.get("rune")).get("mainstat"));

        HashMap<Integer, HashMap> mapMaxMain  = new HashMap<>();
        for (int i=1; i<=12 ; i++ ){
            mapMaxMain.put(i, (HashMap) maxMain.get(Integer.toString(i)));
        }

        JSONObject maxSub = (JSONObject)((JSONObject)((JSONObject) jsonDataO.get("rune")).get("substat"));

        HashMap<Integer,HashMap> mapMaxSub  = new HashMap<>();
        for (int i=1; i<=12 ; i++ ){
            mapMaxSub.put(i, (HashMap) maxSub.get(Integer.toString(i)));
        }


        long i =0;
        List<Monster> saveMonster = new ArrayList<>();
        for (JSONObject element : (Iterable<JSONObject>) units) {
            JSONArray occupiedRunes = (JSONArray) element.get("runes");
            JSONArray occupiedArtifacts = (JSONArray) element.get("artifacts");

            Monster tmp_mstr = new Monster();
            tmp_mstr.setIdMonster((Long) element.get("unit_id"));
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
            rune.setIdRune((Long) element.get("rune_id"));
            rune.setOccupied_type((long) ((Long) element.get("occupied_type")).intValue());

            if((Long) element.get("occupied_id")!=0)
                rune.setOccupied_id(monsterRepository.getReferenceById((Long) element.get("occupied_id")));


            rune.setSlot_no(((Long) element.get("slot_no")).intValue());
            rune.setClasse(((Long) element.get("class")).intValue());
            rune.setRang(((Long) element.get("rank")).intValue());
            rune.setSet_id(((Long) element.get("set_id")).intValue());
            rune.setUpgrade_curr(((Long) element.get("upgrade_curr")).intValue());

            JSONArray pri_eff = (JSONArray) element.get("pri_eff");
            JSONArray prefix_eff = (JSONArray) element.get("prefix_eff");
            JSONArray sec_eff = (JSONArray) element.get("sec_eff");

            rune.setStatPri(statRuneRepository.getReferenceById((Long) pri_eff.get(0)));
            rune.setPri((Long) pri_eff.get(1));


            long maxmain = (long)(StatRune.class.getMethod("getMaxMain" + rune.getClasse()%10)).invoke(rune.getStatPri());
            long maxmain6 = rune.getStatPri().getMaxMain6();

            float efficiency = (((float)maxmain) /maxmain6) /2.8f;

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
            saveRune.add(rune);
        }

        runeRepository.saveAll(saveRune);

        List<Artifact> saveArti = new ArrayList<>();
        for (JSONObject element : (Iterable<JSONObject>) artifacts) {
            Artifact artifact = new Artifact();
            artifact.setIdArtifact((Long) element.get("rid"));
            artifact.setOccupied_id((Long) element.get("occupied_id"));
            artifact.setSlot(((Long) element.get("slot")).intValue());
            artifact.setType(((Long) element.get("type")).intValue());
            artifact.setAttribute(((Long) element.get("attribute")).intValue());
            artifact.setUnit_style(((Long) element.get("unit_style")).intValue());
            artifact.setNatural_rank(((Long) element.get("natural_rank")).intValue());
            artifact.setRang(((Long) element.get("rank")).intValue());
            artifact.setLevel(((Long) element.get("level")).intValue());
            artifact.setPri_effect(element.get("pri_effect").toString());
            artifact.setSec_effect(element.get("sec_effects").toString());
            saveArti.add(artifact);

        }
        artifactRepository.saveAll(saveArti);

        saveMonster = new ArrayList<>();
        for (JSONObject element : (Iterable<JSONObject>) units) {
            Monster monster = new Monster();
            monster.setIdMonster((Long) element.get("unit_id"));
            monster.setGameMonster(gameMonsterRepository.getReferenceById((Long) element.get("unit_master_id")));
            monster.setSkills( (element.get("skills").toString()));

            JSONArray occupiedRunes = (JSONArray) element.get("runes");

            for (JSONObject e : (Iterable<JSONObject>) occupiedRunes){
                Method method = Monster.class.getMethod("setRune" + (e.get("slot_no")), Rune.class);
                method.invoke(monster, runeRepository.getReferenceById((Long) e.get("rune_id")));
            }

            JSONArray occupiedArtifacts = (JSONArray) element.get("artifacts");
            for (JSONObject e : (Iterable<JSONObject>) occupiedArtifacts){
                Method method = Monster.class.getMethod("setArtifact" + (e.get("slot")), Artifact.class);
                method.invoke(monster, artifactRepository.getReferenceById((Long) e.get("rid")));
            }

            monster.setUnit_level((Long) element.get("unit_level"));

            saveMonster.add(monster);
        }

        monsterRepository.saveAll(saveMonster);
        redirectAttributes.addFlashAttribute("message",
                "You successfully uploaded '" + file.getOriginalFilename() + "'");

        return "redirect:/monsters";
    }

}
package projectJEE.sw;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.core.io.ClassPathResource;
import projectJEE.sw.dbEntity.Artifact;
import projectJEE.sw.dbEntity.Rune;
import projectJEE.sw.dbRepository.ArtifactRepository;
import projectJEE.sw.dbRepository.MonsterRepository;
import projectJEE.sw.dbRepository.RuneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import javax.swing.text.html.HTMLDocument;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

@SpringBootApplication
public class SwApplication {

	public static void main(String[] args) {
		SpringApplication.run(SwApplication.class, args);
	}

	@Autowired
	MonsterRepository monsterRepository;
	@Autowired
	RuneRepository runeRepository;
	@Autowired
	ArtifactRepository artifactRepository;

	@Bean
	public CommandLineRunner CommandLineRunnerBean() {
		return (args) -> {
			System.out.println("Démarrage... ");

			System.out.println(runeRepository.findAll());
			System.out.println(monsterRepository.findAll());
			System.out.println(artifactRepository.findAll());
			File file = new ClassPathResource("data/NeozFuzzion-840111.json").getFile();

			JSONParser jsonP = new JSONParser();

			File fileData = new ClassPathResource("data/monster.json").getFile();

			JSONObject jsonDataO = (JSONObject)jsonP.parse(new FileReader(fileData));
			JSONParser jsonS = new JSONParser();
			JSONObject jsonO = (JSONObject)jsonS.parse(new FileReader(file));
			JSONArray runes = (JSONArray) jsonO.get("runes");
			JSONArray units = (JSONArray) jsonO.get("unit_list");
			JSONObject maxSub = (JSONObject)((JSONObject)((JSONObject) jsonDataO.get("rune")).get("substat"));

			HashMap<Integer,HashMap> mapMaxSub  = new HashMap<>();
			for (int i=1; i<=12 ; i++ ){
				mapMaxSub.put(i, (HashMap) maxSub.get(Integer.toString(i)));
			}
			System.out.println((mapMaxSub.get(1).get("6")));

			Iterator<JSONObject> itUnits = units.iterator();
			while(itUnits.hasNext()){
				JSONObject element = itUnits.next();
				JSONArray occupiedRunes = (JSONArray) element.get("runes");
				runes.addAll(occupiedRunes);

			}
			Iterator<JSONObject> it = runes.iterator();
			int i =0;
			while(it.hasNext()) {
				JSONObject element = it.next();
				Rune rune = new Rune();

				rune.setIdRune((Long) element.get("rune_id"));
				rune.setOccupied_type(((Long) element.get("occupied_type")).intValue());
				rune.setOccupied_id((Long) element.get("occupied_id"));
				rune.setSlot_no(((Long) element.get("slot_no")).intValue());
				rune.setClasse(((Long) element.get("class")).intValue());
				rune.setRang(((Long) element.get("rank")).intValue());
				rune.setSet_id(((Long) element.get("set_id")).intValue());
				rune.setUpgrade_curr(((Long) element.get("upgrade_curr")).intValue());
				rune.setPri_eff(element.get("pri_eff").toString());
				JSONArray prefix_eff = (JSONArray) element.get("prefix_eff");
				rune.setPrefix_eff(prefix_eff.toString());
				JSONArray sec_eff = (JSONArray) element.get("sec_eff");
				rune.setSec_eff(sec_eff.toString());
				float efficiency = (float) ((float) 1/2.8);
				if ((Long) ((JSONArray) prefix_eff).get(0) != 0) {
					float pre_eff_substat = (float) (((Long) (mapMaxSub.get(((Long) ((JSONArray) prefix_eff).get(0)).intValue()).get(Integer.toString(((Long) element.get("class")).intValue() % 10))))*2.8);
					efficiency += ((((Long) ((JSONArray) prefix_eff).get(1)).intValue()) / pre_eff_substat);
				}
				System.out.println(efficiency);
				for (Object e : (JSONArray) element.get("sec_eff")) {
					System.out.println(e);
					if (((JSONArray) e).size() > 0) {
						int sommesub = ((Long) (((Long) ((JSONArray) e).get(1)) + ((Long) ((JSONArray) e).get(3)))).intValue();
						int maxsub = ((Long) (mapMaxSub.get(((Long) ((JSONArray) e).get(0)).intValue()).get(Integer.toString(((Long) element.get("class")).intValue() % 10)))).intValue();
						efficiency += ((float) sommesub / (maxsub * 2.8));
						System.out.println(sommesub + "/" + maxsub );
					}

				}
				System.out.println(efficiency);
				rune.setEfficiency(efficiency);
				runeRepository.save(rune);
			}



		};
	}
}

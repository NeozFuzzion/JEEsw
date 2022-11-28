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
import java.util.Iterator;

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
			JSONObject jsonO = (JSONObject)jsonP.parse(new FileReader(file));
			JSONArray runes = (JSONArray) jsonO.get("runes");
			JSONArray units=(JSONArray) jsonO.get("unit_list");
			File fileData = new ClassPathResource("data/monster.json").getFile();

			JSONParser jsonDataP = new JSONParser();
			JSONObject jsonDataO = (JSONObject)jsonP.parse(new FileReader(fileData));


			System.out.println(((JSONObject) jsonDataO.get("rune")).get("substat"));

			Iterator<JSONObject> itUnits = units.iterator();
			while(itUnits.hasNext()){
				JSONObject element = itUnits.next();
				JSONArray occupiedRunes = (JSONArray) element.get("runes");
				runes.addAll(occupiedRunes);

			}
			Iterator<JSONObject> it = runes.iterator();
			while(it.hasNext()){
				JSONObject element = it.next();
				Rune rune = new Rune();

				rune.setIdRune((Long)element.get("rune_id"));
				rune.setOccupied_type(((Long)element.get("occupied_type")).intValue());
				rune.setOccupied_id((Long)element.get("occupied_id"));
				rune.setSlot_no(((Long)element.get("slot_no")).intValue());
				rune.setClasse(((Long)element.get("class")).intValue());
				rune.setRang(((Long)element.get("rank")).intValue());
				rune.setSet_id(((Long)element.get("set_id")).intValue());
				rune.setUpgrade_curr(((Long)element.get("upgrade_curr")).intValue());
				rune.setPri_eff(element.get("pri_eff").toString());
				rune.setPrefix_eff(element.get("prefix_eff").toString());
				rune.setSec_eff(element.get("sec_eff").toString());
				runeRepository.save(rune);
			}



		};
	}
}

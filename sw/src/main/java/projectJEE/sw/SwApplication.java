package projectJEE.sw;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.core.io.ClassPathResource;
import projectJEE.sw.dbEntity.Artifact;
import projectJEE.sw.dbEntity.GameMonster;
import projectJEE.sw.dbEntity.Monster;
import projectJEE.sw.dbEntity.Rune;
import projectJEE.sw.dbRepository.ArtifactRepository;
import projectJEE.sw.dbRepository.GameMonsterRepository;
import projectJEE.sw.dbRepository.MonsterRepository;
import projectJEE.sw.dbRepository.RuneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.io.*;
import java.net.URL;
import java.util.*;

@SpringBootApplication
public class SwApplication {

	public static void main(String[] args) {
		SpringApplication.run(SwApplication.class, args);
	}

	@Autowired
	MonsterRepository monsterRepository;

	@Autowired
	GameMonsterRepository gameMonsterRepository;
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
			JSONArray artifacts = (JSONArray) jsonO.get("artifacts");
			JSONObject maxMain = (JSONObject)((JSONObject)((JSONObject) jsonDataO.get("rune")).get("mainstat"));

			HashMap<Integer,HashMap> mapMaxMain  = new HashMap<>();
			for (int i=1; i<=12 ; i++ ){
				mapMaxMain.put(i, (HashMap) maxMain.get(Integer.toString(i)));
			}

			JSONObject maxSub = (JSONObject)((JSONObject)((JSONObject) jsonDataO.get("rune")).get("substat"));

			HashMap<Integer,HashMap> mapMaxSub  = new HashMap<>();
			for (int i=1; i<=12 ; i++ ){
				mapMaxSub.put(i, (HashMap) maxSub.get(Integer.toString(i)));
			}
			System.out.println((mapMaxSub.get(1).get("6")));


			long i =0;
			List<Monster> saveMonster = new ArrayList<>();
			for (JSONObject element : (Iterable<JSONObject>) units) {
				Monster monster = new Monster();
				monster.setIdMonster((Long) element.get("unit_id"));
				monster.setId_game((Long) element.get("unit_master_id"));
				monster.setSkills( (element.get("skills").toString()));

				JSONArray occupiedRunes = (JSONArray) element.get("runes");
				runes.addAll(occupiedRunes);
				if (occupiedRunes.size()>=1){
					monster.setRune1((Long) ((JSONObject) occupiedRunes.get(0)).get("rune_id"));
					if (occupiedRunes.size()>=2){
						monster.setRune2((Long) ((JSONObject) occupiedRunes.get(1)).get("rune_id"));
						if (occupiedRunes.size()>=3){
							monster.setRune3((Long) ((JSONObject) occupiedRunes.get(2)).get("rune_id"));
							if (occupiedRunes.size()>=4){
								monster.setRune4((Long) ((JSONObject) occupiedRunes.get(3)).get("rune_id"));
								if (occupiedRunes.size()>=5){
									monster.setRune5((Long) ((JSONObject) occupiedRunes.get(4)).get("rune_id"));
									if (occupiedRunes.size()>=6){
										monster.setRune6((Long) ((JSONObject) occupiedRunes.get(5)).get("rune_id"));
									}
								}
							}
						}
					}
				}

				monster.setUnit_level((Long) element.get("unit_level"));

				saveMonster.add(monster);
			}

			monsterRepository.saveAll(saveMonster);

			Iterator<JSONObject> itRunes = runes.iterator();
			List<Rune> saveRune=new ArrayList<>();
			while(itRunes.hasNext()) {
				JSONObject element = itRunes.next();
				Rune rune = new Rune();
				i++;
				rune.setIdRune((Long) i);
				rune.setOccupied_type(((Long) element.get("occupied_type")).intValue());
				rune.setOccupied_id((Long) element.get("occupied_id"));
				rune.setSlot_no(((Long) element.get("slot_no")).intValue());
				rune.setClasse(((Long) element.get("class")).intValue());
				rune.setRang(((Long) element.get("rank")).intValue());
				rune.setSet_id(((Long) element.get("set_id")).intValue());
				rune.setUpgrade_curr(((Long) element.get("upgrade_curr")).intValue());

				JSONArray pri_eff = (JSONArray) element.get("pri_eff");
				JSONArray prefix_eff = (JSONArray) element.get("prefix_eff");
				JSONArray sec_eff = (JSONArray) element.get("sec_eff");

				rune.setPri_eff(pri_eff.toString());
				rune.setPrefix_eff(prefix_eff.toString());
				rune.setSec_eff(sec_eff.toString());

				int maxmain = ((Long) (mapMaxMain.get(((Long) ((JSONArray) pri_eff).get(0)).intValue()).get(Integer.toString(((Long) element.get("class")).intValue() % 10)))).intValue();
				int maxmain6 = ((Long) (mapMaxMain.get(((Long) ((JSONArray) pri_eff).get(0)).intValue()).get(Integer.toString(6)))).intValue();

				float efficiency = (float) ((float) (((float) maxmain)/maxmain6)/2.8);
				if ((Long) ((JSONArray) prefix_eff).get(0) != 0) {
					float pre_eff_substat = (float) (((Long) (mapMaxSub.get(((Long) ((JSONArray) prefix_eff).get(0)).intValue()).get(Integer.toString(6)))*2.8));
					efficiency += ((((Long) ((JSONArray) prefix_eff).get(1)).intValue()) / pre_eff_substat);
				}

				for (Object e : (JSONArray) element.get("sec_eff")) {
					if (((JSONArray) e).size() > 0) {
						float sommesub = ((((Long) ((JSONArray) e).get(0)).intValue())==1 || (((Long) ((JSONArray) e).get(0)).intValue())==3 ||( ((Long) ((JSONArray) e).get(0)).intValue())==5 ) ? (float) ((((Long) (((Long) ((JSONArray) e).get(1)) + ((Long) ((JSONArray) e).get(3)))).intValue()) * 0.5) : ((Long) (((Long) ((JSONArray) e).get(1)) + ((Long) ((JSONArray) e).get(3)))).intValue();

						int maxsub = ((Long) (mapMaxSub.get(((Long) ((JSONArray) e).get(0)).intValue()).get(Integer.toString(6)))).intValue();
						efficiency += ( sommesub / (maxsub * 2.8));

					}

				}

				rune.setEfficiency(efficiency*100);
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



			List<GameMonster> saveDataMonster = new ArrayList<>();
			JSONArray data_monster = (JSONArray) ((JSONObject)jsonP.parse(new FileReader(new ClassPathResource("data/monsters.json").getFile()))).get("monster");
			System.out.println(data_monster.size());
			int vmax = data_monster.size();
			for (int v=0;v<vmax;v++) {
				JSONObject mstr = (JSONObject) data_monster.get(v);
				GameMonster gameMonster = new GameMonster();
				gameMonster.setIdMonster((Long) mstr.get("com2us_id"));
				gameMonster.setName((String) mstr.get("name"));

				gameMonster.setHp((Long) mstr.get("max_lvl_hp"));
				gameMonster.setDef((Long) mstr.get("max_lvl_defense"));
				gameMonster.setAtk((Long) mstr.get("max_lvl_attack"));
				gameMonster.setSpd((Long) mstr.get("speed"));
				gameMonster.setCrate((Long) mstr.get("crit_rate"));
				gameMonster.setCdmg((Long) mstr.get("crit_damage"));
				gameMonster.setRes((Long) mstr.get("resistance"));
				gameMonster.setAcc((Long) mstr.get("resistance"));

				gameMonster.setImage((String) mstr.get("image_filename"));

				if(mstr.get("leader_skill")!=null)
					gameMonster.setLeader_skill((Long) ((JSONObject) mstr.get("leader_skill")).get("id"));

				gameMonster.setElement((String) mstr.get("element"));

				JSONArray tabSkills = (JSONArray) mstr.get("skills");
				if(tabSkills.size()>=1){
					gameMonster.setS1((Long) tabSkills.get(0));
					if (tabSkills.size()>=2){
						gameMonster.setS2((Long) tabSkills.get(1));
						if (tabSkills.size()>=3){
							gameMonster.setS3((Long) tabSkills.get(2));
							if (tabSkills.size()>=4){
								gameMonster.setS4((Long) tabSkills.get(3));
							}
						}
					}
				}

				gameMonster.setAwaken_lvl((Long) mstr.get("awaken_level"));
				if(mstr.get("awakens_from")!=null)
					gameMonster.setAwakens_from((Long) mstr.get("awakens_from"));
				gameMonster.setCan_awaken((Boolean) mstr.get("can_awaken"));
				gameMonster.setAwaken_bonus((String) mstr.get("awaken_bonus"));

				gameMonster.setFamily_id((Long) mstr.get("family_id"));

				gameMonster.setNatural_stars((Long) mstr.get("natural_stars"));

				if ((Long) mstr.get("skill_ups_to_max") != null)
					gameMonster.setSkill_ups_to_max((Long) mstr.get("skill_ups_to_max"));

				gameMonster.setArchetype((String) mstr.get("archetype"));

				gameMonster.setFusion_food((Boolean) mstr.get("fusion_food"));

				saveDataMonster.add(gameMonster);
			}
			gameMonsterRepository.saveAll(saveDataMonster);
			System.out.println("Initiated... ");

			/*
	System.out.println("AAAAAAAAAAAAAAAA");
			System.out.println(new ClassPathResource("data/monsters.json").getURL());
			BufferedWriter out = new BufferedWriter(new FileWriter("monsters.json"));
			String urlMonster;
			String inputLine = "";
			for(i=1;i<=21;i++){
				inputLine = "";
				urlMonster="https://swarfarm.com/api/v2/monsters/?format=json&page=" +  i ;
				URL oracle = new URL(urlMonster);
				BufferedReader in = new BufferedReader(
						new InputStreamReader(oracle.openStream()));

				while ((urlMonster = in.readLine()) != null) {
					inputLine += urlMonster;
				}
				JSONObject a = (JSONObject) jsonP.parse(inputLine);
				out.write(String.valueOf((JSONArray) a.get("results")));
				out.write('\n');
				in.close();

			}
			out.close();
			System.out.println("AAAAAAAAAAAAAAAA");

			image_filename;
			skills;
*/
		};
	}
}
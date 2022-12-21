package projectJEE.sw;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.ClassPathResource;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import projectJEE.sw.dbEntity.*;
import projectJEE.sw.dbRepository.*;

import java.io.FileReader;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class SwApplication {

	public static void main(String[] args) {
		SpringApplication.run(SwApplication.class, args);
	}

	@Autowired
	SkillRepository skillRepository;
	@Autowired
	GameMonsterRepository gameMonsterRepository;

	@Autowired
	StatRuneRepository statRuneRepository;

	@Autowired
	LeaderSkillRepository leaderSkillRepository;

	@Autowired
	GrindstoneRepository grindstoneRepository;

	@Autowired
	GemstoneRepository gemstoneRepository;

	@Autowired
	RuneSetRepository runeSetRepository;

	@Bean
	public MultipartResolver multipartResolver() {
		CommonsMultipartResolver multipartResolver
				= new CommonsMultipartResolver();
		multipartResolver.setMaxUploadSize(5242880);
		return multipartResolver;
	}

	@Bean
	public CommandLineRunner CommandLineRunnerBean() {
		return (args) -> {
			System.out.println("Démarrage... ");
			JSONParser jsonP = new JSONParser();

			if (statRuneRepository.findAll().size()!=11){

				JSONArray leaderS = (JSONArray) ((JSONObject)jsonP.parse(new FileReader(new ClassPathResource("data/lskill.json").getFile()))).get("leader_skills");
				int vmax = leaderS.size();
				List<LeaderSkill> saveLS = new ArrayList<>();
				for (int v=0;v<vmax;v++){
					JSONObject ls= (JSONObject) leaderS.get(v);
					LeaderSkill leaderSkill = new LeaderSkill();
					leaderSkill.setAmount((Long) ls.get("amount"));
					leaderSkill.setArea((String) ls.get("area"));
					leaderSkill.setAttribute((String) ls.get("attribute"));
					leaderSkill.setIdLs((Long) ls.get("id"));

					String att = leaderSkill.getAttribute();
					att=att.replace(" ","_");

					String image = "/leader/leader_skill_"+att;
					if(ls.get("element")!=null){
						leaderSkill.setElement((String) ls.get("element"));

						String elemt = leaderSkill.getElement();
						elemt = elemt.replace(" ","_");

						image = image +"_"+elemt ;

					}
					leaderSkill.setImage(image+".png");
					saveLS.add(leaderSkill);
				}
				leaderSkillRepository.saveAll(saveLS);

				JSONObject fileData =  ((JSONObject)jsonP.parse(new FileReader(new ClassPathResource("data/monster.json").getFile())));

				JSONObject statrune =(JSONObject)fileData.get("rune");
				List<StatRune> savestrune = new ArrayList<>();
				for (int v=1;v<=6;v++){
					StatRune statRune = new StatRune();
					statRune.setIdStat((long) v);
					JSONObject maxMain = (JSONObject) ((JSONObject) statrune.get("mainstat")).get(Integer.toString(v));
					JSONObject maxSub = (JSONObject) ((JSONObject) statrune.get("substat")).get(Integer.toString(v));
					for (int k=0 ; k<6; k++) {
						Method method1 = StatRune.class.getMethod("setMaxMain" + (k + 1), Long.class);
						method1.invoke(statRune, maxMain.get(Integer.toString(k+1)));
						Method method2 = StatRune.class.getMethod("setMaxSub" + (k + 1), Long.class);
						method2.invoke(statRune, maxSub.get(Integer.toString(k+1)));
					}
					statRune.setName((String) ((JSONObject) statrune.get("effectTypes")).get(Integer.toString(v)));
					savestrune.add(statRune);
				}
				for (int v=8;v<=12;v++){
					StatRune statRune = new StatRune();
					statRune.setIdStat((long) v);
					JSONObject maxMain = (JSONObject) ((JSONObject) statrune.get("mainstat")).get(Integer.toString(v));
					JSONObject maxSub = (JSONObject) ((JSONObject) statrune.get("substat")).get(Integer.toString(v));
					for (int k=0 ; k<6; k++) {
						Method method1 = StatRune.class.getMethod("setMaxMain" + (k + 1), Long.class);
						method1.invoke(statRune, maxMain.get(Integer.toString(k+1)));
						Method method2 = StatRune.class.getMethod("setMaxSub" + (k + 1), Long.class);
						method2.invoke(statRune, maxSub.get(Integer.toString(k+1)));
					}
					statRune.setName((String) ((JSONObject) statrune.get("effectTypes")).get(Integer.toString(v)));
					savestrune.add(statRune);
				}
				statRuneRepository.saveAll(savestrune);

				JSONObject setsInfos = (JSONObject) statrune.get("sets");
				List<RuneSet> saveRuneSets = new ArrayList<>();
				for(int v=1;v<=23;v++){
					String name=(String) setsInfos.get(String.valueOf(v));
					if(name!=null){
						RuneSet runeSet = new RuneSet();
						runeSet.setIdSet(v);
						runeSet.setName(name);
						runeSet.setImage(name.toLowerCase()+".png");
						saveRuneSets.add(runeSet);
					}

				}
				runeSetRepository.saveAll(saveRuneSets);

				List<Grindstone> saveGrind = new ArrayList<>();
				List<Gemstone> saveGem = new ArrayList<>();
				JSONObject grindInfos = (JSONObject) fileData.get("grindstone");
				JSONObject gemInfos = (JSONObject) fileData.get("enchanted_gem");
				for(int v=1;v <=12;v++){
					if(v!=7){
						Grindstone grind = new Grindstone();
						grind.setIdGrind(v);
						JSONObject grindRange = (JSONObject) ((JSONObject) grindInfos.get(String.valueOf(v))).get("range");
						JSONObject grindRangeHero = (JSONObject) grindRange.get("4");
						JSONObject grindRangeLegend = (JSONObject) grindRange.get("5");
						grind.setMinHero((long)grindRangeHero.get("min"));
						grind.setMaxHero((long)grindRangeHero.get("max"));
						grind.setMinLegend((long)grindRangeLegend.get("min"));
						grind.setMaxLegend((long)grindRangeLegend.get("max"));
						saveGrind.add(grind);

						Gemstone gem = new Gemstone();
						gem.setIdGem(v);
						JSONObject gemRange = (JSONObject) ((JSONObject) gemInfos.get(String.valueOf(v))).get("range");
						JSONObject gemRangeHero = (JSONObject) gemRange.get("4");
						JSONObject gemRangeLegend = (JSONObject) gemRange.get("5");
						gem.setMinHero((long)gemRangeHero.get("min"));
						gem.setMaxHero((long)gemRangeHero.get("max"));
						gem.setMinLegend((long)gemRangeLegend.get("min"));
						gem.setMaxLegend((long)gemRangeLegend.get("max"));
						saveGem.add(gem);
					}
				}
				grindstoneRepository.saveAll(saveGrind);
				gemstoneRepository.saveAll(saveGem);

				List<Skill> saveSkill = new ArrayList<>();
				JSONArray data_skill = (JSONArray) ((JSONObject)jsonP.parse(new FileReader(new ClassPathResource("data/skills.json").getFile()))).get("skills");
				System.out.println(data_skill.size());
				vmax = data_skill.size();
				for (int v=0;v<vmax;v++) {
					JSONObject skl = (JSONObject) data_skill.get(v);
					Skill skill	= new Skill();
					skill.setAoe((Boolean) skl.get("aoe"));
					skill.setIdSkill((Long) skl.get("id"));
					if(skl.get("cooltime")!=null)
						skill.setCooltime((Long) skl.get("cooltime"));
					skill.setGameSkill((Long) skl.get("com2us_id"));
					skill.setDescription((String) skl.get("description"));
					skill.setEffects(skl.get("effects").toString());
					skill.setHits((Long) skl.get("hits"));
					skill.setMax_level((Long) skl.get("max_level"));
					skill.setMultiplier_formula((String) skl.get("multiplier_formula"));
					skill.setUpgrades(skl.get("upgrades").toString());
					skill.setPassive((Boolean) skl.get("passive"));
					skill.setSlot((Long) skl.get("slot"));
					skill.setImage((String) skl.get("icon_filename"));
					skill.setName((String) skl.get("name"));
					saveSkill.add(skill);
				}
				skillRepository.saveAll(saveSkill);

				List<GameMonster> saveDataMonster = new ArrayList<>();

				JSONArray data_monster = (JSONArray) ((JSONObject)jsonP.parse(new FileReader(new ClassPathResource("data/monsters.json").getFile()))).get("monster");
				System.out.println(data_monster.size());
				vmax = data_monster.size();
				for (int v=0;v<vmax;v++) {
					JSONObject mstr = (JSONObject) data_monster.get(v);
					GameMonster gameMonster = new GameMonster();
					gameMonster.setIdMonster((Long) mstr.get("com2us_id"));
					gameMonster.setName((String) mstr.get("name"));
					gameMonster.setObtainable((Boolean) mstr.get("obtainable"));
					gameMonster.setHp((Long) mstr.get("max_lvl_hp"));
					gameMonster.setDef((Long) mstr.get("max_lvl_defense"));
					gameMonster.setAtk((Long) mstr.get("max_lvl_attack"));
					gameMonster.setSpd((Long) mstr.get("speed"));
					gameMonster.setCrate((Long) mstr.get("crit_rate"));
					gameMonster.setCdmg((Long) mstr.get("crit_damage"));
					gameMonster.setRes((Long) mstr.get("resistance"));
					gameMonster.setAcc((Long) mstr.get("accuracy"));

					gameMonster.setImage((String) mstr.get("image_filename"));

					if(mstr.get("leader_skill")!=null)
						gameMonster.setLeader_skill(leaderSkillRepository.getReferenceById((Long) ((JSONObject) mstr.get("leader_skill")).get("id")));

					gameMonster.setElement((String) mstr.get("element"));

					JSONArray tabSkills = (JSONArray) mstr.get("skills");
					int size = tabSkills.size();
					for (int k=0 ; k<size; k++){
						Method method = GameMonster.class.getMethod("setS" + (k + 1), Skill.class);
						method.invoke(gameMonster, skillRepository.getReferenceById((Long) tabSkills.get(k)));
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
			} else {
				System.out.println("Database already completed");
			}


/*
	System.out.println("AAAAAAAAAAAAAAAA");
			BufferedWriter out = new BufferedWriter(new FileWriter("lskill.json"));
			String urlMonster;
			String inputLine = "";

			for(int i=1;i<=3;i++){
				inputLine = "";
				urlMonster="https://swarfarm.com/api/v2/leader-skills/?format=json&page=" +  i ;
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
System.out.println("AAAAAAAAAAAAAAAA");
			BufferedWriter out = new BufferedWriter(new FileWriter("./src/main/resources/data/skills.json"));
			String urlMonster="https://swarfarm.com/api/v2/skills/?format=json&page=1";
			String inputLine = "";
			URL oracle = new URL(urlMonster);
			BufferedReader in = new BufferedReader(
					new InputStreamReader(oracle.openStream()));
			int i=1;
			while(in!=null){


				while ((urlMonster = in.readLine()) != null) {
					inputLine += urlMonster;
				}

				JSONObject a = (JSONObject) jsonP.parse(inputLine);
				out.write(String.valueOf((JSONArray) a.get("results")));
				out.write('\n');
				in.close();
				i++;

				inputLine = "";
				urlMonster="https://swarfarm.com/api/v2/skills/?format=json&page=" +  i ;

				oracle = new URL(urlMonster);
				try{
					in = new BufferedReader(new InputStreamReader(oracle.openStream()));
				} catch (Exception e){
					break;
				}

			}
			in.close();
			out.close();
			System.out.println("AAAAAAAAAAAAAAAA");
 */
		};
	}
}
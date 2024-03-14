package com.giantLink.Hiring.recrutementservice.utilities;

import java.util.ArrayList; 
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;
import com.giantLink.Hiring.recrutementservice.entities.*;
import com.giantLink.Hiring.recrutementservice.repositories.*;
import com.giantLink.Hiring.recrutementservice.enums.PermissionEnum;
import com.giantLink.Hiring.recrutementservice.enums.Telecommuting;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class DataBaseUtility {

	@Autowired
	EducationRepository educationRepository;
	
	@Autowired
	DomainRepository domainRepository;
	
	@Autowired
	SkillRepository skillRepository;
	
	@Autowired
	LanguageRepository languageRepository;
	
	@Autowired
	CampaignRepository campaignRepository;
	
	@Autowired
	RegionRepository regionRepository; 
	
	@Autowired
	PostRepository postRepository;


	@Autowired
	CandidacyRepository candidacyRepository;

	@Autowired
	CandidateRepository candidateRepository;

	@Autowired
	CvRepository cvRepository;
	@Autowired
	QualificationRepository qualificationRepository;

	@Autowired
	PasswordEncoder passwordEncoder;

	@Autowired
	UserRepository userRepository;

	@Autowired
	RoleRepository roleRepository;

	@Autowired
	PermissionRepository permissionRepository;
	@Autowired
	ContractRepository contractRepository;


	public void initDatabase() {
        Logger.getLogger("Database utility").info("Seeding database ...");
		initRole();
		initPermissions();
		initUser();
        initEducation();
        initDomain();
        initSkills();
        initLanguage();
        initRegion();
        initCampaign();
		initContract();
        initPost();
		initCV();
		initCandidate();
		initQualification();
		initCandidacy();
        Logger.getLogger("Database utility").info("Database seeding complete");
    }

	public void initRole(){
		if( roleRepository.count() > 0) return;
		Role role1 = Role.builder()
				.roleName("Recruitment manager")
				.build();
		Role role2 = Role.builder()
				.roleName("Senior developer")
				.build();
		Role role3 = Role.builder()
				.roleName("Team leader")
				.build();
		Role role4 = Role.builder()
				.roleName("Security")
				.build();

		roleRepository.saveAll(Arrays.asList(
				role1,
				role2,
				role3,
				role4
		));
	}

	public void initPermissions(){
		if( permissionRepository.count() > 0) return;
		Arrays.stream(PermissionEnum.values()).forEach(permissionEnum -> {
			permissionRepository.save(
				Permission.builder()
					.label(permissionEnum)
					.build()
			);
		});

		initRolesPermissions();
	}

	public void initRolesPermissions(){
		List<Role> roles = roleRepository.findAll();
		List<Permission> permissions = permissionRepository.findAll();

		roles.forEach(role -> {
			permissions.forEach(permission -> {
				role.getPermissions().add(permission);
			});
			roleRepository.save(role);
		});
	}

	public void initUser(){
		if( educationRepository.count() > 0) return;
		Role role1 = roleRepository.findById(1L).get();
		Role role2 = roleRepository.findById(2L).get();
		Role role3 = roleRepository.findById(3L).get();
		Role role4 = roleRepository.findById(4L).get();

		User user1 = User.builder()
				.username("cd152368")
				.password(passwordEncoder.encode("12345"))
				.role(role1)
				.build();
		User user2 = User.builder()
				.username("z6842841")
				.password(passwordEncoder.encode("12345"))
				.role(role2)
				.build();
		User user3 = User.builder()
				.username("k8879215")
				.password(passwordEncoder.encode("12345"))
				.role(role3)
				.build();
		User user4 = User.builder()
				.username("cd1523687")
				.password(passwordEncoder.encode("12345"))
				.role(role4)
				.build();

		userRepository.saveAll(Arrays.asList(
				user1,
				user2,
				user3,
				user4
		));
	}

	public void initEducation() {
		if( educationRepository.count() > 0) return;
		Education education1 = Education.builder()
				.name("Bac+1").build();
		Education education2 = Education.builder()
				.name("Bac+2").build();
		Education education3 = Education.builder()
				.name("Bac+3").build();
		Education education4 = Education.builder()
				.name("Bac+4").build();
		Education education5 = Education.builder()
				.name("Bac+5").build();
		Education education6 = Education.builder()
				.name("Bac+6/7").build();
		List<Education> educations = new ArrayList<>();
		educations.add(education1);
		educations.add(education2);
		educations.add(education3);
		educations.add(education4);
		educations.add(education5);
		educations.add(education6);
		educationRepository.saveAll(educations);
	}
	
	public void initDomain() {
		if (domainRepository.count() > 0) return;
		Domain domain1 = Domain.builder()
				.name("Informatique").build();
		Domain domain2 = Domain.builder()
				.name("Economie").build();
		Domain domain3 = Domain.builder()
				.name("Système embarqué").build();
		Domain domain4 = Domain.builder()
				.name("Gestion").build();
		Domain domain5 = Domain.builder()
				.name("Droit").build();
		
		
		List<Domain> domains = new ArrayList<>();
		domains.add(domain1);
		domains.add(domain2);
		domains.add(domain3);
		domains.add(domain4);
		domains.add(domain5);
		
		domainRepository.saveAll(domains);
	}
	
	public void initSkills() {
		
		if( skillRepository.count() > 0) return;
		 
		Skill skill1 = Skill.builder()
				.name("Développement Web Full Stack").build();
		Skill skill2 = Skill.builder()
				.name("Machine Learning et Intelligence Artificielle").build();
		Skill skill3 = Skill.builder()
				.name("Cybersécurité").build();
		Skill skill4 = Skill.builder()
				.name("Développement d'Applications Mobiles").build();
		Skill skill5 = Skill.builder()
				.name("Cloud Computing").build();
		Skill skill6 = Skill.builder()
				.name("Analyse de Données et Statistiques").build();
		Skill skill7 = Skill.builder()
				.name("Économie du Marché International").build();
		Skill skill8 = Skill.builder()
				.name("Modélisation Économique et Prévisions").build();
		Skill skill9 = Skill.builder()
				.name("Analyse des Politiques Publiques").build();
		Skill skill10 = Skill.builder()
				.name("Finance d'Entreprise et Gestion des Risques").build();
		Skill skill11 = Skill.builder()
				.name("Programmation en Langages Bas Niveau (C/C++)").build();
		Skill skill12 = Skill.builder()
				.name("Conception de Matériel Embarqué (Hardware)").build();
		Skill skill13 = Skill.builder()
				.name("Systèmes Temps Réel").build();
		Skill skill14 = Skill.builder()
				.name("Protocoles de Communication Industrielle").build();
		Skill skill15 = Skill.builder()
				.name("Développement de Firmware").build();
		Skill skill16 = Skill.builder()
				.name("Gestion de Réseau et Sécurité").build();
		Skill skill17 = Skill.builder()
				.name("Sécurité des Communications sans Fil").build();
		Skill skill18 = Skill.builder()
				.name("Surveillance et Gestion de la Bande Passante").build();
		Skill skill19 = Skill.builder()
				.name("Leadership et Gestion d'Équipe").build();
		Skill skill20 = Skill.builder()
				.name("Gestion de la Qualité et de l'Amélioration Continue").build();
		
		List<Skill> skills = new ArrayList<>();
		
		skills.add(skill1);
		skills.add(skill2);
		skills.add(skill3);
		skills.add(skill4);
		skills.add(skill5);
		skills.add(skill6);
		skills.add(skill7);
		skills.add(skill8);
		skills.add(skill9);
		skills.add(skill10);
		skills.add(skill11);
		skills.add(skill12);
		skills.add(skill13);
		skills.add(skill14);
		skills.add(skill15);
		skills.add(skill16);
		skills.add(skill17);
		skills.add(skill18);
		skills.add(skill19);
		skills.add(skill20);
		
		skillRepository.saveAll(skills);
		
	}
	
	public void initLanguage() {
		
		if( languageRepository.count() > 0) return;
		
		Language language1 = Language.builder()
				.languageTitle("Arabic").build();
		Language language2 = Language.builder()
				.languageTitle("French").build();
		Language language3 = Language.builder()
				.languageTitle("Chinese").build();
		Language language4 = Language.builder()
				.languageTitle("Russian").build();
		Language language5 = Language.builder()
				.languageTitle("Spanish").build();
		Language language6 = Language.builder()
				.languageTitle("German").build();
		
		List<Language> languages = new ArrayList<>();
		languages.add(language1);
		languages.add(language2);
		languages.add(language3);
		languages.add(language4);
		languages.add(language5);
		languages.add(language6);
		
		languageRepository.saveAll(languages);
		
	}

    public void initCampaign(){
        if (campaignRepository.count() > 0) return;
        Region region1 = regionRepository.findById(1L).get();
        Region region2 = regionRepository.findById(5L).get();
        Region region3 = regionRepository.findById(3L).get();
        Region region4 = regionRepository.findById(2L).get();
        Campaign campaign1 = Campaign.builder()
                .name("recruit Developer")
                .description("We want to recruit developers for java projects")
                .numberOfPosts(4)
                .closingDate(new Date())
                .regions(Arrays.asList(
                        region1,
                        region3
                ))
                .build();
        Campaign campaign2 = Campaign.builder()
                .name("recruit Developer Mobile")
                .description("We want to recruit developers for java projects")
                .numberOfPosts(5)
                .closingDate(new Date())
                .regions(Arrays.asList(
                        region4
                ))
                .build();
        Campaign campaign3 = Campaign.builder()
                .name("recruit Developer Desktop")
                .description("We want to recruit developers for C# projects")
                .numberOfPosts(2)
                .closingDate(new Date())
                .regions(Arrays.asList(
                        region2
                ))
                .build();
        campaignRepository.saveAll(Arrays.asList(
                campaign1,
                campaign2,
                campaign3
        ));
    }
	public void initContract(){
		if (contractRepository.count() > 0)return;
		Campaign campaign1 = campaignRepository.findById(1L).get();
		Campaign campaign2 = campaignRepository.findById(2L).get();
		Campaign campaign3 = campaignRepository.findById(3L).get();

		Contract contract1 = Contract.builder()
				.name("CDI")
				.description("Contract CDI for for 2 Year can be renewable")
				.campaign(campaign1)
				.build();
		Contract contract2 = Contract.builder()
				.name("CDD")
				.description("Contract CDD for for 6 Months can be renewable")
				.campaign(campaign2)
				.build();
		Contract contract3 = Contract.builder()
				.name("CI")
				.description("Contract CI for for 2 Months")
				.campaign(campaign3)
				.build();
		contractRepository.saveAll(Arrays.asList(
				contract1,
				contract2,
				contract3
		));
	}
    public void initPost(){
        if (postRepository.count() > 0) return;
        Campaign campaign1 = campaignRepository.findById(1L).get();
        Campaign campaign2 = campaignRepository.findById(2L).get();

        Post post1 = Post.builder()
                .name("Developeur Java/JEE")
                .description("+5 d'expérience")
                .keyWords("java;developeur;jee")
                .language("francais")
                .name("Java/JEE Developer")
                .description("+5 experience")
                .keyWords("java;Developer;jee")
                .language("french")
                .telecommuting(Telecommuting.FULL_TIME)
                .campaign(campaign1)
                .build();
        Post post2 = Post.builder()
                .name("Developeur Flatter")
                .description("+2 d'expérience")
                .keyWords("flatter;developeur;mobile;data")
                .language("francais")
                .name("Flutter Developer")
                .description("+2 experience")
                .keyWords("flutter;Developer;mobile;data")
                .language("french")
                .telecommuting(Telecommuting.PART_TIME)
                .campaign(campaign2)
                .build();

        postRepository.saveAll(Arrays.asList(
                post1,
                post2
        ));
    }

    public void initRegion(){
        if (regionRepository.count() > 0) return;
        Region region1 = Region.builder()
                .name("Tanger - Tetouan - Al Hoceima")
                .build();
        Region region2 = Region.builder()
                .name("Oriental")
                .build();
        Region region3 = Region.builder()
                .name("Fes - Meknes")
                .build();
        Region region4 = Region.builder()
                .name("Rabat - Sale - Kenitra")
                .build();
        Region region5 = Region.builder()
                .name("Beni Mellal - Khenifra")
                .build();
        Region region6 = Region.builder()
                .name("Casablanca - Settat")
                .build();
        Region region7 = Region.builder()
                .name("Marrakech - Safi")
                .build();
        Region region8 = Region.builder()
                .name("Draa - Tafilalet")
                .build();
        Region region9 = Region.builder()
                .name("Souss -Massa")
                .build();
        Region region10 = Region.builder()
                .name("Guelmim - Oued Noun")
                .build();
        Region region11 = Region.builder()
                .name("Laayoune - Saguia al Hamra")
                .build();
        Region region12 = Region.builder()
                .name("Dakhla - Oued Ed-Dahab")
                .build();

        regionRepository.saveAll(Arrays.asList(
                region1,
                region2,
                region3,
                region4,
                region5,
                region6,
                region7,
                region8,
                region9,
                region10,
                region11,
                region12
        ));
    }

	public void initCV() {

		if( cvRepository.count() > 0) return;

		Cv cv1 = Cv.builder()
				.languages(languageRepository.findAll())
				.education(educationRepository.findById(1L).get())
				.domain(domainRepository.findById(1L).get()).build();
		cvRepository.save(cv1);

	}
	public void initCandidate() {

		if( candidateRepository.count() > 0) return;

		Candidate candidate = Candidate.builder()
				.cv(cvRepository.findById(1L).get())
				.email("zineb@gamil.com")
				.firstName("zineb")
				.lastName("baraka")
				.build();
		candidateRepository.save(candidate);

	}
	public void initQualification() {

		if( qualificationRepository.count() > 0) return;

		Qualification qualification = Qualification.builder()
				.qualificationName("Full stack Developer")
				.wording("java;Developer;jee")
				.build();
		qualificationRepository.save(qualification);
		Qualification qualification1 = Qualification.builder()
				.qualificationName("Mobile")
				.wording("Android;")
				.build();
		qualificationRepository.save(qualification1);

	}
	public void initCandidacy() {
		if( candidacyRepository.count() > 0) return;

		Candidacy candidacy = Candidacy.builder()
				.cv(cvRepository.findById(1L).get())
				.post(postRepository.findById(1L).get())
				.applicationName("candidacy for full stack developer")
				.qualification(qualificationRepository.findById(1L).get())
				.build();
		candidacyRepository.save(candidacy);

	}

}

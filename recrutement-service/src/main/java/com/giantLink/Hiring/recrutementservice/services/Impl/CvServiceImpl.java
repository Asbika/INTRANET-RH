package com.giantLink.Hiring.recrutementservice.services.Impl;

import com.giantLink.Hiring.recrutementservice.models.requests.CvUpdateRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.giantLink.Hiring.recrutementservice.entities.Candidate;
import com.giantLink.Hiring.recrutementservice.entities.Cv;
import com.giantLink.Hiring.recrutementservice.entities.Domain;
import com.giantLink.Hiring.recrutementservice.entities.Education;
import com.giantLink.Hiring.recrutementservice.entities.GlobalExperience;
import com.giantLink.Hiring.recrutementservice.entities.Language;
import com.giantLink.Hiring.recrutementservice.entities.Skill;
import com.giantLink.Hiring.recrutementservice.mappers.CvMapper;

import com.giantLink.Hiring.recrutementservice.models.requests.CvRequest;
import com.giantLink.Hiring.recrutementservice.models.response.CvResponse;
import com.giantLink.Hiring.recrutementservice.repositories.CandidateRepository;
import com.giantLink.Hiring.recrutementservice.repositories.CvRepository;
import com.giantLink.Hiring.recrutementservice.repositories.DomainRepository;
import com.giantLink.Hiring.recrutementservice.repositories.EducationRepository;
import com.giantLink.Hiring.recrutementservice.repositories.GlobalExperienceRepository;
import com.giantLink.Hiring.recrutementservice.repositories.LanguageRepository;
import com.giantLink.Hiring.recrutementservice.repositories.SkillRepository;
import com.giantLink.Hiring.recrutementservice.services.CvService;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import java.util.Optional;

@Service
public class CvServiceImpl implements CvService {

	@Autowired
    private CandidateRepository candidateRepository;
	@Autowired
    private CvRepository cvRepository;
	@Autowired
    private GlobalExperienceRepository globalExperienceRepository;
	@Autowired
	private SkillRepository skillRepository;
	@Autowired
	private DomainRepository domainRepository;
	@Autowired
	private EducationRepository educationRepository;
	@Autowired
	private LanguageRepository languageRepository;
	
	@Autowired
    private MessageSource messageSource;	

	@Override
	@Transactional
	public CvResponse add(CvRequest request) {
	    // Create a new CvBuilder
	    Cv.CvBuilder cvBuilder = Cv.builder();

	    List<String> errors = new ArrayList<>();

	    // Set the candidate
	    if (request.getCandidate() == null) {
	        errors.add(messageSource.getMessage("cv.validation.candidateIdNotNull", null, LocaleContextHolder.getLocale()));
	    } else {
	        cvBuilder.candidate(request.getCandidate());
	    }

	    // Map and fetch skills by IDs
	    if (request.getSkillsId() == null || request.getSkillsId().isEmpty()) {
	        errors.add(messageSource.getMessage("cv.validation.skillsIdNotEmpty", null, LocaleContextHolder.getLocale()));
	    } else {
	        List<Skill> skills = skillRepository.findAllById(request.getSkillsId());
	        if (skills.size() != request.getSkillsId().size()) {
	            errors.add(messageSource.getMessage("cv.validation.invalidSkillsId", null, LocaleContextHolder.getLocale()));
	        } else {
	            cvBuilder.skills(skills);
	        }
	    }

	    // Fetch education by ID
	    Education education = educationRepository.findById(request.getEducationId())
	            .orElseThrow(() -> new IllegalArgumentException(messageSource.getMessage("cv.validation.educationIdNotFound", null, LocaleContextHolder.getLocale())));

	    cvBuilder.education(education);

	    // domain
	    Domain domain = domainRepository.findById(request.getDomainId())
	            .orElseThrow(() -> new IllegalArgumentException(messageSource.getMessage("cv.validation.domainIdNotFound", null, LocaleContextHolder.getLocale())));
	    cvBuilder.domain(domain);

	    // Map and fetch languages by IDs
	    List<Language> languages = languageRepository.findAllById(request.getLanguagesId());
	    if (languages.size() != request.getLanguagesId().size()) {
	        errors.add(messageSource.getMessage("cv.validation.invalidLanguagesId", null, LocaleContextHolder.getLocale()));
	    } else {
	        cvBuilder.languages(languages);
	    }

	    if (!errors.isEmpty()) {
	        String joinedErrors = String.join(", ", errors);
	        throw new IllegalArgumentException(joinedErrors);
	    }

	    // Save the Cv
	    Cv savedCv = cvRepository.save(cvBuilder.build());

	    if (request.getGlobalExperiences() != null && !request.getGlobalExperiences().isEmpty()) {
	        List<GlobalExperience> savedGlobalExperiences = new ArrayList<>();
	        for (GlobalExperience globalExperience : request.getGlobalExperiences()) {
	            globalExperience.setCv(savedCv);
	            savedGlobalExperiences.add(globalExperienceRepository.save(globalExperience));
	        }
	        savedCv.setGlobalExperiences(savedGlobalExperiences);
	    }
	    
	    Candidate candidate = savedCv.getCandidate();
	    candidate.setCv(savedCv);
	    candidateRepository.save(candidate);

	    System.out.println(savedCv.toString());
	    return CvMapper.INSTANCE.entityToResponse(savedCv);
	}

    @Override
    public List<CvResponse> get() {
        List<Cv> cvs = cvRepository.findAll();
        return CvMapper.INSTANCE.listToResponseList(cvs);
    }

    @Override
    public CvResponse get(Long id) {
        Optional<Cv> cv = cvRepository.findById(id);
        return CvMapper.INSTANCE.entityToResponse(cv.get());
    }

    @Override
    public CvResponse update(CvUpdateRequest request, Long id)
	{
		Cv existingCv = cvRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("Cv having ID " + id + " not found."));
		List<GlobalExperience> cvGlobalExperiences = new ArrayList<>();
		for (GlobalExperience experience : existingCv.getGlobalExperiences())
		{
			globalExperienceRepository.delete(experience);
		}
		List<GlobalExperience> newGlobalExperiences = new ArrayList<>();
		for (GlobalExperience experience : request.getGlobalExperiences())
		{
			GlobalExperience newExperience = new GlobalExperience();
			newExperience.setName(experience.getName());
			newExperience.setDescription(experience.getDescription());
			newExperience.setCv(existingCv);
			newGlobalExperiences.add(newExperience);
		}
		newGlobalExperiences = globalExperienceRepository.saveAll(newGlobalExperiences);
		existingCv.setGlobalExperiences(newGlobalExperiences);

		List<Language> updatedLanguages = new ArrayList<>();
		request.getLanguagesId().forEach(languageId ->
		{
			Language existingLanguage = languageRepository.findById(languageId)
					.orElseThrow(() -> new RuntimeException("Language having ID " + languageId + " not found."));
			updatedLanguages.add(existingLanguage);
		});
		existingCv.setLanguages(updatedLanguages);

		List<Skill> updatedskills = new ArrayList<>();
		request.getSkillsId().forEach(skillId ->
		{
			Skill existingSkill = skillRepository.findById(skillId)
					.orElseThrow(() -> new RuntimeException("Skill having ID " + skillId + " not found."));
			updatedskills.add(existingSkill);
		});
		existingCv.setSkills(updatedskills);

		Domain cvDomain = domainRepository.findById(request.getDomainId())
				.orElseThrow(() -> new RuntimeException("Domain having ID " + request.getDomainId() + " not found."));
		existingCv.setDomain(cvDomain);

		Education cvEducation = educationRepository.findById(request.getEducationId())
				.orElseThrow(() -> new RuntimeException("Education having ID " + request.getEducationId() + " not found."));
		existingCv.setEducation(cvEducation);

		Cv updatedCv = cvRepository.save(existingCv);

		return CvMapper.INSTANCE.entityToResponse(updatedCv);
    }

    @Override
    public void delete(Long id) {
        cvRepository.deleteById(id);
    }


}

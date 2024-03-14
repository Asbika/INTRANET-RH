package com.giantLink.Hiring.recrutementservice.models.response;

import java.util.List; 

import com.giantLink.Hiring.recrutementservice.entities.Candidate;
import com.giantLink.Hiring.recrutementservice.entities.Domain;
import com.giantLink.Hiring.recrutementservice.entities.Education;
import com.giantLink.Hiring.recrutementservice.entities.GlobalExperience;
import com.giantLink.Hiring.recrutementservice.entities.Language;
import com.giantLink.Hiring.recrutementservice.entities.Skill;

import lombok.Data;

@Data
public class CvResponse {
	
	private Long id;
	
	private Candidate candidate;
	
	private Education education;
	
	private Domain domain;
	
	private List<Skill> skills;
	
	private List<Language> languages;
	
	private List<GlobalExperience> globalExperiences;
	
	private String message;

}

package com.fse.user.service;

import java.util.List;

import com.fse.user.exception.InvalidSkillRangeException;
import com.fse.user.model.NonTechSkill;
import com.fse.user.model.TechnicalSkill;

public class SkillRangeValidation {
	
	public static void techSkillRangeValidate(List<TechnicalSkill> techSkill) {
		
		techSkill.forEach((skill)->{
			if(skill.getSkillRange().isEmpty() || skill.getSkillRange() == null || skill.getSkillRange().isBlank()) {
				throw new InvalidSkillRangeException(skill.getSkillName(),"Skill level cant be empty or null");
			}
			if(Integer.valueOf(skill.getSkillRange()) < 0 || Integer.valueOf(skill.getSkillRange()) > 20) {
				throw new InvalidSkillRangeException(skill.getSkillName(),"Skill level should be in the 10 to 20 range");
			}
		});
	}
		
	public static void NonTechSkillRangeValidate(List<NonTechSkill> nonTechSkill) {
			
		nonTechSkill.forEach((skill)->{
			if(skill.getSkillRange() .isEmpty() || skill.getSkillRange() == null || skill.getSkillRange().isBlank()) {
				throw new InvalidSkillRangeException(skill.getSkillName(),"Skill level cant be empty or null");
			}
			if(Integer.valueOf(skill.getSkillRange()) < 0 || Integer.valueOf(skill.getSkillRange()) > 20) {
				throw new InvalidSkillRangeException(skill.getSkillName(),"Skill level should be in the 10 to 20 range");
			}
		});	
	}
	
}

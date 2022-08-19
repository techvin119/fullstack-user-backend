package com.fse.user.service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.fse.user.exception.InvalidAssociateId;
import com.fse.user.exception.InvalidUserIdExcpetion;
import com.fse.user.exception.UpdateProfileDaysException;
import com.fse.user.model.Profile;
import com.fse.user.repository.UserRepository;

@Service
public class UserService{
	
	@Autowired
	UserRepository userRepository;
		
	@Autowired
	SequenceGeneratorService sequenceGenerator;
	
	@Autowired
	private KafkaTemplate<String,Profile> kafkaTemplate; 
	
	private String topic= "profiledata";
	
	public Profile addProfile(Profile profile) {
		
		if(!profile.getAssociateId().startsWith("CTS"))
		{
			throw new InvalidAssociateId(profile.getAssociateId(),"The Associate Id should begin with CTS");
		}
		
		SkillRangeValidation.techSkillRangeValidate(profile.getTechSkills());
		SkillRangeValidation.NonTechSkillRangeValidate(profile.getNonTechSkills());			
		profile.setId(sequenceGenerator.getSequenceNumber(Profile.SEQUENCE_NAME));
		profile.setCreateOrLastUpdate(LocalDate.now());
		
		this.userRepository.save(profile);
		kafkaTemplate.send(topic,profile);
		return profile;
	

	}
	
	
	public String updateProfile(Profile profile, int id) {
		
		Profile dbProfile = userRepository.findById(id).orElseThrow(()-> new InvalidUserIdExcpetion(id));
		
		profile.setCreateOrLastUpdate(LocalDate.now());
		long daysDiff = ChronoUnit.DAYS.between(dbProfile.getCreateOrLastUpdate(),profile.getCreateOrLastUpdate());
		if(daysDiff <= 10) {
			throw new UpdateProfileDaysException();
		}		
				
		SkillRangeValidation.techSkillRangeValidate(profile.getTechSkills());
		SkillRangeValidation.NonTechSkillRangeValidate(profile.getNonTechSkills());
		
		dbProfile.setTechSkills(profile.getTechSkills());
		dbProfile.setNonTechSkills(profile.getNonTechSkills());
		dbProfile.setCreateOrLastUpdate(profile.getCreateOrLastUpdate());
			
		this.userRepository.save(dbProfile);
		kafkaTemplate.send(topic,dbProfile);
		
		return "Updated the UserId " + id + " Sucessfully" ;

	}
	
	
}



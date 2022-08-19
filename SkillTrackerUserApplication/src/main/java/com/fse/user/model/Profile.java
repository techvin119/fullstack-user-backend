package com.fse.user.model;


import java.time.LocalDate;
import java.util.List;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data

@Document(collection = "Profile")
public class Profile {
	
	@Transient
	public static final String SEQUENCE_NAME="user_sequence";
	
	@Id
	private int id ;
	
	@Field("Name")
	@NotEmpty(message = "Name cannot be empty")
	@Size(min=5,max=30,message="Name minimum character is 5 and maximum character is 30")
	private String name;
	
	@Field("AssociateId")
	@NotEmpty(message = "Associate Id cannot be empty")
	@Size(min=5,max=30,message="Associate Id minimum character is 5 and maximum character is 30")
	private String associateId;

	@Field("MobileNum")
	@NotEmpty(message = "Mobile Number cannot be empty")
	@Size(min=10,max=10,message="Mobile Number should be 10 digit")
	@Pattern(regexp= "^[0-9]{10}",message="Mobile number should be numeric")
	private String mobileNum;
		
	@Field("EmailID")
	@NotEmpty(message = "Email Id cannot be empty")
	@Email(message = "Email Id is not valid",regexp = "[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,3}")
	private String emailId;
		
	@Field("TechnicalSkill")
	private List<TechnicalSkill> techSkills;
	
	@Field("NonTechnicalSkill")
	private List<NonTechSkill> nonTechSkills;
	
	@Field("CreateOrLastUpdate")
	private LocalDate createOrLastUpdate;
		
}
			

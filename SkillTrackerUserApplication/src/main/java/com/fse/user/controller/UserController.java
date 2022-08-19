package com.fse.user.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.fse.user.model.Profile;
import com.fse.user.service.UserService;


@RestController
@RequestMapping("/skill-tracker/api/v1/engineer")
public class UserController {
	
	@Autowired
	UserService userService;

	@PostMapping("/add-profile")
	public ResponseEntity<Profile> addProfile(@Valid @RequestBody Profile profile) {
		
		Profile profileData =  this.userService.addProfile(profile);
		return new ResponseEntity<>(profileData,HttpStatus.CREATED);
	}
	

	@PutMapping("/update-profile/{id}")
	public ResponseEntity<String> updateProfile(@RequestBody Profile profile, @PathVariable("id") int id ) {
		
		String userID = this.userService.updateProfile(profile, id);
		return new ResponseEntity<>(userID,HttpStatus.OK);
		
	}
	
	
}

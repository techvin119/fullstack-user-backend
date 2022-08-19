package com.fse.user.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class InvalidUserIdExcpetion extends RuntimeException {

	private static final long serialVersionUID = 1L;
	private int userId;

}

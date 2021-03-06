package com.PayMyBuddy.services.util;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.PayMyBuddy.dto.UserDTO;

public class PasswordMatchesValidator implements ConstraintValidator<PasswordMatches, Object> {

	@Override
	public void initialize(PasswordMatches constraintAnnotation) {
	}

	@Override
	public boolean isValid(Object obj, ConstraintValidatorContext context) {
		UserDTO user = (UserDTO) obj;
		return user.getPassword().equals(user.getMatchingPassword());
	}

}

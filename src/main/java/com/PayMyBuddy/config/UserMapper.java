package com.PayMyBuddy.config;

import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.PayMyBuddy.dto.UserDTO;
import com.PayMyBuddy.models.User;


/* @Configuration
@Mapper(componentModel = "spring")
public interface UserMapper {
	
	@Bean
	@BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
	public void updateCustomerFromDto(UserDTO dto, @MappingTarget User user);
} */


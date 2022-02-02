package com.PayMyBuddy.services.util;

import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import com.PayMyBuddy.dto.UserDTO;
import com.PayMyBuddy.models.User;

@Mapper(componentModel = "spring")
public interface UserMapper {

	@BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
	public void updateCustomerFromDto(UserDTO dto, @MappingTarget User user);

}

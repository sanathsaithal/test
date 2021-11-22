package com.wolken.Wolken.service;

import java.util.List;

import com.wolken.Wolken.dto.TicketDto;
import com.wolken.Wolken.dto.UserDto;

public interface UserService {

	String validateAndSaveUser(UserDto userDto);

	List<UserDto> validateAndFindAllUser();

	String validateAndUpdateUser(UserDto userDto);

	String validateAndSaveAllUser(List<UserDto> userDto);

	UserDto validateAndGetById(int id);

}

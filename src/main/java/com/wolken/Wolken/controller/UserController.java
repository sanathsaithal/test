package com.wolken.Wolken.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ser.std.UUIDSerializer;
import com.wolken.Wolken.dto.TicketDto;
import com.wolken.Wolken.dto.UserDto;
import com.wolken.Wolken.repository.UserRepo;
import com.wolken.Wolken.service.UserService;

@RestController
public class UserController {
	private Logger logger=LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	UserService userService;
	
	@PostMapping("addUser")
	public String save(@RequestBody UserDto userDto)
	{
		String message=null;
		try {
			message=userService.validateAndSaveUser(userDto);
		} catch (Exception e) {
			logger.error(e.getMessage(),e.getClass().getName());
		}
		return message;
	}
	
	@GetMapping("getAllUser")
	public List<UserDto> getAllUser()
	{
		List<UserDto> userDto=null;
		try {
			userDto=userService.validateAndFindAllUser();
		} catch (Exception e) {
			logger.error(e.getMessage(),e.getClass().getName());
		}
		return userDto;
	}
	
	@GetMapping("getUserTickets")
	UserDto getUserTickets(@RequestParam int cid)
	{
		UserDto userDto=new UserDto();
		try {
			userDto=userService.validateAndGetById(cid);
		} catch (Exception e) {
			logger.error(e.getMessage(),e.getClass().getName());
		}
		return userDto;
	}

	
	@PostMapping("updateUser")
	public String updateUser(@RequestBody UserDto userDto)
	{
		String message=null;
		try {
			message=userService.validateAndUpdateUser(userDto);
		} catch (Exception e) {
			logger.error(e.getMessage(),e.getClass().getName());
		}
		return message;
	}
	
	@PostMapping("saveAllUser")
	public String saveAllUser(@RequestBody List<UserDto> userDto)
	{
		String message=null;
		try {
			message=userService.validateAndSaveAllUser(userDto);
		} catch (Exception e) {
			logger.error(e.getMessage(),e.getClass().getName());
		}
		return message;
	}
	
	
	
	

}

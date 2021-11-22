package com.wolken.Wolken.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wolken.Wolken.dto.UserDto;
import com.wolken.Wolken.entity.TicketEntity;
import com.wolken.Wolken.entity.UserEntity;
import com.wolken.Wolken.repository.TicketRepo;
import com.wolken.Wolken.repository.UserRepo;

@Service
public class UserServiceImpl implements UserService {
private Logger logger=LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	UserRepo userRepo;
	@Autowired
	TicketRepo ticketRepo;
	
	@Override
	public String validateAndSaveUser(UserDto userDto) {
		try {
			if (!userDto.getFirstname().equals(null) && !userDto.getFirstname().equals("")) {
				if (!userDto.getLastname().equals(null) && !userDto.getLastname().equals("")) {
					if (!userDto.getEmail().equals(null) && !userDto.getEmail().equals("")) {
						if (userDto.getContact() > 5999999999l && userDto.getContact() < 9999999999l) {
							if (!userDto.getGender().equals(null) && !userDto.getGender().equals("")) {
								if (!userDto.getCity().equals(null) && !userDto.getCity().equals("")) {
									if (!userDto.getState().equals(null) && !userDto.getState().equals("")) {
										if (!userDto.getCountry().equals(null) && !userDto.getCountry().equals("")) {
											if (!userDto.getAddress().equals(null) && !userDto.getAddress().equals("")) {
												if (userDto.getPincode() > 0) {
													if(userDto.getGender().equalsIgnoreCase("male") || userDto.getGender().equalsIgnoreCase("female") || userDto.getGender().equalsIgnoreCase("others")) {
														UserEntity userEntity=new UserEntity();
														BeanUtils.copyProperties(userDto, userEntity);
														logger.info(""+userDto);
														userEntity.setTicketEntities(userDto.getTicketEntities());
														logger.info(""+userEntity);
														userRepo.save(userEntity);
														ticketRepo.saveAll(userDto.getTicketEntities());
														logger.info(""+userEntity);
														logger.info("User Data saved");
													} else {
														logger.error("Please enter gender in(male/female/others)");
														return "Please enter gender in(male/female/others)";
													}
												} else {
													logger.error("Please enter pincode");
													return "Please enter pincode";
												}
											} else {
												logger.error("Please enter address");
												return "Please enter address";
											}
										} else {
											logger.error("Please enter country");
											return "Please enter country";
										}
									} else {
										logger.error("Please enter state");
										return "Please enter state";
									}
								} else {
									logger.error("Please enter city");
									return "Please enter city";
								}
							} else {
								logger.error("Please enter gender");
								return "Please enter gender";
							}
						} else {
							logger.error("Please enter valid contact no");
							return "Please enter valid contact no";
						}
					} else {
						logger.error("Please provide email");
						return "Please provide email";
					}
				} else {
					logger.error("Please enter last name");
					return "Please enter last name";
				}
			} else {
				logger.error("Please enter first name");
				return "Please enter first name";
			}
		} catch(Exception e) {
			logger.error(e.getMessage(),e.getClass().getName());
		}
		return "User Data saved";
	}
	

	@Override
	public List<UserDto> validateAndFindAllUser() {
		List<UserDto> lUserDto=new ArrayList<>();
		List<UserEntity> userEntity=userRepo.findAll();
		try {
		for (UserEntity userEntity2 : userEntity) {
			UserDto userDto=new UserDto();
			BeanUtils.copyProperties(userEntity2, userDto);
			lUserDto.add(userDto);
			logger.info(""+lUserDto);
		}
		}
		catch(Exception e) {
			logger.error(e.getMessage(),e.getClass().getName());
		}
		return lUserDto;
	}


	@Override
	public String validateAndUpdateUser(UserDto userDto) {
		int id=userDto.getCid();
		UserEntity userEntity=userRepo.getById(id);
			try {
				if(userEntity!=null)
				{
					if(userDto.getFirstname()!=null)
						userEntity.setFirstname(userDto.getFirstname());
					if(userDto.getLastname()!=null)
						userEntity.setLastname(userDto.getLastname());
					if(userDto.getEmail()!=null)
						userEntity.setEmail(userDto.getEmail());
					if(userDto.getContact()!=0)
						userEntity.setContact(userDto.getContact());
					if(userDto.getGender()!=null)
						userEntity.setGender(userDto.getGender());
					if(userDto.getCity()!=null)
						userEntity.setCity(userDto.getCity());
					if(userDto.getState()!=null)
						userEntity.setState(userDto.getState());
					if(userDto.getCountry()!=null)
						userEntity.setState(userDto.getState());
					if(userDto.getAddress()!=null)
						userEntity.setAddress(userDto.getAddress());
					if(userDto.getPincode()!=0)
						userEntity.setPincode(userDto.getPincode());
					userRepo.save(userEntity);
					logger.info(""+userEntity);	
				}
			} catch (Exception e) {
				logger.error(e.getMessage(), e.getClass().getName());
			}
			return "User data updated";
		}


	@Override
	public String validateAndSaveAllUser(List<UserDto> userDto) {
		try {
		if(userDto != null)
		{
			for (UserDto userDto2 : userDto) {
				UserEntity userEntity=new UserEntity();
				List<UserEntity> list=new ArrayList<>();
				BeanUtils.copyProperties(userDto2, userEntity);
				list.add(userEntity);
				userRepo.saveAll(list);
				logger.info("Users Added");
				
			}
		}
		}
		catch(Exception e)
		{
			logger.error(e.getMessage(),e.getClass().getName());
		}
		return "Users Added";
	}

	@Override
	public UserDto validateAndGetById(int cid) {
		UserDto userDto=new UserDto();
		try {
			if(cid!=0)
			{
				UserEntity userEntity=userRepo.getById(cid);
				BeanUtils.copyProperties(userEntity, userDto);
				logger.info("" + userDto);
			}
		} catch (Exception e) {
			logger.error(e.getMessage(),e.getClass().getName());
		}
		return userDto;
	}
	
	

	
	}

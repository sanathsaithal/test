package com.wolken.Wolken.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wolken.Wolken.dto.TicketDto;
import com.wolken.Wolken.entity.TicketEntity;
import com.wolken.Wolken.repository.TicketRepo;

@Service
public class TicketServiceImpl implements TicketService {
private Logger logger=LoggerFactory.getLogger(this.getClass());
SimpleDateFormat date=new SimpleDateFormat("dd/MM/yyyy");
	@Autowired
	TicketRepo ticketRepo;
	
	@Override
	public String validateAndAddTicket(TicketDto ticketDto) {
		TicketEntity ticketEntity=new TicketEntity();
		try {
			if (!ticketDto.getQuerytype().equals(null) && !ticketDto.getQuerytype().equals("")) {
				if (!ticketDto.getQuery().equals(null) && !ticketDto.getQuery().equals("")) {
					if (!ticketDto.getDate().equals(null) && !ticketDto.getDate().equals("")) {
						//if (ticketDto.getCustid() != 0) {
							if (!ticketDto.getAgentid().equals(null) && !ticketDto.getAgentid().equals("")) {
								if (!ticketDto.getAssignedtechid().equals(null) && !ticketDto.getAssignedtechid().equals("")) {
									if (!ticketDto.getProductname().equals(null) && !ticketDto.getProductname().equals("")) {
										if (!ticketDto.getProductid().equals(null) && !ticketDto.getProductid().equals("")) {
											if (!ticketDto.getStatus().equals(null) && !ticketDto.getStatus().equals("")) {
												if (!ticketDto.getPriority().equals(null) && !ticketDto.getPriority().equals("")) {
													if(ticketDto.getStatus().equals("registered") || ticketDto.getStatus().equals("inprocess") || ticketDto.getStatus().equals("completed")) {
														if(ticketDto.getPriority().equals("high") || ticketDto.getPriority().equals("intermediate") || ticketDto.getPriority().equals("low")) {
															Date date1=new SimpleDateFormat("dd/MM/yyyy").parse(ticketDto.getDate());
															ticketEntity.setDate(date1);
															BeanUtils.copyProperties(ticketDto, ticketEntity);
															ticketRepo.save(ticketEntity);
															logger.info(""+ticketEntity);
														} else {
															logger.info("Please enter priority in(high/low/intermediate)");
															return "Please enter priority in(high/low/intermediate)";
														}
													} else {
														logger.info("Please enter status in (registered/inprocess/completed)");
														return "Please enter status in (registered/inprocess/completed)";
													}
												} else {
													logger.error("Please enter priority");
													return "Please enter priority";
												}
											} else {
												logger.error("Please enter status");
												return "Please enter status";
											}
										} else {
											logger.error("Please enter product id");
											return "Please enter product id";
										}
									} else {
										logger.error("Please enter product name");
										return "Please enter product name";
									}
								} else {
									logger.error("Please assign techniciean");
									return "Please assign techniciean";
								}
							} else {
								logger.error("Please enter agent id");
								return ("Please enter agent id");
							}
						//} else {
//							logger.error("Please enter customer id");
							//return "Please enter customer id";
						//}
					} else {
						logger.error("Please enter date");
						return "Please enter date";
					}
				} else {
					logger.error("Please enter your query for which you raised ticket");
					return "Please enter your query for which you raised ticket";
				}
			} else {
				logger.error("Please enter query type");
				return "Please enter query type";
			}
		}
		catch(Exception e) {
			logger.error(e.getMessage(),e.getClass().getName());
		}
		return "ticket added";
	}

	@Override
	public List<TicketDto> validateAndGetAllTicket() {
		List<TicketDto> lTicketDto=new ArrayList<>();
		List<TicketEntity> ticketEntity=ticketRepo.findAll();
		try {
			for (TicketEntity ticketEntity2 : ticketEntity) {
				logger.info(""+ticketEntity);
				TicketDto ticketDto=new TicketDto();
				BeanUtils.copyProperties(ticketEntity2, ticketDto);
				ticketDto.setDate(date.format(ticketEntity2.getDate()));
				lTicketDto.add(ticketDto);
				logger.info(""+lTicketDto);
			} 
		}
		catch(Exception e) {
			logger.error(e.getMessage(),e.getClass().getName());
		}
		return lTicketDto;
	}

	@Override
	public String validateAndUpdate(TicketDto ticketDto) {
		int id=ticketDto.getTicketid();
		TicketEntity ticketEntity=ticketRepo.getById(id);
		Date UpdateDate = null;
		try {
			if(ticketEntity!=null)
			{
				if(ticketDto.getQuerytype()!=null)
					ticketEntity.setQuerytype(ticketDto.getQuerytype());
				if(ticketDto.getQuery()!=null)
					ticketEntity.setQuery(ticketDto.getQuery());
				if(ticketDto.getDate()!=null)
					UpdateDate=new SimpleDateFormat("dd/MM/yyyy").parse(ticketDto.getDate());
					ticketEntity.setDate(UpdateDate);
				//if(ticketDto.getCustid()!=0)
					//ticketEntity.setCustid(ticketDto.getCustid());
				if(ticketDto.getAgentid()!=null)
					ticketEntity.setAgentid(ticketDto.getAgentid());
				if(ticketDto.getAssignedtechid()!=null)
					ticketEntity.setAssignedtechid(ticketDto.getAssignedtechid());
				if(ticketDto.getProductname()!=null)
					ticketEntity.setProductname(ticketDto.getProductname());
				if(ticketDto.getProductid()!=null)
					ticketEntity.setProductid(ticketDto.getProductid());
				if(ticketDto.getStatus()!=null)
					ticketEntity.setStatus(ticketDto.getStatus());
				if(ticketDto.getPriority()!=null)
					ticketEntity.setPriority(ticketDto.getPriority());
				ticketRepo.save(ticketEntity);
				logger.info(""+ticketEntity);
			}
		} catch (Exception e) {
			logger.error(e.getMessage(),e.getClass().getName());
		}
		return "Ticket data updated";
	}

	@Override
	public String validateAndUpdateTicketStatus(int ticketid, String status) {
		try {
			if(ticketid!=0) {
				if(status!=null) {
					if(status.equalsIgnoreCase("registered") || status.equalsIgnoreCase("inprocess") || status.equalsIgnoreCase("complete")) {
						TicketEntity ticketEntity=ticketRepo.findByTicketid(ticketid);
						logger.info(""+ticketEntity);
						if(ticketEntity!=null) {
							ticketEntity.setStatus(status);
							ticketRepo.save(ticketEntity);
							logger.info("Updated Status in Ticket table");
						} else {
							logger.info("No ticket found with this id");
							return "No ticket found with this id";
						}
					} else {
						logger.info("Please enter status in (registered/inprocess/complete)");
						return "Please enter status in (registered/inprocess/complete)";
					}
				} else {
					logger.info("Please enter status");
					return "Please enter status";
				}
			} else {
				logger.info("Please enter ticket id");
				return "Please enter ticket id";
			}
		} catch (Exception e) {
			logger.error(e.getMessage(),e.getClass().getName());
		}
		return "Updated Status in Ticket table";
	}

	@Override
	public String validateAndUpdateTechnician(int ticketid, String assignedtechid) {
		try {
			if(ticketid!=0) {
				if(assignedtechid!=null) {
					TicketEntity ticketEntity=ticketRepo.findByTicketid(ticketid);
					if(ticketEntity!=null) {
						ticketEntity.setAssignedtechid(assignedtechid);
						ticketRepo.save(ticketEntity);
						logger.info("Technician updated for this ticketid");
					} else {
						logger.info("No ticket found with this id");
						return "No ticket found with this id";
					}
				} else {
					logger.info("Please enter Tech Id");
					return "Please enter Tech Id";
				}
			} else {
				logger.info("Please enter Ticket Id");
				return "Please enter Ticket Id";
			}
		} catch (Exception e) {
			logger.error(e.getMessage(),e.getClass().getName());
		}
		return "Technician updated for this ticketid";
	}

	@Override
	public String validateAndGetStatus(int ticketid) {
		TicketDto ticketDto=new TicketDto();
		TicketEntity ticketEntity=ticketRepo.findByTicketid(ticketid);
		try {
			BeanUtils.copyProperties(ticketEntity, ticketDto );
			logger.info(""+ticketDto.getStatus());
			return ""+ticketDto.getStatus();
		} catch (Exception e) {
			logger.error(e.getMessage(),e.getClass().getName());
		}
		return null;
	}

	@Override
	public String validateAndSaveAll(List<TicketDto> ticketDto) throws ParseException {
		if (ticketDto != null)
		{
		for (TicketDto ticketDto2 : ticketDto) {
			TicketEntity ticketEntity=new TicketEntity();
			List<TicketEntity> list=new ArrayList<>();
			Date date1=new SimpleDateFormat("dd/MM/yyyy").parse(ticketDto2.getDate());
			ticketEntity.setDate(date1);
			logger.info(""+ticketEntity);
			BeanUtils.copyProperties(ticketDto2, ticketEntity);
			list.add(ticketEntity);
			ticketRepo.saveAll(list);
			logger.info("Tickets added");
		}
		}
		return "Tickets added";
		
	}
	
	
	
	

}

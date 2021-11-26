package com.wolken.Wolken.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import com.wolken.Wolken.dto.TicketDto;
import com.wolken.Wolken.repository.TicketRepo;
import com.wolken.Wolken.service.TicketService;

@RestController
public class TicketController {
private Logger logger=LoggerFactory.getLogger(this.getClass());

@Autowired
TicketService ticketService;

@PostMapping("addTicket")
public String addTicket(@RequestBody TicketDto ticketDto)
{
	String message=null;
	try {
		message=ticketService.validateAndAddTicket(ticketDto);
	} catch (Exception e) {
		logger.error(e.getMessage().getClass().getName());	
	}
	return message;
}

@GetMapping("getAllTickets")
List<TicketDto> getAllTickets()
{
	List<TicketDto> ticketDto=null;
	try {
		ticketDto=ticketService.validateAndGetAllTicket();
	} catch (Exception e) {
		logger.error(e.getMessage().getClass().getName());
	}
	return ticketDto;
}

@PostMapping("updateTickets")
String updateTickets(@RequestBody TicketDto ticketDto)
{
	String message=null;
	try {
		message=ticketService.validateAndUpdate(ticketDto);
	} catch (Exception e) {
		logger.error(e.getMessage().getClass().getName());
	}
	return message;
}

@PostMapping("updateOrderStatus")
public String updateOrderStatus(@RequestParam int ticketid, String status)
{
	String message=null;
	try {
		message=ticketService.validateAndUpdateTicketStatus(ticketid,status);
	} catch (Exception e) {
		logger.error(e.getMessage(),e.getClass().getName());
	}
	return message;
}

@PostMapping("updateAssignedTech")
public String updateAssignedTech(@RequestParam int ticketid, String assignedtechid)
{
	String message=null;
	try {
		message=ticketService.validateAndUpdateTechnician(ticketid,assignedtechid);
	} catch (Exception e) {
		logger.error(e.getMessage(),e.getClass().getName());
	}
	return message;	
}

@GetMapping("getStatus")
public String getStatus(@RequestParam int ticketid)
{
	String message=null;
	try {
		message=ticketService.validateAndGetStatus(ticketid);
	} catch (Exception e) {
		logger.error(e.getMessage(),e.getClass().getName());
	}
	return message;
}

@PostMapping("saveAllTickets")
public String saveAllTickets(@RequestBody List<TicketDto> ticketDto)
{
	String message=null;
	try {
		message=ticketService.validateAndSaveAll(ticketDto);
	} catch (Exception e) {
		logger.error(e.getMessage(),e.getClass().getName());
	}
	return message;
}

@ExceptionHandler(value= {HttpMessageNotReadableException.class,MethodArgumentTypeMismatchException.class})
String exception()
{
	return "data not proper";
}
}

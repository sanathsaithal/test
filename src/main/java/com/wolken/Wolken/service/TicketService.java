package com.wolken.Wolken.service;

import java.text.ParseException;
import java.util.List;

import com.wolken.Wolken.dto.TicketDto;

public interface TicketService {

	String validateAndAddTicket(TicketDto ticketDto);

	List<TicketDto> validateAndGetAllTicket();

	String validateAndUpdate(TicketDto ticketDto);

	String validateAndUpdateTicketStatus(int ticketid, String status);

	String validateAndUpdateTechnician(int ticketid, String assignedtechid);

	String validateAndGetStatus(int ticketid);

	String validateAndSaveAll(List<TicketDto> ticketDto) throws ParseException;

}

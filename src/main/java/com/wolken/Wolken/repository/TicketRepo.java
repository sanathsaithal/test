package com.wolken.Wolken.repository;

import org.springframework.data.jpa.repository.support.JpaRepositoryImplementation;

import com.wolken.Wolken.entity.TicketEntity;

public interface TicketRepo extends JpaRepositoryImplementation<TicketEntity, Integer>{
	TicketEntity findByTicketid(int ticketid);
	
}

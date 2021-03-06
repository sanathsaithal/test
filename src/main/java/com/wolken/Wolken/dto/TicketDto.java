package com.wolken.Wolken.dto;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.wolken.Wolken.entity.UserEntity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class TicketDto {
	private int ticketid;
	private String querytype;
	private String query;
	private String date;
	private String agentid;
	private String assignedtechid;
	private String productname;
	private String productid;
	private String status;
	private String priority;
	@JsonIgnoreProperties("ticketDto")
	private UserEntity userEntity;
}

package com.wolken.Wolken.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Entity
@Table
@NoArgsConstructor
@ToString
public class TicketEntity {

	@Id
	@GenericGenerator(name="ticket", strategy="increment")
	@GeneratedValue(generator = "ticket")
	private int ticketid;
	private String querytype;
	private String query;
	private Date date;
	private String agentid;
	private String assignedtechid;
	private String productname;
	private String productid;
	private String status;
	private String priority;
}

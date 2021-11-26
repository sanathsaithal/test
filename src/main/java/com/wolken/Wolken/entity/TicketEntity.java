package com.wolken.Wolken.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Entity
@Table
@NoArgsConstructor
public class TicketEntity implements Serializable {

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
	
	@ManyToOne(targetEntity = UserEntity.class)
	@JsonIgnore
	private UserEntity userEntity;
}

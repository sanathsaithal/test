package com.wolken.Wolken.dto;

import java.util.List;

import javax.persistence.Entity;

import com.wolken.Wolken.entity.TicketEntity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class UserDto {
	private int cid;
	private String firstname;
	private String lastname;
	private String email;
	private long contact;
	private String gender;
	private String city;
	private String state;
	private String country;
	private String address;
	private int pincode;
	private List<TicketEntity> ticketEntities;
}

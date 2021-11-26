package com.wolken.Wolken.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
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
@Table
@NoArgsConstructor
@Entity
public class UserEntity implements Serializable{
	@Id
	@GenericGenerator(name="wolken", strategy="increment")
	@GeneratedValue(generator = "wolken")
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
	
//	@OneToMany(targetEntity= TicketEntity.class, cascade=CascadeType.ALL)
//	@JoinColumn(name="custid", referencedColumnName="cid")
//    private List<TicketEntity> ticketEntities;
	
	@OneToMany(targetEntity= TicketEntity.class, mappedBy="userEntity", cascade=CascadeType.ALL)
	@JsonIgnore
	private List<TicketEntity> ticketEntities;
}

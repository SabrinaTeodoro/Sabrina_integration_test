package com.topicos.sabrina.dtos;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter

public class CostumerDTO {
	private Long cpf;
	private String name;
	private Date birthDate;
	private String gender;
	private String email;
	private float loyaltyRate;
	@JsonIgnore
	private boolean active;
}

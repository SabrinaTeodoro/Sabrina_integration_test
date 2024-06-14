package com.topicos.sabrina.entities;
import java.io.Serializable;
import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Getter @Setter
@ToString

public class CostumerEntity implements Serializable{
	@Id
	private Long cpf;
	private String name;
	private Date birthDate;
	private String gender;
	private String email;
	private float loyaltyRate;
	private boolean active;
}

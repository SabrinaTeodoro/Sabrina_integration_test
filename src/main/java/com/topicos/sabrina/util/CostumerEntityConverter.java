package com.topicos.sabrina.util;

import org.springframework.stereotype.Component;

import com.topicos.sabrina.dtos.CostumerDTO;
import com.topicos.sabrina.entities.CostumerEntity;

@Component
public class CostumerEntityConverter {
	public static CostumerDTO toDTO(CostumerEntity costumer) {
		return new CostumerDTO(
					costumer.getCpf(),
					costumer.getName(),
					costumer.getBirthDate(),
					costumer.getGender(),
					costumer.getEmail(),
					costumer.getLoyaltyRate(),
					costumer.isActive()
					
				);
	}
	public CostumerEntity toEntity(CostumerDTO costumer) {
		return new CostumerEntity(
					costumer.getCpf(),
					costumer.getName(),
					costumer.getBirthDate(),
					costumer.getGender(),
					costumer.getEmail(),
					costumer.getLoyaltyRate(),
					costumer.isActive()
				);
	}
}

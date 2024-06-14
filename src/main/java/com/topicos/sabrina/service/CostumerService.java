package com.topicos.sabrina.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.topicos.sabrina.dtos.CostumerDTO;
import com.topicos.sabrina.repositories.CostumerRepository;
import com.topicos.sabrina.util.CostumerEntityConverter;

import br.edu.univas.si7.topicos.product.util.ProductEntityConverter;

import com.topicos.sabrina.entities.CostumerEntity;

@Service
public class CostumerService {
	private CostumerRepository repo;
	
	@Autowired
	private CostumerEntityConverter converter;
	
	@Autowired
	public CostumerService(CostumerRepository repo) {
		this.repo = repo;
	}
	
	public CostumerDTO findById(Long cpf) {
		Optional<CostumerEntity> obj = repo.findById(cpf);
		CostumerEntity entity = obj.orElseThrow(() -> new RuntimeException("Object not found: " + cpf));
		return CostumerEntityConverter.toDTO(entity);
	}
	
	private void updateData(CostumerEntity existingObj, CostumerDTO newObj) {
		existingObj.setName(newObj.getName());
	}
	
	public void updateCostumer(CostumerDTO costumer, Long cpf) {
		if (cpf == null || costumer == null || !cpf.equals(costumer.getCpf())) {
			throw new RuntimeException("Invalid product code.");
		}
		CostumerDTO existingObj = findById(cpf);
		updateData(existingObj, costumer);
		repo.save(existingObj);
	}
	
	public void deleteCostumer(Long cpf) {//ativa/desativa
		if (cpf == null) {
			throw new RuntimeException("Product code can not be null.");
		}
		CostumerEntity obj = findById(cpf);
		try {
			obj.setActive(!obj.isActive());
			repo.save(obj);
		} catch (RuntimeException e) {
			throw new RuntimeException("Can not delete a Product with dependencies constraints.");
		}
	}
	
	
	public void createCostumer(CostumerDTO costumer) {
		repo.save(converter.toEntity(costumer));
	}
}

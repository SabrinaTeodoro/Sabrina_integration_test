package com.topicos.sabrina.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;
import com.topicos.sabrina.dtos.CostumerDTO;
import com.topicos.sabrina.service.CostumerService;


@RestController
@RequestMapping("/api/costumers")

public class CostumerController {
	@Autowired
	private CostumerService service;
	
	@GetMapping("/{cpf}")
	public CostumerDTO getCostumerById(@PathVariable Long cpf) {
		return service.findById(cpf);
	}

	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void updateCostumer(@RequestBody CostumerDTO dto, @PathVariable Long cpf) {
		service.updateCostumer(dto, cpf);
	}
	
	@DeleteMapping("/{cpf}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteCostumer(@PathVariable Long cpf) {
		service.deleteCostumer(cpf);
	}

	@PostMapping("")
	@ResponseStatus(HttpStatus.CREATED)
	public void createCostumer(@RequestBody CostumerDTO costumer) {
		service.createCostumer(costumer);
	}
}

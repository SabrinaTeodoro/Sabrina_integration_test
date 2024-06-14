package com.topicos.sabrina.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.topicos.sabrina.entities.CostumerEntity;

@Repository
public interface CostumerRepository extends JpaRepository<CostumerEntity, Long>{
	public List<CostumerEntity> findByActive(Boolean active);
}

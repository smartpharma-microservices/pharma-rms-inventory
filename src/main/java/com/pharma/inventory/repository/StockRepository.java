package com.pharma.inventory.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pharma.inventory.entity.StockEntity;

@Repository
public interface StockRepository extends JpaRepository<StockEntity, Long>{
}

package com.pharma.inventory.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pharma.inventory.entity.RetailerProductEntity;

@Repository
public interface RetailerProductRepository extends JpaRepository<RetailerProductEntity, String> {
	Optional<RetailerProductEntity> findByProductCodeAndStoreCode(long productCode, long storeCode);

}

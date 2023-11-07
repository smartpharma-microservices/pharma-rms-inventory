package com.pharma.inventory.mapper;

import java.util.UUID;

import org.mapstruct.Mapper;

import com.pharma.inventory.dto.RetailerProductDto;
import com.pharma.inventory.dto.StockDto;
import com.pharma.inventory.entity.RetailerProductEntity;
import com.pharma.inventory.entity.StockEntity;

@Mapper(componentModel = "spring")
public interface RetailerProductMapper {

	public RetailerProductEntity dtoToRetailerProductEntity(RetailerProductDto retailerProductDto);

	public StockEntity dtoToStockEntity(StockDto stockDto);

	public RetailerProductDto entityToRetailerProductDto(RetailerProductEntity retailerProductEntity);

	public StockDto entityToStockDto(StockEntity stockEntity);

	
	default Long generateCodeIfNotExists(Long existingCode) {
		if (existingCode == null || existingCode == 0L) {
			long min = 100_000L;
			long max = 999_999L;
			return min + (long) (Math.random() * (max - min + 1));
		}
		return existingCode;
	}

	default String generateGuidIfNotExists(String existingGuid) {
		if (existingGuid == null) {
			return UUID.randomUUID().toString();
		}
		return existingGuid;
	}
}

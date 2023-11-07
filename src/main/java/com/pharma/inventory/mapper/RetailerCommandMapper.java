
package com.pharma.inventory.mapper;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.pharma.inventory.dto.RetailerProductDto;
import com.pharma.inventory.entity.RetailerProductEntity;
import com.pharma.inventory.entity.StockEntity;
import com.pharma.inventory.repository.StockRepository;

@Component
public class RetailerCommandMapper {

	RetailerProductMapper retailerProductMapper;

	StockRepository stockRepository;

	public RetailerCommandMapper(RetailerProductMapper retailerProductMapper, StockRepository stockRepository) {
		this.retailerProductMapper = retailerProductMapper;
		this.stockRepository = stockRepository;
	}

	public RetailerProductEntity mapToRetailerProductEntity(RetailerProductDto retailerProductDto) {
		System.out.println(retailerProductDto);
		var retailerProductEntity = retailerProductMapper.dtoToRetailerProductEntity(retailerProductDto);
		// retailerProductEntity.setStockEntities(mapToStockEntities(retailerProductEntity));
		return retailerProductEntity;
	}

	/*
	 * public StockEntity mapToStockEntity(StockDto stockDto) { var stockEntity =
	 * retailerProductMapper.dtoToStockEntity(stockDto); stockEntity
	 * .setRetailerProductEntity(mapToRetailerProductEntities(stockDto.
	 * getRetailerProductDtos(), stockEntity)); return stockEntity; }
	 */

	public List<StockEntity> mapToStockEntities(RetailerProductEntity retailerProductEntity) {
		List<StockEntity> stockEntities = new ArrayList<>();
		StockEntity stockEntity = new StockEntity();
		stockEntity.setStockId(retailerProductMapper.generateCodeIfNotExists(null));
		stockEntity.setRetailerProductEntity(retailerProductEntity);
		stockEntity.setStockIn(retailerProductEntity.getQuantity());
		stockEntity.setStockOut(retailerProductEntity.getQuantity());
		stockEntity.setLastUpdatedDate(Timestamp.valueOf(LocalDateTime.now()));
		stockEntities.add(stockEntity);
		return stockEntities;
	}
}

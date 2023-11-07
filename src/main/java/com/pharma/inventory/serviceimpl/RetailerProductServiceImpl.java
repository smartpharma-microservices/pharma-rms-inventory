package com.pharma.inventory.serviceimpl;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.pharma.inventory.client.ProductClient;
import com.pharma.inventory.dto.RetailerProductDto;
import com.pharma.inventory.dto.RetailerProductQueryDto;
import com.pharma.inventory.dto.StockDto;
import com.pharma.inventory.entity.RetailerProductEntity;
import com.pharma.inventory.entity.StockEntity;
import com.pharma.inventory.mapper.RetailerProductMapper;
import com.pharma.inventory.repository.RetailerProductRepository;
import com.pharma.inventory.repository.StockRepository;
import com.pharma.inventory.service.RetailerProductService;

@Service
public class RetailerProductServiceImpl implements RetailerProductService {

	private RetailerProductRepository retailerProductRepository;

	private RetailerProductMapper retailerProductMapper;

	private StockRepository stockRepository;

	private ProductClient productClient;

	@Autowired
	public RetailerProductServiceImpl(RetailerProductRepository retailerProductRepository,
			RetailerProductMapper retailerProductMapper, StockRepository stockRepository, ProductClient productClient) {
		super();
		this.retailerProductRepository = retailerProductRepository;
		this.retailerProductMapper = retailerProductMapper;
		this.stockRepository = stockRepository;
		this.productClient = productClient;
	}

	@Override
	public void createAndUpdateRetailerProduct(RetailerProductDto retailerProductDto) {
		var retailerProductEntity = retailerProductMapper.dtoToRetailerProductEntity(retailerProductDto);
		var retailerProductEntityOptional = retailerProductRepository
				.findById(retailerProductDto.getRetailerProductUid());
		var stockEntity = new StockEntity();
		stockEntity.setStockId(retailerProductMapper.generateCodeIfNotExists(null));
		stockEntity.setLastUpdatedDate(Timestamp.valueOf(LocalDateTime.now()));
		stockEntity.setRetailerProductEntity(retailerProductEntity);
		if (retailerProductEntityOptional.isPresent()) {
			if (retailerProductDto.getQuantity() > retailerProductEntityOptional.get().getQuantity()) {
				stockEntity.setStockIn(
						retailerProductDto.getQuantity() - retailerProductEntityOptional.get().getQuantity());
			} else {
				stockEntity.setStockOut(
						retailerProductEntityOptional.get().getQuantity() - retailerProductDto.getQuantity());
			}
		} else {
			stockEntity.setStockIn(retailerProductDto.getQuantity());
		}
		retailerProductRepository.save(retailerProductEntity);
		stockRepository.save(stockEntity);
	}

	@Override
	public RetailerProductQueryDto findByProductAndStoreCodes(long productCode, long storeCode) {
		var retailerProductEntity = retailerProductRepository.findByProductCodeAndStoreCode(productCode, storeCode)
				.orElseThrow(() -> new RuntimeException("Given codes are not valid"));
		RetailerProductQueryDto retailerProductQueryDto = new RetailerProductQueryDto();
		var retailerProductDto = retailerProductMapper.entityToRetailerProductDto(retailerProductEntity);
		var stockDtos = retailerProductEntity.getStockEntities().stream().map(stockEntity -> mapToStockDto(stockEntity))
				.collect(Collectors.toList());
		retailerProductQueryDto.setRetailerProductDto(retailerProductDto);
		retailerProductQueryDto.setStockDtos(stockDtos);
		return retailerProductQueryDto;
	}

	public StockDto mapToStockDto(StockEntity stockEntity) {
		return retailerProductMapper.entityToStockDto(stockEntity);
	}

	@Override
	public void inventoryUpload(MultipartFile file) throws IOException {
		InputStream inputStream = file.getInputStream();
		CSVReader reader = new CSVReaderBuilder(new InputStreamReader(inputStream)).build();
		reader.skip(1);
		List<RetailerProductEntity> retailerProductEntities = new ArrayList<>();
		reader.forEach(csvRecord -> {
				var retailerProductEntity = new RetailerProductEntity();
				
				retailerProductEntity.setRetailerProductUid(Optional.ofNullable(csvRecord[1]).filter(uid -> !uid.trim().isEmpty()).map(UUID::fromString).orElseGet(UUID::randomUUID));
				retailerProductEntity.setRetailerCode(Optional.of(csvRecord[2])
						.filter(str -> !str.isEmpty() && str.matches("-?\\d+")).map(Long::parseLong).orElse(0L));
				retailerProductEntity.setStoreCode(Optional.of(csvRecord[3])
						.filter(str -> !str.isEmpty() && str.matches("-?\\d+")).map(Long::parseLong).orElse(0L));
				retailerProductEntity.setProductCode(Optional.of(csvRecord[4])
						.filter(str -> !str.isEmpty() && str.matches("-?\\d+")).map(Long::parseLong).orElse(0L));
				retailerProductEntity.setProductName(csvRecord[5]);
				retailerProductEntity.setQuantity(Optional.of(csvRecord[6])
						.filter(str -> !str.isEmpty() && str.matches("-?\\d+")).map(Integer::parseInt).orElse(0));
				retailerProductEntity.setPtr(Optional.of(csvRecord[7])
						.filter(str -> !str.isEmpty() && str.matches("-?\\d+")).map(Double::parseDouble).orElse(0.0));
				retailerProductEntity.setMrp(Optional.of(csvRecord[8])
						.filter(str -> !str.isEmpty() && str.matches("-?\\d+")).map(Double::parseDouble).orElse(0.0));
				
				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
				retailerProductEntity.setExpiryDate(LocalDate.parse(csvRecord[9], formatter));
				retailerProductEntity.setManufacturedDate(LocalDate.parse(csvRecord[10], formatter));
				retailerProductEntity.setReOrderLevel(Optional.of(csvRecord[11])
						.filter(str -> !str.isEmpty() && str.matches("-?\\d+")).map(Integer::parseInt).orElse(0));
				retailerProductEntities.add(retailerProductEntity);		
		});
		retailerProductRepository.saveAllAndFlush(retailerProductEntities);
	}

}
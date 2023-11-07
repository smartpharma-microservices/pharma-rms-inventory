package com.pharma.inventory.service;

import java.io.IOException;

import org.springframework.web.multipart.MultipartFile;

import com.pharma.inventory.dto.RetailerProductDto;
import com.pharma.inventory.dto.RetailerProductQueryDto;

public interface RetailerProductService {

	public void createAndUpdateRetailerProduct(RetailerProductDto retailerProductDto);

	public RetailerProductQueryDto findByProductAndStoreCodes(long productCode, long storeCode);
	
	public void inventoryUpload(MultipartFile file) throws IOException;

}

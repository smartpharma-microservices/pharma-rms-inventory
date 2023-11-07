package com.pharma.inventory.controller;

import java.io.IOException;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.pharma.inventory.dto.RetailerProductDto;
import com.pharma.inventory.dto.RetailerProductQueryDto;
import com.pharma.inventory.service.RetailerProductService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/retailerInventory")
public class RetailerProductController {

	private RetailerProductService retailerProductService;

	public RetailerProductController(RetailerProductService retailerProductService) {
		this.retailerProductService = retailerProductService;
	}

	@PostMapping("/create")
	public void createRetailerProduct(@Valid @RequestBody RetailerProductDto retailerProductDto) {
		retailerProductService.createAndUpdateRetailerProduct(retailerProductDto);
	}

	@GetMapping("/findByProductAndStoreCodes")
	public RetailerProductQueryDto findByProductAndStoreCodes(@RequestParam("productCode") long productCode,
			@RequestParam("storeCode") long storeCode) {
		return retailerProductService.findByProductAndStoreCodes(productCode, storeCode);
	}
	
	@PostMapping("/inventoryUpload")
	public void inventoryUpload(@RequestParam("file") MultipartFile file) throws IOException {
		retailerProductService.inventoryUpload(file);
	}
}

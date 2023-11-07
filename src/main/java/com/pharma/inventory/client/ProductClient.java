package com.pharma.inventory.client;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.pharma.inventory.dto.ProductCodesDto;
import com.pharma.inventory.dto.ProductDto;

@FeignClient(name = "pharma-product-service",url = "http://localhost:8089",path = "/api/v1/product")
public interface ProductClient {
	
	@GetMapping("/findByProductCodes")
	public List<ProductDto> findByProductCodes(@RequestBody ProductCodesDto productCodesDto);
	}


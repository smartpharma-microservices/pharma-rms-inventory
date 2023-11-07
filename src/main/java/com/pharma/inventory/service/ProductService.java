package com.pharma.inventory.service;

import java.util.List;

import com.pharma.inventory.dto.ProductDto;

public interface ProductService {
	public ProductDto findByProductCodes(List<Long> productCode);
}

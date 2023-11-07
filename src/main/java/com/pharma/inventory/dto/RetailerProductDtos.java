package com.pharma.inventory.dto;

import java.util.List;

public class RetailerProductDtos {
	private List<RetailerProductDto> retailerProductDtos;

	public RetailerProductDtos() {
	}

	public RetailerProductDtos(List<RetailerProductDto> retailerProductDtos) {
 		this.retailerProductDtos = retailerProductDtos;
	}

	public List<RetailerProductDto> getRetailerProductDtos() {
		return retailerProductDtos;
	}

	public void setRetailerProductDtos(List<RetailerProductDto> retailerProductDtos) {
		this.retailerProductDtos = retailerProductDtos;
	}

	@Override
	public String toString() {
		return "RetailerProductDtos [retailerProductDtos=" + retailerProductDtos + "]";
	}

}

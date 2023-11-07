package com.pharma.inventory.entity;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "retailer_product", schema = "rms_inventory_schema")
public class RetailerProductEntity {

	@Id
	@Column(name = "retailer_product_uid")
	private UUID retailerProductUid;

	@Column(name = "retailer_code")
	private long retailerCode;

	private int quantity;
	private double ptr;
	private double mrp;

	@Column(name = "store_code")
	private long storeCode;

	@Column(name = "product_code")
	private long productCode;

	@Column(name = "expiry_date")
	private LocalDate expiryDate;

	@Column(name = "manufactured_date")
	private LocalDate manufacturedDate;

	@Column(name = "re_order_level")
	private int reOrderLevel;

	@Column(name = "product_name")
	private String productName;

	@JsonManagedReference("stock-retailer_product")
	@OneToMany(mappedBy = "retailerProductEntity", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<StockEntity> stockEntities;

	public RetailerProductEntity() {
	}

	public RetailerProductEntity(UUID retailerProductUid, long retailerCode, int quantity, double ptr, double mrp,
			long storeCode, long productCode, LocalDate expiryDate, LocalDate manufacturedDate, int reOrderLevel,
			String productName, List<StockEntity> stockEntities) {
		this.retailerProductUid = retailerProductUid;
		this.retailerCode = retailerCode;
		this.quantity = quantity;
		this.ptr = ptr;
		this.mrp = mrp;
		this.storeCode = storeCode;
		this.productCode = productCode;
		this.expiryDate = expiryDate;
		this.manufacturedDate = manufacturedDate;
		this.reOrderLevel = reOrderLevel;
		this.productName = productName;
		this.stockEntities = stockEntities;
	}

	public UUID getRetailerProductUid() {
		return retailerProductUid;
	}

	public void setRetailerProductUid(UUID retailerProductUid) {
		this.retailerProductUid = retailerProductUid;
	}

	public long getRetailerCode() {
		return retailerCode;
	}

	public void setRetailerCode(long retailerCode) {
		this.retailerCode = retailerCode;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public double getPtr() {
		return ptr;
	}

	public void setPtr(double ptr) {
		this.ptr = ptr;
	}

	public double getMrp() {
		return mrp;
	}

	public void setMrp(double mrp) {
		this.mrp = mrp;
	}

	public long getStoreCode() {
		return storeCode;
	}

	public void setStoreCode(long storeCode) {
		this.storeCode = storeCode;
	}

	public long getProductCode() {
		return productCode;
	}

	public void setProductCode(long productCode) {
		this.productCode = productCode;
	}

	public LocalDate getExpiryDate() {
		return expiryDate;
	}

	public void setExpiryDate(LocalDate expiryDate) {
		this.expiryDate = expiryDate;
	}

	public LocalDate getManufacturedDate() {
		return manufacturedDate;
	}

	public void setManufacturedDate(LocalDate manufacturedDate) {
		this.manufacturedDate = manufacturedDate;
	}

	public int getReOrderLevel() {
		return reOrderLevel;
	}

	public void setReOrderLevel(int reOrderLevel) {
		this.reOrderLevel = reOrderLevel;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public List<StockEntity> getStockEntities() {
		return stockEntities;
	}

	public void setStockEntities(List<StockEntity> stockEntities) {
		this.stockEntities = stockEntities;
	}

}

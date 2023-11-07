package com.pharma.inventory.entity;

import java.sql.Timestamp;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "stock", schema = "rms_inventory_schema")
public class StockEntity {

	@Id
	@Column(name = "stock_id")
	private long stockId;

	@Column(name = "stock_in")
	private int stockIn;

	@Column(name = "stock_out")
	private int stockOut;

	@Column(name = "last_updated_date")
	private Timestamp lastUpdatedDate;

	@JsonBackReference("stock_retailer_product")
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "retailer_product_uid")
	private RetailerProductEntity retailerProductEntity;

	public StockEntity() {
	}

	public StockEntity(long stockId, int stockIn, int stockOut, Timestamp lastUpdatedDate,
			RetailerProductEntity retailerProductEntity) {
		this.stockId = stockId;
		this.stockIn = stockIn;
		this.stockOut = stockOut;
		this.lastUpdatedDate = lastUpdatedDate;
		this.retailerProductEntity = retailerProductEntity;
	}

	public long getStockId() {
		return stockId;
	}

	public void setStockId(long stockId) {
		this.stockId = stockId;
	}

	public int getStockIn() {
		return stockIn;
	}

	public void setStockIn(int stockIn) {
		this.stockIn = stockIn;
	}

	public int getStockOut() {
		return stockOut;
	}

	public void setStockOut(int stockOut) {
		this.stockOut = stockOut;
	}

	public Timestamp getLastUpdatedDate() {
		return lastUpdatedDate;
	}

	public void setLastUpdatedDate(Timestamp lastUpdatedDate) {
		this.lastUpdatedDate = lastUpdatedDate;
	}

	public RetailerProductEntity getRetailerProductEntity() {
		return retailerProductEntity;
	}

	public void setRetailerProductEntity(RetailerProductEntity retailerProductEntity) {
		this.retailerProductEntity = retailerProductEntity;
	}
}

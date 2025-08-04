package com.loadbookingsystem.entity;

import java.sql.Timestamp;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "loads")
@Data
public class LoadEntity {

	@Id
	@GeneratedValue
	private UUID id;

	@Column(nullable = false)
	private String shipperId;

	private String loadingPoint;
	private String unloadingPoint;
	private Timestamp loadingDate;
	private Timestamp unloadingDate;

	private String productType;
	private String truckType;
	private int noOfTrucks;
	private double weight;

	private String comment;
	private Timestamp datePosted;

	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private LoadStatus status = LoadStatus.POSTED;
}

package com.loadbookingsystem.entity;

import java.sql.Timestamp;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "bookings")
@Data
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class BookingEntity {

	@Id
	@GeneratedValue
	private UUID id;

	@ManyToOne
	@JoinColumn(name = "load_id", nullable = false)
	private LoadEntity load;

	@Column(name = "transporter_id", nullable = false)
	private String transporterId;

	@Column(name = "proposed_rate")
	private double proposedRate;
	private String comment;

	@Column(name = "requested_at")
	private Timestamp requestedAt;

	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private BookingStatus status = BookingStatus.PENDING;
}

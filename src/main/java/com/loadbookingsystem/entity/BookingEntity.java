package com.loadbookingsystem.entity;

import java.security.Timestamp;
import java.util.UUID;

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
public class BookingEntity {

	@Id
	@GeneratedValue
	private UUID id;

	@ManyToOne
	@JoinColumn(name = "load_id", nullable = false)
	private LoadEntity load;

	@Column(nullable = false)
	private String transporterId;

	private double proposedRate;
	private String comment;
	private Timestamp requestedAt;

	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private BookingStatus status = BookingStatus.PENDING;

	// Getters and setters
}

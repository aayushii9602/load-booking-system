package com.loadbookingsystem.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.loadbookingsystem.entity.BookingEntity;
import com.loadbookingsystem.service.BookingServiceImpl;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/booking")
@Tag(name = "Booking Controller", description = "Manage bookings")
public class BookingController {

	@Autowired
	private BookingServiceImpl bookingServiceImpl;

	@PostMapping
	@Operation(summary = "Create a booking", description = "Creates a new booking for a load")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Booking created successfully"),
			@ApiResponse(responseCode = "400", description = "Invalid input"),
			@ApiResponse(responseCode = "500", description = "Internal server error") })
	public ResponseEntity<BookingEntity> createBooking(@RequestBody BookingEntity booking) {
		BookingEntity savedBooking = bookingServiceImpl.createOrUpdateBooking(booking);
		return ResponseEntity.ok(savedBooking);
	}

	@GetMapping("/{id}")
	@Operation(summary = "Get a booking by ID")
	public ResponseEntity<BookingEntity> getBookingById(
			@Parameter(description = "UUID of the booking to retrieve") @PathVariable("id") UUID id) {
		return ResponseEntity.ok(this.bookingServiceImpl.getBookingById(id));
	}

	@GetMapping
	@Operation(summary = "Get all bookings")
	public ResponseEntity<List<BookingEntity>> getAllBookings() {
		return ResponseEntity.ok(bookingServiceImpl.getAllBookings());
	}

	@PutMapping("/{id}")
	@Operation(summary = "Update a booking", description = "Updates a booking with the provided ID and data")
	public ResponseEntity<BookingEntity> updateBooking(
			@Parameter(description = "UUID of the booking to update") @PathVariable("id") UUID id,
			@RequestBody BookingEntity booking) {
		booking.setId(id);
		BookingEntity updatedBooking = bookingServiceImpl.createOrUpdateBooking(booking);
		return ResponseEntity.ok(updatedBooking);
	}

	@DeleteMapping("/{id}")
	@Operation(summary = "Delete a booking")
	public ResponseEntity<Void> deleteBooking(
			@Parameter(description = "UUID of the booking to delete") @PathVariable("id") UUID id) {
		this.bookingServiceImpl.deleteBooking(id);
		return ResponseEntity.noContent().build();
	}
}
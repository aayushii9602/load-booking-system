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

@RestController
@RequestMapping("/booking")
public class BookingController {
	@Autowired
	private BookingServiceImpl bookingServiceImpl;

	@PostMapping
	public ResponseEntity<BookingEntity> createBooking(@RequestBody BookingEntity booking) {
		BookingEntity savedBooking = bookingServiceImpl.createOrUpdateBooking(booking);
		return ResponseEntity.ok(savedBooking);
	}

	@GetMapping("/{id}")
	public ResponseEntity<BookingEntity> getBookingById(@PathVariable("id") UUID id) {
		return ResponseEntity.ok(this.bookingServiceImpl.getBookingById(id));
	}

	@GetMapping
	public ResponseEntity<List<BookingEntity>> getAllBookings() {
		return ResponseEntity.ok(bookingServiceImpl.getAllBookings());
	}

	@PutMapping("/{id}")
	public ResponseEntity<BookingEntity> updateBooking(@PathVariable("id") UUID id,
			@RequestBody BookingEntity booking) {
		booking.setId(id);
		BookingEntity updatedBooking = bookingServiceImpl.createOrUpdateBooking(booking);
		return ResponseEntity.ok(updatedBooking);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteBooking(@PathVariable("id") UUID id) {
		this.bookingServiceImpl.deleteBooking(id);
		return ResponseEntity.noContent().build();
	}
}

package com.loadbookingsystem.service;

import java.util.List;
import java.util.UUID;

import com.loadbookingsystem.entity.BookingEntity;

public interface BookingService {

	BookingEntity createOrUpdateBooking(BookingEntity booking);

	BookingEntity getBookingById(UUID id);

	List<BookingEntity> getAllBookings();

	void deleteBooking(UUID id);
}

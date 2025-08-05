package com.loadbookingsystem.service;

import java.sql.Timestamp;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.loadbookingsystem.entity.BookingEntity;
import com.loadbookingsystem.entity.BookingStatus;
import com.loadbookingsystem.entity.LoadEntity;
import com.loadbookingsystem.entity.LoadStatus;
import com.loadbookingsystem.repository.BookingRepository;
import com.loadbookingsystem.repository.LoadRepository;

@Service
public class BookingServiceImpl implements BookingService {

	@Autowired
	private BookingRepository bookingRepository;
	@Autowired
	private LoadRepository loadRepository;

	@Override
	public BookingEntity createOrUpdateBooking(BookingEntity booking) {
		// Check if the booking is new (id is null) or doesn't exist in the database yet
		if (booking.getId() == null || !this.bookingRepository.existsById(booking.getId())) {

			// Get the LoadEntity associated with this booking
			LoadEntity load = booking.getLoad();

			// Validate that the booking is linked to a valid Load (non-null and has an ID)
			if (load == null || load.getId() == null) {
				throw new RuntimeException("Booking must be associated with a valid Load.");
			}

			// Retrieve the LoadEntity from the database using its ID
			LoadEntity attachedLoad = loadRepository.findById(load.getId())
					.orElseThrow(() -> new RuntimeException("Load not found."));

			// Check if the Load has been CANCELLED — if yes, disallow creating a booking
			if (attachedLoad.getStatus() == LoadStatus.CANCELLED) {
				throw new RuntimeException("Cannot create booking: Load is CANCELLED.");
			}

			// Set the booking status to PENDING as this is a new booking request
			booking.setStatus(BookingStatus.PENDING);

			// Set the current timestamp as the requestedAt time for this booking
			booking.setRequestedAt(new Timestamp(System.currentTimeMillis()));

			// Update the Load's status to BOOKED as it is now associated with this booking
			attachedLoad.setStatus(LoadStatus.BOOKED);

			// Save the updated LoadEntity back to the database
			loadRepository.save(attachedLoad);
		}

		// Save the booking (either new or updated) to the database and return the saved
		// entity
		return this.bookingRepository.save(booking);

	}

	@Override
	public BookingEntity getBookingById(UUID id) {
		return this.bookingRepository.getById(id);
	}

	@Override
	public List<BookingEntity> getAllBookings() {
		return this.bookingRepository.findAll();
	}

	@Override
	public void deleteBooking(UUID id) {
		BookingEntity booking = this.bookingRepository.getById(id);
		LoadEntity load = booking.getLoad();
		if (load == null || !loadRepository.existsById(load.getId())) {
			throw new RuntimeException("Cannot delete booking: Load not found or null.");
		}

		LoadEntity attachedLoad = loadRepository.findById(load.getId()).orElseThrow();
		attachedLoad.setStatus(LoadStatus.CANCELLED);
		loadRepository.save(attachedLoad);
		this.bookingRepository.deleteById(id);

	}

}

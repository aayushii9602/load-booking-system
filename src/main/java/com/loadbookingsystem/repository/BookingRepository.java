package com.loadbookingsystem.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.loadbookingsystem.entity.BookingEntity;
import com.loadbookingsystem.entity.LoadEntity;

@Repository
public interface BookingRepository extends JpaRepository<BookingEntity, UUID> {
	List<BookingEntity> findByLoad(LoadEntity load);

}

package com.loadbookingsystem.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.loadbookingsystem.entity.LoadEntity;

@Repository
public interface LoadRepository extends JpaRepository<LoadEntity, UUID> {

}

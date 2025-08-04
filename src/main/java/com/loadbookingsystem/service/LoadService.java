package com.loadbookingsystem.service;

import java.util.List;
import java.util.UUID;

import com.loadbookingsystem.entity.LoadEntity;

public interface LoadService {
	LoadEntity createLoad(LoadEntity load);

	LoadEntity getLoadById(UUID id);

	List<LoadEntity> getAllLoads();

	void deleteLoad(UUID id);
	
	LoadEntity updateLoad(UUID id, LoadEntity updatedLoad);

}
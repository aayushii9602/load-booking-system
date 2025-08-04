package com.loadbookingsystem.service;

import java.sql.Timestamp;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.loadbookingsystem.entity.LoadEntity;
import com.loadbookingsystem.entity.LoadStatus;
import com.loadbookingsystem.repository.LoadRepository;

@Service
public class LoadServiceImpl implements LoadService {

	@Autowired
	private LoadRepository loadRepository;

	@Override
	public LoadEntity createLoad(LoadEntity load) {
		load.setStatus(LoadStatus.POSTED);
		load.setDatePosted(new Timestamp(System.currentTimeMillis()));
		return this.loadRepository.save(load);
	}

	@Override
	public LoadEntity getLoadById(UUID id) {
		return this.loadRepository.getById(id);
	}

	@Override
	public List<LoadEntity> getAllLoads() {
		return this.loadRepository.findAll();
	}

	@Override
	public void deleteLoad(UUID id) {
		this.loadRepository.deleteById(id);
	}

	@Override
	public LoadEntity updateLoad(UUID id, LoadEntity updatedLoad) {
		return loadRepository.findById(id).map(existingLoad -> {
			existingLoad.setShipperId(updatedLoad.getShipperId());
			existingLoad.setLoadingPoint(updatedLoad.getLoadingPoint());
			existingLoad.setUnloadingPoint(updatedLoad.getUnloadingPoint());
			existingLoad.setLoadingDate(updatedLoad.getLoadingDate());
			existingLoad.setUnloadingDate(updatedLoad.getUnloadingDate());
			existingLoad.setProductType(updatedLoad.getProductType());
			existingLoad.setTruckType(updatedLoad.getTruckType());
			existingLoad.setNoOfTrucks(updatedLoad.getNoOfTrucks());
			existingLoad.setWeight(updatedLoad.getWeight());
			existingLoad.setComment(updatedLoad.getComment());
			// Don't update status directly unless needed
			return loadRepository.save(existingLoad);
		}).orElseThrow(() -> new RuntimeException("Load not found with ID: " + id));
	}

}

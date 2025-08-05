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
	public LoadEntity createOrUpdateLoad(LoadEntity load) {
		if (load.getId() == null || !loadRepository.existsById(load.getId())) {
			load.setStatus(LoadStatus.POSTED);
			load.setDatePosted(new Timestamp(System.currentTimeMillis()));
		}
		return this.loadRepository.save(load);
	}

	@Override
	public LoadEntity getLoadById(UUID id) {
		return loadRepository.findById(id).orElseThrow(() -> new RuntimeException("Load not found"));
	}

	@Override
	public List<LoadEntity> getAllLoads() {
		return this.loadRepository.findAll();
	}

	@Override
	public void deleteLoad(UUID id) {
		this.loadRepository.deleteById(id);
	}

}

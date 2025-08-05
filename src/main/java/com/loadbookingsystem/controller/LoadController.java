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

import com.loadbookingsystem.entity.LoadEntity;
import com.loadbookingsystem.service.LoadServiceImpl;

@RestController
@RequestMapping("/load")
public class LoadController {
	@Autowired
	private LoadServiceImpl loadServiceImpl;

	@PostMapping
	public LoadEntity createLoad(@RequestBody LoadEntity load) {
		return this.loadServiceImpl.createOrUpdateLoad(load);
	}

	@GetMapping("/{id}")
	public LoadEntity getLoadById(@PathVariable("id") UUID id) {
		return this.loadServiceImpl.getLoadById(id);
	}

	@GetMapping
	public List<LoadEntity> getAllLoads() {
		return this.loadServiceImpl.getAllLoads();
	}

	@PutMapping("/{id}")
	public ResponseEntity<LoadEntity> updateLoad(@RequestBody LoadEntity updatedLoad) {
		LoadEntity result = this.loadServiceImpl.createOrUpdateLoad(updatedLoad);
		return ResponseEntity.ok(result);
	}

	@DeleteMapping("/{id}")
	public void deleteLoad(@PathVariable UUID id) {
		this.loadServiceImpl.deleteLoad(id);
	}

}

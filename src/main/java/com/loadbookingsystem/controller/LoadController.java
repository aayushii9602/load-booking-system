package com.loadbookingsystem.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.loadbookingsystem.entity.LoadEntity;
import com.loadbookingsystem.service.LoadServiceImpl;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/load")
@Tag(name = "Load Controller", description = "APIs for managing loads")
public class LoadController {

	@Autowired
	private LoadServiceImpl loadServiceImpl;

	@PostMapping
	@Operation(summary = "Create a new load")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Load created successfully"),
			@ApiResponse(responseCode = "400", description = "Invalid load data") })
	public LoadEntity createLoad(@RequestBody LoadEntity load) {
		return this.loadServiceImpl.createOrUpdateLoad(load);
	}

	@GetMapping("/{id}")
	@Operation(summary = "Get a load by ID")
	public LoadEntity getLoadById(
			@Parameter(description = "UUID of the load to retrieve") @PathVariable("id") UUID id) {
		return this.loadServiceImpl.getLoadById(id);
	}

	@GetMapping
	@Operation(summary = "Get all loads")
	public List<LoadEntity> getAllLoads() {
		return this.loadServiceImpl.getAllLoads();
	}

	@PutMapping("/{id}")
	@Operation(summary = "Update an existing load")
	public ResponseEntity<LoadEntity> updateLoad(
			@Parameter(description = "UUID of the load to update") @PathVariable("id") UUID id,
			@RequestBody LoadEntity updatedLoad) {
		updatedLoad.setId(id);
		LoadEntity result = this.loadServiceImpl.createOrUpdateLoad(updatedLoad);
		return ResponseEntity.ok(result);
	}

	@DeleteMapping("/{id}")
	@Operation(summary = "Delete a load by ID")
	public void deleteLoad(@Parameter(description = "UUID of the load to delete") @PathVariable UUID id) {
		this.loadServiceImpl.deleteLoad(id);
	}
}

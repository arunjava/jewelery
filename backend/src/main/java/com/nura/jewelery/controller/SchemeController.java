package com.nura.jewelery.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nura.jewelery.dto.SchemeDTO;
import com.nura.jewelery.entity.Scheme;
import com.nura.jewelery.mapper.SchemeMapper;
import com.nura.jewelery.service.SchemeService;
import com.nura.jewelery.utils.ServiceResponse;
import com.nura.jewelery.utils.ServiceResponseWrapper;

@RestController
@RequestMapping("/api/v1")
public class SchemeController {

	@Autowired
	private SchemeService schemeService;

	@Autowired
	private SchemeMapper schemeMapper;

	@PostMapping("/scheme")
	public ResponseEntity<ServiceResponse<SchemeDTO>> saveScheme(@RequestBody SchemeDTO scheme) {
		return ResponseEntity.ok(new ServiceResponseWrapper<SchemeDTO>().wrapServiceResponse(
				schemeMapper.domainToDTO(schemeService.saveScheme(schemeMapper.dtoToDomain(scheme))),
				HttpStatus.CREATED.getReasonPhrase(), HttpStatus.CREATED.value()));
	}

	@GetMapping("/scheme/{id}")
	public ResponseEntity<ServiceResponse<SchemeDTO>> getSchemeById(@PathVariable long id) {
		Optional<Scheme> scheme = schemeService.getSchemeBsdOnID(id);
		if (scheme.isPresent()) {
			SchemeDTO schemeDTO = schemeMapper.domainToDTO(scheme.get());
			return ResponseEntity.ok(new ServiceResponseWrapper<SchemeDTO>().wrapServiceResponse(schemeDTO,
					HttpStatus.FOUND.getReasonPhrase(), HttpStatus.FOUND.value()));
		}

		return ResponseEntity.ok(new ServiceResponseWrapper<SchemeDTO>().wrapServiceResponse(null,
				HttpStatus.NOT_FOUND.getReasonPhrase(), HttpStatus.NOT_FOUND.value()));
	}

	@GetMapping("/scheme/active")
	public ResponseEntity<ServiceResponse<List<SchemeDTO>>> getActiveSchemes() {
		List<Scheme> activeSchemes = schemeService.getActiveSchemes();
		if (!activeSchemes.isEmpty()) {
			schemeMapper.domainToDTO(activeSchemes);

			return ResponseEntity.ok(new ServiceResponseWrapper<List<SchemeDTO>>().wrapServiceResponse(
					schemeMapper.domainToDTO(activeSchemes), HttpStatus.FOUND.getReasonPhrase(),
					HttpStatus.FOUND.value()));
		}

		return ResponseEntity.ok(new ServiceResponseWrapper<List<SchemeDTO>>().wrapServiceResponse(
				schemeMapper.domainToDTO(activeSchemes), HttpStatus.NOT_FOUND.getReasonPhrase(),
				HttpStatus.NOT_FOUND.value()));
	}
}

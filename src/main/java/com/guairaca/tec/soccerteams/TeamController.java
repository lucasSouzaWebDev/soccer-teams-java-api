package com.guairaca.tec.soccerteams;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.validation.FieldError;

@RestController
@RequestMapping(path = "/teams")
public class TeamController {

	@Autowired
	private TeamRepository repository;

	@GetMapping()
	public Page<Team> index(Pageable pageable, @RequestParam(required = false) String name) {
		Specification<Team> specification = new Specification<Team>() {
			@Override
			public Predicate toPredicate(
				Root<Team> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder
			) {
				List<Predicate> predicates = new ArrayList<Predicate>();
				if (name != null) {
					predicates.add(criteriaBuilder.like(root.get("name"), "%" + name + "%"));
				}
				return criteriaBuilder.and(predicates.toArray(
					new Predicate[predicates.size()]
				));
			}
		};

		return name != null ? repository.findAll(specification, pageable) : repository.findAll(pageable);
	}

	@GetMapping("/{id}")
	public Optional<Team> show(@PathVariable Long id) {
		return repository.findById(id);
	}

	@PostMapping()
	public ResponseEntity<Team> store(@Valid @RequestBody Team team) {
		team = repository.save(team);
		return ResponseEntity.status(HttpStatus.CREATED).body(team);
	}

	@PutMapping("/{id}")
	public ResponseEntity<Team> update(@Valid @PathVariable Long id, @RequestBody Team team) {
		Optional<Team> teamFound = repository.findById(id);

		if (teamFound.isPresent()) {
			team.setId(id);
			team = repository.save(team);
			return ResponseEntity.ok(team);
		}
		return ResponseEntity.notFound().build();
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Team> destroy(@PathVariable Long id) {
		Optional<Team> teamFound = repository.findById(id);

		if (teamFound.isPresent()) {
			repository.deleteById(id);
		}
		return ResponseEntity.notFound().build();
	}

	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public Map<String, String> prepareValidationErrors(
		MethodArgumentNotValidException exception
	){
		Map<String, String> errors = new HashMap<>();
		exception.getBindingResult().getAllErrors().forEach((error) -> {
			String fieldName = ((FieldError) error).getField();
			String message = error.getDefaultMessage();
			errors.put(fieldName, message);
		});
		return errors;
	}
}

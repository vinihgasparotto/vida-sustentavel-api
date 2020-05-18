package br.edu.up.vidasustentavel.resource;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.edu.up.vidasustentavel.model.User;
import br.edu.up.vidasustentavel.repository.UserRepository;
import br.edu.up.vidasustentavel.service.UserService;

@RestController
@RequestMapping("/users")
public class UserResource {
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	UserService userService;
	
	@GetMapping
	public List<User> get() {
		return userRepository.findAll();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<User> getById(@PathVariable int id) {
		Optional<User> user = userRepository.findById(id);
		return user.isPresent() ? ResponseEntity.ok(user.get()) : ResponseEntity.notFound().build();
	}
	
	@PostMapping
	public ResponseEntity<User> create(@Valid @RequestBody User user, HttpServletResponse response) {
		User newUser = userRepository.save(user);
		return ResponseEntity.status(HttpStatus.CREATED).body(newUser);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<User> update(@PathVariable int id, @Valid @RequestBody User user) {
		User userUpdate = userService.update(id, user);
		return ResponseEntity.ok(userUpdate);
	}
	
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable int id) {
		userRepository.deleteById(id);
	}

}

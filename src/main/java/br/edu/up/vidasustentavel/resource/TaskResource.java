package br.edu.up.vidasustentavel.resource;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
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

import br.edu.up.vidasustentavel.model.Task;
import br.edu.up.vidasustentavel.model.UserTask;
import br.edu.up.vidasustentavel.repository.TaskRepository;
import br.edu.up.vidasustentavel.repository.UserRepository;
import br.edu.up.vidasustentavel.repository.UserTaskRepository;
import br.edu.up.vidasustentavel.service.TaskService;

@RestController
@RequestMapping("/tasks")
public class TaskResource {
	
	@Autowired
	TaskRepository taskRepository;
	
	@Autowired
	UserTaskRepository userTaskRepository;
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	TaskService taskService;
	
	@GetMapping
	public List<Task> get() {
		return taskRepository.findAll();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Task> getById(@PathVariable int id) {
		Optional<Task> user = taskRepository.findById(id);
		return user.isPresent() ? ResponseEntity.ok(user.get()) : ResponseEntity.notFound().build();
	}
	
	@GetMapping("/get_not_started_by_user/{userId}")
	public List<Task> getNotStartedTasksByUser(@PathVariable int userId) {
		UserTask ut = new UserTask();
		ut.setIdUser(userRepository.getOne(userId));
		Example<UserTask> userTaskExample = Example.of(ut);
		List<UserTask> userTasks = userTaskRepository.findAll(userTaskExample);
		List<Task> tasks = new ArrayList<Task>();
		for (Task t : taskRepository.findAll()) {
			boolean found = false;
			for (UserTask userTask : userTasks) {
				if (userTask.getIdTask().getId() == t.getId()) {
					found = true;
				}
			}
			if (!found) {
				tasks.add(t);
			}
		}
		return tasks;
	}
	
	@PostMapping
	public ResponseEntity<Task> create(@Valid @RequestBody Task task, HttpServletResponse response) {
		Task newUser = taskRepository.save(task);
		return ResponseEntity.status(HttpStatus.CREATED).body(newUser);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Task> update(@PathVariable int id, @Valid @RequestBody Task task) {
		Task taskUpdate = taskService.update(id, task);
		return ResponseEntity.ok(taskUpdate);
	}
	
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable int id) {
		taskRepository.deleteById(id);
	}
	
	

}

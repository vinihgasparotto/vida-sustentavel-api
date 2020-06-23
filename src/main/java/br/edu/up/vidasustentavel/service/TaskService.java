package br.edu.up.vidasustentavel.service;

import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import br.edu.up.vidasustentavel.model.Task;
import br.edu.up.vidasustentavel.repository.TaskRepository;

@Service
public class TaskService {
	
	@Autowired
	TaskRepository taskRepository;
	
	public Task update(int id, Task task) {
		Task taskUpdate = getTaskById(id);
		BeanUtils.copyProperties(task, taskUpdate, "id");
		return taskRepository.save(taskUpdate);
	}
	
	private Task getTaskById(int id) {
		Optional<Task> user = taskRepository.findById(id);
		if (!user.isPresent()) {
			throw new EmptyResultDataAccessException(1);
		}
		return user.get();
	}

}

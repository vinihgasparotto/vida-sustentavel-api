package br.edu.up.vidasustentavel.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.edu.up.vidasustentavel.model.Task;

public interface TaskRepository extends JpaRepository<Task, Integer> {

}

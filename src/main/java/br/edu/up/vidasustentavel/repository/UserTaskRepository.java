package br.edu.up.vidasustentavel.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.edu.up.vidasustentavel.model.UserTask;

public interface UserTaskRepository extends JpaRepository<UserTask, Integer> {

}

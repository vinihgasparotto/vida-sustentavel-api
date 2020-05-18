package br.edu.up.vidasustentavel.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.edu.up.vidasustentavel.model.User;

public interface UserRepository extends JpaRepository<User, Integer> {

}

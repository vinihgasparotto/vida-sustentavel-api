package br.edu.up.vidasustentavel.service;

import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import br.edu.up.vidasustentavel.model.User;
import br.edu.up.vidasustentavel.repository.UserRepository;



@Service
public class UserService {

	@Autowired
	UserRepository userRepository;
	
	public Optional<User> authenticate(Example<User> user) {
		return userRepository.findOne(user);
	}
	
	public User update(int id, User user) {
		User userUpdate = getUserById(id);
		BeanUtils.copyProperties(user, userUpdate, "id");
		return userRepository.save(userUpdate);
	}
	
	//public User create(User user) {
		//List<UserSkill> userSkills = new List<UserSkill>();
		//for (Skill skill : )
	//}
	
	private User getUserById(int id) {
		Optional<User> user = userRepository.findById(id);
		if (!user.isPresent()) {
			throw new EmptyResultDataAccessException(1);
		}
		return user.get();
	}
	
}

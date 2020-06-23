package br.edu.up.vidasustentavel.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import br.edu.up.vidasustentavel.model.Reward;
import br.edu.up.vidasustentavel.model.Skill;
import br.edu.up.vidasustentavel.model.Task;
import br.edu.up.vidasustentavel.model.User;
import br.edu.up.vidasustentavel.model.UserSkill;
import br.edu.up.vidasustentavel.model.UserTask;
import br.edu.up.vidasustentavel.repository.SkillRepository;
import br.edu.up.vidasustentavel.repository.TaskRepository;
import br.edu.up.vidasustentavel.repository.UserRepository;
import br.edu.up.vidasustentavel.repository.UserSkillRepository;
import br.edu.up.vidasustentavel.repository.UserTaskRepository;



@Service
public class UserService {

	@Autowired
	UserRepository userRepository;
	
	@Autowired
	TaskRepository taskRepository;
	
	@Autowired
	SkillRepository skillRepository;
	
	@Autowired
	UserSkillRepository userSkillRepository;
	
	@Autowired
	UserTaskRepository userTaskRepository;
	
	public Optional<User> authenticate(Example<User> user) {
		return userRepository.findOne(user);
	}
	
	public User update(int id, User user) {
		User userUpdate = getUserById(id);
		BeanUtils.copyProperties(user, userUpdate, "id");
		return userRepository.save(userUpdate);
	}
	
	public User create(User user) {
		List<UserSkill> userSkills = new ArrayList<UserSkill>();
		for (Skill skill : skillRepository.findAll()) {
			userSkillRepository.save(new UserSkill(skill, user));
		}
		user.setUserSkillList(userSkills);
		return user;
	}
	
	public boolean startTask(int userId, int taskId) {
		Optional<User> user = userRepository.findById(userId);
		Optional<Task> task = taskRepository.findById(taskId);
		if (user.isPresent() && task.isPresent()) {
			UserTask userTask = new UserTask(user.get(), task.get(), 'a');
			userTaskRepository.save(userTask);
			return true;
		}
		return false;
	}
	
	public boolean updateTaskStatus(int userId, int taskId, char status) {
		Optional<User> user = userRepository.findById(userId);
		Optional<Task> task = taskRepository.findById(taskId);
		if (user.isPresent() && task.isPresent()) {
			UserTask ut = new UserTask();
			ut.setIdUser(user.get());
			ut.setIdTask(task.get());
			Example<UserTask> userTaskExample = Example.of(ut);
			Optional<UserTask> userTaskOpt = userTaskRepository.findOne(userTaskExample);
			if (userTaskOpt.isPresent()) {
				UserTask userTask = userTaskOpt.get();
				userTask.setStatus(status);
				userTaskRepository.save(userTask);
				if (status == 'c') {
					List<UserSkill> userSkills = new ArrayList<UserSkill>();
					for (Reward r : task.get().getRewardList()) {
						for (UserSkill us : user.get().getUserSkillList()) {
							if (r.getIdSkill().getId() == us.getSkill().getId()) {
								us.setExperience(us.getExperience() + r.getXpReward());
								userSkills.add(us);
							}
						}
					}
					userSkillRepository.saveAll(userSkills);
				}
				return true;
			}
		}
		return false;
	}
	
	private User getUserById(int id) {
		Optional<User> user = userRepository.findById(id);
		if (!user.isPresent()) {
			throw new EmptyResultDataAccessException(1);
		}
		return user.get();
	}
	
}

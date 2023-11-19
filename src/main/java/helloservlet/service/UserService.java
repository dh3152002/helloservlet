package helloservlet.service;

import java.util.List;

import helloservlet.entity.UserEntity;
import helloservlet.repository.UserRepository;

public class UserService {
	private UserRepository userRepository=new UserRepository();
	
	public boolean insert(String email,String password,String fullname,String avatar,int role_id) {
		int count = userRepository.insert(email, password, fullname, avatar, role_id);
		return count>0;
	}
	
	public List<UserEntity> findAllJoinRole(){
		return userRepository.findAllJoinRole();
	}
	
	public UserEntity findById(int id) {
		return userRepository.findById(id);
	}
	
	public boolean update(int id,String email,String password,String fullname,String avatar,int role_id) {
		int count=userRepository.update(id, email,password, fullname, avatar, role_id);
		return count>0;
	}
	
	public boolean delete(int id) {
		int count=userRepository.delete(id);
		return count>0;
	}
}

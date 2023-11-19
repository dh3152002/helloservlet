package helloservlet.service;

import java.util.List;

import helloservlet.entity.RoleEntity;
import helloservlet.repository.RoleRepository;

public class RoleService {
	private RoleRepository roleRepository =new RoleRepository();
	
	public boolean deleteById(int id) {
		int count=roleRepository.deleteById(id);
		return count>0;
	}
	
	public List<RoleEntity> findAll(){
		return roleRepository.findAll();
	}
	
	public boolean insert(String name,String description) {
		int count=roleRepository.insert(name, description);
		return count>0;
	}
	
	public boolean update(int id,String name,String description) {
		int count=roleRepository.update(id,name, description);
		return count>0;
	}
	
	public RoleEntity findById(int id){
		return roleRepository.findById(id);
	}
}

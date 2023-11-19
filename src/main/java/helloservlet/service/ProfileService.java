package helloservlet.service;

import java.util.List;

import helloservlet.entity.StatusEntity;
import helloservlet.entity.TaskEntity;
import helloservlet.repository.TaskRepository;

public class ProfileService {
	private TaskRepository taskRepository=new TaskRepository();
	public List<TaskEntity> findByUserIdJoinUserAndJob(int user_id) {
		return taskRepository.findByUserIdJoinUserAndJob(user_id);
	}
	
	public List<StatusEntity> percentStatusByUserIdJoinStatusGroupByStatusId(int user_id) {
		List<StatusEntity> list= taskRepository.percentStatusByUserIdJoinStatusGroupByStatusId(user_id);
		return list;
	}
	
	public boolean updateStatusId(int id,int status_id) {
		int count=taskRepository.updateStatusId(id, status_id);
		return count>0;
	}
}

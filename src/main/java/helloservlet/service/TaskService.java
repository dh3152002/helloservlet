package helloservlet.service;

import java.sql.Date;
import java.util.List;

import helloservlet.entity.TaskEntity;
import helloservlet.repository.TaskRepository;
import helloservlet.util.ConvertDate;

public class TaskService {
	private TaskRepository taskRepository=new TaskRepository();
	
	public List<TaskEntity> findAllJoinUserAndJob() {
		return taskRepository.findAllJoinUserAndJob();
	}
	
	public boolean insert(String name,String start_date_string,String end_date_string,int user_id,int job_id) {
		Date start_date=ConvertDate.convertStringToDateSql(start_date_string);
		Date end_date=ConvertDate.convertStringToDateSql(end_date_string);
		int count=taskRepository.insert(name, start_date, end_date, user_id, job_id);
		return count>0;
	}
	
	public TaskEntity findByIdJoinJob(int id) {
		return taskRepository.findByIdJoinJob(id);
	}
	
	public boolean update(int id,String name,String start_date_string,String end_date_string,int user_id,int job_id) {
		Date start_date=ConvertDate.convertStringToDateSql(start_date_string);
		Date end_date=ConvertDate.convertStringToDateSql(end_date_string);
		int count=taskRepository.update(id, name, start_date, end_date, user_id, job_id);
		return count>0;
	}
	
	public boolean delete(int id) {
		int count=taskRepository.delete(id);
		return count>0;
	}
}

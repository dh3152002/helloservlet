package helloservlet.service;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import helloservlet.entity.GroupworkEntity;
import helloservlet.repository.GroupworkRepository;
import helloservlet.util.ConvertDate;

public class GroupworkService {
	private GroupworkRepository groupworkRepository=new GroupworkRepository();
	public boolean insert(String name,String startDateString,String endDateString) {
		Date startDate=ConvertDate.convertStringToDateSql(startDateString);
		Date endDate=ConvertDate.convertStringToDateSql(endDateString);
		int count=groupworkRepository.insert(name, startDate, endDate);
		return count>0;
	}
	
	public List<GroupworkEntity> findAll(){
		return groupworkRepository.findAll();
	}
	
	public GroupworkEntity findById(int id) {
		return groupworkRepository.findById(id);
	}
	
	public boolean update(int id,String name,String startDateString,String endDateString) {
		Date startDate=ConvertDate.convertStringToDateSql(startDateString);
		Date endDate=ConvertDate.convertStringToDateSql(endDateString);
		int count=groupworkRepository.update(id, name, startDate, endDate);
		return count>0;
	}
	
	public boolean delete(int id) {
		int count=groupworkRepository.delete(id);
		return count>0;
	}
}

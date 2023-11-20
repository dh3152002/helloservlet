package helloservlet.repository;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import helloservlet.config.MysqlConfig;
import helloservlet.entity.StatusEntity;
import helloservlet.entity.TaskEntity;

public class TaskRepository {
	public List<TaskEntity> findAllJoinUserAndJob() {
		List<TaskEntity> list=new ArrayList<TaskEntity>();
		String query="SELECT t.id,t.name,t.start_date,t.end_date,t.user_id,t.job_id,t.status_id,u.fullname AS user_name,j.name AS job_name,s.name AS status_name \r\n"
				+ "FROM tasks t \r\n"
				+ "JOIN users u ON t.user_id =u.id \r\n"
				+ "JOIN jobs j ON t.job_id=j.id \r\n"
				+ "JOIN status s ON t.status_id =s.id";
		try {
			Connection connection=MysqlConfig.getConnection();
			PreparedStatement preparedStatement=connection.prepareStatement(query);
			ResultSet resultSet=preparedStatement.executeQuery();
			while(resultSet.next()) {
				TaskEntity taskEntity=new TaskEntity();
				taskEntity.setId(resultSet.getInt("id"));
				taskEntity.setStartDate(resultSet.getDate("start_date"));
				taskEntity.setEndDate(resultSet.getDate("end_date"));
				taskEntity.setUserId(resultSet.getInt("user_id"));
				taskEntity.setName(resultSet.getString("name"));
				taskEntity.setStatusId(resultSet.getInt("status_id"));
				taskEntity.setJobId(resultSet.getInt("job_id"));
				taskEntity.setUserName(resultSet.getString("user_name"));
				taskEntity.setJobName(resultSet.getString("job_name"));
				taskEntity.setStatusName(resultSet.getString("status_name"));
				list.add(taskEntity);
			}
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("Loi findAll task: "+e.getLocalizedMessage());
		}
		return list;
	}
	
	public List<TaskEntity> findByUserIdJoinUserAndJob(int user_id) {
		List<TaskEntity> list=new ArrayList<TaskEntity>();
		String query="SELECT t.id,t.name,t.user_id,t.status_id,t.job_id,t.start_date,t.end_date,u.fullname AS user_name,j.name AS job_name,s.name AS status_name,u.email \r\n"
				+ "FROM tasks t \r\n"
				+ "JOIN users u ON t.user_id =u.id \r\n"
				+ "JOIN jobs j ON t.job_id=j.id \r\n"
				+ "JOIN status s ON t.status_id =s.id\r\n"
				+ "WHERE t.user_id =?";
		try {
			Connection connection=MysqlConfig.getConnection();
			PreparedStatement preparedStatement=connection.prepareStatement(query);
			preparedStatement.setInt(1, user_id);
			ResultSet resultSet=preparedStatement.executeQuery();
			while(resultSet.next()) {
				TaskEntity taskEntity=new TaskEntity();
				taskEntity.setId(resultSet.getInt("id"));
				taskEntity.setStartDate(resultSet.getDate("start_date"));
				taskEntity.setEndDate(resultSet.getDate("end_date"));
				taskEntity.setUserId(resultSet.getInt("user_id"));
				taskEntity.setName(resultSet.getString("name"));
				taskEntity.setStatusId(resultSet.getInt("status_id"));
				taskEntity.setJobId(resultSet.getInt("job_id"));
				taskEntity.setUserName(resultSet.getString("user_name"));
				taskEntity.setJobName(resultSet.getString("job_name"));
				taskEntity.setStatusName(resultSet.getString("status_name"));
				taskEntity.setEmail(resultSet.getString("email"));
				list.add(taskEntity);
			}
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("Loi findByUserId task: "+e.getLocalizedMessage());
		}
		return list;
	}
	
	public int insert(String name,Date start_date,Date end_date,int user_id,int job_id) {
		int count=0;
		String query="INSERT INTO tasks (name,start_date,end_date,user_id,job_id,status_id) VALUES (?,?,?,?,?,?)";
		try {
			Connection connection=MysqlConfig.getConnection();
			PreparedStatement preparedStatement=connection.prepareStatement(query);
			preparedStatement.setString(1, name);
			preparedStatement.setDate(2, start_date);
			preparedStatement.setDate(3, end_date);
			preparedStatement.setInt(4, user_id);
			preparedStatement.setInt(5, job_id);
			preparedStatement.setInt(6, 1);
			count=preparedStatement.executeUpdate();
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("Loi insert task: "+e.getLocalizedMessage());
		}
		return count;
	}
	
	public TaskEntity findByIdJoinJob(int id) {
		TaskEntity taskEntity=new TaskEntity();
		String query="SELECT t.id,t.name,t.start_date,t.end_date,t.user_id,t.job_id,t.status_id, j.name AS job_name\r\n"
				+ "FROM tasks t JOIN jobs j ON t.job_id =j.id WHERE t.id=?";
		try {
			Connection connection=MysqlConfig.getConnection();
			PreparedStatement preparedStatement=connection.prepareStatement(query);
			preparedStatement.setInt(1, id);
			ResultSet resultSet=preparedStatement.executeQuery();
			while(resultSet.next()) {
				taskEntity.setId(resultSet.getInt("id"));
				taskEntity.setJobId(resultSet.getInt("job_id"));
				taskEntity.setName(resultSet.getString("name"));
				taskEntity.setStartDate(resultSet.getDate("start_date"));
				taskEntity.setEndDate(resultSet.getDate("end_date"));
				taskEntity.setUserId(resultSet.getInt("user_id"));
				taskEntity.setJobName(resultSet.getString("job_name"));
				taskEntity.setStatusId(resultSet.getInt("status_id"));
			}
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("Loi findById task: "+e.getLocalizedMessage());
		}
		return taskEntity;
	}
	
	public int update(int id,String name,Date start_date,Date end_date,int user_id,int job_id) {
		int count =0;
		String query="UPDATE tasks SET name=?,start_date=? ,end_date=? ,user_id=?,job_id=? WHERE id=?";
		try {
			Connection connection=MysqlConfig.getConnection();
			PreparedStatement preparedStatement=connection.prepareStatement(query);
			preparedStatement.setString(1, name);
			preparedStatement.setDate(2, start_date);
			preparedStatement.setDate(3, end_date);
			preparedStatement.setInt(4, user_id);
			preparedStatement.setInt(5, job_id);
			preparedStatement.setInt(6, id);
			count=preparedStatement.executeUpdate();
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("Loi update task: "+e.getLocalizedMessage());
		}
		return count;
	}
	
	public int delete(int id) {
		int count=0;
		String query="DELETE FROM tasks WHERE id=?";
		try {
			Connection connection=MysqlConfig.getConnection();
			PreparedStatement preparedStatement=connection.prepareStatement(query);
			preparedStatement.setInt(1, id);
			count=preparedStatement.executeUpdate();
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("Loi delete task: "+e.getLocalizedMessage());
		}
		return count;
	}
	
	public List<StatusEntity> percentStatusByUserIdJoinStatusGroupByStatusId(int user_id) {
		List<StatusEntity> list=new ArrayList<StatusEntity>();
		String query="SELECT t.status_id ,ROUND(COUNT(*)*100/(SELECT COUNT(*) FROM tasks t2 WHERE user_id=?)) AS percent,s.name AS status_name\r\n"
				+ "FROM tasks t JOIN status s ON t.status_id =s.id \r\n"
				+ "WHERE user_id =?\r\n"
				+ "GROUP BY status_id\r\n"
				+ "ORDER BY t.status_id ";
		try {
			Connection connection=MysqlConfig.getConnection();
			PreparedStatement preparedStatement=connection.prepareStatement(query);
			preparedStatement.setInt(1, user_id);
			preparedStatement.setInt(2, user_id);
			ResultSet resultSet=preparedStatement.executeQuery();
			while(resultSet.next()) {
				StatusEntity statusEntity=new StatusEntity();
				statusEntity.setId(resultSet.getInt("status_id"));
				statusEntity.setName(resultSet.getString("status_name"));
				statusEntity.setPercent(resultSet.getInt("percent"));
				list.add(statusEntity);
			}
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("Loi findTaskByUserIdGroupByStatusId task: "+e.getLocalizedMessage());
		}
		return list;
	}
	
	public int updateStatusId(int id,int status_id) {
		int count =0;
		String query="UPDATE tasks SET status_id=? WHERE id=?";
		try {
			Connection connection=MysqlConfig.getConnection();
			PreparedStatement preparedStatement=connection.prepareStatement(query);
			preparedStatement.setInt(1, status_id);
			preparedStatement.setInt(2, id);
			count=preparedStatement.executeUpdate();
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("Loi updateStatusId task: "+e.getLocalizedMessage());
		}
		return count;
	}
}

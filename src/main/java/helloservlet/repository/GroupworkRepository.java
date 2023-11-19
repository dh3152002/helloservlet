package helloservlet.repository;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import helloservlet.config.MysqlConfig;
import helloservlet.entity.GroupworkEntity;

public class GroupworkRepository {
	public int insert(String name,Date start_date,Date end_date) {
		int count=0;
		String query="INSERT INTO jobs (name,start_date,end_date) VALUES (?,?,?)";
		try {
			Connection connection=MysqlConfig.getConnection();
			PreparedStatement preparedStatement=connection.prepareStatement(query);
			preparedStatement.setString(1, name);
			preparedStatement.setDate(2, start_date);
			preparedStatement.setDate(3, end_date);	
			count=preparedStatement.executeUpdate();
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("Loi insert groupwork: "+e.getLocalizedMessage());
		}
		return count;
	}
	
	public List<GroupworkEntity> findAll() {
		List<GroupworkEntity> list=new ArrayList<GroupworkEntity>();
		String query="SELECT * FROM jobs";
		try {
			Connection connection=MysqlConfig.getConnection();
			PreparedStatement preparedStatement=connection.prepareStatement(query);
			ResultSet resultSet=preparedStatement.executeQuery();
			while(resultSet.next()) {
				GroupworkEntity groupworkEntity=new GroupworkEntity();
				groupworkEntity.setId(resultSet.getInt("id"));
				groupworkEntity.setName(resultSet.getString("name"));
				groupworkEntity.setStartDate(resultSet.getDate("start_date"));
				groupworkEntity.setEndDate(resultSet.getDate("end_date"));
				list.add(groupworkEntity);
			}
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("Loi findAll groupwork: "+e.getLocalizedMessage());
		}
		return list;
	}
	
	public GroupworkEntity findById(int id) {
		GroupworkEntity groupworkEntity=new GroupworkEntity();
		String query="SELECT * FROM jobs WHERE id=?";
		try {
			Connection connection=MysqlConfig.getConnection();
			PreparedStatement preparedStatement=connection.prepareStatement(query);
			preparedStatement.setInt(1, id);
			ResultSet resultSet=preparedStatement.executeQuery();
			while(resultSet.next()) {
				groupworkEntity.setId(resultSet.getInt("id"));
				groupworkEntity.setName(resultSet.getString("name"));
				groupworkEntity.setStartDate(resultSet.getDate("start_date"));
				groupworkEntity.setEndDate(resultSet.getDate("end_date"));
			}
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("Loi findById groupwork: "+e.getLocalizedMessage());
		}
		return groupworkEntity;
	}
	
	public int update(int id, String name,Date start_date,Date end_date) {
		int count=0;
		String query="UPDATE jobs SET name=?,start_date=?,end_date=? WHERE id=?";
		try {
			Connection connection=MysqlConfig.getConnection();
			PreparedStatement preparedStatement=connection.prepareStatement(query);
			preparedStatement.setString(1, name);
			preparedStatement.setDate(2, start_date);
			preparedStatement.setDate(3, end_date);
			preparedStatement.setInt(4, id);
			count=preparedStatement.executeUpdate();
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("Loi update groupwork: "+e.getLocalizedMessage());
		}
		return count;
	}
	
	public int delete(int id) {
		int count =0;
		String query="DELETE FROM jobs WHERE id=?";
		try {
			Connection connection=MysqlConfig.getConnection();
			PreparedStatement preparedStatement=connection.prepareStatement(query);
			preparedStatement.setInt(1, id);
			count=preparedStatement.executeUpdate();
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("Loi delete groupwork: "+e.getLocalizedMessage());
		}
		return count;
	}
}

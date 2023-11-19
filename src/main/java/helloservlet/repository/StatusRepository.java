package helloservlet.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import helloservlet.config.MysqlConfig;
import helloservlet.entity.StatusEntity;

public class StatusRepository {
	public List<StatusEntity> findAll(){
		List<StatusEntity> list=new ArrayList<StatusEntity>();
		String query="SELECT * FROM status";
		try {
			Connection connection=MysqlConfig.getConnection();
			PreparedStatement preparedStatement=connection.prepareStatement(query);
			ResultSet resultSet=preparedStatement.executeQuery();
			while(resultSet.next()) {
				StatusEntity statusEntity=new StatusEntity();
				statusEntity.setId(resultSet.getInt("id"));
				statusEntity.setName(resultSet.getString("name"));
				list.add(statusEntity);
			}
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("Loi findAll: "+e.getLocalizedMessage());
		}
		return list;
	}
}

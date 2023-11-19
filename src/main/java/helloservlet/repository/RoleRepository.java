package helloservlet.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import helloservlet.config.MysqlConfig;
import helloservlet.entity.RoleEntity;

public class RoleRepository {
	public int deleteById(int id) {
		int count=0;
		String query="DELETE FROM roles r WHERE r.id =?";
		try {
			Connection connection=MysqlConfig.getConnection();
			PreparedStatement preparedStatement=connection.prepareStatement(query);
			preparedStatement.setInt(1, id);
			
			count=preparedStatement.executeUpdate();
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("Loi deleteById Role: "+e.getLocalizedMessage());
		}
		
		return count;
	}
	
	public RoleEntity findById(int id) {
		RoleEntity roleEntity=new RoleEntity();
		String query="SELECT * FROM roles r WHERE r.id=?";
		try {
			Connection connection = MysqlConfig.getConnection();
			PreparedStatement preparedStatement=connection.prepareStatement(query);
			preparedStatement.setInt(1, id);
			
			ResultSet result=preparedStatement.executeQuery();
			
			while(result.next()) {
				roleEntity.setId(result.getInt("id"));
				roleEntity.setName(result.getString("name"));
				roleEntity.setDescription(result.getString("description"));
			}
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("Loi findById role: "+e.getLocalizedMessage());
		}
		return roleEntity;
	}
	
	public int update(int id,String name,String description) {
		int count=0;
		String query="UPDATE roles SET name = ?, description = ? WHERE id = ?";
		try {
			Connection connection=MysqlConfig.getConnection();
			PreparedStatement preparedStatement=connection.prepareStatement(query);
			preparedStatement.setString(1, name);
			preparedStatement.setString(2, description);
			preparedStatement.setInt(3, id);
			
			count=preparedStatement.executeUpdate();
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("Loi update role: "+e.getLocalizedMessage());
		}
		return count;
	}
	
	public List<RoleEntity> findAll() {
		List<RoleEntity> list = new ArrayList<RoleEntity>();
		String query = "SELECT * FROM roles r";
		Connection conn = MysqlConfig.getConnection();
		try {
			PreparedStatement preparedStatement = conn.prepareStatement(query);
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				RoleEntity roleEntity = new RoleEntity();
				roleEntity.setId(resultSet.getInt("id"));
				roleEntity.setName(resultSet.getString("name"));
				roleEntity.setDescription(resultSet.getString("description"));
				list.add(roleEntity);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("Loi finAll role: " + e.getLocalizedMessage());
		}

		return list;
	}

	public int insert(String name, String description) {
		// Chuẩn bị câu query
		String query = "INSERT INTO roles (name,description) VALUES (?,?)";
		int count = 0;
		// Kết nối với CSDL
		Connection conn = MysqlConfig.getConnection();
		try {
			// Truyền câu query vào CSDL
			PreparedStatement preparedStatement = conn.prepareStatement(query);
			preparedStatement.setString(1, name);
			preparedStatement.setString(2, description);

			count = preparedStatement.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("Lỗi insert role: " + e.getLocalizedMessage());
		}
		return count;
	}
}

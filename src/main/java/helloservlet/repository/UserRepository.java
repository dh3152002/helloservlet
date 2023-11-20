package helloservlet.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import helloservlet.config.MysqlConfig;
import helloservlet.entity.UserEntity;

/*
 * Chứa tất cả các câu query liên quan đến bảng User
 * 
 * select : đại diện cho find
 * where : đại diện bởi by
 */

public class UserRepository {
	public List<UserEntity> findByEmailAndPassword(String email, String password) {
		// Cách 2:
		String query = "SELECT * FROM users u WHERE u.email = ? AND u.password = ?";

		// Bước 3: Mở kết nối CSDL
		Connection conn = MysqlConfig.getConnection();
		// Tạo listUser để lưu trữ từng dòng query được
		List<UserEntity> listUser = new ArrayList<UserEntity>();
		try {
			// Bước 4: Truyền câu query vào CSDL vừa kết nối thông qua PrepareStatement
			PreparedStatement preparedStatement = conn.prepareStatement(query);
			// Gán giá trị cho tham số dấu ? bên trong câu query
			preparedStatement.setString(1, email);
			preparedStatement.setString(2, password);

			// Bước 5: Thông báo cho CSDL biết và thực thi câu query
			// Có 2 cách thực thi
			// executeQuery: dành cho câu truy vấn là câu SELECT => luôn trả ra ResultSet
			// executeUpdate: tất cả câu truy vấn còn lại ngoài câu SELECT, VD: INSERT,...
			// => luôn trả ra boolean
			ResultSet result = preparedStatement.executeQuery();

			// Bước 6: Duyệt từng dòng dữ liệu query được và gán vào trong listUser
			while (result.next()) {
				UserEntity user = new UserEntity();
				user.setId(result.getInt("id")); // => lấy giá trị của cột id gán vào id của user
				user.setFullname(result.getString("fullname"));
				listUser.add(user);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("Lỗi findByEmailAndPassword: " + e.getLocalizedMessage());
		}
		return listUser;
	}
	
	public int insert(String email,String password,String fullname,String avatar,int role_id) {
		int count=0;
		String query="INSERT INTO users (email, password, fullname, avatar, role_id) VALUES (?,?,?,?,?)";
		try {
			Connection connection=MysqlConfig.getConnection();
			PreparedStatement preparedStatement=connection.prepareStatement(query);
			preparedStatement.setString(1, email);
			preparedStatement.setString(2, password);
			preparedStatement.setString(3, fullname);
			preparedStatement.setString(4, avatar);
			preparedStatement.setInt(5, role_id);
			count=preparedStatement.executeUpdate();
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("Loi insert user: "+e.getLocalizedMessage());
		}
		return count;
	}
	
	public List<UserEntity> findAllJoinRole(){
		List<UserEntity> list=new ArrayList<UserEntity>();
		String query="SELECT u.id,u.email,u.fullname,r.name as role_name FROM users u JOIN roles r ON r.id = u.role_id";
		try {
			Connection connection=MysqlConfig.getConnection();
			PreparedStatement preparedStatement=connection.prepareStatement(query);
			ResultSet resultSet=preparedStatement.executeQuery();
			while(resultSet.next()) {
				UserEntity userEntity=new UserEntity();
				userEntity.setId(resultSet.getInt("id"));
				userEntity.setEmail(resultSet.getString("email"));
				userEntity.setFullname(resultSet.getString("fullname"));
				userEntity.setNameRole(resultSet.getString("role_name"));
				list.add(userEntity);
			}
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("Loi findAll user: "+e.getLocalizedMessage());
		}
		return list;
	}
	
	public UserEntity findById(int id) {
		UserEntity userEntity=new UserEntity();
		String query="SELECT id,email,fullname,avatar,role_id FROM users u WHERE u.id=?";
		try {
			Connection connection=MysqlConfig.getConnection();
			PreparedStatement preparedStatement=connection.prepareStatement(query);
			preparedStatement.setInt(1, id);
			ResultSet resultSet=preparedStatement.executeQuery();
			while(resultSet.next()) {
				userEntity.setId(resultSet.getInt("id"));
				userEntity.setEmail(resultSet.getString("email"));
				userEntity.setFullname(resultSet.getString("fullname"));
				userEntity.setIdRole(resultSet.getInt("role_id"));
				userEntity.setAvatar(resultSet.getString("avatar"));
			}
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("Loi findById user: "+e.getLocalizedMessage());
		}
		
		return userEntity;
	}
	
	public int update(int id,String email,String fullname,String avatar,int role_id) {
		int count=0;
		String query="UPDATE users SET email = ?, fullname = ?, avatar=?, role_id=? WHERE id = ?";
		try {
			Connection connection=MysqlConfig.getConnection();
			PreparedStatement preparedStatement=connection.prepareStatement(query);
			preparedStatement.setString(1, email);
			preparedStatement.setString(2, fullname);
			preparedStatement.setString(3, avatar);
			preparedStatement.setInt(4, role_id);
			preparedStatement.setInt(5, id);
			count=preparedStatement.executeUpdate();
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("Loi update user: "+e.getLocalizedMessage());
		}
		return count;
	}
	
	public int delete(int id) {
		int count=0;
		String query="DELETE FROM users u WHERE u.id =?";
		try {
			Connection connection=MysqlConfig.getConnection();
			PreparedStatement preparedStatement=connection.prepareStatement(query);
			preparedStatement.setInt(1, id);
			count=preparedStatement.executeUpdate();
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("Loi delete user: "+e.getLocalizedMessage());
		}
		
		return count;
	}
}

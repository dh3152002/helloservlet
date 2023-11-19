package helloservlet.config;

import java.sql.Connection;
import java.sql.DriverManager;

/*
 * Class dùng để khai báo thông tin cấu hình tạo kết nối tới CSDL
 */
public class MysqlConfig {
	
	// Hàm dùng để kết nối tới CSDL
	public static Connection getConnection() {
		
		try {
			// Khai báo Driver sử dụng cho JDBC ( từ khóa tên driver class.forname )
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			// Khai báo thông tin CSDL mà JDBC sẽ kết nối tới ( mysql url jdbc )
			return DriverManager.getConnection("jdbc:mysql://localhost:3307/crm_app", "root", "admin123");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("Lỗi kết nối CSDL "+e.getLocalizedMessage());
		}
		return null;
	}
}

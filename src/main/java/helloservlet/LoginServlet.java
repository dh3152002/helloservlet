//package helloservlet;
//
//import java.io.IOException;
//import java.sql.Connection;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.util.ArrayList;
//import java.util.List;
//
//import javax.servlet.ServletException;
//import javax.servlet.annotation.WebServlet;
//import javax.servlet.http.Cookie;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import javax.servlet.http.HttpSession;
//
//import helloservlet.config.MysqlConfig;
//import helloservlet.entity.UserEntity;
//
///* Mô hình strategy: 
// * controller: chỉ dùng chứa các class khai báo đường dẫn và nhận tham số (không xử lý logic code)
// * service: chứa các class để xử lý logic code cho các controller tương ứng
// * repository: chứa các class trả ra dữ liệu của các câu query liên quan đến các bảng trong database.
// * Tức là chỉ thực thi câu query và trả ra kết quả câu query (không xử lý logic code)
// * 
// * controller chỉ được goi service, service gọi repository
// */
//
//@WebServlet(name = "loginServlet", urlPatterns = { "/login" })
//public class LoginServlet extends HttpServlet {
//
//	@Override
//	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//		// TODO Auto-generated method stub
////		Cookie cook = new Cookie("username", "abcdef");
////		cook.setMaxAge(5 * 60);
////
////		resp.addCookie(cook);
//
//		// Lấy danh sách cookie từ request của người dùng
//		Cookie[] listCookies = req.getCookies();
//		String email = "";
//		String password = "";
//		// Duyệt qua từng cookie bên trong list
//		if (listCookies != null && listCookies.length > 0) {
//			for (Cookie cookie : listCookies) {
//				// Kiểm tra tên cookie
//				if (cookie.getName().equals("email")) {
//					// Lấy giá trị
//					email = cookie.getValue();
//				}
//				if (cookie.getName().equals("password")) {
//					// Lấy giá trị
//					password = cookie.getValue();
//				}
//			}
//		}
//		req.setAttribute("email", email);
//		req.setAttribute("password", password);
//
//		// Hiển thị nội dung file html khi người dùng gọi link /login
//		req.getRequestDispatcher("login.jsp").forward(req, resp);
//	}
//
//	@Override
//	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//		// TODO Auto-generated method stub
//
//		// Bước 1: Nhận tham số người dùng truyền lên
//		String email = req.getParameter("email");
//		String password = req.getParameter("password");
//		String remember = req.getParameter("remember");
//
//		// Bước 2: Chuẩn bị câu query ( truy vấn )
//		// Cách 1:
////		String query = "SELECT * FROM users u WHERE u.email = '"+email+"' AND u.password = '"+password+"'";
//
//		// Cách 2:
//		String query = "SELECT * FROM users u WHERE u.email = ? AND u.password = ?";
//
//		// Bước 3: Mở kết nối CSDL
//		Connection conn = MysqlConfig.getConnection();
//
//		try {
//			// Bước 4: Truyền câu query vào CSDL vừa kết nối thông qua PrepareStatement
//			PreparedStatement preparedStatement = conn.prepareStatement(query);
//			// Gán giá trị cho tham số dấu ? bên trong câu query
//			preparedStatement.setString(1, email);
//			preparedStatement.setString(2, password);
//
//			// Bước 5: Thông báo cho CSDL biết và thực thi câu query
//			// Có 2 cách thực thi
//			// executeQuery: dành cho câu truy vấn là câu SELECT => luôn trả ra ResultSet
//			// executeUpdate: tất cả câu truy vấn còn lại ngoài câu SELECT, VD: INSERT,...
//			// => luôn trả ra boolean
//			ResultSet result = preparedStatement.executeQuery();
//			// Tạo listUser để lưu trữ từng dòng query được
//			List<UserEntity> listUser = new ArrayList<UserEntity>();
//
//			// Bước 6: Duyệt từng dòng dữ liệu query được và gán vào trong listUser
//			while (result.next()) {
//				UserEntity user = new UserEntity();
//				user.setId(result.getInt("id")); // => lấy giá trị của cột id gán vào id của user
//				user.setFullname(result.getString("fullname"));
//				listUser.add(user);
//			}
//			// Kiểm tra đăng nhập bằng cách kiểm tra listUser có giá trị không
//			if (listUser.size() > 0) {
//				System.out.println("Đăng nhập thành công");
//				if (remember != null) {
//					Cookie ckEmail = new Cookie("email", email);
//					Cookie ckPassword = new Cookie("password", password);
//					resp.addCookie(ckEmail);
//					resp.addCookie(ckPassword);
//				}
//
//			} else {
//				System.out.println("Đăng nhập thất bại");
//			}
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//
//		req.getRequestDispatcher("login.jsp").forward(req, resp);
//	}
//}

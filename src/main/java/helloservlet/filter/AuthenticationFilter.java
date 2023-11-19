package helloservlet.filter;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import helloservlet.config.MysqlConfig;
import helloservlet.entity.UserEntity;

//Link kích hoạt filter
@WebFilter(filterName = "authenFilter", urlPatterns = { "/user", "/user-add", "/user-update", "/user-delete", "/role",
		"/role-add", "/role-update", "/role-delete", "/task", "/task-add", "/task-update", "/task-delete", "/groupwork",
		"/groupwork-add", "/groupwork-update", "/groupwork-delete","/profile","/profile-update" })
public class AuthenticationFilter implements Filter {

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		// TODO Auto-generated method stub
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse resp = (HttpServletResponse) response;

		Cookie[] cookies = req.getCookies();
		String email = "";
		String password = "";
		String contextPath = req.getContextPath();
		if (cookies != null) {
			for (Cookie cookie : cookies) {
				if (cookie.getName().equals("email")) {
					email = cookie.getValue();
				}
				if (cookie.getName().equals("password")) {
					password = cookie.getValue();
				}
			}
		}

		if (email.trim().length() > 0 && password.trim().length() > 0) {
			// Đã đăng nhập

			// Kết nối tới CSDL
			Connection conn = MysqlConfig.getConnection();
			// Chuẩn bị câu query
			String query = "SELECT * FROM users u WHERE u.email = ? AND u.password = ?";
			try {
				// Truyền câu query vào CSDL
				PreparedStatement preparedStatement = conn.prepareStatement(query);
				// Set giá trị cho dấu ?
				preparedStatement.setString(1, email);
				preparedStatement.setString(2, password);
				// Thực thi câu query
				ResultSet result = preparedStatement.executeQuery();
				// Tạo danh sách UserEntity
				List<UserEntity> listUser = new ArrayList<UserEntity>();
				// Duyệt từng dòng query được và gán vào listUser
				if (result.next()) {
					UserEntity userEntity = new UserEntity();
					userEntity.setFullname(result.getString("fullname"));
					userEntity.setId(result.getInt("role_id"));
					listUser.add(userEntity);
				}
				// Kiểm tra size của listUser để kiểm tra đăng nhập
				if (listUser.size() > 0) {
					// Cho phép đi vào link người dùng đang gọi mà kích hoạt filter
					chain.doFilter(req, resp);
				} else {
					// Đăng nhập thất bại, về trong login
					resp.sendRedirect(contextPath + "/login");
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				resp.sendRedirect(contextPath + "/login");
			}
		} else {
			// Chưa đăng nhập thì về trong login
			resp.sendRedirect(contextPath + "/login");
		}
	}

}

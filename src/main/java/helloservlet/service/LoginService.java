package helloservlet.service;

import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import helloservlet.entity.UserEntity;
import helloservlet.repository.UserRepository;

public class LoginService {
	private UserRepository userRepository = new UserRepository();

	public boolean checkLogin(String email, String password, String remember, HttpServletResponse resp) {
		List<UserEntity> list = userRepository.findByEmailAndPassword(email, password);
		boolean isSuccess = list.size() > 0;
		if (isSuccess) {
			Cookie ckEmail = new Cookie("email", email);
			Cookie ckId=new Cookie("user_id", new Integer(list.get(0).getId()).toString());
			Cookie ckPassword = new Cookie("password", password);
			resp.addCookie(ckEmail);
			resp.addCookie(ckPassword);
			resp.addCookie(ckId);
		}
		return isSuccess;
	}
}

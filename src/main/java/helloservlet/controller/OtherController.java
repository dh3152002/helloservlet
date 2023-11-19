package helloservlet.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import helloservlet.entity.StatusEntity;
import helloservlet.entity.TaskEntity;
import helloservlet.service.ProfileService;

@WebServlet(name = "otherController",urlPatterns = {"","/blank","/404"})
public class OtherController extends HttpServlet{
	private ProfileService profileService=new ProfileService();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String path=req.getServletPath();
		if(path.equals("")) {
			doGetDashBoard(req,resp);
		}else if(path.equals("/blank")) {
			doGetBlank(req, resp);
		}else if(path.equals("/404")) {
			doGet404(req, resp);
		}
	}

	private void doGetDashBoard(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Cookie[] cookies=req.getCookies();
		int user_id=0;
		if(cookies!=null) {
			for (Cookie cookie : cookies) {
				if(cookie.getName().equals("user_id")) {
					user_id=Integer.parseInt(cookie.getValue());
				}
			}
		}
		List<StatusEntity> listStatus=profileService.percentStatusByUserIdJoinStatusGroupByStatusId(user_id);
		req.setAttribute("listStatus", listStatus);
		req.getRequestDispatcher("index.jsp").forward(req, resp);
	}
	
	private void doGetBlank(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getRequestDispatcher("blank.jsp").forward(req, resp);
	}
	
	private void doGet404(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getRequestDispatcher("404.jsp").forward(req, resp);
	}
}

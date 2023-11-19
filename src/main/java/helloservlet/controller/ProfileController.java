package helloservlet.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import helloservlet.entity.GroupworkEntity;
import helloservlet.entity.StatusEntity;
import helloservlet.entity.TaskEntity;
import helloservlet.entity.UserEntity;
import helloservlet.service.ProfileService;
import helloservlet.service.StatusService;
import helloservlet.service.TaskService;
import helloservlet.service.UserService;

@WebServlet(name = "profileController",urlPatterns = {"/profile","/profile-update"})
public class ProfileController extends HttpServlet{
	private ProfileService profileService=new ProfileService();
	private UserService userService=new UserService();
	private StatusService statusService=new StatusService();
	private TaskService taskService=new TaskService();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String path=req.getServletPath();
		if(path.equals("/profile")) {
			doGetProfile(req,resp);
		}else if(path.equals("/profile-update")) {
			doGetProfileUpdate(req, resp);
		}
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String path=req.getServletPath();
		if(path.equals("/profile-update")) {
			doPostProfileUpdate(req, resp);
		}
	}
	
	private void doGetProfile(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Cookie[] cookies=req.getCookies();
		int user_id=0;
		if(cookies!=null) {
			for (Cookie cookie : cookies) {
				if(cookie.getName().equals("user_id")) {
					user_id=Integer.parseInt(cookie.getValue());
				}
			}
		}
		List<TaskEntity> listTask= profileService.findByUserIdJoinUserAndJob(user_id);
		List<StatusEntity> listStatus=profileService.percentStatusByUserIdJoinStatusGroupByStatusId(user_id);
		req.setAttribute("listTask", listTask);
		req.setAttribute("listStatus", listStatus);
		req.getRequestDispatcher("profile.jsp").forward(req, resp);
	}
	
	private void doGetProfileUpdate(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int id=Integer.parseInt(req.getParameter("id"));
		List<StatusEntity> listStatus=statusService.findAll();
		TaskEntity taskEntity=taskService.findByIdJoinJob(id);
		req.setAttribute("listStatus", listStatus);
		req.setAttribute("data", taskEntity);
		req.getRequestDispatcher("profile-update.jsp").forward(req, resp);
	}
	
	private void doPostProfileUpdate(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int id=Integer.parseInt(req.getParameter("id"));
		int status_id=Integer.parseInt(req.getParameter("status_id"));
		boolean isSuccess=profileService.updateStatusId(id, status_id);
		if(isSuccess) {
			System.out.println("update status thanh cong");
		}else {
			System.out.println("update status that bai");
		}
		String contextPath=req.getContextPath();
		resp.sendRedirect(contextPath+"/profile");
	}
}

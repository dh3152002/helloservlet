package helloservlet.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import helloservlet.entity.RoleEntity;
import helloservlet.entity.UserEntity;
import helloservlet.service.RoleService;
import helloservlet.service.UserService;

@WebServlet(name = "userController",urlPatterns = {"/user-add","/user","/user-update","/user-delete"})
public class UserController extends HttpServlet{
	private UserService userService=new UserService();
	private RoleService roleService=new RoleService();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String path=req.getServletPath();
		if(path.equals("/user-add")) {
			doGetUserAdd(req,resp);
		}else if(path.equals("/user")) {
			doGetUserTable(req, resp);
		}else if(path.equals("/user-update")) {
			doGetUserUpdate(req, resp);
		}else if(path.equals("/user-delete")) {
			doGetUserDelete(req, resp);
		}
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String path=req.getServletPath();
		if(path.equals("/user-add")) {
			doPostUserAdd(req,resp);
		}else if(path.equals("/user-update")) {
			doPostUserUpdate(req, resp);
		}
	}
	
	private void doGetUserAdd(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		List<RoleEntity> list = roleService.findAll();
		req.setAttribute("listRole", list);
		req.getRequestDispatcher("user-add.jsp").forward(req, resp);
	}
	
	private void doGetUserUpdate(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		List<RoleEntity> list = roleService.findAll();
		req.setAttribute("listRole", list);
		
		int id=Integer.parseInt(req.getParameter("id"));
		UserEntity userEntity= userService.findById(id);
		req.setAttribute("user", userEntity);
		
		req.getRequestDispatcher("user-update.jsp").forward(req, resp);
	}
	
	private void doGetUserDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int id=Integer.parseInt(req.getParameter("id"));
		String contextPath=req.getContextPath();
		boolean isSuccess=userService.delete(id);
		if(isSuccess) {
			System.out.println("Them user thanh cong");
		}else {
			System.out.println("Them user that bai");
		}
		resp.sendRedirect(contextPath+"/user");
	}
	
	private void doGetUserTable(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		List<UserEntity> list=userService.findAllJoinRole();
		req.setAttribute("listUser", list);
		req.getRequestDispatcher("user-table.jsp").forward(req, resp);
	}
	
	private void doPostUserAdd(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String contextPath=req.getContextPath();
		String email=req.getParameter("email");
		String password=req.getParameter("password");
		String fullname=new String(req.getParameter("fullname").getBytes("ISO-8859-1"), "UTF-8");
		String avatar=req.getParameter("avatar");
		int role_id=Integer.parseInt(req.getParameter("role_id"));
		boolean isSuccess=userService.insert(email, password, fullname, avatar, role_id);
		if(isSuccess) {
			System.out.println("Them user thanh cong");
		}else {
			System.out.println("Them user that bai");
		}
		resp.sendRedirect(contextPath+"/user");
	}
	
	private void doPostUserUpdate(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String contextPath=req.getContextPath();
		int id=Integer.parseInt(req.getParameter("id"));
		String email=req.getParameter("email");
		String password=req.getParameter("password");
		String fullname=new String(req.getParameter("fullname").getBytes("ISO-8859-1"), "UTF-8");
		String avatar=req.getParameter("avatar");
		int role_id=Integer.parseInt(req.getParameter("role_id"));
		boolean isSuccess=userService.update(id, email, fullname, avatar, role_id);
		if(isSuccess) {
			System.out.println("Cap nhat user thanh cong");
		}else {
			System.out.println("Cap nhat user that bai");
		}
		resp.sendRedirect(contextPath+"/user");
	}
}

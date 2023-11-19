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
import helloservlet.service.RoleService;

@WebServlet(name = "roleController", urlPatterns = { "/role-add", "/role","/role-update","/role-delete" })
public class RoleController extends HttpServlet {
	RoleService roleService = new RoleService();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String path = req.getServletPath();
		if (path.equals("/role-add")) {
			doGetRoleAdd(req, resp);
		} else if (path.equals("/role")) {
			doGetRoleTable(req, resp);
		} else if (path.equals("/role-update")) {
			doGetRoleUpdate(req, resp);
		} else if (path.equals("/role-delete")) {
			doGetRoleDelete(req, resp);
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String path=req.getServletPath();
		if (path.equals("/role-add")) {
			doPostRoleAdd(req, resp);
		} else if (path.equals("/role-update")) {
			doPostRoleUpdate(req, resp);
		}
	}
	
	private void doGetRoleAdd(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getRequestDispatcher("role-add.jsp").forward(req, resp);
	}
	
	private void doGetRoleUpdate(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int id=Integer.parseInt(req.getParameter("id"));
		RoleEntity roleEntity=roleService.findById(id);
		req.setAttribute("role", roleEntity);
		req.getRequestDispatcher("role-update.jsp").forward(req, resp);
	}
	
	private void doPostRoleAdd(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String name = new String(req.getParameter("name").getBytes("ISO-8859-1"), "UTF-8");
		String description = new String(req.getParameter("description").getBytes("ISO-8859-1"), "UTF-8");
		boolean isSuccess = roleService.insert(name, description);
		if (isSuccess) {
			System.out.println("Thêm quyền thành công");
		} else {
			System.out.println("Thêm quyền thất bại");
		}
		String contextPath = req.getContextPath();
		resp.sendRedirect(contextPath + "/role");
	}
	
	private void doPostRoleUpdate(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int id=Integer.parseInt(req.getParameter("id"));
		String name = new String(req.getParameter("name").getBytes("ISO-8859-1"), "UTF-8");
		String description = new String(req.getParameter("description").getBytes("ISO-8859-1"), "UTF-8");
		boolean isSuccess = roleService.update(id,name, description);
		if (isSuccess) {
			System.out.println("Cập nhật quyền thành công");
		} else {
			System.out.println("Cập nhật quyền thất bại");
		}
		String contextPath = req.getContextPath();
		resp.sendRedirect(contextPath + "/role");
	}
	
	private void doGetRoleTable(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		List<RoleEntity> list=roleService.findAll();
		req.setAttribute("listRole", list);
		req.getRequestDispatcher("role-table.jsp").forward(req, resp);
	}
	
	private void doGetRoleDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int id=Integer.parseInt(req.getParameter("id"));
		boolean isSuccess=roleService.deleteById(id);
		if(isSuccess) {
			System.out.println("Xoa quyen thanh cong");
		}else {
			System.out.println("Xoa quyen that bai");
		}
		resp.sendRedirect(req.getContextPath()+"/role");
	}
}

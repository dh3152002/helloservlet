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
import helloservlet.service.GroupworkService;

@WebServlet(name = "groupworkController", urlPatterns = { "/groupwork-add", "/groupwork", "/groupwork-update",
		"/groupwork-delete" })
public class GroupworkController extends HttpServlet {
	private GroupworkService groupworkService = new GroupworkService();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String path = req.getServletPath();
		if (path.equals("/groupwork-add")) {
			doGetGroupworkAdd(req, resp);
		} else if (path.equals("/groupwork")) {
			doGetGroupwork(req, resp);
		} else if (path.equals("/groupwork-update")) {
			doGetGroupworkUpdate(req, resp);
		} else if (path.equals("/groupwork-delete")) {
			doGetGroupworkDelete(req, resp);
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String path = req.getServletPath();
		if (path.equals("/groupwork-add")) {
			doPostGroupworkAdd(req, resp);
		} else if (path.equals("/groupwork-update")) {
			doPostGroupworkUpdate(req, resp);
		}
	}

	private void doGetGroupworkAdd(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		req.getRequestDispatcher("groupwork-add.jsp").forward(req, resp);
	}

	private void doGetGroupworkDelete(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		int id = Integer.parseInt(req.getParameter("id"));
		boolean idSuccess = groupworkService.delete(id);
		if (idSuccess) {
			System.out.println("Xoa groupwork thanh cong");
		} else {
			System.out.println("Xoa groupwork that bai");
		}
		String contextPath = req.getContextPath();
		resp.sendRedirect(contextPath + "/groupwork");
	}

	private void doGetGroupworkUpdate(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		int id = Integer.parseInt(req.getParameter("id"));
		GroupworkEntity groupworkEntity = groupworkService.findById(id);
		req.setAttribute("data", groupworkEntity);
		req.getRequestDispatcher("groupwork-update.jsp").forward(req, resp);
	}

	private void doGetGroupwork(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		List<GroupworkEntity> list = groupworkService.findAll();
		req.setAttribute("list", list);
		req.getRequestDispatcher("groupwork.jsp").forward(req, resp);
	}

	private void doPostGroupworkAdd(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String name = new String(req.getParameter("name").getBytes("ISO-8859-1"), "UTF-8");
		String end_date = req.getParameter("end_date");
		String start_date = req.getParameter("start_date");
		boolean idSuccess = groupworkService.insert(name, start_date, end_date);
		if (idSuccess) {
			System.out.println("Them moi groupwork thanh cong");
		} else {
			System.out.println("Them moi groupwork that bai");
		}

		String contextPath = req.getContextPath();
		resp.sendRedirect(contextPath + "/groupwork");
	}

	private void doPostGroupworkUpdate(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		int id = Integer.parseInt(req.getParameter("id"));
		String name = new String(req.getParameter("name").getBytes("ISO-8859-1"), "UTF-8");
		String end_date = req.getParameter("end_date");
		String start_date = req.getParameter("start_date");
		boolean idSuccess = groupworkService.update(id, name, start_date, end_date);
		if (idSuccess) {
			System.out.println("Cap nhat groupwork thanh cong");
		} else {
			System.out.println("Cap nhat groupwork that bai");
		}
		String contextPath = req.getContextPath();
		resp.sendRedirect(contextPath + "/groupwork");
	}
}

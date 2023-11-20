package helloservlet.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import helloservlet.entity.GroupworkEntity;
import helloservlet.entity.TaskEntity;
import helloservlet.entity.UserEntity;
import helloservlet.service.GroupworkService;
import helloservlet.service.TaskService;
import helloservlet.service.UserService;

@WebServlet(name = "taskController", urlPatterns = { "/task", "/task-add", "/task-update", "/task-delete" })
public class TaskController extends HttpServlet {
	private TaskService taskService = new TaskService();
	private UserService userService = new UserService();
	private GroupworkService groupworkService = new GroupworkService();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String path = req.getServletPath();
		if (path.equals("/task-add")) {
			doGetTaskAdd(req, resp);
		} else if (path.equals("/task")) {
			doGetTask(req, resp);
		} else if (path.equals("/task-update")) {
			doGetTaskUpdate(req, resp);
		} else if (path.equals("/task-delete")) {
			doGetTaskDelete(req, resp);
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String path = req.getServletPath();
		if (path.equals("/task-add")) {
			doPostTaskAdd(req, resp);
		} else if (path.equals("/task-update")) {
			doPostTaskUpdate(req, resp);
		}
	}

	private void doGetTaskAdd(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		List<UserEntity> listUserEntities = userService.findAllJoinRole();
		List<GroupworkEntity> listGroupworkEntities = groupworkService.findAll();
		req.setAttribute("listGroupwork", listGroupworkEntities);
		req.setAttribute("listUser", listUserEntities);
		req.getRequestDispatcher("task-add.jsp").forward(req, resp);
	}

	private void doGetTask(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		List<TaskEntity> list = taskService.findAllJoinUserAndJob();
		req.setAttribute("list", list);
		req.getRequestDispatcher("task.jsp").forward(req, resp);
	}

	private void doGetTaskUpdate(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		int id = Integer.parseInt(req.getParameter("id"));
		TaskEntity taskEntity = taskService.findByIdJoinJob(id);
		List<UserEntity> listUserEntities = userService.findAllJoinRole();
		List<GroupworkEntity> listGroupworkEntities = groupworkService.findAll();
		req.setAttribute("listGroupwork", listGroupworkEntities);
		req.setAttribute("listUser", listUserEntities);
		req.setAttribute("data", taskEntity);
		req.getRequestDispatcher("task-update.jsp").forward(req, resp);
	}

	private void doGetTaskDelete(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		int id = Integer.parseInt(req.getParameter("id"));
		boolean isSuccess = taskService.delete(id);
		if (isSuccess) {
			System.out.println("Xoa task thanh cong");
		} else {
			System.out.println("Xoa task that bai");
		}
		String contextPath = req.getContextPath();
		resp.sendRedirect(contextPath + "/task");
	}

	private void doPostTaskAdd(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int user_id = Integer.parseInt(req.getParameter("user_id"));
		int job_id = Integer.parseInt(req.getParameter("job_id"));
		String start_date = req.getParameter("start_date");
		String end_date = req.getParameter("end_date");
		String name = new String(req.getParameter("name").getBytes("ISO-8859-1"), "UTF-8");
		boolean isSuccess = taskService.insert(name, start_date, end_date, user_id, job_id);
		String contextPath = req.getContextPath();
		if (isSuccess) {
			System.out.println("Them moi task thanh cong");
		} else {
			System.out.println("Them moi task that bai");
		}
		resp.sendRedirect(contextPath + "/task");
	}

	private void doPostTaskUpdate(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		int id = Integer.parseInt(req.getParameter("id"));
		int user_id = Integer.parseInt(req.getParameter("user_id"));
		int job_id = Integer.parseInt(req.getParameter("job_id"));
		String start_date = req.getParameter("start_date");
		String end_date = req.getParameter("end_date");
		String name = new String(req.getParameter("name").getBytes("ISO-8859-1"), "UTF-8");
		boolean isSuccess = taskService.update(id, name, start_date, end_date, user_id, job_id);
		if (isSuccess) {
			System.out.println("Cap nhat task thanh cong");
		} else {
			System.out.println("Cap nhat task that bai");
		}
		String contextPath = req.getContextPath();
		resp.sendRedirect(contextPath + "/task");
	}
}

package helloservlet.api;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import helloservlet.payload.BasicResponse;
import helloservlet.service.RoleService;

@WebServlet(name = "apiRoleController",urlPatterns = {"/api/role"})
public class ApiRoleController extends HttpServlet{
	private RoleService roleService=new RoleService();
	
	/*
	 * {
	 * 	"statusCode":200,
	 * 	"message":"",
	 * 	"data":true=>dynamic
	 * }
	 * 
	 * 	payload: request,response
	 */
	
	private Gson gson=new Gson();
	
	@Override
	protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int id=Integer.parseInt(req.getParameter("id"));
		boolean isSuccess=roleService.deleteById(id);
		
		BasicResponse basicResponse=new BasicResponse();
		basicResponse.setStatusCode(200);
		basicResponse.setMessage("");
		basicResponse.setData(isSuccess);
		
		String dataJson=gson.toJson(basicResponse);
		
		PrintWriter out = resp.getWriter();
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        
        out.print(dataJson);
        out.flush();
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("Kiem tra get");
	}
}

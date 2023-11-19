package helloservlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/*
 * Annotation: @
 */

@WebServlet(name="helloServlet",urlPatterns = {"/hello"})
public class HelloServlet extends HttpServlet{
	//GET, POST => Methods cách thức client gọi đường dẫn
	/*
	 * Nhận tham số từ client gửi lên:
	 * 	-Giữa GET và POST cách nhận tham số sẽ giống nhau 
	 * 	-GET: Tham số sẽ truyền trực tiếp trên URL => ?tenthamso=giatri&tenthamso=giatri&...
	 * 	-POST: Tham số sẽ truyền thông qua thẻ FORM của html hoặc các ứng dụng, code gọi link
	 * với phương thức POST (Tham số sẽ được truyền ngầm)
	 */

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		// Sẽ thực hiện khi người dùng gọi đường dẫn với phương thức GET
		//		PrintWriter printWriter= resp.getWriter();
		//		printWriter.write("Hello");
		//		printWriter.close();

		// Lấy giá trị tham số có tên là username, age
		String username=req.getParameter("username");
		//		int age=Integer.parseInt(req.getParameter("age"));
		
		HttpSession session=req.getSession();
		session.setAttribute("cyber", "hello");

		//		System.out.println("Kiểm tra "+username+" - "+age);
		req.getRequestDispatcher("hello.jsp").forward(req, resp);
	}
}

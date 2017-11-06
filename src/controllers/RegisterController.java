package controllers;

import java.io.IOException;
import java.io.PrintWriter;
 
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONObject;

import models.User;
import services.UsersService;

@WebServlet("/cadastrar")
public class RegisterController extends HttpServlet {
	
	public RegisterController() {
		super();
	}
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("register.jsp").forward(request, response);
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String name = request.getParameter("name");
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		
		User user = new User(name, email, password);
		UsersService service = new UsersService();
		
		User newUser = service.create(user);
		
		HttpSession session = request.getSession(); 
		session.setAttribute("auth", newUser);		
		
		JSONObject data = new JSONObject();
		
		data.put("id", user.getId());
		data.put("status", 200);
		
		response.setContentType("application/json");
        response.getWriter().write(data.toString());
	}
}

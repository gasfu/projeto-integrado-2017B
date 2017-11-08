package controllers;

import java.io.IOException;
import java.io.PrintWriter;
import java.security.NoSuchAlgorithmException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONObject;

import models.User;
import services.UsersService;

@WebServlet("/authorize")
public class LoginController extends HttpServlet {
	
	public LoginController() {
		super();
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		JSONObject data = new JSONObject();
		
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		
		UsersService service = new UsersService();
		
		
		User user = null;
		
		try {
			user = service.authorize(email, User.hash(password));
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
				
		if(user == null)
			data.put("status", 600);
		else
			data.put("status", 200);

		response.setContentType("application/json");
        response.getWriter().write(data.toString());
	}
	
}

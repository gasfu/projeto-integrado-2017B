package controllers;

import java.io.IOException;
import java.io.PrintWriter;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Iterator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONObject;
import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;

import models.User;
import services.UsersService;

@WebServlet("/session")
public class SessionController extends HttpServlet {
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		JSONObject data = new JSONObject();
		
		String id = request.getParameter("user_id");
		UsersService service = new UsersService();
		HttpSession session = request.getSession(); 		
		
		User user = null;
		user = service.findOneById(id);
	
		if(user == null)
			data.put("status", 601);
		else {
			data.put("status", 200);
			data.put("user_id", user.getId());
			session.setAttribute("auth", user);
		}

		response.setContentType("application/json");
        response.getWriter().write(data.toString());
	}
	
}

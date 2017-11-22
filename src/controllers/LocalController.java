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

import models.Local;
import models.User;
import services.LocalsService;
import services.UsersService;

@WebServlet("/local")
public class LocalController  extends HttpServlet {
	
	public LocalController() {
		super();
	}
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		JSONObject data = new JSONObject();
		String id = request.getParameter("id");
//		String id = "AW0SFSGN";
		LocalsService service = new LocalsService();
		
		HttpSession session = request.getSession(); 		
		
		Local local = service.getOne(id);
		
		JSONObject user = new JSONObject();
		user.put("id", local.getUser().getId());
		user.put("email", local.getUser().getEmail());
		user.put("name", local.getUser().getName());
		
		JSONObject category= new JSONObject();
		category.put("id", local.getCategory().getId());
		category.put("label", local.getCategory().getLabel());
		
		data.put("id", local.getId());
		data.put("name", local.getName());
		data.put("zipcode", local.getZipcode());
		data.put("address", local.getAddress());
		data.put("neighbourhood", local.getNeighbourhood());
		data.put("city", local.getCity());
		data.put("state", local.getState());
		data.put("lat", local.getLat());
		data.put("lng", local.getLng());
		data.put("name", local.getName());
		data.put("description", local.getDescription());
		data.put("create_at", local.getCreateAt());
		data.put("user", user);
		data.put("category", category);
	
		response.setContentType("application/json");
        response.getWriter().write(data.toString());
	}
	
	@Override
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		JSONObject data = new JSONObject();
		String id = request.getParameter("id");
		LocalsService service = new LocalsService();
		
		String localId = service.delete(id);
		
		data.put("status", 200);
		data.put("id", localId);
		
		response.setContentType("application/json");
        response.getWriter().write(data.toString());
	}
}

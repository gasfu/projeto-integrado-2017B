package controllers;

import java.io.IOException;
import java.io.PrintWriter;
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

import models.Local;
import models.User;
import services.LocalsService;

@WebServlet("/locals")
public class LocalsController extends HttpServlet {
	
	public LocalsController() {
		super();
	}
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		LocalsService service = new LocalsService();
		ArrayList<Local> locals = service.fetch();
		
		Iterator<Local> list = locals.iterator();
		
		JSONArray array = new JSONArray();
		
		
		while(list.hasNext()) {
			JSONObject user = new JSONObject();
			
			Local object = list.next();
			System.out.println(object.getDescription());
			
			user.put("name", object.getName());
			user.put("address", object.getAddress());
			user.put("description", object.getDescription());
			user.put("creatAt", object.getCreateAt());
			
			array.add(user);
		}
		
		JSONObject data = new JSONObject();
		
		data.put("locals", array);
		data.put("status", 200);
		
		response.setContentType("application/json");
        response.getWriter().write(data.toString());
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(true); 
		
		String name = request.getParameter("name");
		String address = request.getParameter("address");
		String description = request.getParameter("description");
		
		User user = (User) session.getAttribute("auth");
		
		Local local = new Local(user, name, address, description);
		LocalsService service = new LocalsService();
		service.create(local);
		
		JSONObject data = new JSONObject();
		
		data.put("id", local.getId());
		data.put("status", 200);
		
		response.setContentType("application/json");
        response.getWriter().write(data.toString());
	}

}
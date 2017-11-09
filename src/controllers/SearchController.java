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

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import models.Local;
import services.LocalsService;

@WebServlet("/search")
public class SearchController extends HttpServlet {
	
	public SearchController() {
		super();
	}
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String name = request.getParameter("name");
		LocalsService service = new LocalsService();
		ArrayList<Local> locals = service.search(name);
		
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
		
}

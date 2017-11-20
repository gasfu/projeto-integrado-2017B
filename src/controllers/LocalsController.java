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

import models.Evaluation;
import models.Local;
import models.User;
import services.EvaluationService;
import services.LocalsService;

@WebServlet("/locals")
public class LocalsController extends HttpServlet {
	
	public LocalsController() {
		super();
	}
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html; charset=UTF-8");
		response.setCharacterEncoding("UTF-8");
		
		LocalsService service = new LocalsService();
		ArrayList<Local> locals = service.fetch();
		
		Iterator<Local> list = locals.iterator();
		
		JSONArray array = new JSONArray();
		
		
		while(list.hasNext()) {
			JSONObject local = new JSONObject();
			Local object = list.next();
			
			EvaluationService evaluationsService = new EvaluationService();
			ArrayList<Evaluation> evaluations = evaluationsService.getByLocalId(object.getId());
			
			Iterator<Evaluation> evaluationsList = evaluations.iterator();
			
			double average = 0;
			int count = 0;
			
			while(evaluationsList.hasNext()) {
				average += Double.parseDouble(evaluationsList.next().getValue());
				count++;
			}
			
			if(count > 0) average = average / count;
			
			local.put("name", object.getName());
			local.put("id", object.getId());
			local.put("lat", object.getLat());
			local.put("lng", object.getLng());
			local.put("city", object.getCity());
			local.put("state", object.getState());
			local.put("description", object.getDescription());
			local.put("creatAt", object.getCreateAt());
			local.put("average", average);
			
			array.add(local);
		}
		
		JSONObject data = new JSONObject();
		
		data.put("locals", array);
		data.put("status", 200);
		
		response.setContentType("application/json");
        response.getWriter().write(data.toString());
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		HttpSession session = request.getSession(true); 
		
		String name = request.getParameter("name");
		String description = request.getParameter("description");
		String address = request.getParameter("address");
		String number = request.getParameter("number");
		String city = request.getParameter("city");
		String neighbourhood = request.getParameter("neighbourhood");
		String state = request.getParameter("state");
		String zipcode = request.getParameter("zipcode");
		String lat = request.getParameter("lat");
		String lng = request.getParameter("lng");
		
		User user = (User) session.getAttribute("auth");
		
		Local local = new Local(user, name, description, address, number, city, neighbourhood, state, zipcode, lat, lng);
		LocalsService service = new LocalsService();
		service.create(local);
		
		JSONObject data = new JSONObject();
		
		data.put("id", local.getId());
		data.put("status", 200);
		
		response.setContentType("application/json");
        response.getWriter().write(data.toString());
	}

}
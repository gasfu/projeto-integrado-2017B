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

@WebServlet("/user/locals")
public class UserLocalsController extends HttpServlet {
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html; charset=UTF-8");
		response.setCharacterEncoding("UTF-8");
		String userId = request.getParameter("user_id"); 
		System.out.println(userId);
		
		LocalsService service = new LocalsService();
		ArrayList<Local> locals = service.getLocalsByUserId(userId);
		
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
				average += evaluationsList.next().getAverage();
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

}
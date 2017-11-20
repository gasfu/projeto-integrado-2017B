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

import models.Evaluation;
import models.Local;
import services.EvaluationService;
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
		
}

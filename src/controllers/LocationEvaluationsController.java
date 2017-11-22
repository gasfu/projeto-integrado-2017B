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

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import models.Evaluation;
import models.Local;
import models.User;
import services.EvaluationService;
import services.LocalsService;

@WebServlet("/local/evaluations")
public class LocationEvaluationsController  extends HttpServlet {
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String locationId = request.getParameter("local_id");
//		String locationId = "OXXIUR1K";
		EvaluationService service = new EvaluationService();
		ArrayList<Evaluation> evaluations = service.getByLocalId(locationId);
		
		Iterator<Evaluation> list = evaluations.iterator();
		
		JSONArray array = new JSONArray();
		
		while(list.hasNext()) {
			JSONObject data = new JSONObject();
			Evaluation object = list.next();
			
			JSONObject user = new JSONObject();
			user.put("id", object.getUser().getId());
			user.put("name", object.getUser().getName());
			user.put("email", object.getUser().getEmail());
			user.put("create_at", object.getUser().getEmail());
			
			data.put("id", object.getId());
			data.put("comment", object.getComment());
			data.put("value", object.getAverage());
			data.put("wheelchair_access_value", object.getWheelchairAccessValue());
			data.put("wheelchair_wc_value", object.getWheelchairWcValue());
			data.put("braile_value", object.getBraileValue());
			data.put("tatil_floor_value", object.getTatilFloorValue());
			data.put("create_at", object.getCreateAt());
			data.put("user", user);
			
			array.add(data);
		}
		
		JSONObject data = new JSONObject();
		
		data.put("evaluations", array);
		data.put("status", 200);
		
		response.setContentType("application/json");
        response.getWriter().write(data.toString());
	}
	
}

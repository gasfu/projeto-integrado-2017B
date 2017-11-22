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

import models.Evaluation;
import models.User;
import services.EvaluationService;

@WebServlet("/evaluations")
public class EvaluationsController extends HttpServlet{
	
	public EvaluationsController() {
		super();
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		HttpSession session = request.getSession(true); 
		
		String localId = request.getParameter("local_id");
		String comment = request.getParameter("comment");
		String wheelchairAccessValue = request.getParameter("wheelchair_access_value");
		String wheelchairWcValue = request.getParameter("wheelchair_wc_value");
		String tatilFloorValue = request.getParameter("tatil_floor_value");
		String braileValue = request.getParameter("braile_value");
		User user = (User) session.getAttribute("auth");
		
		Evaluation evaluation = new Evaluation();
		evaluation.setId(evaluation.generateId());
		evaluation.setLocalId(localId);
		evaluation.setWheelchairAccessValue(wheelchairAccessValue);
		evaluation.setWheelchairWcValue(wheelchairWcValue);
		evaluation.setTatilFloorValue(tatilFloorValue);
		evaluation.setBraileValue(braileValue);
		evaluation.setUser(user);
		evaluation.setComment(comment);
		
		EvaluationService service = new EvaluationService();
		service.create(evaluation);
		
		JSONObject data = new JSONObject();
		
		data.put("evaluation_id", evaluation.getId());
		data.put("status", 200);
		
		response.setContentType("application/json");
        response.getWriter().write(data.toString());
	}
}

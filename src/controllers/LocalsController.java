package controllers;

import java.io.IOException;
import java.io.PrintWriter;
 
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

@WebServlet("/local")
public class LocalsController extends HttpServlet {
	
	public LocalsController() {
		super();
	}
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/registerLocal.jsp").forward(request, response);
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(true); 
		
		String name = request.getParameter("name");
		String address = request.getParameter("address");
		String description = request.getParameter("description");
		
		User user = (User) session.getAttribute("auth");
		
		System.out.println(user.getEmail());
		
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

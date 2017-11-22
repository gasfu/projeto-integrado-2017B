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

@WebServlet("/me/locais")
public class UserLocalsInterfaceController extends HttpServlet {
	
	public UserLocalsInterfaceController() {
		super();
	}
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/myLocals.jsp").forward(request, response);
	}
}

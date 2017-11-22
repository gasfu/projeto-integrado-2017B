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

import models.Category;
import services.CategoriesService;

@WebServlet("/categories")
public class CategoriesController extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		JSONObject data = new JSONObject();
		
		CategoriesService service = new CategoriesService();
		ArrayList<Category> categories = service.fetch();
		
		Iterator<Category> list = categories.iterator();
		
		JSONArray array = new JSONArray();
		
		while(list.hasNext()) {
			JSONObject category = new JSONObject();
			Category object = list.next();
			
			category.put("id", object.getId());
			category.put("label", object.getLabel());
			
			array.add(category);
		}
		
		data.put("categories", array);
		data.put("status", 200);
		
		response.setContentType("application/json");
        response.getWriter().write(data.toString());
	}
	
}

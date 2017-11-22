package services;

import java.util.ArrayList;

import models.Category;
import repositories.CategoriesRepository;;

public class CategoriesService {
	
	public ArrayList<Category> fetch() {
		CategoriesRepository repository = new CategoriesRepository();
		return repository.fetch();
	}
	
}

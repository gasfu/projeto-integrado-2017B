package services;

import java.util.ArrayList;

import models.Local;
import repositories.LocalsRepository;

public class LocalsService {
	
	public Local create(Local local) {
		LocalsRepository repository = new LocalsRepository();
		return repository.create(local);
	}
	
	public Local getOne(String id) {
		LocalsRepository repository = new LocalsRepository();
		return repository.getLocalById(id);
	}
	
	public ArrayList<Local> fetch() {
		LocalsRepository repository = new LocalsRepository();
		return repository.fetch();
	}
	
	public ArrayList<Local> search(String name) {
		LocalsRepository repository = new LocalsRepository();
		return repository.search(name);
	}
	
	public ArrayList<Local> getLocalsByUserId(String userId) {
		LocalsRepository repository = new LocalsRepository();
		return repository.getLocalsByUserId(userId);
	}
	
	public String delete(String id) {
		LocalsRepository repository = new LocalsRepository();
		return repository.delete(id);
	}

}

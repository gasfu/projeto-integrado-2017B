package services;

import models.Local;
import repositories.LocalsRepository;

public class LocalsService {
	
	public Local create(Local local) {
		LocalsRepository repository = new LocalsRepository();
		return repository.create(local);
	}

}

package services;

import models.User;
import repositories.UsersRepository;

public class UsersService {
	
	public User create(User user) {
		UsersRepository repository = new UsersRepository();
		return repository.create(user);
	}
	
}

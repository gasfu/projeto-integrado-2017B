package services;

import models.User;
import repositories.UsersRepository;

public class UsersService {
	
	public User create(User user) {
		UsersRepository repository = new UsersRepository();
		return repository.create(user);
	}
	
	public User findOneByEmail(String email) {
		UsersRepository repository = new UsersRepository();
		return repository.findOneByEmail(email);
	}
	
	public User findOneById(String id) {
		UsersRepository repository = new UsersRepository();
		return repository.findOneById(id);
	}
	
	public User authorize(String email, String password) {
		UsersRepository repository = new UsersRepository();
		return repository.authorize(email, password);
	}
	
}

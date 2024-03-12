package com.tunehub.demo.services;



import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;



import com.tunehub.demo.repositories.*;
import com.tunehub.demo.entities.Users;

@Service
public class UsersServiceImplementation implements UsersService{
	@Autowired
	UsersRepository repo;

	@Override
	public String addUser(Users user) {
		repo.save(user);
		return "user added succesfully";
	}

	@Override
	public boolean emailExists(String email) {
		if(repo.findByEmail(email)==null) {
			return false;
		}

		else {
			return true;
		}
	}

	@Override
	public boolean validateUser(String email, String password) {
		Users user=repo.findByEmail(email);//if email is present the it return the object of that user
		String db_pass=user.getPassword();//extrating the password of that object
		if(password.equals(db_pass)) {
			return true;
		}
		
		else{
			return false;
		}
	}

	@Override
	public String getRole(String email) {
		Users user=repo.findByEmail(email);
		return user.getRole();
	}

	@Override
	public Users getUser(String email) {
		
		return (repo.findByEmail(email));
	}

	@Override
	public void updateUser(Users user) {
		repo.save(user);
		
	}
}

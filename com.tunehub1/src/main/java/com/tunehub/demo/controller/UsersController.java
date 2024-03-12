package com.tunehub.demo.controller;


import java.util.List;




import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tunehub.demo.entities.LoginData;
import com.tunehub.demo.entities.Song;
import com.tunehub.demo.entities.Users;
import com.tunehub.demo.services.SongService;
import com.tunehub.demo.services.UsersService;

import jakarta.servlet.http.HttpSession;

//Adding the user information and checking the user if user is new then user account will be created
//else it will display "User already exists"
@CrossOrigin("*")
@RestController
public class UsersController {
	@Autowired
	UsersService service;
	@Autowired
	SongService songService;
	
	@PostMapping("/register")
	public String addUsers(@ModelAttribute Users user) {
		boolean userStatus=service.emailExists(user.getEmail());
		if(userStatus== false)
		{
			service.addUser(user);
			System.out.println("User added");
		}
		else
		{
			System.out.println("User already exists");
		}
		return "home";
	}
//In this the method will be check the validate mail if the mail is register by admin then it will
//go to admin home or else customerHome page
	@PostMapping("/validate")
	public String validate(@RequestBody LoginData data,
			HttpSession session, Model model)
	{
		System.out.println("call received");
		String email=data.getEmail();
		String password=data.getPassword();
		if(service.validateUser(email,password)==true)
		{
			String role=service.getRole(email);
			session.setAttribute("email",email);
			if(role.equals("admin"))
			{
				return "adminHome";
			}
			else
			{
				Users user=service.getUser(email);
				boolean userStatus=user.isPremium();
				
				List<Song> songsList=songService.fetchAllSongs();
				
				model.addAttribute("songs", songsList);
				
				model.addAttribute("isPremium",userStatus);
				
				return "customerHome";
			}
		}
		else
		{
			return"login";
		}
		}	
	
	@GetMapping("/logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "login";
	
	}
	
}


















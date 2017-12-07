package com.reimb.servlets;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.reimb.pojos.User;
import com.reimb.service.Service;

@WebServlet("/update")
public class update extends HttpServlet {
	static Service service = new Service();
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("Please update servlet");
		BufferedReader br = 
				new BufferedReader(new InputStreamReader(request.getInputStream()));
		String json = "";
		if(br != null){
			json = br.readLine();
		}
		ObjectMapper mapper = new ObjectMapper();
		
		User u = mapper.readValue(json, User.class);
		HttpSession session = request.getSession();
		User ogU=(User)session.getAttribute("user");
		
		System.out.println(ogU);
		u.setId(ogU.getId()); // id wont change and niether will e-mail .. nah lets let that happen username will remain though. session bound as will role
		u.setUsername(ogU.getUsername());
		u.setRole(ogU.getRole());

		if(u.getLastname()=="")
			u.setLastname(ogU.getLastname());
		else 
			ogU.setLastname(u.getLastname());
		
		if(u.getName()=="")
			u.setName(ogU.getName());
		else 
			ogU.setName(u.getName());
		
		if(u.getPassword()=="")
			u.setPassword(ogU.getPassword());
		else 
			ogU.setPassword(u.getPassword());
		
		if(u.getEmail()=="")
			u.setEmail(ogU.getEmail());
		else 
			ogU.setEmail(u.getEmail());
		
	
		
		service.updateUser(u);
	}

}

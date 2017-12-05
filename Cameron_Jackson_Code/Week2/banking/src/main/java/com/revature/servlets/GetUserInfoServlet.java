package com.revature.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.pojos.User;
import com.revature.service.BankService;

@WebServlet("/getUserInfo")
public class GetUserInfoServlet extends HttpServlet {
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("In get user info servlet doGet");
		BankService service = new BankService();
		
		HttpSession session = req.getSession();
		User user = (User)session.getAttribute("user");
		System.out.println(user);
		
		if (user != null) {
			ObjectMapper mapper = new ObjectMapper();
			String json = mapper.writeValueAsString(user);
			
			PrintWriter out = resp.getWriter();
			resp.setContentType("application/json");
			out.write(json);
		} else {
			resp.setStatus(418);
		}
	}
}

package com.ers.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ers.pojos.Reimbursement;
import com.ers.pojos.User;
import com.ers.util.Service;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebServlet("/GetReimbServlet")
public class GetReimbServlet extends HttpServlet
{
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		ArrayList<Reimbursement> reimbs = new ArrayList<>();

		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");

		Service service = new Service();
		reimbs = service.getReimbByUser(user);

		Reimbursement reimb = reimbs.get(0);

		System.out.println(reimb.toString());
		System.out.println(reimb);

		HttpSession sessionReimb = request.getSession();
		sessionReimb.setAttribute("reimbs", reimbs);

		ObjectMapper mapper = new ObjectMapper();
		String json = mapper.writeValueAsString(reimbs);

		PrintWriter out = response.getWriter();
		response.setContentType("application/json");
		out.write(json);

	}
}
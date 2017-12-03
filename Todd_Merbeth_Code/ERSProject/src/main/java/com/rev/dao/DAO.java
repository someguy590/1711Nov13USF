package com.rev.dao;

import java.util.ArrayList;

import com.rev.pojos.Reimbursement;
import com.rev.pojos.User;

public interface DAO {

	//For employees
	public ArrayList<Reimbursement> getUserReimbursements(int u_id);
	
	public Reimbursement addReimbursement(Reimbursement r);
	
	//For managers
	public ArrayList<Reimbursement> getReimbursements();
	
//	public ArrayList<Reimbursement> getReimbursementsByStatus();
	
	public ArrayList<Reimbursement> getPendingReimbursements();
	
	public Reimbursement getReimbursement(int r_id);
	
	public Reimbursement updateReimbursement(int r_id, int r_res, int r_status);
	//For both
	public User addUser(User user);
	
	public User getUser(String username, String password);
	
	public String getR_Status(int r_id);
	
	public String getR_Type(int r_id);
	
	public String getUser_Role(int u_id);
	
	
}
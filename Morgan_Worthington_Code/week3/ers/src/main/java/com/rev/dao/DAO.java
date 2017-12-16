package com.rev.dao;

import java.util.ArrayList;

import com.rev.pojos.Reimbursement;
import com.rev.pojos.User;

public interface DAO {
	User getUserById(int id);
	User getUserByUsername(String username);
	Reimbursement getReimbById(int id);
	ArrayList<Reimbursement> getReimbByAuthor(int auth);
	ArrayList<Reimbursement> getReimbursements();
	ArrayList<User> getUsers();
	void addNewUser(String[] userInfo);
	void addNewReimbursement(String[] reimbInfo);
	void updateStatus(int rId, int newStatus, int resolver);
}

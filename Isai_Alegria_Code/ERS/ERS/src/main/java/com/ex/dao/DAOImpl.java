package com.ex.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import com.ex.util.ConnectionFactory;
import com.rev.pojos.Reimbursement;
import com.rev.pojos.User;

import oracle.jdbc.OracleTypes;

public class DAOImpl implements DAO{

	public int addUser(User u) {

		int id = 0;
		try(Connection conn = ConnectionFactory.getInstance().getConnection()){
			
			conn.setAutoCommit(false);
			
			//doen't need to insert userID
			String sql = "insert into ers_users (ers_users_id, ers_username, ers_password, user_first_name,"
					+ " user_last_name, user_email, user_role_id ) values (?, ?, ?, ?, ?, ?, ?)";

			String []key = new String[1];
			key[0] = "ers_users_id";
			
			PreparedStatement ps = conn.prepareStatement(sql,key);
			ps.setInt(1, u.getUserID());
			ps.setString(2, u.getUsername());
			ps.setString(3, u.getPassword());
			ps.setString(4, u.getFirstName());
			ps.setString(5, u.getLastName());
			ps.setString(6, u.getEmail());
			ps.setInt(7, u.getUserRoleID());
			int rows = ps.executeUpdate();

			ResultSet pk = ps.getGeneratedKeys();
			while(pk.next()) {
				
				id = pk.getInt(1);
			}
			
			conn.commit();

			
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return id;
		
	}

	public User findUser(String uname) {
		
		User temp = new User();
		try(Connection conn = ConnectionFactory.getInstance().getConnection()){
			
			String sql = "select * from ers_users where ers_username = ? ";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, uname);
			ResultSet info = ps.executeQuery();

			if(info.next() == false) {
				return null;
			}
			
			//maybe remove else since empty query returns null?
			else {
					//might start at 0, not sure
					temp.setUserID(info.getInt(1));
					temp.setUsername(info.getString(2));
					temp.setPassword(info.getString(3));
					temp.setFirstName(info.getString(4));
					temp.setLastName(info.getString(5));
					temp.setEmail(info.getString(6));
					temp.setUserRoleID(info.getInt(7));
				
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return temp;
		
	}

	@Override
	public boolean checkPass(User u, String pass) {
		
		if(u == null) {
			return false;
		}
		
		if(u.getPassword().equals(pass))
			return true;
		
		else
			return false;
	}

	@Override
	
	//check if provided username is available
	public boolean findUser2(String uname) {
		
		try(Connection conn = ConnectionFactory.getInstance().getConnection()){
			
			String sql = "select * from ers_users where ers_username = ? ";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, uname);
			ResultSet info = ps.executeQuery();

			if(info.next() == false) {
				return true;
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return false;
	}

	@Override
	public void addRequest(Reimbursement reimb) {
		
		//Reimbursement tempReimb = new Reimbursement();
		try(Connection conn = ConnectionFactory.getInstance().getConnection()){
			conn.setAutoCommit(false);
			
			String sql = "insert into ers_reimbursement (reimb_id, reimb_amount, reimb_submitted, reimb_resolved,"
					+ " reimb_description, reimb_receipt, reimb_author,reimb_resolver, reimb_status_id,reimb_type_id) "
					+ "values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

			
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, reimb.getReimbID());
			ps.setInt(2, reimb.getReimbAmount());
			ps.setString(3, reimb.getReimbSubmitted());
			ps.setString(4, reimb.getReimbResolved());
			ps.setString(5, reimb.getReimbDescription());
			ps.setString(6, reimb.getReimbReceipt());
			ps.setInt(7, reimb.getReimbAuthor());
			ps.setInt(8, reimb.getReimbResolver());
			ps.setInt(9, reimb.getReimbStatusID());
			ps.setInt(10, reimb.getReimbTypeID());
			ps.executeUpdate();
			conn.commit();

			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public void viewTickets(User u) {

		try(Connection conn = ConnectionFactory.getInstance().getConnection()){
			
			int check = u.getUserRoleID();
			if(check == 0) {
				//associate, print only tickets he/she has submitted
				String sql = "select * from ers_reimbursement where reimb_author = ? ";
				PreparedStatement ps = conn.prepareStatement(sql);
				ps.setInt(1, u.getUserID());
				ResultSet info = ps.executeQuery();
			}
			
			else {
				//manager is able to see all tickets pending
				String sql = "select * from ers_users where reimb_status_id = ? ";
				PreparedStatement ps = conn.prepareStatement(sql);
				ps.setInt(1, 1); //search for where reimbursement status is pending (1)
				ResultSet info = ps.executeQuery();
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	



}

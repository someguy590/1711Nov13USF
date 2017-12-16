package com.rev.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.rev.pojos.Reimbursement;
import com.rev.pojos.User;
import com.rev.util.ConnectionFactory;

import oracle.jdbc.OracleTypes;

public class DAOImpl implements DAO {
	
	@Override
	public User getUserById(int id) {
		User u=new User();
		try(Connection conn=ConnectionFactory.getInstance().getConnection()){
			String sql="SELECT * FROM ERS_USERS WHERE ERS_USERS_ID = ?";
			PreparedStatement ps=conn.prepareStatement(sql);
			ps.setInt(1, id);
			ResultSet rs=ps.executeQuery();
			while(rs.next()) {
				u.setId(rs.getInt(1));
				u.setUsername(rs.getString(2));
				u.setPassword(rs.getString(3));
				u.setFn(rs.getString(4));
				u.setLn(rs.getString(5));
				u.setEmail(rs.getString(6));
				u.setRole(rs.getInt(7));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return u;
	}
	
	@Override
	public User getUserByUsername(String username) {
		User u=new User();
		try(Connection conn=ConnectionFactory.getInstance().getConnection()){
			String sql="SELECT * FROM ERS_USERS WHERE ERS_USERNAME = ?";
			PreparedStatement ps=conn.prepareStatement(sql);
			ps.setString(1, username);
			ResultSet rs=ps.executeQuery();
			while(rs.next()) {
				u.setId(rs.getInt(1));
				u.setUsername(rs.getString(2));
				u.setPassword(rs.getString(3));
				u.setFn(rs.getString(4));
				u.setLn(rs.getString(5));
				u.setEmail(rs.getString(6));
				u.setRole(rs.getInt(7));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return u;
	}

	@Override
	public Reimbursement getReimbById(int id) {
		Reimbursement reimb= new Reimbursement();
		try(Connection conn=ConnectionFactory.getInstance().getConnection()){
			String sql="SELECT * FROM ERS_REIMBURSEMENT WHERE REIMB_ID = ?";
			PreparedStatement ps=conn.prepareStatement(sql);
			ps.setInt(1, id);
			ResultSet rs=ps.executeQuery();
			while(rs.next()) {
				reimb.setId(rs.getInt(1));
				reimb.setAmount(rs.getDouble(2));
				reimb.setSubmitted(rs.getString(3));
				reimb.setResolved(rs.getString(4));
				reimb.setDescription(rs.getString(5));
				reimb.setReceipt("");
				reimb.setAuthor(rs.getInt(7));
				reimb.setResolver(rs.getInt(8));
				reimb.setStatusId(rs.getInt(9));
				reimb.setTypeId(rs.getInt(10));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return reimb;
	}

	@Override
	public ArrayList<Reimbursement> getReimbByAuthor(int auth) {
		ArrayList<Reimbursement> reimbs=new ArrayList<>();
		
		try(Connection conn=ConnectionFactory.getInstance().getConnection()){
			String sql="SELECT * FROM ERS_REIMBURSEMENT WHERE REIMB_AUTHOR = ?";
			PreparedStatement ps=conn.prepareStatement(sql);
			ps.setInt(1, auth);
			ResultSet rs=ps.executeQuery();
			while(rs.next()) {
				Reimbursement temp=new Reimbursement();
				temp.setId(rs.getInt(1));
				temp.setAmount(rs.getDouble(2));
				temp.setSubmitted(rs.getString(3));
				temp.setResolved(rs.getString(4));
				temp.setDescription(rs.getString(5));
				temp.setReceipt("");
				temp.setAuthor(rs.getInt(7));
				temp.setResolver(rs.getInt(8));
				temp.setStatusId(rs.getInt(9));
				temp.setTypeId(rs.getInt(10));
				reimbs.add(temp);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return reimbs;
	}

	@Override
	public ArrayList<Reimbursement> getReimbursements() {
		ArrayList<Reimbursement> reimbs=new ArrayList<>();
		
		try(Connection conn=ConnectionFactory.getInstance().getConnection()){
			String sql="SELECT * FROM ERS_REIMBURSEMENT";
			Statement statement=conn.createStatement();
			statement.execute(sql);
			ResultSet rs=statement.getResultSet();
			while(rs.next()) {
				Reimbursement temp=new Reimbursement();
				temp.setId(rs.getInt(1));
				temp.setAmount(rs.getDouble(2));
				temp.setSubmitted(rs.getString(3));
				temp.setResolved(rs.getString(4));
				temp.setDescription(rs.getString(5));
				temp.setReceipt("");
				temp.setAuthor(rs.getInt(7));
				temp.setResolver(rs.getInt(8));
				temp.setStatusId(rs.getInt(9));
				temp.setTypeId(rs.getInt(10));
				reimbs.add(temp);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return reimbs;
	}

	@Override
	public ArrayList<User> getUsers() {
		ArrayList<User> users=new ArrayList<>();
		
		try(Connection conn=ConnectionFactory.getInstance().getConnection()){
//			String sql="SELECT * FROM ERS_USERS";
//			PreparedStatement ps=conn.prepareStatement(sql); 
//			ResultSet rs=ps.executeQuery();
			
			CallableStatement cs=conn.prepareCall("{CALL GET_USERS(?)}");
			cs.registerOutParameter(1, OracleTypes.CURSOR);
			cs.execute();
			ResultSet rs=(ResultSet) cs.getObject(1);
			
			while(rs.next()) {
				User temp=new User();
				temp.setId(rs.getInt(1));
				temp.setUsername(rs.getString(2));
				temp.setPassword(rs.getString(3));
				temp.setFn(rs.getString(4));
				temp.setLn(rs.getString(5));
				temp.setEmail(rs.getString(6));
				temp.setRole(rs.getInt(7));
				users.add(temp);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return users;
	}

	@Override
	public void addNewUser(String[] userInfo) {
		try(Connection conn=ConnectionFactory.getInstance().getConnection()){
			String sql="INSERT INTO ERS_USERS "
					+ "(USER_FIRST_NAME,USER_LAST_NAME,USER_EMAIL,ERS_USERNAME, ERS_PASSWORD,USER_ROLE_ID)"
					+ "VALUES(?,?,?,?,?,?)";
			PreparedStatement ps=conn.prepareStatement(sql);
			ps.setString(1, userInfo[0]);
			ps.setString(2, userInfo[1]);
			ps.setString(3, userInfo[2]);
			ps.setString(4, userInfo[3]);
			ps.setString(5, userInfo[4]);
			if(userInfo[5].equals("manager")) {
				ps.setInt(6, 1);
			} else {
				ps.setInt(6, 2);
			}
			
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void addNewReimbursement(String[] reimbInfo) {
		try(Connection conn=ConnectionFactory.getInstance().getConnection()){
			
			double amount=Double.parseDouble(reimbInfo[0]);
			String description=reimbInfo[1];
			int author=Integer.parseInt(reimbInfo[2]);
			int type=Integer.parseInt(reimbInfo[3]);
			
			String sql="INSERT INTO ERS_REIMBURSEMENT "
					+ "(REIMB_AMOUNT,REIMB_DESCRIPTION,REIMB_AUTHOR,REIMB_STATUS_ID,REIMB_TYPE_ID)"
					+ "VALUES(?,?,?,1,?)";
			PreparedStatement ps=conn.prepareStatement(sql);
			ps.setDouble(1, amount);
			ps.setString(2, description);
			ps.setInt(3, author);
			ps.setInt(4, type);
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void updateStatus(int rId, int newStatus, int resolver) {
		try(Connection conn=ConnectionFactory.getInstance().getConnection()){
			String sql="UPDATE ERS_REIMBURSEMENT SET REIMB_STATUS_ID = ?, REIMB_RESOLVER = ?"
					+ " WHERE REIMB_ID = ?";
			PreparedStatement ps=conn.prepareStatement(sql);
			ps.setInt(1, newStatus);
			ps.setInt(2, resolver);
			ps.setInt(3, rId);
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
}

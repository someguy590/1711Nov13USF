package com.bank.util;

import java.util.ArrayList;
import java.util.Scanner;

import com.bank.dao.AccountDAO;
import com.bank.dao.AccountDAOImp;
import com.bank.dao.UserDAO;
import com.bank.dao.UserDAOImp;
import com.bank.pojos.Account;
import com.bank.pojos.User;

public class Service
{

	// ------------------------------------------------------------
	// UserDAO Service
	// ------------------------------------------------------------
	public UserDAO userDao = new UserDAOImp();
	public AccountDAO accountDao = new AccountDAOImp();

	public ArrayList<User> getAllUsers()
	{
		return userDao.getAllUsers();
	}

	public User addUser(User user)
	{
		return userDao.addUser(user);
	}

	public User getUser(User user)
	{
		return userDao.getUser(user);
	}

	public void deleteUser(int user_id)
	{
		userDao.deleteUser(user_id);
	}

	// ------------------------------------------------------------
	// AccountDAO Service
	// ------------------------------------------------------------
	public ArrayList<Account> getAllAccounts(User user)
	{
		return accountDao.getAllAccounts(user);
	}

	public ArrayList<Account> getAccountsByUserId(User user)
	{
		return accountDao.getAccountsByUserId(user);
	}

	public Account addAccount(User user)
	{
		return accountDao.addAccount(user);
	}

	public Account getAccount(int acc_id)
	{
		return accountDao.getAccount(acc_id);
	}

	public void updateAccount(Account account)
	{
		accountDao.updateAccount(account);
	}

	public void deleteAccount(int acc_id)
	{
		System.out.println("Are you sure you want to delete this account? Select an option below: \n1- Yes\n2- No");
		Scanner scan = new Scanner(System.in);
		String option = scan.nextLine();
		switch (option)
		{
		case "1":
			accountDao.deleteAccount(acc_id);
		case "2":
			break;
		default:
			System.out.println("You have entered an invalid answer. Please try again.");
			deleteAccount(acc_id);
		}

	}

}
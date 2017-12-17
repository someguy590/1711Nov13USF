package com.bank.pojos;

public class User
{

	private String firstName, lastName, userName, passWord;
	private int userId;

	public User()
	{
		this.firstName = null;
		this.lastName = null;
		this.userName = null;
		this.passWord = null;
		this.userId = 0;
	}

	public User(String firstName, String lastName, String userName, String passWord, int userId)
	{
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.userName = userName;
		this.passWord = passWord;
		this.userId = userId;
	}

	public String getFirstName()
	{
		return firstName;
	}

	public void setFirstName(String firstName)
	{
		this.firstName = firstName;
	}

	public String getLastName()
	{
		return lastName;
	}

	public void setLastName(String lastName)
	{
		this.lastName = lastName;
	}

	public String getUserName()
	{
		return userName;
	}

	public void setUserName(String userName)
	{
		this.userName = userName;
	}

	public String getPassWord()
	{
		return passWord;
	}

	public void setPassWord(String passWord)
	{
		this.passWord = passWord;
	}

	public int getUserId()
	{
		return userId;
	}

	public void setUserId(int userId)
	{
		this.userId = userId;
	}

	public String toString()
	{
		return this.getFirstName() + "," + this.getLastName() + "," + this.getUserName() + "," + this.getPassWord()
				+ "," + this.getUserId() + "\n";
	}

	public String toFile()
	{
		return this.getFirstName() + "," + this.getLastName() + "," + this.getUserName() + "," + this.getPassWord()
				+ "," + this.getUserId() + "\n";
	}

}
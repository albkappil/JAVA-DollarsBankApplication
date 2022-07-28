package com.dollarsbank.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;



public class Account implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private AtomicInteger ID_GENERATOR_SA = new AtomicInteger(1);
	
	private int userId;
	
	private String username;
	
	private String password;
	
	private double initialDeposit;
	
	private Customer customer;
	
	private List<SavingsAccount> sAList;



	public Account( int userId ,String username, String password, double initialDeposit, Customer customer) {
		super();
		this.userId = userId; 
		this.username = username;
		this.password = password;
		this.initialDeposit = initialDeposit;
		this.customer = customer;
		sAList = new ArrayList<SavingsAccount>();
	}


	
	
	public  AtomicInteger getID_GENERATOR_SA() {
		return ID_GENERATOR_SA;
	}




	public  void setID_GENERATOR_SA(AtomicInteger iD_GENERATOR_SA) {
		ID_GENERATOR_SA = iD_GENERATOR_SA;
	}




public int getUserId() {
	return userId;
}


public String getUsername() {
	return username;
}



public void setUsername(String username) {
	this.username = username;
}



public String getPassword() {
	return password;
}


public void setPassword(String oldPassword, String newPassword) {
	
	if(oldPassword.equals(this.getPassword())) 
		this.password = newPassword;
}


public double getInitalDeposit() {
	return initialDeposit;
}



public void setInitalDeposit(double initalDeposit) {
	this.initialDeposit = initalDeposit;
}



public Customer getCustomer() {
	return customer;
}



public void setCustomer(Customer customer) {
	this.customer = customer;
}


public List<SavingsAccount> getSAList() {
	return sAList;
}


public void setSAList(List<SavingsAccount> sAList) {
	this.sAList = sAList;
}



	
}

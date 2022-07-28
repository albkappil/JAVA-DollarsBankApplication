package com.dollarsbank.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class SavingsAccount implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private int id;
	
	private double interest;
	
	private double balance;
	
	private List<String> transaction;

	public SavingsAccount(int id,  double interest, double balance) {
		super();
		this.id = id;
		this.interest = interest;
		this.balance = balance;
		 transaction = new ArrayList<String>();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public double getInterest() {
		return interest;
	}

	public void setInterest(double interest) {
		this.interest = interest;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	public List<String> getTransaction() {
		return transaction;
	}

	public void setTransaction(List<String> transaction) {
		this.transaction = transaction;
	}
	
		

}

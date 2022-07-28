package com.dollarsbank.model;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.concurrent.atomic.AtomicInteger;

public class Customer implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static AtomicInteger ID_GENERATOR = new AtomicInteger(1);
	
	private int id;
	
	private String name;
	
	private String address;
	
	private String phoneNumber;
	
	private HashMap<String, String> additionalFields = new HashMap<>();

	public Customer( String name, String address, String phoneNumber) {
		super();
		this.id = ID_GENERATOR.getAndIncrement();
		this.name = name;
		this.address = address;
		this.phoneNumber = phoneNumber;
		
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getAdditionalFieldsValue(String fieldKey) {
		 String field ="";
		 if (additionalFields.containsKey(fieldKey))
			 field = additionalFields.get(fieldKey);
	            
		return field;
	}

	public void setAdditionalFields(String fieldKey, String fieldValue) {
		additionalFields.put(fieldKey, fieldValue);
		
	}
	
	@Override
	public String toString() {
		String fields = "+----------------------+\n"
				+ "| Customer Information |\n"
				+ "+----------------------+\n"
				+ "id: " + id 
				+ "\nName: " + name 
				+ "\nAddress: " + address
				+ "\nPhone Number: " + phoneNumber; 
		String addFields ="";
		if(!additionalFields.isEmpty()) {
			for (Entry<String, String> e : additionalFields.entrySet())
	          addFields += e.getKey() +": " + e.getValue() + "\n";
			return fields + "\n" + addFields;
		}
		else
			return fields;
	}
	
	

}

package com.dollarsbank.utility;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

import com.dollarsbank.model.Account;

public class FileStorage {

	File file = new File("AccountObjects.data");
	
	public void storeObjAcc(List<Account> accList) {
		try {
			file.delete();
			file = new File("AccountObjects.data");
			
			FileOutputStream out = new FileOutputStream(file);
			ObjectOutputStream writer = new ObjectOutputStream(out);
			for(Account acc : accList) {
				writer.writeObject(acc);
			}
			writer.close();
		} catch (FileNotFoundException e) {
			System.out.println("File not found");
		}catch (IOException e) {
			System.out.println("IO Exception");
		}
		
	}
	
	public List<Account> retriveObjAcc() {
		try {
			//file.createNewFile();
			
			FileInputStream out = new FileInputStream(file);
			ObjectInputStream reader = new ObjectInputStream(out);
			List<Account> accList = new ArrayList<Account>();
			while(true) {
				try {
					accList.add( (Account) reader.readObject());
				} catch (EOFException e) {
					System.out.println(ColorsUtility.BLACK_BOLD + "Retrived All Accounts from File Storage." + ColorsUtility.RESET);
					break;
				}
				
			}
			
			reader.close();
			return accList;
		}catch (FileNotFoundException e) {
			System.out.println("File not found");
			return null;
		}catch (IOException e) {
			System.out.println("IO Exception");
			return null;
		}
		catch (ClassNotFoundException e) {
			System.out.println("IO Exception");
			return null;
		}
		
	}
	
	
}

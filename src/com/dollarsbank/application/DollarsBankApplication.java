package com.dollarsbank.application;

import java.util.InputMismatchException;
import java.util.Scanner;


import com.dollarsbank.controller.DollarsBankController;
import com.dollarsbank.exceptions.IncorrectCredentialException;
import com.dollarsbank.model.Account;
import com.dollarsbank.model.Customer;
import com.dollarsbank.utility.ColorsUtility;

public class DollarsBankApplication {
	
	private static Scanner sc = new Scanner(System.in);
	
	private static DollarsBankController dBController = new DollarsBankController();
	
	
	public static void main(String[] args) {
		System.out.println(ColorsUtility.BLACK_BOLD + "Application is starting." + ColorsUtility.RESET);
		dBController.loadAccounts();
		promptUser();
		
		
		
		
		sc.close();
	}
	
	public static void promptUser() {
		boolean Choosing = true;
		while(Choosing) {
			System.out.println(ColorsUtility.GREEN_BOLD + "\n+------------------------+");
			System.out.println(ColorsUtility.GREEN_BOLD +"| WELCOME TO DOLLARSBANK |");
			System.out.println(ColorsUtility.GREEN_BOLD +"+------------------------+"+
					ColorsUtility.RESET);
			System.out.println(ColorsUtility.BLUE + "1. Create New Account\n2. Login\n3. Account List\n4. Exit"+  ColorsUtility.RESET);
			System.out.println("\nEnter Choice (1,2,3 or 4) :" );
			
			
			
			int choice = 0;
			String wrongChoice="";
			if(sc.hasNextInt()) {
				choice = sc.nextInt();
				switch(choice) {
				case 1:
					sc.nextLine();
					createNewAccout();
					break;
				case 2:
					sc.nextLine();
					loginUser();
	
					break;
				case 3: 
					boolean viewing = true;
					while(viewing) {
						System.out.println(ColorsUtility.GREEN_BOLD +"\n+---------------------------+");
						System.out.println(ColorsUtility.GREEN_BOLD +"|       Accounts List       |");
						System.out.println(ColorsUtility.GREEN_BOLD +"+---------------------------+\n");
						System.out.println(ColorsUtility.BLUE);
						dBController.viewAccList();
						System.out.println(ColorsUtility.RESET);
						viewing = viewingChoice();
					}	
					break;
				case 4:
					dBController.saveAccounts();
					System.out.println(ColorsUtility.GREEN_BOLD + "Exit was Successful. GOODBYE"+ ColorsUtility.RESET);
					Choosing = false;
					break;
				default:
					if(choice > 3 || choice < 1)
						System.out.println( ColorsUtility.RED + "Incorrect Choice. Please Try Again" + ColorsUtility.RESET);
				}
			}
			else if (sc.hasNextLine()) {
				wrongChoice= sc.nextLine();
				System.out.println(ColorsUtility.RED + wrongChoice +" is an Incorrect Choice. Please Try Again" + ColorsUtility.RESET);
			
			}
			
		
		}
		
	}
	
	
	public static void createNewAccout() {
	
		boolean choosing = true;
		
		while(choosing) {
			String name = ""; 
			String address = "";
			String contact = "";
			String username = "";
			String password = "";
			String verfiyPassword = "";
			Double initialDA = 0.00;
			
			System.out.println(ColorsUtility.GREEN_BOLD + "\n+-------------------------------+");
			System.out.println("| Enter Details for New Account |");
			System.out.println("+-------------------------------+" + ColorsUtility.RESET);
			
			System.out.print(ColorsUtility.BLUE);
			Boolean Successful = true;
			try {
				
				System.out.println("Customer Name:");
				name= sc.nextLine();

				System.out.println("Customer Address:");
				address = sc.nextLine();
				
				System.out.println("Customer Contact Number:");
				contact = sc.nextLine();
				System.out.println(ColorsUtility.RESET);
				if(contact.length() > 11 || contact.length() < 10  || !contact.matches("[0-9]+")) {
					System.out.println(ColorsUtility.RED + "Must be a Phone Number. Please Try Again"+ ColorsUtility.RESET);
					Successful = false;
				}
				else {
					System.out.print(ColorsUtility.BLUE);
					System.out.println("Set Username:");
					username = sc.nextLine();
					
					System.out.println("Set Password:");
					password = sc.nextLine();
					
					System.out.println("Set Password Again:");
					verfiyPassword = sc.nextLine();
					System.out.print(ColorsUtility.RESET);
					if(!password.equals(verfiyPassword)) {
						System.out.println(ColorsUtility.RED + "Password fields do not Match. Please Try Again" + ColorsUtility.RESET);
						Successful = false;
					}
					else {
						System.out.println(ColorsUtility.BLUE + " Enter Initial Deposit Amount:" + ColorsUtility.RESET);
						initialDA = sc.nextDouble();
					}
				}
			} catch (InputMismatchException e) {
				System.out.println(ColorsUtility.RED + "Incorrect Input. Please Try Again" + ColorsUtility.RESET);
				Successful = false;
				sc.nextLine();
				continue;
			}	
			
			if(Successful) {
				dBController.getAccountInfo(name, address, contact, username, password, initialDA);
				choosing = false;
			}
		}

	}
	
	public static void loginUser() {
		Boolean stillLogin = true;
		
		while(stillLogin) {
			System.out.println(ColorsUtility.GREEN_BOLD +"\n+---------------------+");
			System.out.println("| Enter Login Details |");
			System.out.println("+---------------------+"+ ColorsUtility.RESET);
			
			String username = "";
			String password = "";
			System.out.print(ColorsUtility.BLUE);
			
			System.out.println("Enter Username or UserID:");
			username= sc.nextLine();
			
			System.out.println("Enter Password:");
			password = sc.nextLine();
			try {
				Account acc = dBController.verifiyCred(username, password);
				stillLogin = false;
				greetCustomer(acc);
				System.out.print(ColorsUtility.RESET);
			} catch (IncorrectCredentialException e) {
				System.out.println(ColorsUtility.RED + "INVAILD CREDENTIALS. TRY AGAIN" + ColorsUtility.RESET);
				stillLogin = true;
				continue;
			}		
			
		}
		
		
	}

	private static void greetCustomer(Account acc) {
		//System.out.println("userID atomic value :" + acc.getUserId() +"\n\n\n");

		boolean Choosing = true;
		while(Choosing) {
			System.out.println(ColorsUtility.GREEN_BOLD + "\n+------------------------+");
			System.out.println("| WELCOME Customer |");
			System.out.println("+------------------------+"+ ColorsUtility.RESET);
			
			System.out.print(ColorsUtility.BLUE);
			System.out.println("1. Deposit Amount\n2. Withdraw Amount\n"
					+ "3. Funds Transfer\n4. View 5 Recent Transactions\n"
					+ "5. Display Customer Information\n6. Create New Saving Account \n7. Sign Out ");
			System.out.println(ColorsUtility.RESET);
			System.out.println("\nEnter Choice (1, 2, 3, 4, 5 , 6, 7) :");	
						
			try {
				int choice = 0;
				String wrongChoice="";
				if(sc.hasNextInt()) {
					choice = sc.nextInt();
					System.out.print(ColorsUtility.BLUE);
					switch(choice) {
					
					case 1:
						sc.nextLine();
						System.out.println("Enter SavingAccount ID");
						int depositSAid = sc.nextInt();
						System.out.println("Enter Deposit Amount");
						double depAmount = sc.nextDouble();
						
						dBController.deposit(depositSAid , depAmount, acc.getUserId(),false);
						break;
						
					case 2:
						sc.nextLine();
						System.out.println("Enter SavingAccount ID");
						int withdrawSAid = sc.nextInt();
						System.out.println("Enter Withdraw Amount");
						double withdrawAmount = sc.nextDouble();
						dBController.withdraw(withdrawSAid , withdrawAmount, acc.getUserId(),false);
						break;
						
					case 3:
						sc.nextLine();
						System.out.println("Enter Account ID to Transfer TO");
						int transferAidTO = sc.nextInt();
						System.out.println("Enter SavingAccount ID to Transfer TO");
						int transferSAidTO = sc.nextInt();
						System.out.println("----------------------------------------");
						System.out.println("Enter SavingAccount ID to Transfer FROM");
						int transferSAidFROM = sc.nextInt();
						System.out.println("Enter Transfer Amount");
						double transferAmount = sc.nextDouble();
						dBController.transfer(transferAidTO ,transferSAidTO ,acc.getUserId() , transferSAidFROM, transferAmount);
						break;
						
					case 4:
						boolean viewing = true;
						while(viewing) {
							sc.nextLine();
							System.out.println("Enter SavingAccount ID to view 5 recent transactions");
							int historySAid = sc.nextInt();
							dBController.recentTransact(acc.getUserId(), historySAid);
							viewing = viewingChoice();
						}
						break;
					case 5:
						boolean viewingInfo = true;
						while(viewingInfo) {
							System.out.print(ColorsUtility.BLUE);
							sc.nextLine();
							System.out.println(acc.getCustomer().toString());
							
							System.out.println("\nEnter (1) to add more customer information \n"
									+ "Enter (2) to go back");
							
								int viewChoice = sc.nextInt();
								if(viewChoice == 1) 
									addCustInfo(acc.getCustomer());
								else if(viewChoice == 2)
									viewingInfo = false;
								else {
									System.out.print(ColorsUtility.RESET);
									System.out.println(ColorsUtility.RED +"Invaild Input. Please Try Again" + ColorsUtility.RESET);
								}
						}
						break;
					case 6:
						
						boolean adding = true;
						while(adding) {
							System.out.print(ColorsUtility.BLUE);
							System.out.println("Enter Initial Deposit Amount:");
							try {
								double initialDA = sc.nextDouble();
								dBController.addSavingAccount(acc, initialDA);
								adding = false;
							} catch (InputMismatchException e) {
								System.out.print(ColorsUtility.RESET);
								System.out.println(ColorsUtility.RED + "Incorrect Input. Please Try Again" + ColorsUtility.RESET);
								sc.nextLine();
								continue;
							}
						
						}
						break;
					case 7:
						System.out.print(ColorsUtility.GREEN);
						System.out.println("Exit was Successful. GOODBYE");
						System.out.print(ColorsUtility.RESET);
						Choosing = false;
					default:
						if(choice > 6 || choice < 1) {
							System.out.println(ColorsUtility.RED +"Incorrect Choice. Please Try Again" + ColorsUtility.RESET);
							sc.nextLine();
						}
					}
				}
				else if (sc.hasNextLine()) {
					wrongChoice= sc.nextLine();
					System.out.println(ColorsUtility.RED + wrongChoice +" is an Incorrect Choice. Please Try Again" +  ColorsUtility.RESET);
				
				}
			
			} catch (InputMismatchException e) {
				System.out.println(ColorsUtility.RED +"Incorrect Input. Please Try Again" + ColorsUtility.RESET);
				sc.nextLine();
				continue;
			}
		}
		
	}

	private static void addCustInfo(Customer customer) {
		sc.nextLine();
		boolean addingInfo = true;
		while (addingInfo) {
			
			System.out.println(ColorsUtility.GREEN_BOLD + "\n+--------------------------+");
			System.out.println("| Add Customer Information |");
			System.out.println("+--------------------------+\n");
			System.out.print(ColorsUtility.RESET);
			System.out.print(ColorsUtility.BLUE);
			System.out.println("Enter the name of the new field you would like to create:");
			String newFieldKey  = sc.nextLine();
			System.out.println("Enter the value for that new field :");
			String newFieldValue = sc.nextLine();
			
			customer.setAdditionalFields(newFieldKey, newFieldValue);
			
			boolean checkAI = true;
			while(checkAI) {
				try {		
					System.out.print(ColorsUtility.BLUE);
					System.out.println(customer.toString());
					System.out.println("Enter (1) to add more customer information \n"
						+ "Enter (2) to go back");
				
				
					int viewChoice = sc.nextInt();
					if(viewChoice == 1) {
						addingInfo = true;
						checkAI = false;
					}
					else if(viewChoice == 2) {
						addingInfo = false;
						checkAI = false;
					}
					else {
						System.out.print(ColorsUtility.RESET);
						System.out.println(ColorsUtility.RED +"Invaild Input. Please Try Again"+ ColorsUtility.RESET);
						
					}
					sc.nextLine();
				}catch (InputMismatchException e) {
					System.out.print(ColorsUtility.RESET);
					System.out.println(ColorsUtility.RED +"Incorrect Input. Please Try Again" +ColorsUtility.RESET);
					sc.nextLine();
					continue;
				}
			}
		}
	}
	
	
 public static boolean viewingChoice() {
	 
	 
	
	 	while(true) {
			try {
				System.out.println(ColorsUtility.BLUE + "\n Enter 1 to go back." + ColorsUtility.RESET);
				int goBack = sc.nextInt();
				if(goBack == 1)
					return false;
				else 
					System.out.println(ColorsUtility.RED +"Invaild Input. Please Try Again"+ ColorsUtility.RESET);
				
			} catch (InputMismatchException e) {
				System.out.println(ColorsUtility.RED +"Incorrect Input. Please Try Again" +ColorsUtility.RESET);
				sc.nextLine();
				
			}
	 	}

 }
	
	
	
}

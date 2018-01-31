package com.bank.barclays.inventory;

import java.text.ParseException;
import java.util.Scanner;

import com.bank.barclays.inventory.dao.DaoImpl;
import com.bank.barclays.inventory.validator.RequestValidatorImpl;

public class InventoryManagement {
	public static void main(String[] args) throws ParseException {
		Scanner inputScanner = new Scanner(System.in);
		String inputCommand = null;
		String[] splitStr = null;
		do {

			System.out.println("Enter your command statement");
			inputCommand = inputScanner.nextLine();
			splitStr = inputCommand.split(" ");
			RequestValidatorImpl requestValidatorImpl = new RequestValidatorImpl();
			DaoImpl daoImpl = new DaoImpl();
			boolean validationSuccess = requestValidatorImpl.validate(splitStr);
			if (validationSuccess) {
				daoImpl.performCommand(splitStr);
			}
		} while (!inputCommand.equalsIgnoreCase("Exit"));
		inputScanner.close();
	}
}

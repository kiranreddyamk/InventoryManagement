package com.bank.barclays.inventory.validator;

public class RequestValidatorImpl {
	
	// Verifying if the given input statement is valid
	public boolean validate(String[] splitStr) {
		boolean boo = false;

		// create
		if (splitStr[0].equals("create")) {
			if (splitStr.length == 4) {
				if (isDouble(splitStr[2]) && isDouble(splitStr[3])) {
					return true;
				} else {
					System.out.println("Invalid Datatypes given for item price. Please check and try again.");
					return false;
				}
			}
		}

		// delete
		else if (splitStr[0].equals("delete")) {
			if (splitStr.length == 2) {
				return true;
			}
		}

		// updateBuy
		else if (splitStr[0].equals("updateBuy")) {
			if (splitStr.length == 3) {
				if (isInteger(splitStr[2])) {
					return true;
				} else {
					System.out.println("Invalid Datatypes given for item quantity. Please check and try again.");
					return false;
				}
			}
		}

		// updateSell
		else if (splitStr[0].equals("updateSell")) {
			if (splitStr.length == 3) {
				if (isInteger(splitStr[2])) {
					return true;
				} else {
					System.out.println("Invalid Datatypes given for item quantity. Please check and try again.");
					return false;
				}
			}
		}

		// report
		else if (splitStr[0].equals("report") && splitStr.length == 1) {
			return true;
		}

		else {
			System.out.println("Invalid Input! Please check you input statement and try again.");
			return false;
		}
		return boo;

	}

	// To Verify the given input sell and buy price are of type 'Double' or not
	public static boolean isDouble(String s) {
		boolean isValidDouble = false;
		try {
			Double.parseDouble(s);
			isValidDouble = true;
		} catch (NumberFormatException ex) {
			System.out.println(ex);
		}

		return isValidDouble;
	}

	// To Verify the given input quantity is of type 'Integer' or not
	public static boolean isInteger(String s) {
		boolean isValidInteger = false;
		try {
			Integer.parseInt(s);
			isValidInteger = true;
		} catch (NumberFormatException ex) {
			System.out.println(ex);
		}

		return isValidInteger;
	}

}

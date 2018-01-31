package com.bank.barclays.inventory.dao;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.bank.barclays.inventory.model.Item;

public class DaoImpl {

	private static List<Item> itemList = new ArrayList<Item>();

	private static double previousReportProfit = 0;

	private static boolean reportWasRan = false;

	private static List<Item> transactionList = new ArrayList<Item>();

	public void performCommand(String[] splitStr) {

		String commandName = splitStr[0];
		Item item = null;

		switch (commandName) {
		case "create":
			// sending 3 params
			item = getCreateItemFromInput(splitStr);
			createItem(item);
			break;
		case "delete":
			deleteItem(splitStr[1]);
			break;

		case "updateBuy":
			// sending 2 params
			item = getUpdateBuyOrSellItemFromInput(splitStr);
			updateBuy(item);
			break;
		case "updateSell":
			// sending 2 params
			item = getUpdateBuyOrSellItemFromInput(splitStr);
			updateSell(item);
			break;
		case "report":
			printReport(splitStr);
		}

	}

	private void createItem(Item item) {
		itemList.add(item);
		// System.out.println("Total Item = " + itemList.size());
	}

	private void deleteItem(String itemName) {
		// System.out.println("itemList before delete =" + itemList.size());

		itemList.removeIf(i -> i.getItemName().equalsIgnoreCase(itemName));

		// System.out.println("itemList after delete =" + itemList.size());
	}

	private void updateBuy(Item item) {
		for (Item i : itemList) {
			if (i.getItemName().equalsIgnoreCase(item.getItemName())) {
				// System.out.println("Quantity before update = " +
				// i.getItemQuantity() + " of " + i.getItemName());
				i.setItemQuantity(i.getItemQuantity() + item.getItemQuantity());
				// System.out.println("Quantity after update = " +
				// i.getItemQuantity() + " of " + i.getItemName());
			}
		}
	}

	private void updateSell(Item item) {
		int quantityDifference = 0;
		for (Item i : itemList) {
			if (i.getItemName().equalsIgnoreCase(item.getItemName())) {
				// System.out.println("Quantity before update = " +
				// i.getItemQuantity() + " of item Name " + i.getItemName());
				quantityDifference = i.getItemQuantity() - item.getItemQuantity();
				if (quantityDifference >= 0) {
					i.setItemQuantity(quantityDifference);
					Item transactionItem = new Item();
					transactionItem.setItemName(item.getItemName());
					transactionItem.setCostPrice(i.getCostPrice());
					transactionItem.setSellPrice(i.getSellPrice());
					transactionItem.setItemQuantity(item.getItemQuantity());
					transactionList.add(transactionItem);
					// System.out.println("item Name = " +
					// transactionItem.getItemName() + " buy price=" +
					// transactionItem.getCostPrice() + " sell price = "+
					// transactionItem.getSellPrice() + " quan= "+
					// item.getItemQuantity() );
				} else {
					System.out.println("Sell not possible please reduce Quantity");
					break;
				}
				// System.out.println("Quantity after update = " +
				// i.getItemQuantity() + " of item Name" + i.getItemName());
			}
		}
	}

	private Item getCreateItemFromInput(String[] splitStr) {
		Item item = new Item();
		item.setItemName(splitStr[1]);
		item.setCostPrice(roundOfTwoDecimals(Double.parseDouble(splitStr[2])));
		item.setSellPrice(roundOfTwoDecimals(Double.parseDouble(splitStr[3])));
		// Initially setting Quantity to 0 also there was no mention about this
		// in the requirement
		item.setItemQuantity(0);
		return item;
	}

	private double roundOfTwoDecimals(double price) {
		return Math.round(price * 100) / 100.00;
	}

	private Item getUpdateBuyOrSellItemFromInput(String[] splitStr) {
		Item item = new Item();
		item.setItemName(splitStr[1]);
		item.setItemQuantity(Integer.parseInt(splitStr[2]));
		return item;
	}

	private void printReport(String[] splitStr) {
		// sorting the array before printing the report
		Collections.sort(itemList, (Item i1, Item i2) -> i1.getItemName().compareTo(i2.getItemName()));
		double totalValue = 0;
		double itemValue = 0;
		double profitTotal = 0;
		if (splitStr.length == 1) {
			System.out.println("       Inventory Report");
			System.out.println("Item Name  BoughtAt    Sold At  AvailableQty  Value");
			System.out.println("---------  ---------   -------  -----------   ---------");
			for (Item i : itemList) {
				itemValue = i.getCostPrice() * i.getItemQuantity();
				System.out.println(i.getItemName() + "        " + i.getCostPrice() + "        " + i.getSellPrice()
						+ "        " + i.getItemQuantity() + "          " + itemValue);

				totalValue += itemValue;
			}

			System.out.println("-------------------------------------------------------------");
			System.out.println("Total Value: " + "                                      " + totalValue);

			if (reportWasRan) {
				for (Item i : transactionList) {
					// System.out.println(i.getSellPrice() + " " +
					// i.getCostPrice() + " " + i.getItemQuantity());
					profitTotal += ((i.getSellPrice() - i.getCostPrice()) * i.getItemQuantity());
				}
				// System.out.println("profitTotal =" + profitTotal);
				// System.err.println("previousReportProfit = "+
				// previousReportProfit);
				System.out.println("Profit since previous report                                    "
						+ (profitTotal - previousReportProfit));
			} else {
				// Requirement was not clear as we will display the profit since
				// previous report ran
				previousReportProfit = 0;
			}
			reportWasRan = true;
			transactionList = new ArrayList<Item>();
		}
	}
}

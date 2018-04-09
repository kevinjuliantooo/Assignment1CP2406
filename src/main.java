import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.*;

public class main {


	private static String userPassword;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		main();													// Start from main function
	}

	private static void main() {
		// TODO Auto-generated method stub
		Scanner scanOption = new Scanner(System.in);				// Get Input from user
		System.out.println("Welcome to fastfood restaurant");		// Statement to welcome user
		System.out.println("This is for... (Enter Number)");		// Asking the user are they Admin, or Customer. User can close program too
		System.out.println("1. Admin\n2. Customer\n3. Exit");		// User will choose


		while (true) {
			int userType = scanOption.nextInt();					// Input from user (respond)
			if (userType == 1) {									// User choose 1
				adminLogin(scanOption);							// Go to adminLogin()
				break;											// Break while loop 
			} else if (userType == 2) {							// User choose 2
				customerLogin();									// Go to customerLogin()
				break;											// Break while loop
			} else if (userType == 3){							// User choose 3
				System.exit(0);									// Exit program
			} else {												// No option
				System.out.println("There's no option here");	// Statement no option
			}
		}
	}

	public static void adminLogin(Scanner scanOption) {
		System.out.println("Enter the password ");				// Statement enter password
		while (true) {											
			userPassword = scanOption.next();					// Get user password input
			if (userPassword.equals("A12345678")) {				// If password "A12345678"
				adminMenu(scanOption);							// Go to adminMenu() function
				break;											// Break while loop
			} else {											
				System.out.println("Please try again");			// Statement password wrong
			}
		}
	}


	private static void adminMenu(Scanner scanOption) {
		// TODO Auto-generated method stub
		System.out.println("Hello admin");													// Welcome admin
		while (true){								
			System.out.println("1. See Income\t2. Add Menu\n3. Delete Menu\t4. Back Menu");	// admin need to choose between number 1, 2, 3, or 4
			String adminChoose = scanOption.next();											// Get user input
            switch (adminChoose) {
                case "1": incomeData(); break;												// If user choose "1" go to incomeData() function
                case "2": addMenu(scanOption); break;											// If user choose "2" go to addMenu() function
                case "3": deleteMenu(scanOption); break;										// If user choose "3" go to deleteMenu() function
                case "4": main(); break;														// If user choose "4" go to main() function
                default:																		// User validation
                    System.out.println("You enter the wrong thing, try again...");			// Statement wrong number
            }
		}
	}

	private static void deleteMenu(Scanner scanOption) {
		// TODO Auto-generated method stub
		String fileName = "doc/data.csv";													// Get data from file provided
		File file = new File(fileName); //Read about file										// Read file
		List<List<String>> menuList = new ArrayList<>();										// List of menuList
		

		try {
			Scanner inputMenu = new Scanner(file);											// Get input from user
			System.out.println("Code \t\tFood\t\t\tPrice");									// coloum of food list
			while (inputMenu.hasNext()) {													// Read the file
				String data = inputMenu.nextLine();											// Go enter
				String[] items = data.split(",");											// Split with ","
				menuList.add(Arrays.asList(items));											// Add item into list
			}
			inputMenu.close();																// Close the file
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Food");
		int foodNo = 0;																		// Number of food
		for (List<String> line : menuList) {													// Get all data from list
			if (line.get(0).contains("F")) {													// If the code is F
				System.out.println(menuList.get(foodNo).get(0) + foodNo + "\t\t" + menuList.get(foodNo).get(1) + "\t\t $" + menuList.get(foodNo).get(2));

			}
            foodNo++;																		// Get to next food

		}
		System.out.println("\nDrinks");
		int drinkNo = 0;																		// Number of drink
		for (List<String> i : menuList) {													// Get all data from list
			System.out.println(menuList.get(drinkNo).get(0) + drinkNo + "\t\t" + menuList.get(drinkNo).get(1) + "\t\t $" + menuList.get(drinkNo).get(2));
			drinkNo++;
		}
		System.out.print("Choose the code menu you want to delete: ");						// Statement to delete food or drink
		int deleteChoose = scanOption.nextInt();												// Get input from user
		if (menuList.get(deleteChoose) != null) {											// If menu choosen by user
			menuList.remove(deleteChoose);													// Remove the menu
		}
		int newMenuNo = 0;
		
		try {
			//Print the new menu and store it into new list
			FileWriter fw = new FileWriter("doc/data.csv"); 
			System.out.println("Here's the menu:");
			for (List<String> i : menuList) {
				System.out.println(menuList.get(newMenuNo).get(0) + newMenuNo + "\t\t" + menuList.get(newMenuNo).get(1) + "\t\t $" + menuList.get(newMenuNo).get(2));
				fw.append(i.get(0));
		        fw.append(',');
		        fw.append(i.get(1));
		        fw.append(',');
		        fw.append(i.get(2));
		        fw.append('\n');
				newMenuNo++;
			}
			fw.flush();
			fw.close();
			System.out.println("Menu Deleted");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	private static void addMenu(Scanner scanOption) {
		// Rest of code is similar like delete menu
		FileWriter pw;
		StringBuilder sb;
		try {
			pw = new FileWriter("doc/data.csv",true);
			sb = new StringBuilder();
			// Starting get the new menu from user
			while (true) {
				System.out.println("Enter the menu code: \nD. Drink\nF. Food");
				String foodType = scanOption.next();
				if (foodType.contains("D") || foodType.contains("F")) {
					pw.append(foodType);
			        pw.append(',');
					break;
				} else {
					System.out.print("Sorry... Wrong Code");
				}
			}
			System.out.print("Enter the menu Name: ");
			String menuName = scanOption.next();
			pw.append(menuName);
	        pw.append(',');



			while (true) {													// Loop that will detele the .0 if not decimal
                DecimalFormat format = new DecimalFormat("0.#");
                System.out.print("Enter the " + menuName + " Price: $");
                Double menuPrice = scanOption.nextDouble();
                if (menuPrice < 0){
                    System.out.println("Price can not below than 0");
                } else {
                    String newMenuPrice = format.format(menuPrice).toString();
                    pw.append(newMenuPrice);
                    pw.append('\n');
                    break;
                }
				}
            pw.flush();
            pw.close();

	        System.out.println("Menu Added");

		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

	private static void incomeData() {
		// Rest have the same code like delete menu. This function will show the income data
		String fileName = "doc/income.csv";
		File file = new File(fileName); //Read about file
		List<List<String>> incomeList = new ArrayList<>();
		

		try {
			Scanner inputMenu = new Scanner(file);
			System.out.println("Code \t\tFood\t\t\tPrice");
			while (inputMenu.hasNext()) {
				String data = inputMenu.nextLine();
				String[] items = data.split(",");
				incomeList.add(Arrays.asList(items));
			}
			inputMenu.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			System.out.println("Food Sold");
			int foodNo = 0;
			double totalFoodIncome = 0;
			for (List<String> line : incomeList) {
				if (line.get(0).contains("F")) {
					System.out.println(incomeList.get(foodNo).get(1) + "\t\t $" + incomeList.get(foodNo).get(2));
					double doubleFoodPrice = Double.parseDouble(incomeList.get(foodNo).get(2));
					totalFoodIncome += doubleFoodPrice;
				}
	            foodNo++;
			}
			System.out.println("\t\tTotal: " + totalFoodIncome);
			System.out.println("\nDrinks Sold");
			int drinkNo = 0;
			double totalDrinkIncome = 0;
			for (List<String> line : incomeList) {
				if (line.get(0).contains("D")) {
					System.out.println(incomeList.get(drinkNo).get(1) + "\t\t $" + incomeList.get(drinkNo).get(2));
					double doubleDrinkPrice = Double.parseDouble(incomeList.get(drinkNo).get(2));
					totalDrinkIncome += doubleDrinkPrice;
				}
	            drinkNo++;
			}
			System.out.println("\t\tTotal: " + totalDrinkIncome);
		} catch (IndexOutOfBoundsException e) {
				e.printStackTrace();
		}
	}

	public static void customerLogin() {
		// Showing all menu with go to foodList() function
		System.out.println("Here's our menu:");
		foodList();
		
		
		//for

	}

	public static void foodList() {
		// Rest of program in this method is same like deleteMenu
		String fileName = "doc/data.csv";
		File file = new File(fileName); //Read about file
		List<List<String>> menuList = new ArrayList<>();
		

		try {
			Scanner inputMenu = new Scanner(file);
			System.out.println("Code \t\tFood\t\t\tPrice");
			while (inputMenu.hasNext()) {
				String data = inputMenu.nextLine();
				String[] items = data.split(",");
				menuList.add(Arrays.asList(items));
			}
			inputMenu.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Food");
		int foodNo = 0;
		for (List<String> line : menuList) {
			if (line.get(0).contains("F")) {
				System.out.println(menuList.get(foodNo).get(0) + foodNo + "\t\t" + menuList.get(foodNo).get(1) + "\t\t $" + menuList.get(foodNo).get(2));

			}
            foodNo++;

		}
		System.out.println("\nDrinks");
		int drinkNo = 0;
		for (List<String> i : menuList) {
			if (i.get(0).contains("D")) {
				System.out.println(menuList.get(drinkNo).get(0) + drinkNo + "\t\t" + menuList.get(drinkNo).get(1) + "\t\t $" + menuList.get(drinkNo).get(2));
			}
            drinkNo++;
		}
		chooseMenu(menuList);				// Go to choose menu and bring the list
		}

	public static void chooseMenu(List<List<String>> menuList) {
		// User as customer will choose the menu they want
		Scanner inputMenu = new Scanner(System.in); 
		List<List<String>> customerList = new ArrayList<>();
		
		double totalPrice = 0;
		while (true) {
			while (true) {
				try {
					System.out.println("Choose your food and drink (Just enter the number)");
					int chooseItem = inputMenu.nextInt();										// User choose the food 
					String[] oneItem = new String[3];											// The list for ONE ITEM
					System.out.println("Your menu added " + menuList.get(chooseItem).get(1) + ". Price: $" + menuList.get(chooseItem).get(2));
					oneItem[0] = menuList.get(chooseItem).get(0);
					oneItem[1] = menuList.get(chooseItem).get(1);
					oneItem[2] = menuList.get(chooseItem).get(2);
					double doublePrice = Double.parseDouble(menuList.get(chooseItem).get(2));
					totalPrice += doublePrice; 													// Calculate the price
					customerList.add(Arrays.asList(oneItem));									// Add into array
					System.out.println(customerList);
					break;
				} catch (IndexOutOfBoundsException e) {
					System.out.println("The food/drink is not exist");
					inputMenu.next();   // or scanner.nextLine()
				} catch (InputMismatchException e) {
					System.out.println("Put the number please");
					inputMenu.next();   // or scanner.nextLine()
				}
			}
			
			
			while(true) {
				System.out.println("More food/drink? (Y/N)");		// User choose is they want to add food/drink or not
				String yesNoInput = inputMenu.next();				// get user input
				if (yesNoInput.contains("y".toUpperCase())) {		// If user want to add food/drink
					// Program will Continue
					break;
				} else if (yesNoInput.contains("n".toUpperCase())) {	// If user want to add food/ drink
					receipt(customerList, totalPrice);				// Go to receipt() function
					System.exit(0);									
				}
				while (!yesNoInput.contains("y".toUpperCase()) || !yesNoInput.contains("n".toUpperCase())) { 		// User validation
				System.out.println("You put the wrong thing. Try again");											// Statement user validation
				break;
				} 
			}
		}
		}
  
	private static void receipt(List<List<String>> customerList, double totalPrice) {
		// This method will print the e-receipt
		FileWriter pw;
        StringBuilder sb;
		try {
			pw = new FileWriter("doc/income.csv",true);
			sb = new StringBuilder();
			String border = "**********************";
			System.out.println(border + "Shop Name" + border + "\n" + border + border);
			System.out.println("All Food and Drink \t\t\tPrice");
			for (List<String> i : customerList) {
				System.out.println(i.get(1) + "\t\t\t\t$" + i.get(2));
				pw.append(i.get(0));
		        pw.append(',');
		        pw.append(i.get(1));
		        pw.append(',');
		        pw.append(i.get(2));
		        pw.append('\n');
			}
			System.out.println("\n\t\t\t\t Total: $" +totalPrice);
			System.out.println(border + "Thankyou for buying" + border);
			pw.flush();
			pw.close();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		main();							// Go to main() function
		}
}
	
	





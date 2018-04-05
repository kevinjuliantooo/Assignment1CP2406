import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.*;

public class main {


	private static String userPassword;
	private static StringBuilder sb;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		main();
	}

	private static void main() {
		// TODO Auto-generated method stub
		Scanner scanOption = new Scanner(System.in);
		System.out.println("Welcome to fastfood restaurant");
		System.out.println("This is for... (Enter Number)");
		System.out.println("1. Admin\n2. Customer\n3. Exit");


		while (true) {
			int userType = scanOption.nextInt();
			if (userType == 1) {
				AdminLogin(scanOption);
				break;
			} else if (userType == 2) {
				CustomerLogin();
				break;
			} else if (userType == 3){
				System.exit(0);
			} else {
				System.out.println("There's no option here");
			}
		}
	}

	public static void AdminLogin(Scanner scanOption) {
		//scanOption = new Scanner (System.in);
		System.out.println("Enter the password ");
		while (true) {
			userPassword = scanOption.next();
			if (userPassword.equals("A12345678")) {
				adminMenu(scanOption);
				break;
			} else {
				System.out.println("Please try again");
			}
		}
	}


	private static void adminMenu(Scanner scanOption) {
		// TODO Auto-generated method stub
		System.out.println("Hello admin");
		while (true){
			System.out.println("1. See Income\t2. Add Menu\n3. Delete Menu\t4. Back Menu");
			int adminChoose = scanOption.nextInt();
			switch (adminChoose) {
				case 1: incomeData(); break;
				case 2: addMenu(scanOption); break;
				case 3: deleteMenu(scanOption); break;
				case 4: main();
			
		}
		}
	}

	private static void deleteMenu(Scanner scanOption) {
		// TODO Auto-generated method stub
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
		int menuNo = 0;
		for (List<String> i : menuList) {
			System.out.println(menuList.get(menuNo).get(0) + menuNo + "\t\t" + menuList.get(menuNo).get(1) + "\t\t $" + menuList.get(menuNo).get(2));
            menuNo++;
		}
		System.out.print("Choose the code menu you want to delete: ");
		int deleteChoose = scanOption.nextInt();
		if (menuList.get(deleteChoose) != null) {
			menuList.remove(deleteChoose);
		}
		int newMenuNo = 0;
		
		try {
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
		// TODO Auto-generated method stub
		FileWriter pw;
		try {
			pw = new FileWriter("doc/data.csv",true);
			sb = new StringBuilder();
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
			while (true) {
				try {
					System.out.print("Enter the " + menuName + " Price: $");
					Double menuPrice = scanOption.nextDouble();
					pw.append(menuPrice.toString());
					break;
				} catch (InputMismatchException e) {
					System.out.print("In number please");
					
				}
			}
			pw.append('\n');
	        System.out.println("Menu Added");
			pw.flush();
			pw.close();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

	private static void incomeData() {
		// TODO Auto-generated method stub
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

	public static void CustomerLogin() {
		System.out.println("Here's our menu:");
		foodList();
		
		
		//for

	}

	public static void foodList() {
		
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
		chooseMenu(menuList);
		}

	public static void chooseMenu(List<List<String>> menuList) {
		Scanner inputMenu = new Scanner(System.in); 
		List<List<String>> customerList = new ArrayList<>();
		
		double totalPrice = 0;
		while (true) {
			while (true) {
				try {
					System.out.println("Choose your food and drink (Just enter the number)");
					int chooseItem = inputMenu.nextInt();
					String[] oneItem = new String[3];
					System.out.println("Your menu added " + menuList.get(chooseItem).get(1) + ". Price: $" + menuList.get(chooseItem).get(2));
					oneItem[0] = menuList.get(chooseItem).get(0);
					oneItem[1] = menuList.get(chooseItem).get(1);
					oneItem[2] = menuList.get(chooseItem).get(2);
					double doublePrice = Double.parseDouble(menuList.get(chooseItem).get(2));
					totalPrice += doublePrice;
					customerList.add(Arrays.asList(oneItem));
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
				System.out.println("More food/drink? (Y/N)");
				String yesNoInput = inputMenu.next();
				if (yesNoInput.contains("y".toUpperCase())) {
					// Program will Continue
					break;
				} else if (yesNoInput.contains("n".toUpperCase())) {
					receipt(customerList, totalPrice);
					System.exit(0);
				}
				while (!yesNoInput.contains("y".toUpperCase()) || !yesNoInput.contains("n".toUpperCase())) {
				System.out.println("You put the wrong thing. Try again");
				break;
				} 
			}
		}
		}
  
	private static void receipt(List<List<String>> customerList, double totalPrice) {
		// TODO Auto-generated method stub
		FileWriter pw;
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
		main();

		
		
		}
		
		


}
	
	





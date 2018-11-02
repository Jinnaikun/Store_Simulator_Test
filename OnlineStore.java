/* 
 * File name: OnlineStore.java
 * Author: Evan Lieu
 * Class: CS2336.503
 * Professor: Vidroha Debroy
 * 5 October, 2018
 * 
 */ 

// This program simulates an online store where the user can store up to five items in their cart and checkout.
// The program has menu options that allow the user to sort the items by price

import java.text.DecimalFormat;
import java.util.Scanner;

public class OnlineStore 

{

	public static void main(String[] args) 
	
	{
		// Initialize the arrays of books and book prices
		// These arrays will have the name of the books for sale and their prices
		String [] books = {"Intro to Java", "Intro to C++", "Python", "Perl", "C#"};
		double [] bookPrices = {45.99, 89.34, 100.00, 25.00, 49.99};		
		
		// Initialize the arrays of dvds and dvd prices
		// These arrays will have the name of the dvds for sale and their prices
		String [] dvds = {"Snow White", "Cinderella", "Dumbo", "Bambi", "Frozen"};
		double [] dvdPrices = {19.99, 24.99, 17.99, 21.99, 24.99};
		
		// Initialize the arrays for the user's cart
		// The arrays will store the name of the item and the price of the item in the user's cart
		String [] itemsInCart = new String[5];
		double [] pricesOfItemsInCart = new double[5];
		int itemsInCartCounter = 0; //Keeps track of how many items are in the cart
		
		int userMainMenuChoice; //Holds the user's input for the main menu
		int userAddToCartChoice; //Holds the user's inventory choice
		boolean repeatMainMenu; //Checks to see if the menu should be displayed again
		
		//This loop displays the main menu until the user decides to exit
		//This loop also carries out the user's request from the menu by calling the method attached their choice
		do
		{
			userMainMenuChoice = displayMenu(); //Shows the menu and takes the user's input
			
			//Switch statement takes the user's input and calls a method depending on the user's input.
			switch(userMainMenuChoice)
			{
			case 1: //Sort book prices from low to high
				displayArrays(books, bookPrices, "Books");
				break;
			case 2: //Sort DVD prices from low to high
				displayArrays(dvds, dvdPrices, "DVDs");
				break;
			case 3: //Show the sorted book prices and ask for a purchase
				displayArrays(books, bookPrices, "Books");
				try //Check user input by checking the array's boundaries
				{
					userAddToCartChoice = getInventoryNumber();
					
					if(userAddToCartChoice != -1)
					{
						books[(userAddToCartChoice-1)] = books[(userAddToCartChoice-1)];
					}
				}
				catch(java.lang.ArrayIndexOutOfBoundsException e)
				{
					System.out.println("Invalid Input");
					System.out.println("Returning to main menu");
					break;
				}
				if(userAddToCartChoice != -1)
				{
					if(itemsInCartCounter < itemsInCart.length)
					{
						itemsInCart[itemsInCartCounter] = books[(userAddToCartChoice-1)];
						pricesOfItemsInCart[itemsInCartCounter] = bookPrices[(userAddToCartChoice-1)];
						itemsInCartCounter++;
					}
					else
					{
						System.out.println("Cart is full. Remove item(s) to add more to the cart.");
						System.out.println(" ");
					}
				}
				else
				{
					System.out.println("Returning to the main menu...");
				}
				break;
			case 4: //Show the sorted DVDs prices and ask for a purchase
				displayArrays(dvds, dvdPrices, "DVDs");
				try //Check user input by checking the array's boundaries
				{
					userAddToCartChoice = getInventoryNumber();
					if(userAddToCartChoice != -1)
					{
						dvds[(userAddToCartChoice-1)] = dvds[(userAddToCartChoice-1)];
					}
				}
				catch(java.lang.ArrayIndexOutOfBoundsException e)
				{
					System.out.println("Invalid input");
					System.out.println("Returning to main menu");
					break;
				}
				if(userAddToCartChoice != -1)
				{
					if(itemsInCartCounter < itemsInCart.length)
					{
						itemsInCart[itemsInCartCounter] = dvds[(userAddToCartChoice-1)];
						pricesOfItemsInCart[itemsInCartCounter] = dvdPrices[(userAddToCartChoice-1)];
						itemsInCartCounter++;
					}
					else
					{
						System.out.println("Cart is full. Remove item(s) to add more to the cart.");
					}
				}
				else
				{
					System.out.println("Returning to the main menu...");
				}
				break;
				
			case 5: //User views the items in their cart and the total cost
				displayArrays(itemsInCart, pricesOfItemsInCart);
				break;			
			case 6://User goes through checkout process and clears the cart.
				displayArrays(itemsInCart, pricesOfItemsInCart);
				//If the cart is empty, the thanks for shopping message will not display
				if(itemsInCartCounter != 0) //Display this message if the user's cart is not empty.
				{
					System.out.println("Thanks for shopping!");
				}
				
				clearArrays(itemsInCart, pricesOfItemsInCart);
				itemsInCartCounter = 0; //Cart is now empty, updating counter
				break;
			case 7://User cancels their purchase, clearing the cart.
				clearArrays(itemsInCart, pricesOfItemsInCart);
				System.out.println("Your order has been canceled");
				itemsInCartCounter = 0; //Cart is now empty, updating counter
				break;
			case 8:
				break;
			default:
				System.out.println("This option is not acceptable");
				break;
			}
				
			
			//If/else checks to see if the main menu should be brought up again
			if(userMainMenuChoice != 8)
			repeatMainMenu = true;
			else
			repeatMainMenu = false;
		} while (repeatMainMenu);
	}
	
	/// This method displays a menu that asks for the user's input and returns the user's input
	public static int displayMenu()
	{
		//Input object used to get user's input and menuChoice stores their input.
		Scanner input = new Scanner(System.in);
		int menuChoice;
		
		//These outputs display the main menu
		System.out.println("**Welcome to the Comets Books and DVDs Store**");
		System.out.println(" ");
		System.out.println("Choose from the following options");
		System.out.println("1 - Browse books inventory (price low to high)");
		System.out.println("2 - Browse DVDs inventory (price low to high)");
		System.out.println("3 - Add a book to the cart");
		System.out.println("4 - Add a DVD to the cart");
		System.out.println("5 - View cart");
		System.out.println("6 - Checkout");
		System.out.println("7 - Cancel Order");
		System.out.println("8 - Exit Store");
		
		//Taking the user's integer input
		try
		{
			menuChoice = input.nextInt();
		}
		catch(java.util.InputMismatchException e) //If user enters in anything other than an integer
		{
			return -999;
		}
		return menuChoice;
		
	}

	///Displays the prices of the items from low to highest.
	public static void displayArrays(String[] itemsArray, double[] pricesArray, String itemType)
	{
		//Formatting the display
		System.out.format("|%-20s|", "Inventory Number");
		System.out.format("|%-20s|", itemType);
		System.out.format("|%-20s|", "Prices");
		System.out.printf("%n", ' ');
		System.out.println("------------------------------------------------------------------");
		
		//Holds a copy of the prices array and inventory numbers to keep the original the same
		double[][] pricesCopy = new double[2][5];

		//Makes the copy, puts the values of the original array into the copy.
		for(int i = 0; i < pricesCopy[0].length; i++)
		{
			pricesCopy[0][i] = pricesArray[i]; //first array keeps track of prices
			pricesCopy[1][i] = i+1; //second array keeps track of inventory number
		}
		
		//Sort the copy of the prices from low to high
		for(int i = 0; i < pricesCopy[0].length; i++)
		{
			//These variables hold the current smallest value as a double and it's index as an integer
			double currentMinPrice = pricesCopy[0][i];
			int currentMinIndex = i;
			
			//Holds the array copy's inventory number that will be swapped
			double inventoryNumToSwap;
			
			//This loop finds the current minimum in the array by checking the other values
			for(int x = i+1; x < pricesCopy[0].length; x++)
			{
				if(currentMinPrice > pricesCopy[0][x])
				{
					currentMinPrice = pricesCopy[0][x];
					currentMinIndex = x;
				}
			}
			
			//Swapping the found lowest price, if the prices are not the same
			if(currentMinIndex != i)
			{
				pricesCopy[0][currentMinIndex] = pricesCopy[0][i];
				pricesCopy[0][i] = currentMinPrice;
				inventoryNumToSwap = (pricesCopy[1][i]); //Store inventory number
				pricesCopy[1][i] = pricesCopy[1][currentMinIndex];
				pricesCopy[1][currentMinIndex] = inventoryNumToSwap;
				
			}
		}
		
		//Print the rest of the display
		for(int i = 0; i < pricesCopy[0].length; i++)
		{
			System.out.format("|%-20s|", pricesCopy[1][i]);
			System.out.format("|%-20s|", itemsArray[((int)pricesCopy[1][i])-1]);
			System.out.format("|%-20s|", pricesCopy[0][i]);
			System.out.printf("%n", ' ');
			System.out.println("------------------------------------------------------------------");
			
		}
	}
	
	///This method reads the user's inventory number choice and returns it.
	public static int getInventoryNumber()
	{
		//These variables hold the user's choice to return to the main function
		Scanner input = new Scanner(System.in);
		int addToCart;
		
		//Prompt user for input
		System.out.println("Enter the inventory number you wish to purchase from.");
		System.out.println("Enter in -1 if you wish to go back to the main menu and not select an inventory number.");
		
		//Checking user's input
		try
		{
			addToCart = input.nextInt();
		}
		catch(java.util.InputMismatchException e) //If user enters in anything other than an integer
		{
			System.out.println("Invalid input");
			return -1;
		}
		return addToCart;
	}
	
	///Method overloads displayArrays and only takes in the items in the cart and their prices.
	///This shows the current items in the user's cart and their current total + tax.
	public static void displayArrays(String[] itemsInCart, double [] pricesOfCartItems)
	{
		int checkCartCounter = 0; //Counter checks to see if the cart is empty
		
		//Loop checks through the prices array to see if one of the arrays are empty
		for(int i = 0; i <  pricesOfCartItems.length; i++)
		{
			if(pricesOfCartItems[i] == 0)
			{
				checkCartCounter++;
			}
		}
		
		//If the cart is not empty, then it will display the cart contents and the total cost + tax.
		if(checkCartCounter != pricesOfCartItems.length)
		{
			//Format and display the cart
			DecimalFormat df = new DecimalFormat ("#.00");
			System.out.format("|%-20s|", "Items");
			System.out.format("|%-20s|", "Prices");
			System.out.printf("%n", ' ');
			System.out.println("--------------------------------------------");
			
			//Goes through the items in the cart
			for(int i = 0; i < itemsInCart.length; i++)
			{
				//If there is an item in the cart, print it.
				if(pricesOfCartItems[i] != 0)
				{
					System.out.format("|%-20s|", itemsInCart[i]);
					System.out.format("|%-20s|", pricesOfCartItems[i]);
					System.out.printf("%n", ' ');
				}
			}
			
			//Displays the total in a certain format
			System.out.println("--------------------------------------------");
			System.out.format("|%-20s|", "Total + Tax");
			System.out.format("|%-20s|", df.format(getTotal(pricesOfCartItems)));
			System.out.printf("%n", ' ');
			 
		}
		else //When the cart is empty, it will tell the user that the cart is empty and go back to the main menu
		{
			System.out.println("your cart is empty");
			System.out.println(" ");
		}
	}
	
	///This method gets the prices from the user's cart and returns the total price + tax
	public static double getTotal(double [] priceArray)
	{
		
		double total = 0; //Holds the total that will be returned
		double tax = .0825; //The value of the tax
		
		//For-each/enhanced for loop that adds all of the prices up
		for(double price: priceArray)
		{
			total += price;
		}
		
		//Calculating the total + tax
		total+=(total*tax);
		
		return total;
	}
	
	///This method empties/clears the cart (string array and double array) to default values (null and 0).
	public static void clearArrays(String [] cart, double [] pricesInCart)
	{
		//Clears the item names
		for(int i = 0; i < cart.length; i++)
		{
			cart[i] = null;
		}
		
		//Clears the prices
		for(int i = 0; i < pricesInCart.length; i++)
		{
			pricesInCart[i] = 0;
		}
	}
}

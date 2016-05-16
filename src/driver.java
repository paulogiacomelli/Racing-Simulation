package Problem2;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;


public class driver {

	static int added = 0;
	static String previousName = "", currentName;
	static String firstName, lastName, carType;
	static int score;
	static Racers myRacers;
	static ArrayList<Racers> data = new ArrayList<Racers>();
	static Racers[] allRacers = new Racers[5];
	static Race race1, race2, race3, race4, race5;
	static Scanner input = new Scanner(System.in);
	static String lastNameSort = "";
	static String searchedName;

	static int[] database;
	static int size;

	public static void main(String[] args) throws FileNotFoundException {

		String yesorno;

		// Read the Racers in.
		readRacers();



		System.out.println("Before we start, would you like to add a new racer? (Yes or No)");
		// Error Check Handling
		while(input.hasNextInt()){
			System.out.println("Sorry, Invalid Input. Please enter (Yes or No, Y, N)");
			input.next();
		}
		yesorno = input.next();


		if(yesorno.equalsIgnoreCase("yes") || yesorno.equalsIgnoreCase("y")) {
			addRacer();
			runRace();
		}
		else if (yesorno.equalsIgnoreCase("no") || yesorno.equalsIgnoreCase("n")) {
			runRace();
		}






	}


	// Method reads all the racers from a file.
	private static void readRacers() {
		File racers;
		Scanner lineScan = null;
		
		try {
			// Read in the file
			 racers = new File("racers.txt");
			 lineScan = new Scanner(racers);
			
		}catch (FileNotFoundException e) {
			System.out.println("Sorry, the file was not found. Please Re-Open the program");
			//e.printStackTrace();
			
		}
	

		lineScan.nextLine(); // picks up the header;

		while (lineScan.hasNext()) // Read all the data inside of the file.
		{
			Scanner line = new Scanner(lineScan.nextLine()).useDelimiter(",");

			firstName = line.next();
			currentName = firstName;
			lastName = line.next().trim();
			carType = line.next().trim();
			String strScore = line.next().trim();

			score = Integer.parseInt(strScore);
			// Checks the file, and adds the object to the correct spot in the array.
			if(previousName.equals(currentName)) {
				added++;

			}

			else {


				myRacers = new Racers(firstName, lastName, carType, score, 0);
				added=0;
				data.add(myRacers);
				//System.out.println(previousName);


			}



			line.close();
			previousName = currentName;

		}// outside loop



	}

	private static void runRace() {
		// Print out stats between races, and update the score accordingly.
		System.out.println("XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX");
		System.out.println("\t\t\t\tRace 1 Final Positions");
		System.out.println("XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX");

		race1 = new Race("Desert",1);



		updatePositions();

		// Update and print stats.
		updateScore(race1);
		printStats();

		System.out.println("XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX");
		System.out.println("\t\t\t\tRace 2 Final Positions");
		System.out.println("XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX");

		race2 = new Race("Street",2);


		updatePositions();
		updateScore(race2);
		printStats();

		System.out.println("XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX");
		System.out.println("\t\t\t\tRace 3 Final Positions");
		System.out.println("XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX");
		race3 = new Race("Snow",3);


		updatePositions();
		updateScore(race3);
		printStats();

		System.out.println("XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX");
		System.out.println("\t\t\t\tRace 4 Final Positions");
		System.out.println("XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX");
		race4 = new Race("Off-Road",4);

		updatePositions();

		updateScore(race4);
		printStats();

		System.out.println("XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX");
		System.out.println("\t\t\t\tRace 5 Final Positions");
		System.out.println("XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX");
		race5 = new Race("City",5);

		updatePositions();

		updateScore(race5);
		printStats();


		System.out.println("XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX");
		System.out.println("\t\t\t\tFinal Scores and Racers Positions");
		System.out.println("XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX");

		// Sorts the people using insertionSort Sort.
		allRacers = data.toArray(new Racers[data.size()]);

		Sorting.insertionSort(allRacers);
		printInt(allRacers);

		size = allRacers.length;

		allRacers[0].setSearchBy("lastname");
		// Searching Binary.
		lastNameSort = "LastName";
		
		Sorting.selectionSort(allRacers);


		System.out.println("Would you like to search our racers? (Yes or No)");

		// Error Check Handling
		while(input.hasNextInt()){
			System.out.println("Sorry, Invalid Input. Please enter (Yes or No, Y, N)");
			input.next();
		}
		String userYesorNo = input.next();

		// Using Binary Search to look for Racers information.
		if(userYesorNo.equalsIgnoreCase("yes") || userYesorNo.equalsIgnoreCase("y")){
			printInt(allRacers);

			System.out.println("Who would you like to search for? (Last Name, Please)");
			// Error Check Handling
			while(input.hasNextInt()){
				System.out.println("Sorry, Invalid Input. Please enter (Yes or No, Y, N)");
				input.next();
			}
			searchedName = input.next().trim();

			// Find value Solo for testing.
			//int index = Arrays.binarySearch(allRacers, "Solo");
			Racers searchRacer = new Racers(searchedName);
			searchRacer.setSearchBy("lastname");
			Racers foundRacer = (Racers)Searching.binarySearch(allRacers, searchRacer);
			// Display result.
			//System.out.println("Index = " + index);
			System.out.println("Info = " + foundRacer);
			
		}
		else {
			System.out.println("Thanks for Racing!");
		}

	}


	private static void printStats() {
		for(int i = 0; i < data.size(); i++) {
			System.out.println(data.get(i).toString());
		}
	}

	private static void updateScore(Race o) {

		// Updates the scores according to the race level, the racers position and previous score
		for(int i =0; i < data.size(); i++) {
			data.get(i).setScore((data.get(i).getRacePosition() * o.getLevel()) + data.get(i).getScore());


		}





	}

	private static void addRacer() {

		Scanner userinput = new Scanner(System.in);

		System.out.println("Racer First Name:");
		// Error Check Handling
		while(userinput.hasNextInt()){
			System.out.println("Sorry, Invalid Input. Please enter First Name (i.e Mike)");
			userinput.next();
		}
		firstName = userinput.next();


		System.out.println("Racer Last Name:");
		// Error Check Handling
		while(userinput.hasNextInt()){
			System.out.println("Sorry, Invalid Input. Please enter First Name (i.e Cassens)");
			userinput.next();
		}
		lastName = userinput.next();

		System.out.println("Racer Car Type:");
		// Error Check Handling
		while(userinput.hasNextInt()){
			System.out.println("Sorry, Invalid Input. Please enter Car Type (i.e Bike)");
			userinput.next();
		}
		carType = userinput.nextLine();

		Racers myRacers = new Racers(firstName, lastName, carType, 0, 0);
		data.add(myRacers);


	}

	private static void updatePositions() {
		Random rc = new Random();

		// Look if the position has been assigned to a racer and if has, then add a different from.
		for (int i = 0; i < data.size();) {
			int positionRandom = rc.nextInt(data.size()) + 1;
			boolean found = false;

			// Does the check here.
			for(int index=0; index < i; index++) {
				if(positionRandom == data.get(index).getRacePosition()) {
					found = true; // Number already in array
					break;
				}
			}
			// Number not found in the array. Add it to the array.
			if(!found) {
				data.get(i).setRacePosition(positionRandom);
				i++;
			}

		}

	}

	public static void printInt(Racers[] list)
	{
		for(int i = 0; i < list.length; i++)
		{
			System.out.print(list[i].finalInfo() + "\t\n");
		}
		System.out.println("");
	}


	public static boolean binarySearch(int key)
	{
		int low = 0;
		int high = size - 1;

		while(high >= low) {
			int middle = (low + high) / 2;
			if(database[middle] == key) {
				return true;
			}
			if(database[middle] < key) {
				low = middle + 1;
			}
			if(database[middle] > key) {
				high = middle - 1;
			}
		}
		return false;
	}

}









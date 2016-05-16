package Problem2;


public class Racers implements Comparable<Racers>{
	
	private String firstName, lastName, carType;
	private int score, racePosition;
	private static String searchBy = "";
	
	public Racers(String firstName, String lastName, String carType, int score, int racePosition) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.carType = carType;
		this.racePosition = racePosition;

		this.score = score;
	}

	public Racers(String lastName)
	{
		this.lastName = lastName;
	}
	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public int getRacePosition() {
		return racePosition;
	}

	public void setRacePosition(int racePosition) {
		this.racePosition = racePosition;
	}
	

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public void setSearchBy(String searchBy)
	{
		this.searchBy = searchBy;
	}
	
	public String toString() {
		return "First Name: " + firstName +
				" Last Name: " + lastName +
				" Car Type: " + carType +
				" Race Position: " + racePosition+
				" Score: " + score;
	}
	
	public String finalInfo() {
		return "Racers Name: " + firstName + " " + lastName +
				" Car Type: " + carType +
				" Score: " + score;
	}


	@Override
	public int compareTo(Racers o) {
		//if(driver.lastNameSort.equalsIgnoreCase("LastName")) {
		if(searchBy.equalsIgnoreCase("lastname")){
			int finalValue = lastName.toLowerCase().compareTo(o.lastName.toLowerCase());
			
			/*if(finalValue == 0)
			{
				finalValue = lastName.toLowerCase().compareTo(o.lastName.toLowerCase());
			}*/
			return finalValue;
		}
		
		else {
		
		int finalValue = 0;
		if(this.score > o.score)
		{
			finalValue = 1;
		}
		else if(this.score < o.score)
		{
			finalValue = -1;
		}return finalValue;
	}
	}

	
	
	

}

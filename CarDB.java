///////////////////////////////////////////////////////////////////////////////
//                   ALL STUDENTS COMPLETE THESE SECTIONS
// Main Class File:  CarDBMain.java
// File:             CarDB.java
// Semester:         CS367 Summer 2014
//
// Author:           Zak Scholl - zscholl@wisc.edu
// CS Login:         scholl
// Lecturer's Name:  M. Hidayath Ansari
// 
///////////////////////////////////////////////////////////////////////////////
import java.util.ArrayList;
import java.util.Iterator;

/**
 * This is the class that handles the Car database operations. It holds the 
 * ArrayLists of car objects and maker names, and has methods that handle the
 * add, remove, update, selectCars, and getcars/car and maker iterators options.
 * @author Aditya Singh, CS 367 TA
 *
 */
public class CarDB {
	//ArrayLists that store the Car objects and Maker names, respectively
	private ArrayList<Car> carDB;
	private ArrayList<String> makers;
	private int numCars;
	
	/**
	 * Constructs an empty database
	 * 
	 */
	public CarDB() {
		carDB = new ArrayList<Car>();
		makers = new ArrayList<String>();
	}
	
	
	/**
	 * Adds a car with the given features. If the given mpg is not between 0
	 * and 100, or power is not between 0 and 1000 or a null parameter is passed
	 * then an IllegalArgumentException is thrown. 
	 * 
	 * @param carName
	 * @param makerName
	 * @param mpg
	 * @param power
	 * @param year
	 */
	public void addCar(String makerName, String carName, double mpg,
			int cylinders, int power, int year, int region){
		
		if (mpg >= 0 && mpg <= 100 && power >= 0 && power <= 1000 && makerName
				!= null && carName != null){ //if all fields are valid 
			
		carDB.add(new Car(makerName.toLowerCase(), carName.toLowerCase(), mpg, 
				cylinders, power, year, region)); //names converted to lowercase for consistency in name storage
		numCars++;
		addMaker(makerName); //adds makerName to list if not already there
		
		}
		else throw new IllegalArgumentException();
	}
	
	/**
	 * Adds the maker with the given name to the list of makers. If the maker
	 * is already in the list, then it does nothing and returns. Throws an 
	 * IllegalArgumentException if a null parameter is given. 
	 * 
	 * @param maker name of the maker
	 */
	public void addMaker(String maker){
		if (maker == null) throw new IllegalArgumentException();
		if(!makers.contains(maker)){
			makers.add(maker);
			
		}
		return; 
	}
	
	
	/**
	 * Returns true if a car with the given name s and year was removed from the
	 * database. Returns false otherwise.
	 * 
	 * @param s partial or complete name of the car
	 * @return false if car not removed(not present in database), 
	 * returns true otherwise
	 */
	public boolean removeCar(String s, int year){
		
		Car temp = getCarWithName(s, year);
		
		if (!temp.equals(null)){
			carDB.remove(temp);
			System.out.println("Car removed");
			return true;
		}
		else return false;

	}
	
	
    /**
     * Returns the init capitalized sentence 
     * 
     * @param sentence any string
     * 
     * @return returns the init capitalized sentence
     */
    public static String toInitCapitalize(String sentence) {

        String[] arr = sentence.toLowerCase().split(" ");
        StringBuffer sb = new StringBuffer();

        for (int i = 0; i < arr.length; i++) {
          sb.append(Character.toUpperCase
        		  (arr[i].charAt(0))).append(arr[i].substring(1)).append(" ");
        }

        return sb.toString().trim();

    }
	

	/**
	 * Fetches the car with the given name and year
	 * 
	 * @param name the title of a car
	 * @param the year of the car
	 * 
	 * @return the pointer to the car for the given title, null if there is no
	 * such car in the database
	 */
	public Car getCarWithName(String name, int year) {
		
		for(Iterator<Car> carItr = getCarsIterator(); carItr.hasNext();){ //cycles through database
			Car temp = (Car) carItr.next();
			if (temp.getName().equalsIgnoreCase(name) && temp.getYear() == year){ //if match is found
				return temp;
			}
		}
		return null;
	}

	/**
	 * Returns an Iterator to CarList
	 * 
	 * @return iterator
	 */
	public Iterator<Car> getCarsIterator() {
		
		return  carDB.iterator();
	}

	public Iterator<String> getMakersIterator(){
		
		return makers.iterator();
	}

	
	/**
	 * Returns the number of cars in the database
	 * 
	 * @return size
	 */
	public int size() {
		return numCars;
	}

	/**
	 * Returns the size of maker list
	 * 
	 * @return size
	 */
	public int makersSize(){
		return makers.size();
	}
	
	
	/**
	 * Updates a field of a Car
	 * 
	 * @param name the search string for the Car
	 * @param field the field which is to be updated
	 * @param val the new Value for the field
	 */
	public void updateField(String name,int year, String field, String val){
		
		Car temp = getCarWithName(name, year);
		
		if(!temp.equals(null)){
			switch(field){
				case "power":
					
					int tempP = Integer.parseInt(val);
					
					if(tempP >= 0 && tempP <= 1000){ //if power is valid
						temp.setPower(tempP);
					}
					
					else throw new IllegalArgumentException();
					
					break;
				
				case "mpg":
					
					double tempG = Double.parseDouble(val);
					
					if (tempG >= 0 && tempG <= 100){  //if mpg is valid
						temp.setMpg(tempG);
					}
					
					else throw new IllegalArgumentException();
			
					
					break;
					
				case "cylinders":
					
					temp.setCylinders(Integer.parseInt(val));
					
					break;
					
				case "year":
					temp.setYear(Integer.parseInt(val));
					break;
					
				case "region":
					temp.setRegion(Integer.parseInt(val));
					break;
			}
					
			
		}
		else System.out.println("Car not found");
		
		System.out.println("Successfully updated");
		return;
		
		
	}

	/**
	 * Prints names of the Cars which satisfy the search key
	 * 
	 * @param field Field on which the search is to be executed
	 * @param relation Relation which a car needs to "qualify"
	 * @param value Value to which the field value of each car is compared
	 */
	public void selectCars(String field, String relation, String value) {
		
		
    		char r = relation.charAt(0);
		Iterator<Car> itrCar = getCarsIterator();
		
		while (itrCar.hasNext()){
			Car c = itrCar.next();
			String toPrint = toInitCapitalize(c.getMaker() + " " + c.getName());
			toPrint += (" " + String.valueOf(c.getYear()));
			switch(field){
			case "mpg":
				double m = Double.parseDouble(value);
				switch(r){
				case 'g':
					if (c.getMpg() > m) System.out.println(toPrint + ", MPG: " + c.getMpg()); 
					break;
				case 'l':
					if (c.getMpg() < m) System.out.println(toPrint+ ", MPG: " + c.getMpg());
					break;
				case 'e':
					if (c.getMpg() == m) System.out.println(toPrint+ ", MPG: " + c.getMpg());
					break;
				default: 
					break;
				}
				break;
				
			case "year":
				int y = Integer.parseInt(value);
				switch(r){
				case 'g':
					if (c.getYear() > y) System.out.println(toPrint + ", Year: " + c.getYear()); 
					break;
				case 'l':
					if (c.getYear() < y) System.out.println(toPrint+ ", Year: " + c.getYear());
					break;
				case 'e':
					if (c.getYear() == y) System.out.println(toPrint+ ", Year: " + c.getYear());
					break;
				default: 
					break;
				}
				break;
				
			case "region":
				int reg = Integer.parseInt(value);
				switch(r){
				case 'g':
					if (c.getRegion() > reg) System.out.println(toPrint + ", From: " + convertRegion(c.getRegion())); 
					break;
				case 'l':
					if (c.getRegion() < reg) System.out.println(toPrint+ ", From: " + convertRegion(c.getRegion()));
					break;
				case 'e':
					if (c.getRegion() == reg) System.out.println(toPrint+ ", From: " + convertRegion(c.getRegion()));
					break;
				default: 
					break;
				}
				break;
				
			case "power":
				int p = Integer.parseInt(value);
				switch(r){
				case 'g':
					if (c.getPower() > p) System.out.println(toPrint + ", Power: " + c.getPower()); 
					break;
				case 'l':
					if (c.getPower() < p) System.out.println(toPrint+ ", Power: " + c.getPower());
					break;
				case 'e':
					if (c.getPower() == p) System.out.println(toPrint+ ", Power: " + c.getPower());
					break;
				default: 
					break;
				}
				break;
				
			case "cylinders":
				int cyl = Integer.parseInt(value);
				switch(r){
				case 'g':
					if (c.getCylinders() > cyl) System.out.println(toPrint + ", # of Cylinders: " + c.getCylinders()); 
					break;
				case 'l':
					if (c.getCylinders() < cyl) System.out.println(toPrint+ ", # of Cylinders: " + c.getCylinders());
					break;
				case 'e':
					if (c.getCylinders() == cyl) System.out.println(toPrint+ ", # of Cylinders: " + c.getCylinders());
					break;
				default: 
					break;
				
				}
				break;
		
		}

	}
	
		
	}	
	/**
	 * Prints the given list
	 * 
	 * @param List Any list of String
	 * 
	 */
	public void printList(Iterator<String> itr) {//used
		while(itr.hasNext())
		{ 
		   System.out.println(toInitCapitalize(itr.next()));
		}
	}
	/**
	 * Converts a region integer to the appropriate String counterpart
	 * @param region
	 * @return String of region corresponding to the input integer
	 */
	private static String convertRegion(int region){	
		
		switch(region){
		case 1: return "America";
		case 2: return "Europe";
		case 3: return "Japan";
		
		default: return null;
		}
	}
	
}


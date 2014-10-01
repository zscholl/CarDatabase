///////////////////////////////////////////////////////////////////////////////
//                   ALL STUDENTS COMPLETE THESE SECTIONS
// Main Class File:  CarDBMain.java
// File:             Car.java
// Semester:         CS367 Summer 2014
//
// Author:           Zak Scholl - zscholl@wisc.edu
// CS Login:         scholl
// Lecturer's Name:  M. Hidayath Ansari
//
///////////////////////////////////////////////////////////////////////////////
/**
 * The Car class is used to represent a single car that keeps track of its
 * maker, mpg, year, power, and countries sold ( in a list)
 * 
 * 
 * @author Aditya Singh, CS 367 TA
 */
public class Car {
	private String maker;
	private String name;
	private double mpg;
	private int power;
	private int year;
	private int region;// 1 - America, 2 - Europe. 3 - Japan
	private int cylinders;

	

	/**
	 * 
	 */
	public Car(){
		this("","",0,0,0,0,0);
	}
	
	
	/**
	 * @param maker the name of the maker
	 * @param name the name of the car
	 * @param mpg the mpg of the car
	 * @param power power in hp
	 * @param year year of make
	 */
	public Car(String maker, String name, double mpg,int cylinders, int power, int year, int region) {
		super();
		this.maker = maker;
		this.name = name;
		this.mpg = mpg;
		this.power = power;
		this.year = year;
		this.region = region;
		this.cylinders = cylinders;
	}

	/**
	 * Returns the name of the maker
	 * 
	 * @return
	 */
	public String getMaker() {
		return maker;
	}

	/**
	 * Sets the name of the maker
	 * 
	 * @param maker
	 */
	public void setMaker(String maker) {
		this.maker = maker;
	}

	/**
	 * Returns the name of the car
	 * 
	 * @return
	 */
	public String getName() {
		return name;
	}

	/**
	 * Sets the name of the car
	 * 
	 * @param name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Returns the MPG of the car
	 * 
	 * @return
	 */
	public double getMpg() {
		return mpg;
	}

	/**
	 * Sets the MPG of the car
	 * 
	 * @param mpg
	 */
	public void setMpg(double mpg) {
		this.mpg = mpg;
	}

	/**
	 * Returns the power of the car
	 * 
	 * @return
	 */
	public int getPower() {
		return power;
	}

	/**
	 * Sets the power of the car
	 * 
	 * @param power
	 */
	public void setPower(int power) {
		this.power = power;
	}

	/**
	 * Returns the year of make of the car
	 * 
	 * @return
	 */
	public int getYear() {
		return year;
	}

	/**
	 * Sets the year of make of the car
	 * 
	 * @param year
	 */
	public void setYear(int year) {
		this.year = year;
	}

	public int getRegion() {
		return region;
	}

	public void setRegion(int region) {
		this.region = region;
	}
	/**
	 * Returns the number of cylinders in the car
	 * 
	 * @return cylinders
	 */
	public int getCylinders() {
		return cylinders;
	}


	/**
	 * Set the number of cylinders in the car
	 * 
	 * @param cylinders 
	 */
	public void setCylinders(int cylinders) {
		this.cylinders = cylinders;
	}

	
}

///////////////////////////////////////////////////////////////////////////////
//                   ALL STUDENTS COMPLETE THESE SECTIONS
// Title:            Car Database
// Files:            CarDBMain.java, CarDB.java, 
// Semester:         CS367 Summer 2014
//
// Author:           Zak Scholl
// Email:            zscholl@wisc.edu
// CS Login:         scholl
// Lecturer's Name:  M. Hidayath Ansari
//
///////////////////////////////////////////////////////////////////////////////
import java.io.File;
import java.util.Iterator;
import java.util.Scanner;

/**
 * This is the main class for the Car Database. It handles the operations of:
 * adding a car to the database, removing a car from the database, updating the
 * numeric fields of a car in the database, printing general information about
 * the database, listing the automakers in the database, and printing all cars
 * with a given relation to a specified field in the database.
 * 
 * Also reads in data from a text file to instantiate a database. 
 * 
 * @author Aditya
 * 
 */
public class CarDBMain {
	private static Scanner stdin = new Scanner(System.in);
	public static void main(String[] args) {
		
		// display "Usage: java CarDBMain FileName" if there is not exactly one command line argument
		File srcFile = null;
		
		if(args.length == 1){
			srcFile = new File(args[0]);
		}
		else {
			System.out.println("Usage: java CarDBMain FileName");
			System.exit(0);
		}

		if (srcFile.exists() && srcFile.canRead()){ //if the file exists and can be read
			
		}
		else{
			System.out.println("Error: Cannot access input file");
			System.exit(0);
		}
		
		
		int width = 60;//for printStar
    	try {
			//Load the data from the input file and use it to construct a car database.
			
			Scanner fileIn = new Scanner(srcFile);
			CarDB carDatabase = new CarDB();
			while(fileIn.hasNext())
			{	    		
				String[] line = fileIn.nextLine().split(",");
				double mpg = Double.parseDouble(line[0]);
				int cylinders = Integer.parseInt(line[1]);
				int power = Integer.parseInt(line[2]);
				int year = Integer.parseInt(line[3]);
				int region = Integer.parseInt(line[4]);
				String makerName = line[5].trim();
				String carName = line[6].trim();
				
				carDatabase.addCar(makerName, carName, mpg, cylinders, power, year, region);// add car
				//carDatabase.addMaker(makerName);//add maker to list *this is taken care of in constructor*
				
			}	    	
		    fileIn.close();
		    boolean done = false;
		    while (!done){
		    	
		    	System.out.println("Enter option ( dlcaurmx )");
		    	String input = stdin.nextLine();
		    	
		    	try{
		    		String check = "power.mpg.year.region.cylinders";//used to check if the passed field is valid or not
		    		if(input.length()>0){
		    			char choice = input.charAt(0);  // strip off option character
		    			String remainder = "";  // used to hold the remainder of input
		    			if (input.length() > 1) { // if there is an argument
		    				// trim off any leading or trailing spaces
		    				remainder = input.substring(1).trim(); 
		    				
		    				switch (choice) { // the commands that have arguments
		    				case 'r': // 
		    				{
							
		    					String[] car = remainder.split(","); //splits line into separate array elements
		    					
		    					if(car.length != 2){ //if the array does not have exactly two elements
		    						System.out.println("Usage: r carName,year");
		    						continue;
		    					}
		    					if((!isNumeric(car[1]))){ //if the second element is not numeric
		    						throw new IllegalArgumentException();
		    						
		    					}
		    					
		    					printStar(width); //if valid then remove the car
		    					carDatabase.removeCar(car[0], Integer.parseInt(car[1]));
		    					printStar(width);
		    					break;
		    					
		    					
					        }

					        case 'u':
					        {
					        	String[] update = remainder.split(",");//splits input into elements
							
					        	
					        	if(update.length != 4){ //if there are not exactly 4 elements
					        		System.out.println("Usage: u name,year,field,newValue");
					        		continue;
					        	}
					        	
					        	if ((!check.contains(update[2])) || (!isNumeric(update[3]))){ //if field or newValue is not valid
					        		System.out.println("Usage: u name,year,field,newValue");
					        		continue;
					        	}
					        	printStar(width); //if valid, then update
					        	carDatabase.updateField(
					        				update[0], Integer.parseInt(update[1]), update[2], update[3]);
					        	
					        	printStar(width);
					        	
					        }
					        case 'c':
					        {
					        	String[] line = remainder.split(",");
					        	
							if (line.length != 3){
								System.out.println("Usage: c field,relation,value");
								continue;
							}
							
								String relations = "l.g.e";
					        	String field = line[0];
					        	String relation = line[1];
					        	String val = line[2];
					        	
					            if ((!check.contains(field)) || 
					            		(!relations.contains(relation)) || (!isNumeric(val)))
					            
					            {
					            	System.out.println("Usage: c field,relation,value");
					            	continue;
					            }
					            
					        	printStar(width);
					            carDatabase.selectCars(field, relation, val);
					            printStar(width);
					            break;
							   
							}
							
					        
					        case 'a':
					        {
					            // The following code reads in a comma-separated sequence 
					            // of a string followed by an integer.  If there are not
					                // exactly six elements in the sequence or if the second
					                // element is not an integer an error message is printed.
					        	String[] line = remainder.split(",");
					            if (line.length != 7) {
					            	System.out.println("Usage: a makerName,carName,mpg,cylinders,power,year,region");
					            	continue;
					            }
					            else 
					            {
					            	if (!isNumeric(line[2]) || (!isInteger(line[3]) || 
					            			(!isInteger(line[4])) || (!isInteger(line[5])) || 
					            			(!isInteger(line[6])))){
					            		
					            		throw new IllegalArgumentException();
					            	}
					            	
					            	printStar(width);
					            	String makerName = line[0].trim();
					                String carName = line[1].trim();
					                double mpg = Double.parseDouble(line[2].trim());
					                int cylinders = Integer.parseInt(line[3].trim());
					                int power = Integer.parseInt(line[4].trim());
					                int year =  Integer.parseInt(line[5].trim());
					                int region = Integer.parseInt(line[6].trim());	
					                try{
					                	carDatabase.addCar(makerName,carName, mpg, cylinders, power, year, region);
					                } catch (Exception e){
					                	e.printStackTrace();
					                	System.out.println("Error in adding to database");
					                }
					                System.out.println("Added successfully");

					            }
					            printStar(width);
					            break;
					        }
					        case 'l':
					        {
							
					        	boolean makerFound = false; //used for tracking if maker has been found
					        	
					        	
					        	if(remainder.length() <= 0 || isNumeric(remainder)){
					        		System.out.println("Usage: l makerName");
					        		continue;
					        	}
					        	printStar(width);
					        	Iterator<Car> makerItr = carDatabase.getCarsIterator();
					        	
					        	while(makerItr.hasNext()){
					        		Car carTmp = makerItr.next();
					        							        		
					        		if (carTmp.getMaker().equals(remainder)){
					        			makerFound = true;
					        			System.out.println(CarDB.toInitCapitalize(carTmp.getName()));
					        		}
					        	}
					        	
					        	if(!makerFound) System.out.println("Maker not found");
					        	printStar(width);	
					        	break;
					        	
					        }
					        default: // ignore any unknown commands
					        	System.out.println("Incorrect command");
					        	break;
					        
					        } // end switch
					    } // end if
					    else { //if there is no argument
					    	switch (choice) { // the commands without arguments
					    	
					    	case 'd':
					    	{
							
					    		//Variables to hold average MPG's of cars
					    		double aMPG = 0.0;
					    		double eMPG = 0.0;
					    		double jMPG = 0.0;
					    		//Variables to hold # of cars in each region
					    		double aCars = 0.0;
					    		double eCars = 0.0;
					    		double jCars = 0.0;
					    		Iterator<Car> mpgItr = carDatabase.getCarsIterator();
					    			
					    		while(mpgItr.hasNext()){ //gathers total MPG and number of cars in each region
					    				Car tmpCar = mpgItr.next();
					    				
					    				switch (tmpCar.getRegion()){
					    				case 1: aMPG += tmpCar.getMpg(); aCars++;
					    					break;
					    				case 2: eMPG += tmpCar.getMpg(); eCars++;
					    					break;
					    				case 3: jMPG += tmpCar.getMpg(); jCars++;
					    					break;
					    					
					    				}
					    		}
					    	
					    		
					    		
					    		printStar(width);
					    		System.out.println("Total number of cars in the database: " + carDatabase.size());
					    		System.out.println("Total number of automakers: " + carDatabase.makersSize());
					    		//calculates AVG MPG and formats the output to 2 decimal places
					    		System.out.printf("Average MPG of American cars: %.2f%n", aMPG/aCars);
					    		System.out.printf("Average MPG of European cars: %.2f%n", eMPG/eCars);
					    		System.out.printf("Average MPG of Japanese cars: %.2f%n", jMPG/jCars);
					    		
					    		printStar(width);
					    	} 
					    	break;
					    	
					    	case 'm':{
					    		printStar(width);
					    		System.out.println("List of all the makers in the database: ");
					    		carDatabase.printList(carDatabase.getMakersIterator());
					    		printStar(width);
					    		break;
					    	}
					    	case 'l':
					    		System.out.println("Usage: l makerName");
					    		break;
					    	case 'c':
					    		System.out.println("Usage: c field,relation,value");
					    		break;
					    	case'a':
					    		System.out.println("Usage: a makerName,carName,mpg,cylinders,power,year,region");
					    		break;
					    	case'u':
					    		System.out.println("Usage: u name,year,field,newValue");
					    		break;
					    	case'r':
					    		System.out.println("Usage: r name,year");
					    		break;
					    	case 'x':{
					            done = true;
					            System.out.println("Exit");
					            break;
					    	}    
					    	default:  // a command with no argument
					    		System.out.println("Incorrect command");
					            break;
					    	} // end switch
					    } // end else  
                   }

		    	}catch(Exception e) {
		    		
		    		e.printStackTrace();
		    	} 
		    }
    	}catch(Exception e) {
    		
    		e.printStackTrace();
    	}
    	
	}
	/**
	 * Prints a line of stars for the given length n
	 * @param n
	 */
	public static void printStar(int n){
		for (int i = 0; i < n ; i++) System.out.print("*");
		System.out.println("");
	}
	/**
	 * Returns true if the string is a valid integer, false otherwise
	 * @param str
	 * @return true if str is valid integer, false otherwise
	 */
	public static boolean isInteger(String str){//for checking Integer
		try {
			Integer.parseInt(str);
		}catch(Exception e){
			return false;
		}
		return true;
	}
	/**
	 * returns true if the given String numeric, false otherwise
	 * @param str
	 * @return true if str is numeric, false otherwise
	 */
	public static boolean isNumeric(String str)  
	{  
	  try  
	  {  
	   Double.parseDouble(str);  
	  }  
	  catch(NumberFormatException nfe)  
	  {  
	    return false;  
	  }  
	  return true;  
	}
}

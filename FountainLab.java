/** 
 * Lab1 - Filling the Fountain
 * 
 * A simple command line program to calculate the amount
 * of water needed to fill a fountain design. The dimensions
 * will be received through user input and validated before 
 * assignment.
 * 
 * Goals:
 * 1. Write variables to use with arithmetic operators
 * 2. Prompt user for input and validate input using relational
 *	  and conditional operators.
 * 3. Test if the program works correctly 
 * 
 * @author Will Oprisko, 25501-OL1 
 * 
 * @version 2021-02-05
 * 
 * @experimenting with condensing multiple while-loops into a single static method 
 */

import java.util.*;
import java.text.NumberFormat;


public class FountainLab
{
    public static void main(String args[])
    {
    	// Associated with calculating the volume of water in the fountain basin
    	double radius 		= 0.0;
    	double depth 		= 0.0;

    	// Used with 'depth' to determine the statue base volume
    	double width1 		= 0.0;
    	double width2		= 0.0;

    	double basinVolume 	= 0.0;
    	double statueVolume	= 0.0;
    	double waterVolume	= 0.0;

    	double waterPrice	= 0.10;
    	double totalCost	= 0.0;


        // Values are represented in measurement units of feet
    	radius = obtainValue("radius");
    	width1 = obtainValue("width1");
    	width2 = obtainValue("width2");
    	depth  = obtainValue("depth");

    	// Calculates and stores values in cubic feet
    	basinVolume 	= Math.pow(radius, 2) * Math.PI * depth;
    	statueVolume 	= width1 * width2 * depth;
    	waterVolume 	= basinVolume - statueVolume;

    	// Coverts cubic feet to gallons and rounds up before calculating the cost
        totalCost = Math.ceil(waterVolume * 7.481) * waterPrice;
        
        System.out.printf("Water Volume in cubic feet:  %f%n", waterVolume);
        System.out.printf("Water Volume in gallons:  %f%n", waterVolume * 7.481);
        System.out.println("That amount of water will cost: " + 
                           NumberFormat.getCurrencyInstance().format(totalCost));
	} 

    // Method for receiving and validating user input for each measurement
    // regarding the fountain basin and statue pedestal
	public static double obtainValue(String measurement)
	{
        double minValue = 0.0;
        double maxValue = 0.0;

        String string = new String();

        Scanner userInput = new Scanner(System.in);
        double inputValue = 0.0;

        boolean enterLoop = true;

        switch(measurement)
        {
        	case "radius":
        		minValue = 10.0;
        		maxValue = 15.0;
        		string 	 = "Please enter the radius of the fountain (10.0 to 15.0 feet): ";
        		break;
        	case "width1":
        		minValue = 2.0;
        		maxValue = 8.0;
        		string 	 = "Please enter the rectangular pedestal's x-axis width (2.0 to 8.0 feet): ";
        		break;
        	case "width2":
        		minValue = 2.0;
        		maxValue = 8.0;
        		string 	 = "Please enter the rectangular pedestal's y-axis width (2.0 to 8.0 feet): ";
        		break;
        	case "depth":
        		minValue = 1.0;
        		maxValue = 3.0;
        		string 	 = "Please enter the water’s depth when full (1.0 to 3.0 feet): ";
        		break;
        	default:
        		System.out.println("ERROR: improper use of 'validateInput' method");
        		enterLoop = false;
        }

        while (enterLoop)
        {
        	System.out.print(string);
        	inputValue = userInput.nextDouble();

       		if (inputValue >= minValue & inputValue <= maxValue)
        	{
        		enterLoop = false;	
        	}
        	else
        	{
        		System.out.printf("(INCORRECT VALUE DETECTED. Please enter a valule between %.1f and %.1f)%n", minValue, maxValue);	
        	}
        }

        userInput.close();
        return inputValue;
    }
}
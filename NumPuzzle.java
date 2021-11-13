package project5;

import java.util.*;
/**
* This class represents all the solutions to a number puzzle (represented by an array of
* positive integers)
* In each path, there will be directions (left or right) to follow the path it found. 
* If there is no solution, then there was no path found. 
* There can be multiple solutions
* @author Gabby Clavell
*/
public class WayFinder {
	private static String [] dir = null;
	public static void main(String[] args) {
		// no arguments 
		if (args.length == 0) {
			System.out.println("ERROR: incorrect usage. At least one argument is required.");
			System.exit(0);
		}
		// make sure the last index is 0
		else if (!args[args.length-1].equals("0") ) {
			System.out.println("End number is not zero");
			System.exit(0);
		}
		
		// make sure there are no negative numbers or numbers > 99
		for (int i = 0; i < args.length; i++) {
			int num = Integer.parseInt(args[i]);
			if (num < 0 || num > 99) {
				System.out.println("ERROR: the puzzle values have to be positive integers in range [0, 99].");
				System.exit(0);
			}	
		}
		// call the wrapper function 
		int solutions = finder(args);	

		// the number of solutions in this problem
		if (solutions == 0) {
			System.out.println("No way through this puzzle.");
		}
		else if(solutions == 1) {
			System.out.println("There is 1 way through the puzzle.");
		}
		else {
			System.out.println("There are " + solutions + " ways through the puzzle.");
		}
		
	}
	
	/**
	* Loops the array to add to two new arrays (one int and one string)
	* if arr is of length one, then it prints out the array 
	* If greater than one, calls the helpFinder method to do the recursion 
	* @param arr 	arr is 
	* @return int	the number of solutions the path has 
	*/
	public static int finder(String [] arr) {
		
		// check to see if the array is of length one 
		// return solution of one since it was zero 
		// and there is one path in this 
		if (arr.length == 1) {
			System.out.println("[ "+ arr[0] + " ]");
			return 1;
		}
		//making two arrays to keep track of: a string array and an integer array 
		// dir = string array to keep track of the array 
		// newArr = int array to keep track on whether to go L or R
		dir = new String[arr.length]; 
		int[] newArr = new int[arr.length];
		for (int i = 0; i < arr.length; i++) {
			int num = Integer.parseInt(arr[i]);
			if (num < 0) {
				return -2;
			}
			else {
				// see if the number is less than ten for formatting 
				if(Integer.parseInt(arr[i]) < 10) {
					dir[i] = " "+ String.valueOf(arr[i]) + " ";
				} 
				//greater than ten for formatting 
				else {
					dir[i] = String.valueOf(arr[i])+ " ";
				}
				newArr[i] = Integer.valueOf(arr[i]);
			}
		}
		return helpFinder(newArr, 0, dir);
		
	}
	/**
	* Adds either R or L if it can take either direction and adds up the amount of solutions possible for the path
	* @param arr 	arr is to be checked if the number can be added or subtracted to move in the path
	* @param index	index is to check the element in the index of the two arrays' and increments after the recursive calls
	* @param dir		dir is an string array that adds R or L, if not then the element at position is not touched
	* @return int	the number of solutions the path has 
	*/
	public static int helpFinder(int [] arr, int index, String dir []) {
		// if it is out of bounds
		//backtrack
		if (index < 0 || index > arr.length) {
			return 0;	
		}
		// if index is equal to length of the array,  
		// print out the path for it
		if (index == arr.length-1) {			
			// print the path 
			System.out.println(Arrays.toString(dir));
			return 1; 
		}
		// make sure to see if you visit the char first (charAt)
		if (dir[index].contains("R") || dir[index].contains("L")) {
			return 0;
		}
		// if it can go right, move right and 
		// append to the direction array "R"
		// call the recursive function again 
		int solutions = 0; 
		if ((index + arr[index]) < arr.length) {
			if(arr[index] <10) {
				//for formatting purposes
				dir[index] = String.format("%2d%c",arr[index],'R' ) ;
			}
			else if (arr[index]> 10) {
				dir[index] = String.format("%1d%c",arr[index],'R' ) ;
			}
			
			solutions += helpFinder(arr, index + arr[index], dir);
			//replace the letter with spacing and add that one to solution 
			dir[index] = dir[index].replace("R", " ");
		}
		// if it can go left, move left and 
		//append to the direction array "L"
		// call the recursive function again 
		if ((index - arr[index]) > 0) {
			//for formatting purposes
			if(arr[index] <10) {
				//+ arr[index] + "R"
				dir[index] = String.format("%2d%c",arr[index],'L' ) ;
			}
			else if (arr[index]> 10) {
				dir[index] = String.format("%1d%c",arr[index],'L' ) ;
			} 

			solutions += helpFinder(arr, index - arr[index], dir); 
			//replace the letter with spacing and add that one to solution 
			dir[index] = dir[index].replace("L", " ");
		}
		return solutions; 		
	}
	
}


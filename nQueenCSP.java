
import java.util.Scanner;

public class nQueenCSP {
	
	//Ask user to input the number of queens
	Scanner scr = new Scanner(System.in);
	int max = scr.nextInt(); 
	//Create array for storing 
    int[] array = new int[max];
    
    static int count = 0;

    //Main method
	public static void main(String[] args) {
		
			System.out.println("Enter your number of queens:");
			
			nQueenCSP queen = new nQueenCSP();
			queen.backtracking(0);
			 
			System.out.println("There are " + count + " solution for the queen game");
		   
	 }
	
	//Backtracking method
	public void backtracking(int n) {

		if(n == max) {
			
			int row=0;
	 		for(int i=0; i<array.length; i++) {
	 			System.out.print("For Q" + (row + 1)+ ", the row is: " + row);
				System.out.println(" Column is: " + array[i] + " ");
				row++;
			}
			System.out.println("");
			
			count++;
			
			return;
		}
		
		//put in queen and find out if it is consistent
		//If consistent, place the queen, if not, change the position to the next step
		for(int i=0; i<max; i++) {
			array[n]=i;
			
			if(constraint(n)) {
				backtracking(n+1);
			}
		} 
	}
	

	//Constraint testing the consistency
	private boolean constraint(int n){
		for(int i = 0; i < n; i++) {
			 if(array[i] == array[n]|| Math.abs(n-i) == Math.abs(array[n] - array[i])) {
				 return false;
			 }
		} 
		return true;	
	}
	
}

import java.util.Scanner;

public class ConnectFour {
	

	
	//main method
	public static void main(String[] args) {
		
		Board b = new Board();
//		int[][] board = b.getBoard();
//		b.printBoard();
		Scanner sc = new Scanner(System.in);
		int step =0; //Use step to record whose turn is it
		int first;
		b.boardupdate();
		
		//choose player
		System.out.println("Do you want to player RED (1) or YELLOW (2)? ");
		
		int player_choice = sc.nextInt();
		int agent_choice;
		
		if(player_choice==b.player_red) {
			 agent_choice = b.player_yellow;
			 first = 1; //user goes first
			 while(b.isEnd()==false) {
					step++;
					if(step%2==1) {
						System.out.println("Next to play: RED/X");
						System.out.println("Your move [column]?");
						String column_index = sc.next();
						
						//Convert characters to number
						int column_num = -1;
						switch (column_index) {
						case "a":
							column_num = 0;
							break;
						case "b":
							column_num = 1;
							break;
						case "c":
							column_num = 2;
							break;
						}
						
						//Remind column is full
						int row_index = b.row_index(column_num);
						if(row_index==-1) {
							System.out.println("this column is full");
						}
						else {
							//Update user choice
							State move = new State(row_index,column_num);
							b.calAction(move,player_choice );
							b.boardupdate();
						}
					}
					else {
						System.out.println("Next to play: YELLOW/O");
						// implement agent function
						b.minimax(0, agent_choice,first); 
						b.calAction(b.AIMove, agent_choice);
						System.out.println("I'm thinking... Best move:"+ b.AIMove.y);
						b.boardupdate();
						if(b.isEnd()) {
							break;
						}
					}
				}
			 
			 //print out the result
			 if(b.isWin(player_choice)) {
				 System.out.println("You win!");
			 }else if(b.isWin(agent_choice)) {
				 System.out.println("Agent win!");
			 }else if(b.getSpace().isEmpty()) {
				 System.out.println("Tie");
			 }
		}
		
		//If agent goes first
		else {
			 agent_choice = b.player_red;
			 first = 2;
			 while(b.isEnd()==false) {
					step++;
					if(step%2==1) {
						if(step ==1) {
							b.AIMove = new State(2,1);
							b.calAction(b.AIMove, agent_choice);
							b.boardupdate();
						}else {
							System.out.println("Next to play: RED/X");
							// implement agent function
							b.minimax(0, agent_choice,first); 
							b.calAction(b.AIMove, agent_choice);
							System.out.println("I'm thinking... Best move:"+ b.AIMove.y);
							b.boardupdate();
							if(b.isEnd()) {
								break;
							}
						}
					}
					else {
						System.out.println("Next to play: YELLOW/O");
						System.out.println("Your move [column]?");
						String column_index = sc.next();
						
						
						int column_num = -1;
						switch (column_index) {
						case "a":
							column_num = 0;
							break;
						case "b":
							column_num = 1;
							break;
						case "c":
							column_num = 2;
							break;
						}
						
						int row_index = b.row_index(column_num);
						if(row_index==-1) {
							System.out.println("this column is full");
						}
						else {
							State move = new State(row_index,column_num);
							b.calAction(move,player_choice );
							 
							b.boardupdate();
						}
					}
				}
			 
			 //print out the result
			 if(b.isWin(player_choice)) {
				 System.out.println("You win!");
			 }else if(b.isWin(agent_choice)) {
				 System.out.println("Agent win!");
			 }else if(b.getSpace().isEmpty()) {
				 System.out.println("Tie");
			 }
		}
			
		
		}
		
		
	
		
		
	}

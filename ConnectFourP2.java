import java.util.Scanner;

public class ConnectFourP2 {
	
	//convert input column into number
	public static int charToNum(String letter){
		int num = -1;
		switch (letter) {
		case "a":
			num = 0;
			break;
		case "b":
			num = 1;
			break;
		case "c":
			num = 2;
			break;
		case "d":
			num = 3;
			break;
		case "e":
			num = 4;
			break;
		case "f":
			num = 5;
			break;
		case "g":
			num = 6;
			break;
		}
		return num;
	}

	
	//main method
	public static void main(String[] args) {
		
		BoardP2 b = new BoardP2();
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
						int column_num = charToNum(column_index);
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
					else {
						System.out.println("Next to play: YELLOW/O");
						// implement agent function
						b.minipruning(0, agent_choice,-9999,9999,first); 
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
		
		else {
			 agent_choice = b.player_red;
			 first = 2;
			 while(b.isEnd()==false) {
					step++;
					if(step%2==1) {
						if(step ==1) {
							b.AIMove = new State(5,3);
							b.calAction(b.AIMove, agent_choice);
							b.boardupdate();
						}else {
							System.out.println("NEXT TO PLAY: RED/X");
							// implement agent function
							b.minipruning(0, agent_choice, -9999, 9999, first); 
							b.calAction(b.AIMove, agent_choice);
							System.out.println("I'm thinking... Best move:"+ b.AIMove.y);
							b.boardupdate();
							if(b.isEnd()) {
								break;
							}
						}
					}
					else {
						System.out.println("NEXT TO PLAY: YELLOW/O");
						System.out.println("Your move [column]?");
						String column_index = sc.next();
						int column_num = charToNum(column_index);
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

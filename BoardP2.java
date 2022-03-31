import java.util.ArrayList; 

public class BoardP2 {

	//set 6 * 7board
	private int[][] board = new int[6][7];
	public void printBoard() {
		for(int i=0;i<board.length ;i++) {
			for (int j=0;j<board[i].length;j++) {
				System.out.print(board[i][j]);
			}
			System.out.println();
		}
	}
	public int row_index(int col_num) {
		int index = -1;
		for (int i=board.length-1;i>=0;i--) {
			//System.out.println("for row index i = "+i+" the number in that position is "+board[i][col_num]);
			if(board[i][col_num]==0) {
				index = i;
				return index; 
			}
		}
		return index;
	}
	
	
	//set player1
	public static int player_red = 1;
	
	//set player2
	public static int player_yellow = 2;
	
	public State AIMove;
	
	//update board to show the chess 
	public void boardupdate() {
		char[][] br =  {{' ', ' ', ' ',' ', ' ', ' ',' '},
						{' ', ' ', ' ',' ', ' ', ' ',' '},
						{' ', ' ', ' ',' ', ' ', ' ',' '},
						{' ', ' ', ' ',' ', ' ', ' ',' '},
						{' ', ' ', ' ',' ', ' ', ' ',' '},
						{' ', ' ', ' ',' ', ' ', ' ',' '},};
		
		for(int i = 0; i < 6; i ++) {
			for(int j= 0; j < 7; j++) {
				
				if(getBoard()[i][j] == player_red) {
					br[i][j] = 'X';
				}
				
				if(getBoard()[i][j] == player_yellow) {
					br[i][j] = 'O';
				}
			}
		}
		print(br);

	}
	
	
	//print the board
	private void print(char[][] br) {
		System.out.println("   " + "a" + "   " + "b" + "   " + "c" + "   " + "d"+ "   " + "e" +"   " + "f" + "   " + "g");
		System.out.println("1  " + br[0][0] + " " + "|" + " " + br[0][1] + " " +"|" + " " + br[0][2] + " " +"|" + " " + br[0][3] + " " + "|" + " " + br[0][4] + " " +"|" + " " + br[0][5] + " " +"|" + " " + br[0][6] + " " );
		System.out.println("  ---+---+---+---+---+---+---");
		System.out.println("2  " + br[1][0] + " " + "|" + " " + br[1][1] + " " +"|" + " " + br[1][2] + " " +"|" + " "+ br[1][3] + " " + "|" + " " + br[1][4] + " " +"|" + " " + br[1][5] + " " +"|" + " " + br[1][6] + " " );
		System.out.println("  ---+---+---+---+---+---+---");
		System.out.println("3  " + br[2][0] + " " + "|" + " " + br[2][1] + " " +"|" + " " + br[2][2] + " " +"|" + " "+ br[2][3] + " " + "|" + " " + br[2][4] + " " +"|" + " " + br[2][5] + " " +"|" + " " + br[2][6] + " " );
		System.out.println("  ---+---+---+---+---+---+---");
		System.out.println("4  " + br[3][0] + " " + "|" + " " + br[3][1] + " " +"|" + " " + br[3][2] + " " +"|" + " "+ br[3][3] + " " + "|" + " " + br[3][4] + " " +"|" + " " + br[3][5] + " " +"|" + " " + br[3][6] + " " );
		System.out.println("  ---+---+---+---+---+---+---");
		System.out.println("5  " + br[4][0] + " " + "|" + " " + br[4][1] + " " +"|" + " " + br[4][2] + " " +"|" + " "+ br[4][3] + " " + "|" + " " + br[4][4] + " " +"|" + " " + br[4][5] + " " +"|" + " " + br[4][6] + " " );
		System.out.println("  ---+---+---+---+---+---+---");
		System.out.println("6  " + br[5][0] + " " + "|" + " " + br[5][1] + " " +"|" + " " + br[5][2] + " " +"|" + " "+ br[5][3] + " " + "|" + " " + br[5][4] + " " +"|" + " " + br[5][5] + " " +"|" + " " + br[5][6] + " " );
	}

	//Define when will the game end
	public boolean isEnd() {
		return isWin(player_red) || isWin(player_yellow) || getSpace().isEmpty();
	}

	//Define requirement for win
	public boolean isWin(int player) {
		
		//Connect 4 in a row
		for(int i = 0; i < 6; i++) {
			 for(int j = 0; j < 4; j++) {
				if(getBoard()[i][j] == getBoard()[i][j+1] && getBoard()[i][j+1] == getBoard()[i][j+2] && getBoard()[i][j+2] == getBoard()[i][j+3] && getBoard()[i][j] == player) {
					return true;
				}
			 }
		}
		
		
		//Connect 4 in a column	
		for(int i = 0; i < 6; i++) {
			for(int j = 0; j < 3; j++) {				
				if(getBoard()[j][i] == getBoard()[j+1][i] && getBoard()[j+1][i] == getBoard()[j+2][i]  && getBoard()[j+2][i] == getBoard()[j+3][i] && getBoard()[j][i] == player) {
					return true;
				}
			}
		}
			
		//Connect 4 in a diagonal
		if(getBoard()[0][0] == getBoard()[1][1] && getBoard()[1][1] == getBoard()[2][2] && getBoard()[2][2] == player) {
			return true;
		}
		
		if(getBoard()[0][2] == getBoard()[1][1] && getBoard()[0][2] == getBoard()[2][0] && getBoard()[0][2] == player) {
			return true;
		}
		return false;
		
	}

	
	//Add available step into ArrayList
	public ArrayList<State> getSpace() {
		
		ArrayList<State> getSpace = new ArrayList<>();
		
		for(int i = 0; i < 6 ; i++) {
			for(int j = 0; j < 7; j++) {
				//add the botton space into arraylist
				if((getBoard()[i][j] == 0) && (i < 5) && (getBoard()[i+1][j]!=0)) {
					getSpace.add(new State(i, j));
				}else if((getBoard()[i][j] == 0) && i ==5) {
					getSpace.add(new State(i, j));
				}
			}
		}
		return getSpace;
		
	}
	
	//Place the chess into board
	public void calAction(State state, int player) {
		if(getBoard()[state.x][state.y] ==0 ) {
			getBoard()[state.x][state.y] = player;
		}
	}

	public int[][] getBoard() {
		return board;
	}

	public void setBoard(int[][] board) {
		this.board = board;
	}
	
	
	
	//Implement minimax pruning Algorithm
	public int minipruning(int depth, int turn, int alpha, int beta, int whoFirst) {
		
		
		if(isWin(player_red)){
			if(whoFirst == 1) {
				return -1;//Player wins
			}else {
				return 1;//Agent wins
			}
		}
		
		if(isWin(player_yellow)) {
			if(whoFirst == 2) {
				return -1;//Player wins
			}else {
				return 1;//Agent wins
			}
		}
		
		
		//Call the recorded arrayList
		ArrayList<State> space = getSpace();
		 
		if(space.isEmpty()) {
			return 0;
		}
		
		int max = Integer.MIN_VALUE;
		int min = Integer.MAX_VALUE;
		
		
		//Player goes first
		if(whoFirst == 1) {
			for(int i = 0; i < space.size(); i++) {
				State state = space.get(i);
				
					if(turn == player_red) {
						calAction(state, player_red);
						getBoard()[state.x][state.y] = 0;
						
						//Setting depth limit = 5 to avoid long time search
						if(depth == 5) {
							AIMove = state;
							return 0;
						}
						
						
						int currentScore = minipruning(depth + 1, player_yellow, alpha, beta,1);
						min = Math.min(currentScore, min);
						beta = Math.min(beta, currentScore);
						if(beta <= alpha) {
							getBoard()[state.x][state.y] = 0;
							return min;
						}
						
						
						//Player will win, game ends
						if(min == -1) {
							getBoard()[state.x][state.y] = 0;
							break;
						}
						
					}else {
						calAction(state, player_yellow);
						
						if(depth == 5) {
							AIMove = state;
							return 0;
						}
						
						int currentScore = minipruning(depth + 1, player_red, alpha, beta, 1);
	
						max = Math.max(currentScore, max);
						alpha = Math.max(alpha, currentScore);
	
						
						if(beta <= alpha) {
							getBoard()[state.x][state.y] = 0;
							return max;
						}
						
						
						//Agent evaluating the score of each point
						if(depth == 0) {
							System.out.println("Computer choose" + state +"score" + currentScore);
						}
						
							
						
						if(currentScore >=0) {
							if(depth == 0)
								AIMove = state;
							//System.out.println("test");
						}
						
						//Agent will win
						if(currentScore == 1) {
							getBoard()[state.x][state.y] = 0;
							break;
						}
					
						if(i == space.size() - 1 && max <0){
							if(depth == 0)
								AIMove = state;
						}
					}
					getBoard()[state.x][state.y] = 0; //Set point back to empty 
	
				}
			return turn == player_yellow ? max: min; //If agent returns max if player returns min
	
			
		//If agent goes first	
		}else {
			for(int i = 0; i < space.size(); i++) {
				State state = space.get(i);
				
					if(turn == player_yellow) {
						calAction(state, player_yellow);
						getBoard()[state.x][state.y] = 0;
	
						if(depth == 5) {
							AIMove = state;
							System.out.println("test");
							return 0;
						}
						
						int currentScore = minipruning(depth + 1, player_red, alpha, beta,1);
						min = Math.min(currentScore, min);
						beta = Math.min(beta, currentScore);
						if(beta <= alpha) {
							getBoard()[state.x][state.y] = 0;
							return min;
						}
						
						
						
						if(min == -1) {
							getBoard()[state.x][state.y] = 0;
							break;
						}
						
					}else {
						calAction(state, player_red);
						
						if(depth == 5) {
							boardupdate();
							AIMove = state;
							System.out.println("test");
							return 0;
						}
						
						int currentScore = minipruning(depth + 1, player_red, alpha, beta, 1);
	
						max = Math.max(currentScore, max);
						alpha = Math.max(alpha, currentScore);
	
						
						if(beta <= alpha) {
							getBoard()[state.x][state.y] = 0;
							return max;
						}
						
				
						if(depth == 0) {
							System.out.println("Computer choose" + state +"score" + currentScore);
							boardupdate();//test
						}
						
							
						
						if(currentScore >=0) {
							if(depth == 0)
								AIMove = state;
							//System.out.println("test");
						}
						
						if(currentScore == 1) {
							getBoard()[state.x][state.y] = 0;
							break;
						}
					
						if(i == space.size() - 1 && max <0){
							if(depth == 0)
								AIMove = state;
						}
					}
					getBoard()[state.x][state.y] = 0;
	
				}
			return turn == player_red ? max: min;
		}
	}

}

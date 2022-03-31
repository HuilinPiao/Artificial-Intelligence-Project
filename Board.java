import java.util.ArrayList; 

public class Board {

	//set 3 * 3board
	private int[][] board = new int[3][3];
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
		char[][] br =  {{' ', ' ', ' '},
					{' ', ' ', ' '},
					{' ', ' ', ' '}};
		
		for(int i = 0; i < 3; i ++) {
			for(int j= 0; j < 3; j++) {
				String square = " ";
				
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
	
	private void print(char[][] br) {
		System.out.println("   " + "a" + "   " + "b" + "   " + "c");
		System.out.println("1  " + br[0][0] + " " + "|" + " " + br[0][1] + " " +"|" + " " + br[0][2] + " " );
		System.out.println("  ---+---+---");
		System.out.println("2  " + br[1][0] + " " + "|" + " " + br[1][1] + " " +"|" + " " + br[1][2] + " " );
		System.out.println("  ---+---+---");
		System.out.println("3  " + br[2][0] + " " + "|" + " " + br[2][1] + " " +"|" + " " + br[2][2] + " " );
		
	}

	//Define when will the game end
	public boolean isEnd() {
		return isWin(player_red) || isWin(player_yellow) || getSpace().isEmpty();
	}

	//Define requirement for win
	public boolean isWin(int player) {
		
		//Connect 3 in a row or column
		for(int i = 0; i < 3; i++) {
				if(getBoard()[i][0] == getBoard()[i][1] && getBoard()[i][1] == getBoard()[i][2] && getBoard()[i][0] == player) {
					return true;
				}else if(getBoard()[0][i] == getBoard()[1][i] && getBoard()[1][i] == getBoard()[2][i] && getBoard()[2][i] == player) {
					return true;
				}
			
		}
		
		//Connect 3 in a diagonal
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
		
		for(int i = 0; i < 3 ; i++) {
			for(int j = 0; j < 3; j++) {
				//Add the space that is only currently in the bottom
				if((getBoard()[i][j] == 0) && (i < 2) && (getBoard()[i+1][j]!=0)) {
					getSpace.add(new State(i, j));
				}else if((getBoard()[i][j] == 0) && i ==2) {
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
	
	
	//Implement minimax function
	public int minimax(int depth, int turn, int whoFirst) {
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
		
		
		
		ArrayList<State> space = getSpace();
		 
		if(space.isEmpty()) {
			return 0;
		}
		
		int max = Integer.MIN_VALUE;
		int min = Integer.MAX_VALUE;
		
		//Player goes first
		if(whoFirst ==1) {
			for(int i = 0; i < space.size(); i++) {
				State state = space.get(i);
				
					if(turn == player_red) {
						calAction(state, player_red);
						int currentScore = minimax(depth + 1, player_yellow, 1);
						min = Math.min(currentScore, min);
						
						
						//Player will win the game
						if(min == -1) {
							getBoard()[state.x][state.y] = 0;
							break;
						}
						
					}else {
						calAction(state, player_yellow);
						int currentScore = minimax(depth + 1, player_red, 1);
						max = Math.max(currentScore, max);
						
						//Evaluate score for each available space
						if(depth == 0) {
							System.out.println(state +"score" + currentScore);
							//boardupdate();//test
						}
						
						//Let agent choose space that is good for itself
						if(currentScore >=0) {
							if(depth == 0)
								AIMove = state;
						}
						
						//Agent wins
						if(currentScore == 1) {
							getBoard()[state.x][state.y] = 0;
							break;
						}
					
						if(i == space.size() - 1 && max <0){
							if(depth == 0)
								AIMove = state;
						}
					}
					getBoard()[state.x][state.y] = 0; //Set board empty
	
				}
			return turn == player_yellow ? max: min; //If agent returns max if player returns min
	
		//Agent goes first	
		}else if(whoFirst == 2){
			for(int i = 0; i < space.size(); i++) {
				State state = space.get(i);
				
					if(turn == player_yellow) {
						calAction(state, player_yellow);
						int currentScore = minimax(depth + 1, player_red, 1);
						min = Math.min(currentScore, min);
						
						
						if(min == -1) {
							getBoard()[state.x][state.y] = 0;
							break;
						}
						
					}else {
						calAction(state, player_red);
						int currentScore = minimax(depth + 1, player_yellow, 1);
						max = Math.max(currentScore, max);
						
						if(depth == 0) {
							System.out.println("Computer choose" + state +"score" + currentScore);
							//boardupdate();//test
						}
						
						
						if(currentScore <= 0) {
							if(depth == 0)
								AIMove = state;
						}
						
						if(currentScore == -1) {
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
		return 0;

	}

}

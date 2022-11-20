/* Name: Hanrui Liu
 * NetID: hliu60
 * Email: hliu60@u.rochester.edu
 * Project 1
 * 
 * Name: Tianbo Liu
 * NetID: tliu36
 * Email: tliu36@u.rochester.edu
 * Project 1
 *
 * Name: Xubin Lou
 * NetID: xlou5
 * Email: xlou5@u.rochester.edu
 * Project 1
 */

import java.util.*;

public class CreatePlayBoard {
	
	private int row;
	private int col;
	public int[][] board;//change to public
	
	//constructor
	public CreatePlayBoard(int dim) {
		this.row=dim;
		this.col=dim;
		InitializeBoard();
	}
	
	//method to initialize the board based on the information provided
	public void InitializeBoard() {
		board = new int[this.row][this.col];
		
		//set LIGHT initial position
		board[this.row/2-1][this.col/2-1] = -1;
		board[this.row/2][this.col/2] = -1;
		
		//set DARK initial position
		board[this.row/2-1][this.col/2] = 1;
		board[this.row/2][this.col/2-1] = 1;	
	}

	//method to check availability put the "side" chess piece at (row, col) if evaluating vertical-downward
	public boolean isAvailableVDown(int[][] board, int side, int row, int col) {
		   
		   //evaluating downward
		   boolean MeetRequirement = false;
		   boolean continuity = false;
		   
		   for (int i=row; i<board.length-2; i++) {
		    //test continuity
			   if(board[i+1][col] != 0 && board[i+1][col] != side) {
				   continuity = true;
				  // System.out.println("could make moves");
			   } else {
				   continuity = false;
				   //System.out.println("should stop");
		    }
			   
		    //if continue, check following conditions
			   if(continuity == true) {
				   if(board[i+1][col] == side*(-1) && board[i+2][col] == side) {
					   //System.out.println("meet the requirement -> ox");
					   return MeetRequirement = true;
					   //break;
				   }
			   } else if(continuity == false) {
				   //System.out.println("not continue! break");
				   //return MeetRequirement=false;
				   MeetRequirement = false;
				   break;
			   }
		   }
		   
		  // System.out.println("down no position available");
		   return MeetRequirement;
		   
	}
	
	//method to check availability put the "side" chess piece at (row, col) if evaluating vertical-upward
	public boolean isAvailableVUp(int[][] board, int side, int row, int col) {
		
		  //evaluating upward
		   boolean MeetRequirement = false;
		   boolean continuity = false;
		   
		   for(int i=row; i>1; i--) {
			   
		    //test continuity
			   if(board[i-1][col] != 0 && board[i-1][col] != side) {
				   continuity = true;
				 //  System.out.println("could make moves");
			   } else {
				   continuity = false;
				   //System.out.println("should stop");
		    }
		    //if continue, check following conditions
			   if(continuity == true) {
				   if(board[i-1][col] == side*(-1) && board[i-2][col] == side) {
					 //  System.out.println("meet the requirement -> ox");
					   return MeetRequirement = true;
				   }
			   } else if(continuity == false) {
				  // System.out.println("not continue! break");
				   MeetRequirement = false;
				   break;
			   }
		   }
		   
		   //System.out.println("up no position available");
		   return MeetRequirement;
	}

	//method to check availability put the "side" chess piece at (row, col) if evaluating from horizontal-right
	public boolean isAvailableHRight(int[][] board, int side, int row, int col) {
		
		//evaluating to the right
		boolean MeetRequirement = false;
		boolean continuity = false;
		
		for (int i=col;i<board[0].length-2;i++) {
			
			//test continuity
			//System.out.println("current at " + x + " " + i);
			if (board[row][i+1] != 0 && board[row][i+1] != side) {
				continuity = true;
			//	System.out.println("could make moves");
			} else {
				continuity = false;
			//	System.out.println("should stop");
			}
			
			//if continue, check following conditions
			if (continuity==true) {
				if (board[row][i+1] == side*(-1) && board[row][i+2] == side) {
				//	System.out.println("meet the requirement -> ox");
					return MeetRequirement= true;
				}
			} else if (continuity==false) {
				//System.out.println("not continue! break");
				 MeetRequirement=false;
				 break;
			}
		}
		
		//System.out.println("right no position available");
		return MeetRequirement;
	}
	
	//method to check availability put the "side" chess piece at (row, col) if evaluating from horizontal-left
	public boolean isAvailableHLeft(int[][] board, int side, int row, int col) {
		
		//evaluating to the left
		boolean MeetRequirement = false;
		boolean continuity = false;
		
		for (int i=col;i>1;i--) {
			
			//test continuity
			if (board[row][i-1] != 0 && board[row][i-1] != side) {
				continuity = true;
				//System.out.println("could make moves");
			} else {
				continuity = false;
				//System.out.println("should stop");
			}
			
			//if continue, check following conditions
			if (continuity==true) {
				if (board[row][i-1] == side*(-1) && board[row][i-2] ==side) {
					//System.out.println("meet the requirement -> ox");
					return MeetRequirement= true;
				}
			} else if (continuity==false){
				//System.out.println("not continue! break");
				MeetRequirement=false;
				break;
			}
		}
		
		//System.out.println("left no position available");
		return MeetRequirement;
	}

	//method to check availability put the "side" chess piece at (row, col) if evaluating from TopLeft to BottomRight
	public boolean isAvailableDTopLeft2BottomRight(int[][] board, int side, int row, int col) {
		
		//evaluating from the top-left to bottom-right
		boolean MeetRequirement = false;
		boolean continuity = false;
		
		//System.out.println(Math.max(x, y));
		for (int i=1; i<board.length-Math.max(row,col); i++) {
			
			//test continuity
			if (board[row+i][col+i] != 0 && board[row+i][col+i] != side) {
				continuity = true;
				//System.out.println("continuity true, could make moves");
			} else {
				continuity = false;
				//System.out.println("continuity false, should stop");
			}
			
			//if continue, check following conditions
			if (continuity==true) {
				if (row+i+1>board.length-1 || col+i+1>board[0].length-1) {
					//System.out.println("far node doesn't exist! break");
					MeetRequirement=false;
					break;
				}
				if (board[row+i][col+i] == side*(-1) && board[row+i+1][col+i+1] == side) {
				//	System.out.println("meet the requirement -> ox");
					return MeetRequirement= true;
				}
			} else if (continuity==false) {
				// System.out.println("not continue! break");
				 MeetRequirement=false;
				 break;
			}
			//System.out.println("end loop "+ i);
		}
		
		//System.out.println("bottom-right no position available");
		return MeetRequirement;
	}
	
	//method to check availability put the "side" chess piece at (row, col) if evaluating from BottomRight to TopLeft
	public boolean isAvailableDBottomRight2TopLeft(int[][] board, int side, int row, int col) {
		
		//evaluating from bottom-right to top-left
		boolean MeetRequirement = false;
		boolean continuity = false;
		
		//System.out.println(Math.max(x, y));
		for (int i=1;i<=Math.min(row,col) && (col>0&&row>0); i++) {
			
			//test continuity
			if (board[row-i][col-i] != 0 && board[row-i][col-i] != side) {
				continuity = true;
				//System.out.println("continuity true, could make moves");
			} else {
				continuity = false;
				//System.out.println("continuity false, should stop");
			}
			
			//if continue, check following conditions
			if (continuity == true) {
				if (row-i-1<0 || col-i-1<0) {
					//System.out.println("far node doesn't exist! break");
					MeetRequirement=false;
					break;
				}
				if (board[row-i][col-i] == side*(-1) && board[row-i-1][col-i-1] == side) {
					//System.out.println("meet the requirement -> ox");
					return MeetRequirement= true;
				}
			} else if (continuity == false) {
				 //System.out.println("not continue! break");
				 MeetRequirement=false;
				 break;
			}
			//System.out.println("end loop "+i);
		}
		
		//System.out.println("top-left no position available");
		return MeetRequirement;
	}
	
	//method to check availability put the "side" chess piece at (row, col) if evaluating from TopRight to BottomLeft
	public boolean isAvailableDTopRight2BottomLeft(int[][] board, int side, int row, int col) {
		
		//evaluating from the top-left to bottom-right
		boolean MeetRequirement = false;
		boolean continuity = false;
		
		//System.out.println(Math.max(x, y));
		for (int i=1;i<=Math.max(row,col) && (row<3&&col>0);i++) {
			
			//test continuity
			if (board[row+i][col-i] != 0 && board[row+i][col-i] != side) {
				continuity = true;
				//System.out.println("continuity true, could make moves");
			} else {
				continuity = false;
				//System.out.println("continuity false, should stop");
				}
			
			//if continue, check following conditions
			if (continuity==true) {
				
				if (row+i+1>board.length-1 || col-i-1<0) {
					//System.out.println("far node doesn't exist! break");
					MeetRequirement=false;
					break;
				}
				if (board[row+i][col-i] == side*(-1) && board[row+i+1][col-i-1] ==side) {
					//System.out.println("meet the requirement -> ox");
					return MeetRequirement= true;
				}
			} else if (continuity==false) {
					//System.out.println("not continue! break");
					MeetRequirement=false;
					break;
			}
			//System.out.println("end loop "+i);
		}
		
		//System.out.println("bottom-left no position available");
		return MeetRequirement;
	}
	
	//method to check availability put the "side" chess piece at (row, col) if evaluating from BottomLeft to TopRight
	public boolean isAvailableDBottomLeft2TopRight(int[][] board, int side, int row, int col) {
		
		//evaluating from the top-left to bottom-right
		boolean MeetRequirement = false;
		boolean continuity = false;
		
		//System.out.println(Math.max(x, y));
		for (int i=1;i<=Math.max(row,col) && (row>0&&col<3);i++) {
			
			//test continuity
			if (board[row-i][col+i] != 0 && board[row-i][col+i] != side) {
				continuity = true;
				//System.out.println("continuity true, could make moves");
			} else {
				continuity = false;
				//System.out.println("continuity false, should stop");
			}
			
			//if continue, check following conditions
			if (continuity==true) {
				
				if (row-i-1<0 || col+i+1>board[0].length-1) {
					//System.out.println("far node doesn't exist! break");
					MeetRequirement=false;
					break;
				}
				if (board[row-i][col+i] == side*(-1) && board[row-i-1][col+i+1] == side) {
					//System.out.println("meet the requirement -> ox");
					return MeetRequirement= true;
				}
			} else if (continuity==false) {
				// System.out.println("not continue! break");
				 MeetRequirement=false;
				 break;
			}
			//System.out.println("end loop "+i);
		}
		
		//System.out.println("top-right no position available");
		return MeetRequirement;
	}
	
	//method to find all available positions on board from "side's" perspective
	public List<Cell> getAvailablePosition(int[][] board, int side){
		
		//create a list to store all the available cells
		List<Cell> AvailableCellList = new ArrayList<Cell>();
		
		//search every empty cell to test if it is in an available position
		for(int i=0;i<board.length;i++) {
			for(int j=0;j<board.length;j++) {
				if(board[i][j]==0) {
					if((isAvailableVDown(board,side,i,j)||isAvailableVUp(board,side,i,j)||isAvailableHRight(board,side,i,j)
						||isAvailableHLeft(board,side,i,j)||isAvailableDTopLeft2BottomRight(board,side,i,j)
						||isAvailableDBottomRight2TopLeft(board,side,i,j)||isAvailableDTopRight2BottomLeft(board,side,i,j)
						||isAvailableDBottomLeft2TopRight(board,side,i,j))) {
						Cell cell = new Cell(i,j);
						AvailableCellList.add(cell);
					}
				}
			}
		}
		return AvailableCellList;
		
	}

	//method to vertically flip the chess piece if available
	public void FlipVertically(int[][] updatedBoard, int[][] originalBoard, int side, int row, int col) {
		
		//create list to store all the available "row" position
		List<Integer> FlipPosition = new ArrayList<Integer>();
		
		if (isAvailableVDown(originalBoard, side, row, col)) {
			for (int i=row;i>=0;i++) {
				if (originalBoard[i+1][col] == side*(-1)) {
					FlipPosition.add(i+1);
				} 
				else if (originalBoard[i+1][col] == side){
					break;
				}
			}
		} 
		
		if (isAvailableVUp(originalBoard, side, row, col)) {
			for (int i=row;i>=0;i--) {
				if (originalBoard[i-1][col] == side*(-1)) {
					FlipPosition.add(i-1);
				} 
				else if (originalBoard[i-1][col] == side){
					break;
				}
			}
		} 
		/*
		if (isAvailableVDown(originalBoard, side, row, col) != true && isAvailableVUp(originalBoard, side, row, col) != true) {
			System.out.println("It has no impact vertically!!!");
		}
		*/
		
		for (int var: FlipPosition) {
			updatedBoard[var][col] = side;
		}
	}
	
	//method to horizontally flip the chess piece if available
	public void FlipHorizontally(int[][] updatedBoard, int[][] originalBoard, int side, int row, int col) {
		
		//create list to store all the available "row" position
		List<Integer> FlipPosition = new ArrayList<Integer>();
		
		if (isAvailableHRight(originalBoard, side, row, col)) {
			for (int i=col;i>=0;i++) {
				if (originalBoard[row][i+1] == side*(-1)) {
					FlipPosition.add(i+1);
				} 
				else if (originalBoard[row][i+1] == side){
					break;
				}
			}
		} 
		
		if (isAvailableHLeft(originalBoard, side, row, col)) {
			for (int i=col;i>=0;i--) {
				if (originalBoard[row][i-1] == side*(-1)) {
					FlipPosition.add(i-1);
				} 
				else if (originalBoard[row][i-1] == side){
					break;
				}
			}
		} 
		/*
		if (isAvailableHRight(originalBoard, side, row, col) != true && isAvailableHLeft(originalBoard, side, row, col) != true) {
			System.out.println("It has no impact horizontally!!!");
		}
		*/
		for (int var: FlipPosition) {
			updatedBoard[row][var] = side;
		}
	}

	//method to diagonally flip the chess piece if available
	public void FlipDiagonally(int[][] updatedBoard, int[][] originalBoard, int side, int row, int col) {
		
		//create list to store all the available "row" position
		List<Cell> FlipPosition = new ArrayList<Cell>();
		
		if (isAvailableDTopLeft2BottomRight(originalBoard, side, row, col)) {
			for (int i=1;i>=0;i++) {
				if (originalBoard[row+i][col+i] == side*(-1)) {
					FlipPosition.add(new Cell(row+i,col+i));
					//FlipPosition.add(row+i);
					//FlipPosition.add(col+i);
				} 
				else if (originalBoard[row+i][col+i] == side){
					break;
				}
			}
		} 
		
		if (isAvailableDBottomRight2TopLeft(originalBoard, side, row, col)) {
			for (int i=1;i>=0;i++) {
				if (originalBoard[row-i][col-i] == side*(-1)) {
					FlipPosition.add(new Cell(row-i,col-i));
					//FlipPosition.add(row-i);
					//FlipPosition.add(col-i);
				} 
				else if (originalBoard[row-i][col-i] == side){
					break;
				}
			}
		}
		
		if (isAvailableDTopRight2BottomLeft(originalBoard, side, row, col)) {
			for (int i=1;i>=0;i++) {
				if (originalBoard[row+i][col-i] == side*(-1)) {
					FlipPosition.add(new Cell(row+i,col-i));
					//FlipPosition.add(row+i);
					//FlipPosition.add(col-i);
				} 
				else if (originalBoard[row+i][col-i] == side){
					break;
				}
			}
		}
		
		if (isAvailableDBottomLeft2TopRight(originalBoard, side, row, col)) {
			for (int i=1;i>=0;i++) {
				if (originalBoard[row-i][col+i] == side*(-1)) {
					FlipPosition.add(new Cell(row-i,col+i));
					//FlipPosition.add(row-i);
					//FlipPosition.add(col+i);
				} 
				else if (originalBoard[row-i][col+i] == side){
					break;
				}
			}
		} 
		/*
		if (isAvailableDTopLeft2BottomRight(originalBoard, side, row, col) != true && isAvailableDBottomRight2TopLeft(originalBoard, side, row, col) != true
			&& isAvailableDTopRight2BottomLeft(originalBoard, side, row, col) != true && isAvailableDBottomLeft2TopRight(originalBoard, side, row, col) != true) {
			System.out.println("It has no impact diagonally!!!");
		}
		*/
		for (Cell cell: FlipPosition) {
			updatedBoard[cell.getRow()][cell.getCol()] = side;
		}
		
		/*
		for (int i=0; i<=FlipPosition.size()-2; i+=2) {
			updatedBoard[FlipPosition.get(i)][FlipPosition.get(i+1)] = side;
		}
		*/
	}
	
	//method to flip the board in general
	public int[][] FlipBoard(int[][] board, int side,int row, int col){
		
		int[][] updatedBoard = CopyBoard(board);
		
		FlipVertically(updatedBoard, board, side, row, col);
		FlipHorizontally(updatedBoard, board, side, row, col);
		FlipDiagonally(updatedBoard, board, side, row, col);
		updatedBoard[row][col] = side;
		
		return updatedBoard;
	}
	
	//method to copy the board
	public int[][] CopyBoard(int[][] board){
		int[][] copy = new int[board.length][board.length];
		for(int i=0;i<board.length;i++) {
			for(int j=0;j<board.length;j++){
				copy[i][j] = board[i][j];
			}
		}
		return copy;
	}

	//method to draw the board
	public void DrawBoard(int[][] board) {

		String[] LetterPart = {"a ","b ","c ","d ","e ","f ","g ","h "};
		for (int i=0;i<board.length+2;i++) {
			for (int j=0;j<board.length+2;j++) {
				//first line and last line print the letter
				if (i == 0 || i == board.length+1) {
					//four corners
					if (j==0||j==board.length+1) {
						System.out.print("  ");
					}else {
						System.out.print(LetterPart[j-1]);
					}
					
				}else {//i=1, 2, 3, 4
					if (j==0 ||j==board.length+1) {
						System.out.print(i+" ");
					}else {
						if (board[i-1][j-1] == 1) {
							System.out.print("x ");
						}else if (board[i-1][j-1] == -1) {
							System.out.print("o ");
						}else {
							System.out.print("  ");
						}
					}
				}
			}
			System.out.println();
		}
	}
	
	//method to judge who is the winner
	public String JudgeWinner(int[][] board) {
		int sum=0;
		for(int i=0;i<board.length;i++) {
			for(int j=0;j<board.length;j++) {
				sum+=board[i][j];
			}
		}
		if(sum>0) {
			return "Dark wins!";
		}else if(sum<0) {
			return "Light wins!";
		}else {
			return "Tie";
		}
	}
	
	//basic utility function to evaluate certain situation
	public int getUtility(int[][] board, int side) {
		
		int Side_Perspective_Score = 0;
		int Opponent_Perspective_Score = 0;

		for(int i=0;i<board.length;i++) {
			for(int j=0;j<board.length;j++) {
				
				if(board[i][j] == side) { Side_Perspective_Score+=1;}
				if(board[i][j] == side*(-1)) { Opponent_Perspective_Score+=1;}

			}
		}
		int result = Side_Perspective_Score-Opponent_Perspective_Score;
		if(result > 0) {
			return side;
		}else if(result <0) {
			return side*(-1);
		}else {
			return 0;
		}
		
	}
	
	//Heuristic function to judge certain board situation from "side" perspective 
	public int H_getUtility(int[][] board, int side) {
		
		int Side_Perspective_Score = 0;
		int Opponent_Perspective_Score = 0;
		
		for(int i=0;i<board.length;i++) {
			for(int j=0;j<board.length;j++) {
				if((i==0 && j==0) || (i==0 && j==board.length-1) ||
				(i==board.length-1 && j==board.length-1) ||(i==board.length-1 && j==0)) { //four corners
					
					if(board[i][j] == side) { Side_Perspective_Score+=3;}
					if(board[i][j] == side*(-1)) { Opponent_Perspective_Score+=3;}
					
					
				} else if(i==0 || j==0 || i==board.length-1 || j==board.length-1) { //four sides
					
					if(board[i][j] == side) { Side_Perspective_Score+=2;}
					if(board[i][j] == side*(-1)) { Opponent_Perspective_Score+=2;}
					
					
				}else { //middle positions
					
					if(board[i][j] == side) { Side_Perspective_Score+=1;} 
					if(board[i][j] == side*(-1)) { Opponent_Perspective_Score+=1;}
					
				}
				 
			}
		}
		
		return Side_Perspective_Score-Opponent_Perspective_Score;
	}

	//method to judge the whole board in Minimax decision
	public boolean isTerminal(int board[][]) {

		int empty = 0;
		for(int i=0;i<board.length;i++) {
			for(int j=0;j<board.length;j++){
				if(board[i][j] == 0) {
					empty++;
				}
			}
		}
		if(empty==0) {
			return true;
		}                                    //MIGHT BE A PROBLEM!!!!! (||)
		if(getAvailablePosition(board,1).isEmpty() || getAvailablePosition(board,-1).isEmpty()) {
			return true;
		}
		return false;
	}
	
	//method to judge if the game is ended
	public boolean GameEnd(int board[][]) {

		int empty = 0;
		for(int i=0;i<board.length;i++) {
			for(int j=0;j<board.length;j++){
				if(board[i][j] == 0) {
					empty++;
				}
			}
		}
		if(empty==0) {
			return true;
		}                                    //MIGHT BE A PROBLEM!!!!! (||)
		if(getAvailablePosition(board,1).isEmpty() && getAvailablePosition(board,-1).isEmpty()) {
			return true;
		}
		return false;
	}

	//Minimax method
	public Cell minmaxDecision(int[][] board, int side) {
		  
		Cell action = null;
		int util = -999999;
		List<Cell> pos = getAvailablePosition(board, side);
		  
		for (Cell var: pos) {
			if (util < minValue(FlipBoard(board, side, var.getRow(), var.getCol()), side*(-1))) {
				util = minValue(FlipBoard(board, side, var.getRow(), var.getCol()), side*(-1));
				action = var;
			}
		}
		return action;
	}
		 
	public int maxValue(int[][] board, int side) {
  
		int util = -999999;
  
		if (isTerminal(board)) {
			return getUtility(board, side);
		} else {
			List<Cell> pos = getAvailablePosition(board, side);
			for (Cell var: pos) {
				util = Math.max(util, minValue(FlipBoard(board, side, var.getRow(), var.getCol()), side*(-1)));
			}
		}
		return util;
	}
		 
	public int minValue(int[][] board, int side) {
  
		 int util = 999999;
		 if (isTerminal(board)) {
			 return getUtility(board, side);
		 } else {
			 List<Cell> pos = getAvailablePosition(board, side);
			 for (Cell var: pos) {
				 util = Math.min(util, maxValue(FlipBoard(board, side, var.getRow(), var.getCol()), side*(-1)));
			 }
		 }
  
		 return util;
	 }

	//Minimax a-b pruning new
	public Cell abminimaxDecision(int[][] board, int side){
		
	    double alpha = -99999;
	    double beta = 99999;

	  //return a list of Cell based on the side perspective
		List<Cell> AvailablePosition = getAvailablePosition(board, side);
		double OptimalValue = -99999;
		int OptimalRow = 0;
		int OptimalCol = 0;
		for(Cell cell: AvailablePosition) {
			int[][] SimulatingBoard = FlipBoard(board, side, cell.getRow(), cell.getCol());
			double CellValue = ab_pruning_minimaxValue(SimulatingBoard, side, side*(-1), alpha, beta);
			if(CellValue > OptimalValue) {
				OptimalValue = CellValue;
				OptimalRow = cell.getRow();
				OptimalCol = cell.getCol();
			 
			}
		}	 
		return new Cell(OptimalRow,OptimalCol);
	}
	
	
	public double ab_pruning_minimaxValue(int[][] board, int side, int turn, double a, double b) {
		
		if (isTerminal(board)) {
			return getUtility(board, side);
		}
		List<Cell> pos = getAvailablePosition(board, side);
		if (pos.size() == 0) {
			return ab_pruning_minimaxValue(board, side, turn*(-1), a, b);
		} else {
			double util = -99999;
			if (side != turn) {
				util = 99999;
			}
			for (Cell var: pos) {
				util = ab_pruning_minimaxValue(FlipBoard(board, turn, var.getRow(), var.getCol()), side, turn*(-1), a, b);
				if (side == turn) {
					a = Math.max(a, util);
				} else {
					b = Math.min(b, util);
				}
				if(b <= a) break;
			}
		   
			if (side == turn) return a;
			else return b;
		}
		  
	}
	
	//h-minimax a-b pruning
	public Cell H_abminimaxDecision(int[][] board, int side){
		
	    double alpha = -99999;
	    double beta = 99999;

	  //return a list of Cell based on the side perspective
		List<Cell> AvailablePosition = getAvailablePosition(board, side);
		double OptimalValue = -99999;
		int OptimalRow = 0;
		int OptimalCol = 0;
		for(Cell cell: AvailablePosition) {
			int[][] SimulatingBoard = FlipBoard(board, side, cell.getRow(), cell.getCol());
			double CellValue = h_ab_pruning_minimaxValue(SimulatingBoard, side, side*(-1),1, alpha, beta);
			if(CellValue > OptimalValue) {
				OptimalValue = CellValue;
				OptimalRow = cell.getRow();
				OptimalCol = cell.getCol();
			 
			}
		}	 
		return new Cell(OptimalRow,OptimalCol);
	}
	
	
	public double h_ab_pruning_minimaxValue(int[][] board, int side, int turn,int cutoff, double a, double b) {
		
		if (cutoff == 8) {
			return H_getUtility(board, side);
		}
		List<Cell> pos = getAvailablePosition(board, side);
		if (pos.size() == 0) {
			return h_ab_pruning_minimaxValue(board, side, turn*(-1), cutoff+1, a, b);
		} else {
			double util = -99999;
			if (side != turn) {
				util = 99999;
			}
			for (Cell var: pos) {
				util = h_ab_pruning_minimaxValue(FlipBoard(board, turn, var.getRow(), var.getCol()), side, turn*(-1), cutoff+1, a, b);
				if (side == turn) {
					a = Math.max(a, util);
				} else {
					b = Math.min(b, util);
				}
				if(b <= a) break;
			}
		   
			if (side == turn) return a;
			else return b;
		}
		  
	}
	
	/*
	//Minimax a-b pruning old
	public Cell abminmaxDecision(int[][] board, int side) {
		
		Cell action = null;
		int util = abmaxValue(board, side, -9999999, 9999999);
		System.out.println("final util is: " + util);
		List<Cell> pos = getAvailablePosition(board, side);
		for (Cell var: pos) {
			System.out.println("(" + var.getRow() + ", " + var.getCol() + "), ");
			System.out.println("getutility is: " + getUtility(FlipBoard(board, side, var.getRow(), var.getCol()), side));
			if (getUtility(FlipBoard(board, side, var.getRow(), var.getCol()), side) == util) {
				action = var;
			}
		}
		
		return action;
	}
	
 	public int abmaxValue(int[][] board, int side, int a, int b) {
 		
		if (isTerminal(board)) {
			return getUtility(board, side);//issue
		} 
		int util = -999999;
		List<Cell> pos = getAvailablePosition(board, side);
		
		for (Cell var: pos) {
			
			util = Math.max(util, abminValue(FlipBoard(board, side, var.getRow(), var.getCol()), side*(-1), a, b));
			//DrawBoard(FlipBoard(board, side, var.getRow(), var.getCol()));
			//System.out.println("max value ("+var.getRow()+ ","+var.getCol()+")");
			
			if (util >= b) return util;
			a = Math.max(a, util);
		}
		
		//System.out.println("maxfinalutility is: " + util);
		return util;
		
	}

	public int abminValue(int[][] board, int side, int a, int b) {
		
		if (isTerminal(board)) {
			return getUtility(board, side);//issue
		} 
		int util = 999999;
		List<Cell> pos = getAvailablePosition(board, side);
		for (Cell var: pos) {
			util = Math.min(util, abmaxValue(FlipBoard(board, side, var.getRow(), var.getCol()), side*(-1), a, b));
			//DrawBoard(FlipBoard(board, side, var.getRow(), var.getCol()));
			//System.out.println("min value ("+var.getRow()+ ","+var.getCol()+")");
			if (util <= a) return util;
			b = Math.min(b, util);
		}
		//System.out.println("minfinalutility is: " + util);
		return util;

	}
	
	
	//H-Minimax a-b pruning
	public Cell H_abminmaxDecision(int[][] board, int side) {
		
		Cell action = null;
		int util = H_abmaxValue(board, side, -9999999, 9999999, 0);
		List<Cell> pos = getAvailablePosition(board, side);
		
		for (Cell var: pos) {
			if (H_getUtility(FlipBoard(board, side, var.getRow(), var.getCol()), side) == util) {
				action = var;
			}
		}
		return action;
	}
	
 	public int H_abmaxValue(int[][] board, int side, int a, int b, int count) {
		
		int util = -999999;
		
		if (cutoffTerminal(count)) {
			return H_getUtility(board, side);
		} else {
			count++;
			List<Cell> pos = getAvailablePosition(board, side);
			for (Cell var: pos) {
				util = Math.max(util, H_abminValue(FlipBoard(board, side, var.getRow(), var.getCol()), side, a, b, count));
				if (util >= b) return util;
				a = Math.max(a, util);
			}
		}
		return util;
	}

	public int H_abminValue(int[][] board, int side, int a, int b, int count) {
		
		int util = 999999;
		
		if (cutoffTerminal(count)) {
			return H_getUtility(board, side);
		} else {
			count++;
			List<Cell> pos = getAvailablePosition(board, side);
			for (Cell var: pos) {
				util = Math.min(util, H_abmaxValue(FlipBoard(board, side, var.getRow(), var.getCol()), side, a, b, count));
				if (util <= a) return util;
				b = Math.min(b, util);
			}
		}
		return util;
	}
	
	*/
	
}

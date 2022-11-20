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

public class Reversi {
	
	public static String[] LetterPart = {"a","b","c","d","e","f","g","h"};
	
	
	public static void main(String[] args) {		
		//Instruction part
		Scanner scanner = new Scanner(System.in);
		System.out.println("Reversi by George Ferguson");
		System.out.println("Choose your game:");
		System.out.println("1. Small 4x4 Reversi");
		System.out.println("2. Medium 6x6 Reversi");
		System.out.println("3. Standard 8x8 Reversi");
		System.out.println("Your choice:");
		int gametype = scanner.nextInt();
		int dim;//get the playboard dimension;
		if(gametype == 1) {
			dim = 4;
		}else if(gametype == 2) {
			dim = 6;
		}else {
			dim = 8;
		}
	    
		System.out.println("Choose your opponent:");
		System.out.println("1. An agent that plays randomly");
		System.out.println("2. An agent that uses MINIMAX");
		System.out.println("3. An agent that uses MINIMAX with alpha-beta pruning");//upper/lower bound(if next state is inside, no consider
		System.out.println("4. An agent that uses H-MINIMAX with a fixed depth cutoff and a-b pruning");
		System.out.println("Your choice:");
		int gamedifficulty = scanner.nextInt();
	     
		System.out.println("Do you want to play DARK (x) or LIGHT (o)?");
		//identify side
		String chooseside = scanner.next();
		int playerside;
		int pcside;
		String AI;
	    if(chooseside.equalsIgnoreCase("x")) {
	    	playerside=1;//dark
	    	pcside=-1;//light
	    	AI = "o";
	    }else {
	    	playerside=-1;//light
	    	pcside=1;//dark
	    	AI = "x";
	    }
	    
	    //setup the game
	    CreatePlayBoard game = new CreatePlayBoard(dim);
	    game.DrawBoard(game.board);
	    System.out.println("Next to play: DARK");
	    int turn = 1;  //Dark always go first
	   
	    while(true) {
	    	
	    	if(turn == playerside) {
		    	
		    	List<Cell> AvailablePosition = game.getAvailablePosition(game.board, playerside);
		    	
		    	if(!AvailablePosition.isEmpty()) {
		    		String decision = "";
		    		List<String> APL = new ArrayList<String>();
		            for (Cell cell : AvailablePosition) {
		                APL.add(Integer.toString(cell.getCol()) + Integer.toString(cell.getRow()));
		            }
		            print(APL);
		            int col = 0,row = 0;
		            while (true) {
		            	
		                System.out.println("Your move:");
		                decision = scanner.next();
		                col=mInp(decision.charAt(0));
		                row=Character.getNumericValue(decision.charAt(1))-1;
		                String test=Integer.toString(col)+Integer.toString(row);
		                
		                if (APL.contains(test)){
		                	
		                	break;
		                	
		                } else {
		                	
		                    System.out.println("WRONG DECISION, PLEASE TRY AGAIN!");
		                    
		                }
		            }
		            System.out.println(chooseside+" ---> "+decision);
		            game.board = game.FlipBoard(game.board, playerside, row, col);
		            game.DrawBoard(game.board);
		            if(playerside==1) {
		                System.out.println("Next to play: Light");
		            }else {
		                System.out.println("Next to play: Dark");
		            }
		    		
		    	} else{
		    		
		    		System.out.println("No available positions for HUMAN");
		    		
		    		if(game.GameEnd(game.board)) {
		    			
		    			System.out.println("Game Over! "+ game.JudgeWinner(game.board));
		    			break;
		    			
		    		}
		    	}
		    	
		    	//change player
		    	turn *= -1;

		    }else { // AI's turn
		    	
		    	List<Cell> AvailablePosition = game.getAvailablePosition(game.board, pcside);
		    	
		    	if(!AvailablePosition.isEmpty()) {
		    		
		    		Cell BestDecision = null;
		            long start = System.currentTimeMillis();
		            if(gamedifficulty==1) {
		            	
		            	System.out.println("AI is finding a random position...");
		            	Random generator = new Random();
	                    int index = generator.nextInt(AvailablePosition.size());
		            	BestDecision = AvailablePosition.get(index);
		            	
		            }else if(gamedifficulty==2) {
		            	
		            	System.out.println("AI is finding a position using minimax search...");
		            	BestDecision = game.minmaxDecision(game.board, pcside);
		            	
		            }else if(gamedifficulty==3) {
		            	
		            	System.out.println("AI is finding a position using minimax a-b pruning search...");
		            	BestDecision = game.abminimaxDecision(game.board, pcside);
		            	
		            }else if(gamedifficulty==4) {
		            	
		            	System.out.println("AI is finding a position using h-minimax a-b pruning search...");
		            	BestDecision = game. H_abminimaxDecision(game.board, pcside);
		            	
		            }
		            
		            long finish = System.currentTimeMillis();
		            long timeElapsed = finish - start;
		            String decision = LetterPart[BestDecision.getCol()]+(BestDecision.getRow()+1);
		            System.out.println(String.format("Elapsed time: %.3f secs", timeElapsed / 1000.0));
		            System.out.println(AI+" ---> "+decision);
		            game.board = game.FlipBoard(game.board, pcside, BestDecision.getRow(), BestDecision.getCol());
		            game.DrawBoard(game.board);
		            if(playerside==1) {
		                System.out.println("Next to play: Dark");
		            }else {
		                System.out.println("Next to play: Light");
		            }
		    		
		    		
		    	} else {
		    		
		    		System.out.println("No available positions for AI");
		    		
		    		if(game.GameEnd(game.board)) {
		    			
		    			System.out.println("Game Over! "+game.JudgeWinner(game.board));
		    			break;
		    			
		    		}
		    	}
		    	
		    	//change player
		    	turn *= -1;
		    }
	
	    }
	    
    }
	
	
	public static void print(List<String> APL) {
		System.out.print("Available Positions: [ ");
		for(int i=0;i<APL.size();i++) {
			int col = Integer.parseInt(APL.get(i).substring(0,1));
			int row = Integer.parseInt(APL.get(i).substring(1));
			
			System.out.print(LetterPart[col]);
			System.out.print(row+1);
			System.out.print(" ");
			
		}
		System.out.print("]");
		System.out.println();
	}
	
	//modify input string to position number in board array
	public static int mInp(char inp) {
        int i;
        if (inp=='a') { 
        	i = 0;
        }else if (inp=='b') { 
        	i = 1;
        }else if (inp=='c') {
        	i = 2;
        }else if (inp=='d') {
        	i = 3;
        }else if (inp=='e') {
        	i = 4;
        }else if (inp=='f') {
        	i = 5;
        }else if (inp=='g') {
        	i = 6;
        }else if (inp=='h') {
        	i = 7;
        }else {
        	i = -1;
        }
        return i;
    }
}

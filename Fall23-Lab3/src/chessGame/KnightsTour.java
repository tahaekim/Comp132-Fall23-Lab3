package chessGame;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class KnightsTour {
	
	private static Random random;
	private static int[] horizontal = {2, 1, -1, -2, -2, -1, 1, 2};
	private static int[] vertical = {-1, -2, -2, -1, 1, 2, 2, 1};
	private static int currentRow;
	private static int currentColumn;
	private static int counter;
	private static int[][] chessBoard;
	private static int boardSize;
	
	public KnightsTour() {
		
	}
	
	private static boolean isLegalMove(int row, int col) {
		
		if (row >= 0 && row < boardSize && col >= 0 && col < boardSize && chessBoard[row][col] == 0){
			return true;
		}else {
			return false;
		}
	}
	
	private static void move(int i) {
		int moveNumber = i;
		currentRow += vertical[moveNumber];
		currentColumn += horizontal[moveNumber]; //eksi olma durumuna bak
	}
	
	private static void markBoard() {
		chessBoard[currentRow][currentColumn] = counter;
	}
	
	private static void increaseCounter() {
		counter++;
	}
	
	private static void printBoard() {
		System.out.println("The tour ended with " + counter + " moves.");
		if (counter == boardSize*boardSize) {
			System.out.println("This was a full tour.");
		}else {
			System.out.println("This was not full tour.");
		}
		
		System.out.print("\t");
		
		for(int i = 0; i < boardSize; i++) {
			System.out.print(i + "\t");
		}
		System.out.println("\n");
		
		int head = 0;
		for(int a = 0; a < boardSize; a++) {
			System.out.print(head + "\t");
			for(int b = 0; b < chessBoard[a].length; b++) {
				System.out.print(chessBoard[a][b] + "\t");
			}
			System.out.println();
			head++;
		}
	}
	
	private static boolean deterministicTour() {
		
		for (int i = 0; i <= 7; i++) {
			int row = vertical[i] + currentRow;
			int col = horizontal[i] + currentColumn;
			
			if(isLegalMove(row, col)) {
				move(i);
				increaseCounter();
				markBoard();
				
				return true;
			}
		}
		return false;
	}
	
	public static void singleTour(Scanner sc) {
		int i = sc.nextInt();
		boardSize = i;
		chessBoard = new int[i][i];
		
		int seed = 7;
	    random = new Random(seed);
	    
		int rrow = random.nextInt(i);
		int rcol = random.nextInt(i);
		
		currentRow = rrow;
		currentColumn = rcol;
		chessBoard[rrow][rcol] = 1;
		counter = 1;
		
		int a = 1;
		while (a == 1 && counter < i*i ){
			if (deterministicTour() == false) {
				a = 0;
			}
		}
		
		printBoard();
	}
	
	private static int legalMoveCounter(int row, int col) {
		int count = 0;
		for (int i = 0; i <= 7; i++) {
			int a = row;
			int b = col;
			
			a += vertical[i];
			b += horizontal[i];
			
			if(isLegalMove(a, b)) {
				count++;
			}
		}
		return count;
	}
	
	private static int nextMoveType() {
		ArrayList<Integer> possibles = new ArrayList<>();

		for (int i = 0; i <= 7; i++) {
			int row = vertical[i] + currentRow;
			int col = horizontal[i] + currentColumn;
			
			if(isLegalMove(row, col)) {
				possibles.add(i);
			}
		}
		
		int fewestMove = -1;
		int fewest = 8;
		for(int i = 0; i < possibles.size(); i++) {
			int moveType = possibles.get(i);
			int nextRow = vertical[moveType] + currentRow;
			int nextCol = horizontal[moveType] + currentColumn;
			int count = legalMoveCounter(nextRow, nextCol);
			
			if (fewest > count) {
				fewestMove = possibles.get(i);
				fewest = count;
			}	
		}
		return fewestMove;
	}
	
	private static boolean WarnsdorffTour() {
		int a = nextMoveType();
		if(a != -1) {
			move(a);
			increaseCounter();
			markBoard();
			
			return true;
		}else {
			return false;
		}
	}

	public static void singleTourWarnsdorff(Scanner sc) {
		int i = sc.nextInt();
		boardSize = i;
		chessBoard = new int[i][i];
		
		int seed = 7;
	    random = new Random(seed);
	    
		int rrow = random.nextInt(i);
		int rcol = random.nextInt(i);
		
		currentRow = rrow;
		currentColumn = rcol;
		chessBoard[rrow][rcol] = 1;
		counter = 1;
		
		int a = 1;
		while (a == 1 && counter < i*i ){
			if (WarnsdorffTour() == false) {
				a = 0;
			}
		}
		printBoard();
	}
	
	private static boolean newDeterministicTour() {
		random = new Random();
		int moveType = random.nextInt(8);
		for (int i = 0; i < 8; i++) {
			int row = vertical[moveType] + currentRow;
			int col = horizontal[moveType] + currentColumn;
			
			if(isLegalMove(row, col)) {
				move(moveType);
				increaseCounter();
				markBoard();
				
				return true;
			}
			moveType = (moveType + 1) % 8;
		}
		return false;
	}

	
	public static void thousandTours(Scanner sc) {
		int i = sc.nextInt();
		boardSize = i;
		chessBoard = new int[i][i];
		
		random = new Random(7);
		int rrow = random.nextInt(i);
		int rcol = random.nextInt(i);
		
		int [] tours;
		tours = new int[i*i];
		
		currentRow = rrow;
		currentColumn = rcol;
		chessBoard[rrow][rcol] = 1;
		counter = 1;
		
		
		for (int a = 0; a < 1000; a++){
			while (newDeterministicTour());
			tours[counter - 1]++;
			counter = 1;
			rrow = random.nextInt(i);
			rcol = random.nextInt(i);
			chessBoard = new int[i][i];
			currentRow = rrow;
			currentColumn = rcol;
			chessBoard[rrow][rcol] = 1;
		}
		
		for(int b = 0; b < i*i; b++) {
			System.out.println(tours[b] + "\t" + (b+1));
		}
	}
	
	public static void fullTour(Scanner sc) {
		boardSize = sc.nextInt();
		random = new Random(7);
		int tours [] = new int[boardSize*boardSize];
		
		//Run the program until you get full tour..
		//See the while loop..
		while (counter != boardSize*boardSize) {
			currentRow = random.nextInt(boardSize);
			currentColumn = random.nextInt(boardSize);
			chessBoard = new int[boardSize][boardSize];
			chessBoard[currentRow][currentColumn] = 1;
			counter = 1;
			while(newDeterministicTour());
			tours[counter-1]++;			
		}
		
		for(int b = 0; b < boardSize*boardSize; b++) {
			System.out.println(tours[b] + "\t" + (b+1));
		}
	}
}

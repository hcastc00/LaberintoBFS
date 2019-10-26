package prg2.p2;


import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int size = sc.nextInt();
		//Clear buffer
		sc.hasNextLine();
		System.out.println(size);
		Board board = new Board(size);
		board.readBoard();
		board.solve();
		sc.close();
	}

	
	
	
}

package prg2.p2;


import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int size = sc.nextInt();
		//Clear buffer
		sc.nextLine();
		Board board = new Board(size, sc);
		board.readBoard();
		board.solve();
		sc.close();
	}


}

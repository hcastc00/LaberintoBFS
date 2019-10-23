package prg2.p2;


import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		System.out.println("Introduce el tamaño del laberinto: ");
//		Scanner sc = new Scanner(System.in);
//		int size = sc.nextInt();
//		sc.close();

		Board board = new Board(3);
		board.readBoard();
		board.print2D();

		Box test = new Box(1,2);
		System.out.println(test);
	}

	
	
	
}

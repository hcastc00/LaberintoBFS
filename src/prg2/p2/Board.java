package prg2.p2;

import java.util.Arrays;
import java.util.Scanner;

public class Board {
	
	public int size;
	public String[][] board;

	/*
	 * Contructor de la clase Board
	 * Crea un tablero de Strings con el tamaño dado
	 */
	public Board(int size) {
		this.size = size;
		this.board = new String [size][size];
	}

	
	/*
	 * Lee el tablero
	 */
	public void readBoard() {
		Scanner sc = new Scanner(System.in);
		String input;
		String[] inputArray = new String[this.size];
		
		for (int y = 0; y < this.size; y++) {
			input = sc.nextLine();
			inputArray = input.split("");
			for (int x = 0; x < this.size; x++) {
				this.board[y][x] = inputArray[x];
			}
			
		}
		
		sc.close();
	}

    public void print2D() {
        // Loop through all rows
        for (String[] row : this.board) {

            // converting each row as string
            // and then printing in a separate line
            System.out.println(Arrays.toString(row));
        }
        System.out.println("\n");
    }

}


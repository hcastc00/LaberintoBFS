package prg2.p2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Board {
    int[] dy = {1, -1, 0, 0, 1, 1, -1, -1};
    int[] dx = {0, 0, 1, -1, 1, -1, -1, 1};


    int size;
    private char[][] board;
    public List<Box> solution = new ArrayList<Box>();
    public StringBuffer solPrint = new StringBuffer();

    /*
     * Contructor de la clase Board
     * Crea un tablero de Strings con el tamaño dado
     */
    public Board(int size) {
        this.size = size;
        this.board = new char[size][size];
    }


    /*
     * Lee el tablero
     */
    public void readBoard() {
        Scanner sc = new Scanner(System.in);
        String input;

        for (int y = 0; y < this.size; y++) {
            input = sc.nextLine();
            for (int x = 0; x < this.size; x++) {
                this.board[y][x] = input.charAt(x);
            }
        }
        sc.close();

        //If the maze has a proper ending box it identifies it with a 'e'(exit) to help identify it
        if (this.board[this.size - 1][this.size - 1] == '0' || this.board[this.size - 1][this.size - 1] == '*') {
            this.board[this.size - 1][this.size - 1] = 'e';
        } else invalidImput();

        //If the maze has a proper ending box it identifies it with a 's'(start) to help identify it
        if (this.board[0][0] == '0') {
            this.board[0][0] = 's';
        } else invalidImput();

    }

    public boolean existsPrice() {
        boolean out = false;
        for (int y = 0; y < this.size; y++) {
            for (int x = 0; x < this.size; x++) {
                if (this.board[y][x] == '*') {
                    out = true;
                    break;
                }
            }
        }
        return out;
    }

    public void exploreNeighbours(int y, int x) {
        int yy, xx;
        for (int i = 0; i < 8; i++) {
            yy = y + dy[i];
            xx = x + dx[i];

            //Check that the position is inside the board, isnt visited and isnt a wall
            if (yy < 0 || xx < 0 || yy >= this.size || xx >= this.size) continue;
            if (visited(y, x)) continue;
            if (this.board[y][x] == '1') continue;

        }
    }

    public boolean visited(int y, int x) {
        //TODO
        return false;
    }

    //	Returns the position of the price * in the board
    public Box wherePrice() {
        Box price = new Box(this.size - 1, this.size - 1);
        for (int y = 0; y < this.size; y++) {
            for (int x = 0; x < this.size; x++) {
                if (this.board[y][x] == '*') {
                    price = new Box(x, y);
                }
            }
        }
        return price;
    }

    public void solve() {
        if (this.existsPrice()) {
            this.board[this.size][this.size] = 0;
            solveBFS(0, 0, this.wherePrice().x, this.wherePrice().y);
            this.board[0][0] = 0;
            this.board[this.wherePrice().y][this.wherePrice().y] = 'e';
            this.board[this.size][this.size] = 's';
            //solveBFS();
        }
    }

    private void solveBFS(int xStart, int yStart, int xEnd, int yEnd) {
        //TODO implemntar
    }

    public void invalidImput() {
        System.out.println("Entrada invalida");
        System.exit(0);
    }

    public void print2D() {
        // Loop through all rows
        for (char[] row : this.board) {

            // converting each row as string
            // and then printing in a separate line
            System.out.println(Arrays.toString(row));
        }
        System.out.println("\n");
    }

}


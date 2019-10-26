package prg2.p2;

import java.util.*;

class Board {
    private int[] dy = {1, -1, 0, 0, 1, 1, -1, -1};
    private int[] dx = {0, 0, 1, -1, 1, -1, -1, 1};
    private boolean[][] visited;
    private int size;
    private char[][] board;
    private Queue<Box> nodes = new LinkedList<Box>();
    private int nodesNextLayer = 0;
    private boolean foundExit = false;
    private Queue<Box> path = new LinkedList<Box>();

    /*
     * Contructor de la clase Board
     * Crea un tablero de Strings con el tamaño dado
     */
    Board(int size) {
        this.size = size;
        this.board = new char[size][size];
        this.visited = new boolean[size][size];

        for (int y = 0; y < this.size; y++) {
            for (int x = 0; x < this.size; x++) {
                this.visited[y][x] = false;
            }
        }
    }

    /*
     * Lee el tablero
     */
    void readBoard() {
        Scanner sc = new Scanner(System.in);
        String input = null;

        for (int y = 0; y < this.size; y++) {
            if(sc.hasNextLine()) {
                input = sc.nextLine();
                for (int x = 0; x < this.size; x++) {
                    this.board[y][x] = input.charAt(x);
                }
            }
        }

        if (this.board[0][0] != '0' || this.board[this.size - 1][this.size - 1] != '0') noExit();
    }

    private boolean existsPrice() {
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

    private boolean visited(int y, int x) {
        return this.visited[y][x];
    }

    //	Returns the position of the price * in the board
    private Box wherePrice() {
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

    void solve() {
        StringBuilder out = new StringBuilder();
        if (this.existsPrice()) {
            int priceX = this.wherePrice().x;
            int priceY = this.wherePrice().y;

            this.board[priceY][priceX] = 'e';
            this.solveBFS(0, 0);
            if (foundExit) {
                //Print required data
                out.append("SI, CON PREMIO.\n");
                while (this.path.size() > 1) {
                    out.append(path.poll()).append(" ");
                }
                out.append(path.poll()).append("* ");

                this.board[priceY][priceX] = '0';
                this.board[this.size - 1][this.size - 1] = 'e';
                this.solveBFS(priceX, priceY);
                if (foundExit) {
                    //Print required data
                    path.poll();
                    while (this.path.size() > 0) {
                        out.append(path.poll()).append(" ");
                    }
                    System.out.println(out.toString());
                } else
                    this.noExit();
            }
            //No path to price
            else {
                this.board[this.size - 1][this.size - 1] = 'e';
                this.solveBFS(0, 0);

                //Print the required data
                if (foundExit) {
                    System.out.println("SI, SIN PREMIO.");
                    while (this.path.size() > 0) {
                        System.out.print(path.poll() + " ");
                    }
                } else this.noExit();
            }

        } else {
            this.board[this.size - 1][this.size - 1] = 'e';
            this.solveBFS(0, 0);

            //Print the required data
            if (foundExit) {
                System.out.println("SI, SIN PREMIO.");
                while (this.path.size() > 0) {
                    System.out.print(path.poll() + " ");
                }
            } else noExit();

        }
    }

    private void solveBFS(int xStart, int yStart) {
        path.clear();
        nodes.clear();
        nodesNextLayer = 0;
        int nodesLeftLayer = 1;
        foundExit = false;
        for (int y = 0; y < this.size; y++) {
            for (int x = 0; x < this.size; x++) {
                this.visited[y][x] = false;
            }
        }

        Box start = new Box(xStart, yStart);
        this.nodes.add(start);
        this.visited[yStart][xStart] = true;
        Box node;
        while (this.nodes.size() > 0) {
            node = this.nodes.poll();
            if (this.board[node.y][node.x] == 'e') {
                this.path.add(new Box(node.x, node.y));
                this.foundExit = true;
                break;
            }
            exploreNeighbours(node.y, node.x);
            nodesLeftLayer--;
            if (nodesLeftLayer == 0) {
                this.path.add(new Box(node.x, node.y));
                nodesLeftLayer = nodesNextLayer;
                nodesNextLayer = 0;
            }
        }
    }

    private void exploreNeighbours(int y, int x) {
        int yy, xx;
        Box box;
        for (int i = 0; i < 8; i++) {
            yy = y + dy[i];
            xx = x + dx[i];

            //Check that the position is inside the board, isnt visited and isnt a wall
            if (yy < 0 || xx < 0 || yy >= this.size || xx >= this.size) continue;
            if (this.visited(yy, xx)) continue;
            if (this.board[yy][xx] == '1') continue;

            box = new Box(xx, yy);
            nodes.add(box);
            this.visited[yy][xx] = true;
            nodesNextLayer++;
        }

    }

    private void noExit() {
        System.out.println("NO.");
        System.exit(0);
    }
}


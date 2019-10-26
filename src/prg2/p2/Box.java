package prg2.p2;

public class Box {
    int x;
    int y;

    Box(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public String toString() {
        return "(" + (x + 1) +
                "," + (y + 1) +
                ')';
    }
}

package prg2.p2;

public class Box {
    int x;
    int y;

    Box(int x, int y){
        this.x = x;
        this.y = y;
    }

    public String toString() {
        final StringBuffer sb = new StringBuffer();
        sb.append("(").append(x+1);
        sb.append(",").append(y+1);
        sb.append(')');
        return sb.toString();
    }




}

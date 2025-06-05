public class Point implements Comparable<Point>{
    private final int x, y;
    private final String value;


    public Point(int x, int y, String value) {
        this.x = x;
        this.y = y;
        this.value = value;
    }  
    
    public int getX() { return x; }
    
    public int getY() { return y; }

    public String getValue() { return value; }

    @Override
    public String toString() {
        //return "{"+x+", "+y+": "+value+ "}";
        return value;
    }      

    @Override
    public int compareTo(Point p) {
        return this.value.compareTo(p.value) ;
    }

}

import java.util.LinkedList;
import java.util.List;


public class PointsGraph {

    private int E;
    
    private List<Point>[][] mat_adj;
    
    private final int MAX_HEIGHT;
    private final int MAX_WIDTH; 
    
    /**
     * Initializes an empty graph
     *
     * @param  num_rows number of rows
     * @param  num_columns number of columns
     * @throws IllegalArgumentException if {@code V < 0}
     */
    public PointsGraph(int num_rows, int num_columns) {
        if (num_rows < 0 || num_columns < 0) throw new IllegalArgumentException("Number of vertices must be nonnegative");
        this.E = 0;
        MAX_HEIGHT = num_rows;
        MAX_WIDTH = num_columns;
        
        mat_adj = (List<Point>[][]) new List[MAX_HEIGHT][MAX_WIDTH];
       
        for (int i = 0; i < MAX_HEIGHT; i++) {
            for (int j = 0; j < MAX_WIDTH; j++) {          
                mat_adj[i][j] = new LinkedList<>();    
     
            }            
        }        
    }


    public int MAX_HEIGHT() {
        return MAX_HEIGHT;
    }

    public int MAX_WIDTH() {
        return MAX_WIDTH;
    }

    /**
     * Returns the number of edges in this graph.
     *
     * @return the number of edges in this graph
     */
    public int E() {
        return E;
    }

    /**
     * Validate the vertex
     *
     * @param  p the vertex
     * @throws IllegalArgumentException unless {@code 0 <= p.x < MAX_HEIGHT && 0 <= p.y < MAX_WIDTH} 
     */
    private void validateVertex(Point p) {
        if (p.getX() < 0 || p.getX() >= MAX_HEIGHT)
            throw new IllegalArgumentException("vertex " + p + " is not between 0 and " + (MAX_HEIGHT-1));
        if (p.getY() < 0 || p.getY() >= MAX_WIDTH)
            throw new IllegalArgumentException("vertex " + p + " is not between 0 and " + (MAX_WIDTH-1));
    }

    /**
     * Adds the undirected edge p1-p2 to this graph.
     *
     * @param  p1 one vertex in the edge
     * @param  p2 the other vertex in the edge
     * @throws IllegalArgumentException unless both {@code 0 <= p1.x < MAX_HEIGHT && 0 <= p1.y < MAX_WIDTH} and {@code 0 <= p2.x < MAX_HEIGHT && 0 <= p2.y < MAX_WIDTH}
     */
    public void addEdge(Point p1, Point p2) {
        validateVertex(p1);
        validateVertex(p2);
        E++;
        mat_adj[p1.getX()][p1.getY()].add(p2);
        mat_adj[p2.getX()][p2.getY()].add(p1);
    }


    /**
     * Returns the vertices adjacent to vertex {@code p}.
     *
     * @param  v the vertex
     * @return the vertices adjacent to vertex {@code p}, as an iterable
     * @throws IllegalArgumentException unless {@code 0 <= p.x < MAX_HEIGHT && 0 <= p.y < MAX_WIDTH}
     */
    public Iterable<Point> adj(Point p) {
        validateVertex(p);
        return mat_adj[p.getX()][p.getY()];
    }

    /**
     * Returns the degree of vertex {@code p}.
     *
     * @param  p the vertex
     * @return the degree of vertex {@code p}
     * @throws IllegalArgumentException unless {@code 0 <= p.x < MAX_HEIGHT && 0 <= p.y < MAX_WIDTH}
     */
    public int degree(Point p) {
        validateVertex(p);
        return mat_adj[p.getX()][p.getY()].size();
    }


    /**
     * Returns a string representation of this graph.
     *
     * @return the number of vertices <em>V</em>, followed by the number of edges <em>E</em>,
     *         followed by the <em>V</em> adjacency lists
     */
    // @Override
    // public String toString() {
    //     StringBuilder s = new StringBuilder();
    //     s.append(V).append(" vertices, ").append(E).append(" edges ").append(NEWLINE);
    //     for (int v = 0; v < V; v++) {
    //         s.append(v).append(": ");
    //         for (Point w : mat_adj[MAX_HEIGHT][MAX_WIDTH]) {
    //             s.append(w).append(" ");
    //         }
    //         s.append(NEWLINE);
    //     }
    //     return s.toString();
    // }
   

}

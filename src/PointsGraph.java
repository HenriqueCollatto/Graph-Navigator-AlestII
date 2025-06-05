import java.util.LinkedList;
import java.util.List;


public class PointsGraph {
    private static final String NEWLINE = System.getProperty("line.separator");

    private final int V;
    private int E;
    //private Bag<Point>[][] mat_adj;
    private List<Point>[][] mat_adj;
    
    private final int MAX_HEIGHT;
    private final int MAX_WIDTH; 
    
    /**
     * Initializes an empty graph with {@code V} vertices and 0 edges.
     * param V the number of vertices
     *
     * @param  V number of vertices
     * @throws IllegalArgumentException if {@code V < 0}
     */
    public PointsGraph(int V, int num_rows, int num_columns) {
        if (V < 0) throw new IllegalArgumentException("Number of vertices must be nonnegative");
        this.V = V;
        this.E = 0;
        MAX_HEIGHT = num_rows;
        MAX_WIDTH = num_columns;
        
        //mat_adj = (Bag<Point>[][]) new Bag[MAX_HEIGHT][MAX_WIDTH];
        mat_adj = (List<Point>[][]) new List[MAX_HEIGHT][MAX_WIDTH];
       
        for (int i = 0; i < MAX_HEIGHT; i++) {
            for (int j = 0; j < MAX_WIDTH; j++) {          
                mat_adj[i][j] = new LinkedList<>();    
     
            }            
        }        
    }

    /**
     * Returns the number of vertices in this graph.
     *
     * @return the number of vertices in this graph
     */
    public int V() {
        return V;
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

    // throw an IllegalArgumentException unless {@code 0 <= v < V}
    private void validateVertex(Point p) {
        if (p.getX() < 0 || p.getX() >= MAX_HEIGHT)
            throw new IllegalArgumentException("vertex " + p + " is not between 0 and " + (MAX_HEIGHT-1));
        if (p.getY() < 0 || p.getY() >= MAX_WIDTH)
            throw new IllegalArgumentException("vertex " + p + " is not between 0 and " + (MAX_WIDTH-1));
    }

    /**
     * Adds the undirected edge v-w to this graph.
     *
     * @param  v one vertex in the edge
     * @param  w the other vertex in the edge
     * @throws IllegalArgumentException unless both {@code 0 <= v < V} and {@code 0 <= w < V}
     */
    public void addEdge(Point p1, Point p2) {
        validateVertex(p1);
        validateVertex(p2);
        E++;
        mat_adj[p1.getX()][p1.getY()].add(p2);
        mat_adj[p2.getX()][p2.getY()].add(p1);
    }


    /**
     * Returns the vertices adjacent to vertex {@code v}.
     *
     * @param  v the vertex
     * @return the vertices adjacent to vertex {@code v}, as an iterable
     * @throws IllegalArgumentException unless {@code 0 <= v < V}
     */
    public Iterable<Point> adj(Point p) {
        validateVertex(p);
        return mat_adj[p.getX()][p.getY()];
    }

    /**
     * Returns the degree of vertex {@code v}.
     *
     * @param  v the vertex
     * @return the degree of vertex {@code v}
     * @throws IllegalArgumentException unless {@code 0 <= v < V}
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
    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        s.append(V).append(" vertices, ").append(E).append(" edges ").append(NEWLINE);
        for (int v = 0; v < V; v++) {
            s.append(v).append(": ");
            for (Point w : mat_adj[MAX_HEIGHT][MAX_WIDTH]) {
                s.append(w).append(" ");
            }
            s.append(NEWLINE);
        }
        return s.toString();
    }
    
    /**
     * Returns this graph as an input for GraphViz (dot format).
     *
     * @return dot graph representation
     */    
    // public String toDot() {
    // 	// Uses a set of edges to prevent duplicates
    // 	Set<String> edges = new HashSet<>();
    // 	StringBuilder s = new StringBuilder();
    // 	s.append("graph {").append(NEWLINE);
	// 	s.append("rankdir = LR;").append(NEWLINE);
	// 	s.append("node [shape = circle];").append(NEWLINE);
    // 	for (int v = 0; v < V; v++) {
    //         for (Point w : mat_adj[MAX_HEIGHT][MAX_WIDTH]) {
    //         	String edge = Math.min(v,w)+"-"+Math.max(v, w);
    //         	if(!edges.contains(edge)) {
    //               s.append(v).append(" -- ").append(w).append(";").append(NEWLINE);
    //               edges.add(edge);
    //         	}
    //         }
    //     }
    //     s.append("}");
    //     return s.toString();
    // }


    /**
     * Unit tests the {@code Graph} data type.
     *
     * @param args the command-line arguments
     */
    public static void main(String[] args) {
        In in = new In("./tinyG.txt");
        Graph G = new Graph(in);
        System.out.println(G);
        System.out.println();
        System.out.println(G.toDot());
    }

}

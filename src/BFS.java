import java.util.ArrayDeque;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class BFS{
    private Point [][] edgeTo;
    private int [][] distTo;
    private boolean [][] marked;
    private final Point START;

    public BFS(PointsGraph pg, Point ref) {
        this.START = ref;
        edgeTo=new Point [pg.MAX_HEIGHT()][pg.MAX_WIDTH()];
        distTo=new int [pg.MAX_HEIGHT()][pg.MAX_WIDTH()];
        marked=new boolean [pg.MAX_HEIGHT()][pg.MAX_WIDTH()];
        bfs(pg,START);
    }

    private void bfs(PointsGraph pg, Point ref) {
        Queue<Point> q = new ArrayDeque<>();
        q.add(ref);      

        distTo[ref.getX()][ref.getY()] = 0;        
        marked[ref.getX()][ref.getY()] = true;
        
        while(!q.isEmpty()){
            Point aux = q.remove();            
            
            Point point_edge = edgeTo[aux.getX()][aux.getY()];
            if(point_edge != null && aux!=point_edge){
                
                distTo[aux.getX()][aux.getY()] = distTo[point_edge.getX()][point_edge.getY()]+1;
            }
                
            for(Point adj: pg.adj(aux)){
                if(!marked[adj.getX()][adj.getY()]){
                    marked[adj.getX()][adj.getY()]=true;
                    edgeTo[adj.getX()][adj.getY()]=aux;
                    q.add(adj);
                }
            }
        }

    }

    public boolean hasPath(Point p){
        return marked[p.getX()][p.getY()];
    }

    public Iterable<Point> pathTo(Point p){
        
        if(!marked[p.getX()][p.getY()]) return null;
                
        List<Point> linklist = new LinkedList<>();
        linklist.add(p);
        
        // vai fazendo o caminho reverso, para apenas quando encontra o vertice inicial
        while(START != edgeTo[p.getX()][p.getY()]){
            p=edgeTo[p.getX()][p.getY()];
            linklist.add(p);
        }
        return linklist;
    }

}

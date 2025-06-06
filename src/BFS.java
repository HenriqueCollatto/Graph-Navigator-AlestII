import java.util.ArrayDeque;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class BFS{
    private Point [][] edgeTo;
    private int [][] distTo;
    private boolean [][] marked;
    private Point start;

    public BFS(PointsGraph pg, Point ref) {
        this.start = ref;
        edgeTo=new Point [pg.MAX_HEIGHT()][pg.MAX_WIDTH()];
        distTo=new int [pg.MAX_HEIGHT()][pg.MAX_WIDTH()];
        marked=new boolean [pg.MAX_HEIGHT()][pg.MAX_WIDTH()];
        bfs(pg,start);
    }

    private void bfs(PointsGraph pg, Point ref) {
        Queue<Point> q = new ArrayDeque<>();
        q.add(ref);        // pensar melhor
        distTo[ref.getX()][ref.getY()] = 0;        
        marked[ref.getX()][ref.getY()] = true;
        
        while(!q.isEmpty()){
            Point aux = q.remove();            
            
            
            Point point_edge = edgeTo[aux.getX()][aux.getY()];
            if(point_edge != null && aux!=point_edge){
                //System.out.println("aux: "+ aux.getX() +" "+aux.getY() );
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
        

        //Bag b = new Bag();
        List<Point> linklist = new LinkedList<>();
        linklist.add(p);
        
        //System.out.println("p: "+p + " "+ p.getX()+" "+p.getY());
        while(start != edgeTo[p.getX()][p.getY()]){
            //System.out.println(edgeTo[p.getX()][p.getY()]);
            
            p=edgeTo[p.getX()][p.getY()];
            //System.out.println("p depois: "+ p );
            linklist.add(p);
        }
        return linklist;

    }

    // public static void main(String[] args) {
    //     In in = new In("../tinyG.txt");
    //     Graph G = new Graph(in);
    //     System.out.println(G);
    //     System.out.println();
    //     System.out.println(G.toDot());

    //     System.out.println("Estou na classe Caminhamento Em Largura...");

    //     BFS cep = new BFS(G, 0);
    //     System.out.println("Existe um caminho para o 3? "+(cep.hasPath(3)?"SIM":"NÃO"));
    //     if(cep.hasPath(3)){
    //         for(int p: cep.pathTo(3))
    //             System.out.print(p+"; ");
    //         System.out.println();
    //     }
            
    //     System.out.println("Existe um caminho para o 4? "+(cep.hasPath(4)?"SIM":"NÃO"));
    //     if(cep.hasPath(4)){
    //         for(int p: cep.pathTo(4))
    //             System.out.print(p+"; ");
    //         System.out.println();
    //     }
            

    // }



}

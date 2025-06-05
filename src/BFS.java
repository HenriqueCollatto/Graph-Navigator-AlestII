import java.util.ArrayDeque;
import java.util.Queue;

public class BFS{
    private Point [][] edgeTo;
    private int [][] distTo;
    private boolean [][] marked;
    private Point start;

    public BFS(PointsGraph pg, Point ref) {
        this.start=ref;
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

            if(aux!=edgeTo[aux.getX()][aux.getY()])
                distTo[aux.getX()][aux.getY()] = distTo[edgeTo[aux.getX()][aux.getY()]][]+1;

            for(int adj: g.adj(aux))
                if(!marked[adj]){
                    marked[adj]=true;
                    edgeTo[adj]=aux;
                    q.add(adj);
                }
        }

    }

    public boolean hasPath(int v){
        return marked[v];
    }

    public Iterable<Integer> pathTo(int v){
        if(!marked[v]) return null;

        Bag b = new Bag();
        b.add(v);
        while(v!=edgeTo[v]){
            v=edgeTo[v];
            b.add(v);
        }
        return b;

    }

    public static void main(String[] args) {
        In in = new In("../tinyG.txt");
        Graph G = new Graph(in);
        System.out.println(G);
        System.out.println();
        System.out.println(G.toDot());

        System.out.println("Estou na classe Caminhamento Em Largura...");

        BFS cep = new BFS(G, 0);
        System.out.println("Existe um caminho para o 3? "+(cep.hasPath(3)?"SIM":"NÃO"));
        if(cep.hasPath(3)){
            for(int p: cep.pathTo(3))
                System.out.print(p+"; ");
            System.out.println();
        }
            
        System.out.println("Existe um caminho para o 4? "+(cep.hasPath(4)?"SIM":"NÃO"));
        if(cep.hasPath(4)){
            for(int p: cep.pathTo(4))
                System.out.print(p+"; ");
            System.out.println();
        }
            

    }



}

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Stack;


public class ReadFile {

    private double final_time;
    
    ReadFile(String path) {
        long startTime = System.nanoTime(); 
        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            String[] firstLine = br.readLine().split(" "); 
            int rows = Integer.parseInt(firstLine[0]);
            int columns = Integer.parseInt(firstLine[1]);
            
            System.out.println("\n-----------------------------------------------------------------\n");
            System.out.println("Map: " + rows + " "+ columns);   

            PointsGraph pg = new PointsGraph(rows, columns);
             
            Point[][] matrix_points = new Point[rows][columns];

            PriorityQueue<Point> min_pq = new PriorityQueue<>();

            for (int i = 0; i < rows; i++) {
                String[] line = br.readLine().split(""); 
                
                for (int j = 0; j < columns; j++) {
                    
                    Point p = new Point(i, j, line[j]);
                    matrix_points[i][j] = p;     
                    
                    if(!line[j].equals("*")){ // verifica se é ponto ou número
                        if(j != 0 && !matrix_points[i][j - 1].getValue().equals("*")){ // coluna da esquerda (menos quando a coluna é zero)
                            pg.addEdge(matrix_points[i][j - 1], matrix_points[i][j]); // adiciona uma aresta entre os dois
                            
                        } 

                        if(i != 0 && !matrix_points[i -1][j].getValue().equals("*")){ // linha de cima (menos quando a linha é zero)
                            pg.addEdge(matrix_points[i - 1][j], matrix_points[i][j]); // adiciona uma aresta entre os dois
                            
                        } 
                        
                        if(!line[j].equals(".")){ // verifica se é um numero  
                            min_pq.add(p);    // adiciona na fila de prioridade                       
                        }
                    }   
                }
            }

            //printMatrix(matrix_points, rows, columns);
            

            Point min_p1 = min_pq.poll();
            Point prev = min_p1;
            int total_distance = 0;
            
          
            while (true) { 
                Point min_p = (!min_pq.isEmpty()) ?  min_pq.poll() :  min_p1;

                BFS bfs = (min_p1 == prev) ?  new BFS(pg, min_p1) :  new BFS(pg, prev);
                
                boolean has_path = bfs.hasPath(min_p);
                //System.out.println("porto: "+ min_p + " " + has_path);
                if (has_path) {
                    List<Point> path_to = new ArrayList<>();
                    Stack<Point> stack_points = new Stack<>();
                    
                    for (Point p : bfs.pathTo(min_p)) {
                        stack_points.push(p);
                    }
                    
                    while(!stack_points.isEmpty()) {
                        path_to.add(stack_points.pop());
                    }
                    
                    // System.out.println("caminho "+ min_p + ": " + path_to);
                    
                    int cur_distance = path_to.size();
                    //System.out.println("distancia "+ min_p + ": " + cur_distance);
                    total_distance += cur_distance;
                    
                    prev = min_p;
                }
                
                if(min_p == min_p1){
                    break;
                }
            }
                                    
            System.out.println("Distancia percorrida: "+ total_distance);
            
            long estimatedTime = System.nanoTime() - startTime;
            System.out.println("Tempo em nanosegundos: " + estimatedTime);
            double seconds = (double) estimatedTime / 1000000000;
            final_time = seconds;
            System.out.println("Tempo em segundos: "+ seconds);
                  
            } catch (IOException x) {
                System.err.format("Erro de E/S: %s%n", x);
            }
        }
        
        public static <T> void printMatrix(T[][] matrix, int rows, int columns) {
            System.out.println("\nMatriz de Pontos\n");
            
            System.out.print("  |");
            for (int i = 0; i < columns; i++) {
                System.out.printf("%2d|", i);
            }
            
            System.out.println();

            for (int i = 0; i < rows; i++) {
                System.out.printf("%2d|", i);
            
                for (int j = 0; j < columns; j++) {
                    System.out.printf("%s |", matrix[i][j]);
                }
            System.out.println();
            }
        }

        public double getTime(){
            return final_time;
        }
}
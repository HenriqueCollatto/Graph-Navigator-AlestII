import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class ReadFile {
    
    ReadFile(String path) {
        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            String[] firstLine = br.readLine().split(" "); 
            int linhas = Integer.parseInt(firstLine[0]);
            int colunas = Integer.parseInt(firstLine[1]);

            PointsGraph pg = new PointsGraph(linhas * colunas, linhas, colunas);
            
           

            //String[][] matriz = new String[linhas][colunas];
            Point[][] matriz_points = new Point[linhas][colunas];

            List<Point> harbor_list = new ArrayList<>();
            MinPQ<Point> pq = new MinPQ<>();

            for (int i = 0; i < linhas; i++) {
                String[] line = br.readLine().split(""); 
                List<String> list = Arrays.asList(line);
                for (int j = 0; j < colunas; j++) {
                    
                    //matriz[i][j] = line[j];
                    
                    Point p = new Point(i, j, line[j]);
                    matriz_points[i][j] = p;


                    if(!line[j].equals("*")){
                        if(j != 0 && !matriz_points[i][j - 1].getValue().equals("*")){ // coluna da esquerda (menos quando a coluna é zero)
                            pg.addEdge(matriz_points[i][j - 1], matriz_points[i][j]); // adiciona uma aresta entre os dois
                            //System.out.println("adicionou o da esquerda"+ "(" +i+", "+j+")");
                        } 

                        if(i != 0 && !matriz_points[i -1][j].getValue().equals("*")){ // linha de cima (menos quando a linha é zero)
                            pg.addEdge(matriz_points[i - 1][j], matriz_points[i][j]); // adiciona uma aresta entre os dois
                            //System.out.println("adicionou o de cima"+ "(" +i+", "+j+")");
                        } 
                        
                        if( !line[j].equals(".")){
                            pq.insert(p);
                            harbor_list.add(p);
                            
                        }
                    }

                    
                }
                //System.out.println(list);

            }
            //System.out.println("Matriz de String");
            //imprimeMatriz(matriz, linhas, colunas);

            System.out.println("\n-----------------------------------------------------------------\n");

            System.out.println("Matriz de Pontos");
            imprimeMatriz(matriz_points, linhas, colunas);
                    
            System.out.println(harbor_list);
            
            while (!pq.isEmpty()) { 
                Point min_p = pq.delMin();
                System.out.print(min_p + ", ");
                
            }
            
           
            System.out.println("\nCaso: " + linhas + " "+ colunas);   

            // long startTime = System.nanoTime(); 
            // long estimatedTime = System.nanoTime() - startTime;
            // System.out.println("Tempo em nanosegundos: " + estimatedTime);
            // double seconds = (double) estimatedTime / 1000000000;
            // System.out.println("Tempo em segundos: "+ seconds);

        } catch (IOException x) {
            System.err.format("Erro de E/S: %s%n", x);
        }
    }

    private <T> void imprimeMatriz(T[][] matriz, int linhas, int colunas) {

        System.out.print("  |");
        for (int i = 0; i < colunas; i++) 
            System.out.printf("%2d|", i);

        System.out.println();
        

        for (int i = 0; i < linhas; i++) {
            System.out.printf("%2d|", i);

            for (int j = 0; j < colunas; j++) {
                System.out.printf("%s |", matriz[i][j]);
            }
            System.out.println();
        }
    }

    

    

}


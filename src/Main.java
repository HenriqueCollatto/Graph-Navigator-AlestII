import java.nio.file.Paths;

public class Main {
    public static void main(String[] args) {

        String[] arr = {"case0"
                        , "case1"
                        ,"case2"
                        ,"case3"
                        ,"case4"
                        ,"case5" 
                    };
        

        int iterations = 1;
        Double[][] time = new Double[iterations][arr.length];

        for (int i = 0; i < iterations; i++) {  
            for (int j = 0; j < arr.length; j++) {
                String s = Paths.get("casosDeTeste", arr[j] + ".map").toString();
                ReadFile rf = new ReadFile(s);
                time[i][j] = rf.getTime();
            }
        }

        ReadFile.printMatrix(time,iterations, arr.length);
    }
}
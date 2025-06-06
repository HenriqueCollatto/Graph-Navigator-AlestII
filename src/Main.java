import java.nio.file.Paths;

public class Main {
    public static void main(String[] args) {
        java.util.Arrays.asList(
                            "case0"
                                 ,"case1"
                                 ,"case2"
                                 ,"case3"
                                 ,"case4"
                                 ,"case5"
                                )
        .stream().map(text -> Paths.get("casosDeTeste", text + ".map").toString())
        .forEach(path -> new ReadFile(path));
    }
}
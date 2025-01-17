import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.util.Scanner;

public class Hijo {
    static BufferedReader br;
    static PrintStream out;
    static Scanner sc = new Scanner(System.in);
    static Process process;

    public static void main(String[] args) {
        try {
            ProcessBuilder processBuilder = new ProcessBuilder("java", "C:\\Users\\ADMIN\\IdeaProjects\\PadreHijoNietoCuadrado\\src\\Nieto.java");
            process = processBuilder.start();

            br = new BufferedReader(new InputStreamReader(process.getInputStream()));
            out = new PrintStream(process.getOutputStream());

            while (true){
                String s = sc.nextLine();
                if (s.equals("fin")){
                    out.println("fin");
                    out.flush();
                    break;
                }
                try {
                    out.println(s);
                    out.flush();

                    String numCuadrado = br.readLine();
                    System.out.println(numCuadrado);
                    System.out.flush();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }


        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}

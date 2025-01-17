import java.io.*;
import java.util.Scanner;

public class Padre {
    static Scanner sc = new Scanner(System.in);
    static BufferedReader br;
    static Process process;
    static PrintStream out;

    public static void main(String[] args) {
        try {
            ProcessBuilder processBuilder = new ProcessBuilder("java", "C:\\Users\\ADMIN\\IdeaProjects\\PadreHijoCuadrado\\src\\Hijo.java");
            process = processBuilder.start();

            out = new PrintStream(process.getOutputStream());
            br = new BufferedReader(new InputStreamReader(process.getInputStream()));

            while (true){
                System.out.println("Introduzca un número: ");
                String num = sc.nextLine();

                if (num.equalsIgnoreCase("fin")){
                    out.println("fin");
                    out.flush();
                    System.out.println("FIN");
                    break;
                }

                try {
                    Integer.parseInt(num);
                    out.println(num);
                    out.flush();

                    String numCuad = br.readLine();
                    System.out.println("El cuadrado de " + num + " es " + numCuad);
                } catch (NumberFormatException e) {
                    System.out.println("Introduzca un número correcto.");
                }
            }
            process.waitFor();

            br.close();
            out.close();
            sc.close();

        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

    }
}
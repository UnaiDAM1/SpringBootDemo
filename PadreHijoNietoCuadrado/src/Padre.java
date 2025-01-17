import java.io.*;
import java.util.Scanner;

public class Padre {
    static Process process;
    static Scanner sc;
    static PrintStream out;

    public static void main(String[] args) {
        sc = new Scanner(System.in);
        try {
            ProcessBuilder processBuilder = new ProcessBuilder("java", "C:\\Users\\ADMIN\\IdeaProjects\\PadreHijoNietoCuadrado\\src\\Hijo.java");
            process = processBuilder.start();

            BufferedReader br = new BufferedReader(new InputStreamReader(process.getInputStream()));
            out = new PrintStream(process.getOutputStream());

            while (true){
                System.out.println("Introduzca un número (fin para salir): ");
                String numero = sc.nextLine();

                if (numero.equals("fin")){
                    out.println("fin");
                    out.flush();
                    System.out.println("FIN");
                    break;
                }

                try {
                    Integer.parseInt(numero);
                    out.println(numero);
                    out.flush();

                    String numCuad = br.readLine();
                    System.out.println("El cuadrado de " + numero + " es " + numCuad);
                } catch (NumberFormatException e) {
                    System.out.println("Introduzca un número correcto.");
                }

            }



        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
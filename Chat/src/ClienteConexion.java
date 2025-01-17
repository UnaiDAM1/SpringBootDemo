import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class ClienteConexion {
    public ClienteConexion() {
    }

    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        System.out.println("Introduce dirección ip");
        String servidor = sc.nextLine();

        System.out.println("Introduce el puerto");
        int puerto = sc.nextInt();
        Socket socket = new Socket(servidor, puerto);
        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
        System.out.println("Conectando al servidor");
        System.out.println("Servidor: " + in.readLine()); // Mensaje inicial del servidor
        String mensaje;
        System.out.println("Escribe un mensaje para enviar (Si quieres 'salir' para desconectar)");
        while (!(mensaje = sc.nextLine()).equalsIgnoreCase("salir")){
            out.println(mensaje);
            System.out.println("Servidor: " + in.readLine()); // leer respuesta del servidor
        }
    }
}
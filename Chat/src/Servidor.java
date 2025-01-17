import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Servidor {

    static Map<String, PrintWriter> clientes = new HashMap<>();

    public static void main(String[] args) {

        int puertos = 6666; // puerto de conexión

        try (ServerSocket serverSocket = new ServerSocket(puertos)) {
            System.out.println("Servidor iniciado " + puertos);

            while (true) {
                System.out.println("Esperando conexión");
                Socket socket = serverSocket.accept();
                System.out.println("Cliente iniciando" + socket.getInetAddress().getHostAddress());
                new Thread(() -> {
                    try {
                        manejarCliente(socket);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }).start();
            }
        } catch (IOException e) {
            System.out.println("Servidor errado");
        }
    }
    private static void manejarCliente(Socket socket) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
        out.println("Introduzca su nombre: ");
        String nombre = in.readLine();
        if (clientes.containsKey(nombre)) {
            out.println("Nombre no disponible, introduzca otro ");
            manejarCliente(socket);
        }
        synchronized (clientes) {
            clientes.put(nombre, out);
        }
        out.println("Usuario numero: " + (clientes.size()));
        String mensaje;
        synchronized (clientes) {
            for (PrintWriter cliente : clientes.values()) {
                cliente.println(nombre + " se ha unido al servidor.");
            }
        }

        while ((mensaje = in.readLine()) != null) {
            out.println("Para mandar el mensaje a todos escriba 'todos', para mandarlo a alguien en concreto escriba 'solo': ");
            String respuesta = in.readLine();
            if (respuesta.equals("solo")) {
                enviarMensajeSolo(nombre + " " + LocalDateTime.now() + " te susurra -----> " + mensaje, out, in);
            } else if (respuesta.equals("todos")){
                enviarMensaje(nombre + " " + LocalDateTime.now() + " -----> " + mensaje, out);
            }
        }
    }

    public static void enviarMensajeSolo(String mensaje, PrintWriter user, BufferedReader in) throws IOException {
        user.println("Usurarios conectados: ");
        for (String nombre : clientes.keySet()) {
            user.println(nombre);
        }
        user.println("Escriba el nombre del destinatario: ");
        String destinatario = in.readLine();
        for (Map.Entry<String, PrintWriter> cliente : clientes.entrySet()) {
            if (cliente.getKey().equals(destinatario)) {
                cliente.getValue().println(mensaje);
            }
        }
    }

    public static void enviarMensaje(String mensaje, PrintWriter user) {
        synchronized (clientes) {
            for (PrintWriter cliente : clientes.values()) {
                if (cliente != user) {
                    cliente.println(mensaje);
                }
            }
        }
    }
}

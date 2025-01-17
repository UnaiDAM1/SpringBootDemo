import java.util.List;
import java.util.Scanner;

public class Main {
    static Scanner sc = new Scanner(System.in);
    static Mascota[] mascotas = new Mascota[4];
    static Thread[] threads = new Thread[4];
    static String mascotaGanadora = null;
    public final static Object locked =  new Object();
    public static void main(String[] args) {

        for (int i = 0; i < mascotas.length; i++){
            System.out.println("Nombre de la mascota: ");
            String nombre = sc.nextLine();
            mascotas[i] = new Mascota(nombre);
        }
        try {
            System.out.println("Empieza la carrera.");
            Thread.sleep(1000);
            System.out.println("Preparados...");
            Thread.sleep(1000);
            System.out.println("Listos...");
            Thread.sleep(1000);
            System.out.println("!YA¡");

        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }


        for (int i = 0; i < mascotas.length; i++) {
            threads[i] = new Thread(mascotas[i]);
            threads[i].start();
        }

        for (Thread thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        if (mascotaGanadora != null){
            System.out.println(mascotaGanadora + " ha ganado.");
        }
    }
    public static void establecerGanador(String mascotaGanadora){
        synchronized (locked){
            if (Main.mascotaGanadora == null){
                Main.mascotaGanadora = mascotaGanadora;
            }
        }

    }

}
import java.util.Random;

public class Mascota implements Runnable{
    String nombre;
    Random rand = new Random();

    public Mascota(String nombre) {
        this.nombre = nombre;
    }


    @Override
    public void run() {
        try {
            for (int i = 0; i < 10; i++) {
                int tiempo = rand.nextInt(2000, 5000);
                Thread.sleep(tiempo);
                System.out.println(nombre + " ha avanzado " + (i + 1) + "m");
            }
            System.out.println(nombre + " ha terminado");
            Main.establecerGanador(nombre);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

    }
}

import java.util.Scanner;
import java.util.concurrent.Semaphore;

public class Main {

    private static final Semaphore semaphore = new Semaphore(2);

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("¿Cuántos coches desea construir?");
        int numCoches = sc.nextInt();

        for (int i = 0; i < numCoches; i++) {
            Thread coche = new Thread(new Coche(i + 1, semaphore));
            coche.start();
        }
    }
}
import java.util.Random;
import java.util.concurrent.Semaphore;

public class Coche implements Runnable {
    int numSerie;
    boolean chasis;
    boolean motor;
    boolean pintura;
    Random rand = new Random();

    private final Semaphore semaphore;

    public Coche(int numSerie, Semaphore semaphore) {
        this.numSerie = numSerie;
        chasis = false;
        motor = false;
        pintura = false;
        this.semaphore = semaphore;
    }

    public void run(){
        try{
            semaphore.acquire();
            Thread.sleep(rand.nextInt(1000));
            System.out.println("Empieza el ensamblaje del coche " + numSerie);

            System.out.println("Coche " + numSerie + ": Comienza la fase de chasis\n");
            int tiempoChasis = rand.nextInt(3000,5000);
            Thread.sleep(tiempoChasis);
            System.out.println("Coche " + numSerie + ": Fase de chasis terminada");
            chasis = true;

            System.out.println("Coche " + numSerie + ": Comienza la fase del motor\n");
            int tiempoMotor = rand.nextInt(3000,5000);
            Thread.sleep(tiempoMotor);
            System.out.println("Coche " + numSerie + ": Fase del motor terminada");
            motor = true;

            System.out.println("Coche " + numSerie + ": Comienza la fase de la pintura\n");
            int tiempoPintura = rand.nextInt(3000,5000);
            Thread.sleep(tiempoPintura);
            System.out.println("Coche " + numSerie + ": Fase de la pintura terminada");
            pintura = true;

            System.out.println("Comienza la inspección final\n");
            int tiempoInspeccion = rand.nextInt(3000,5000);
            Thread.sleep(tiempoInspeccion);
            if (chasis && motor && pintura){
                int num = rand.nextInt(1, 10);
                if (num != 1){
                    System.out.println("Coche " + numSerie + ": Ha pasado la inspección final.");
                    System.out.println("Coche " + numSerie + ": Ensamblaje hecho en " + (tiempoChasis + tiempoMotor + tiempoInspeccion + tiempoPintura) / 1000.00 + "s.\n");
                } else {
                    System.out.println("Coche " + numSerie + ": No ha pasado la inspección final.");
                }
            } else {
                System.out.println("Coche " + numSerie + ": No ha pasado la inspección final.");
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            semaphore.release();
        }
    }
}

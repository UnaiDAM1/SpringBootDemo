package Carrera100m;

import java.util.Random;
import java.util.concurrent.CountDownLatch;

public class Atleta extends Thread{
	private int dorsal; // Número de dorsal del atleta
    private CountDownLatch startSignal; // Señal de inicio para todos los atletas
    private long startTime; // Tiempo de inicio de la carrera
    private Random random; // Para simular el tiempo que tarda en correr
    
    // Constructor del Atleta
    public Atleta(int dorsal, CountDownLatch startSignal, long startTime) {
        this.dorsal = dorsal;
        this.startSignal = startSignal;
        this.startTime = startTime;
        this.random = new Random();
    }

    // Método que se ejecuta cuando se inicia el hilo (el atleta corre)
    @Override
    public void run() {
        try {
            // El atleta espera el pistoletazo de salida
            startSignal.await();
            // Simula el tiempo de la carrera (entre 9 y 11 segundos)
            int timeToRun = 9000 + random.nextInt(2000);
            Thread.sleep(timeToRun);
            // Notifica cuando el atleta llega a la meta
            long finishTime = System.currentTimeMillis();
            System.out.println("Atleta con dorsal " + dorsal + " tarda " + (finishTime - startTime) / 1000.00 + "s en llegar a la meta.");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

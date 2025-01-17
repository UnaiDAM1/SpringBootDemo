package Carrera100m;

import java.util.concurrent.CountDownLatch;

public class Carrera {
	public static void main(String[] args) throws InterruptedException {
        int numAtletas = 8; // Número de atletas
        CountDownLatch startSignal = new CountDownLatch(1);// Señal para el pistoletazo de salida
        Object inicio = new Object();
        long startTime = System.currentTimeMillis(); // Tiempo inicial 

        // Crear los 8 atletas
        Atleta[] atletas = new Atleta[numAtletas];
        for (int i = 0; i < numAtletas; i++) {
            atletas[i] = new Atleta(i + 1, startSignal, startTime);
            atletas[i].start(); // Inicia el hilo de cada atleta
        }

        // Simulación de la cuenta regresiva de la carrera
        System.out.println("Preparados...");
        Thread.sleep(1000); // Espera 1 segundo
        System.out.println("Listos...");
        Thread.sleep(1000); // Espera 1 segundo
        System.out.println("¡Ya!");
        startSignal.countDown(); // Pistoletazo de salida: todos los hilos comienzan

        // Esperar a que todos los atletas terminen
        for (Atleta atleta : atletas) {
            atleta.join();
        }

        System.out.println("¡Carrera terminada!");
    }
}

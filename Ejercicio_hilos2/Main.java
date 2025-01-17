package Ejercicio_hilos2;

public class Main extends Thread {
	public static void main(String[] args) throws InterruptedException {
		Contador contador = new Contador();
		Thread hilo1 = new Thread(new TareaContador(contador), "Hilo 1");
		Thread hilo2 = new Thread(new TareaContador(contador), "Hilo 2");
		hilo1.start();
		hilo2.start();
		hilo1.join();
		hilo2.join();
	}
}

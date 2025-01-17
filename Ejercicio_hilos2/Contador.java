package Ejercicio_hilos2;

public class Contador {
	private int valor = 0;
	
	public synchronized void increment() {
		valor++;
		System.out.println("El contador incrementa a " + valor + " por " + Thread.currentThread().getName());
	}

}

class TareaContador implements Runnable{
	private Contador contador;
	public TareaContador (Contador contador) {
		this.contador = contador;
	}

	@Override
	public void run() {
		for (int i = 0; i < 5; i++) {
			contador.increment();
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
	}
	
}

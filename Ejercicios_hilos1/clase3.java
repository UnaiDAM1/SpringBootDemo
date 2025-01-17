package Ejercicios_hilos1;

public class clase3 implements Runnable{

	@Override
	public void run() {
		for (int i = 0; i < 10; i++) {
			System.out.println(Thread.currentThread().getName() + " " + (i+1));
		}
		
	}

}

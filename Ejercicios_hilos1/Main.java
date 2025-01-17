package Ejercicios_hilos1;

public class Main extends Thread{
	public static void main(String[] args) {
		Thread clase1 = new Thread(new clase1());
		Thread clase2 = new Thread(new clase2());
		Thread clase3 = new Thread(new clase3());
		
		clase1.start();
		clase2.start();
		clase3.start();
	}
}

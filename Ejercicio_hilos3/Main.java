package Ejercicio_hilos3;

public class Main {
	public static void main(String[] args) {
		Thread descarga1 = new Thread(new DescargaDeArchivos("archivo1.txt"));
		Thread descarga2 = new Thread(new DescargaDeArchivos("archivo2.zip"));
		Thread descarga3 = new Thread(new DescargaDeArchivos("archivo3.pdf"));
		descarga1.start();
		descarga2.start();
		descarga3.start();
	}
	
	
}

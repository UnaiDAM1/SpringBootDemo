package Ejercicio_hilos3;

public class DescargaDeArchivos implements Runnable {
	String nombre;
	
	public DescargaDeArchivos(String nombre) {
		this.nombre = nombre;
	}

	@Override
	public void run() {
		System.out.println("Iniciando descarga del archivo: " + nombre);
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("\nDescarga completada: " + nombre);
	}

}

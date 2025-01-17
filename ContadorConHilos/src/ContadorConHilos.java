
public class ContadorConHilos {
    public static void main(String[] args) {
        // Instancia de la clase que contiene el contador compartido
        ContadorCompartido contadorCompartido = new ContadorCompartido();

        // Crear tres hilos y asignarles la tarea
        Thread hilo1 = new Thread(new HiloSumador(contadorCompartido), "Hilo-1");
        Thread hilo2 = new Thread(new HiloSumador(contadorCompartido), "Hilo-2");
        Thread hilo3 = new Thread(new HiloSumador(contadorCompartido), "Hilo-3");

        // Iniciar los hilos
        hilo1.start();
        hilo2.start();
        hilo3.start();
    }
}
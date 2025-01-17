public class Carrera {

    public static final int NUM_EQUIPOS = 4;          // Número de equipos
    public static final int ATLETAS_POR_EQUIPO = 4;   // Número de atletas por equipo

    public static volatile int equipoGanador = -1;    // Almacena el equipo ganador
    public static final Object lockGanador = new Object(); // Bloqueo para declarar ganador

    public static void main(String[] args) {
        System.out.println("Inicio de la carrera de relevos por equipos...\n");

        // Crear los equipos
        Equipo[] equipos = new Equipo[NUM_EQUIPOS];
        Thread[] hilosEquipos = new Thread[NUM_EQUIPOS];

        for (int i = 0; i < NUM_EQUIPOS; i++) {
            equipos[i] = new Equipo(i + 1, ATLETAS_POR_EQUIPO);
            hilosEquipos[i] = new Thread(equipos[i]);
            hilosEquipos[i].start(); // Iniciar cada equipo
        }

        // Esperar a que todos los equipos terminen
        for (Thread hiloEquipo : hilosEquipos) {
            try {
                hiloEquipo.join();
            } catch (InterruptedException e) {
                System.out.println("La carrera fue interrumpida.");
            }
        }

        // Mostrar el equipo ganador
        if (equipoGanador != -1) {
            System.out.println("\n¡El equipo " + equipoGanador + " ha ganado la carrera!");
        } else {
            System.out.println("\nNingún equipo ha terminado la carrera.");
        }
    }

    // Metodo que establece el ganador cuando la carrera termina
    public static void establecerGanador(int equipo) {
        synchronized (lockGanador) {
            if (equipoGanador == -1) {
                equipoGanador = equipo;
                System.out.println("¡El equipo " + equipo + " ha ganado la carrera!\n");
            }
        }
    }
}
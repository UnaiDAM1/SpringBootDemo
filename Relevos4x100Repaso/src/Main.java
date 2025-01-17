
public class Main {
    public static final int EQUIPOS = 4;
    public static final int ATLETAS_POR_EQUIPO = 4;

    public static int equipoGanador = -1;
    public static final Object lockGanador = new Object();

    public static void main(String[] args) {
        System.out.println("Inicio de la carrera");

        Equipo[] equipos = new Equipo[EQUIPOS];
        Thread[] equiposThread = new Thread[EQUIPOS];

        for (int i = 0; i < EQUIPOS; i++) {
            equipos[i] = new Equipo(i + 1, ATLETAS_POR_EQUIPO);
            equiposThread[i] = new Thread(equipos[i]);
            equiposThread[i].start();
        }

        for (Thread thread : equiposThread) {
            try {
                thread.join();
            } catch (InterruptedException e){
                System.out.println("Carrera interrumpida.");
            }
        }

        if (equipoGanador != -1){
            System.out.println("El equipo " + equipoGanador + " ha ganado.");
        } else {
            System.out.println("Ningun equipo ha ganado la carrera.");
        }
    }

    public static void declararGanador(int equipo) {
        synchronized (lockGanador) {
            if (equipoGanador == -1){
                equipoGanador = equipo;
                System.out.println("El equipo " + equipoGanador + " ha ganado.");
            }
        }
    }
}
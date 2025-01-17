import java.util.Random;

public class Atleta {

    private final int numeroEquipo;  // Número del equipo
    private final int numeroAtleta;  // Número del atleta dentro del equipo
    private final Equipo equipo;     // Referencia al equipo
    private final Random random = new Random();

    public Atleta(int numeroEquipo, int numeroAtleta, Equipo equipo) {
        this.numeroEquipo = numeroEquipo;
        this.numeroAtleta = numeroAtleta;
        this.equipo = equipo;
    }

    public void correr(Object testigo) {
        try {
            System.out.println("Equipo " + numeroEquipo + " - Atleta " + numeroAtleta + " empieza a correr con el testigo.\n");

            // Simular el tiempo de carrera (entre 9 y 11 segundos)
            int tiempoCarrera = 9000 + random.nextInt(2000); // 9000 ms a 11000 ms
            Thread.sleep(tiempoCarrera);

            System.out.printf("Equipo %d - Atleta %d terminó de correr en %.1f segundos.\n",
                    numeroEquipo, numeroAtleta, tiempoCarrera / 1000.00);

            // Si es el último atleta del equipo, declara ganador
            if (numeroAtleta == Carrera.ATLETAS_POR_EQUIPO) {
                equipo.declararGanador();
            }

        } catch (InterruptedException e) {
            System.out.println("Atleta " + numeroAtleta + " del equipo " + numeroEquipo + " fue interrumpido.");
        }
    }
}

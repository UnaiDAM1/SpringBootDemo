public class Equipo implements Runnable {

    private final int numeroEquipo;   // Número del equipo
    private final int numAtletas;     // Número de atletas en el equipo
    private final Atleta[] atletas;   // Array de atletas
    private final Object testigo;     // Objeto testigo que se iran pasando los atletas para saber cuando empezar el hilo

    public Equipo(int numeroEquipo, int numAtletas) {
        this.numeroEquipo = numeroEquipo;
        this.numAtletas = numAtletas;
        this.atletas = new Atleta[numAtletas];
        this.testigo = new Object();

        // Inicializar los atletas
        for (int i = 0; i < numAtletas; i++) {
            atletas[i] = new Atleta(numeroEquipo, i + 1, this);
        }
    }

    @Override
    public void run() {
        for (Atleta atleta : atletas) {
            synchronized (testigo) {
                // Cuando a un atleta le llega el testigo empieza a correr
                atleta.correr(testigo);
            }
        }
    }

    // Metodo que manda a la clase carrera cuál de los equipos ha ganado
    public void declararGanador() {
        Carrera.establecerGanador(numeroEquipo);
    }
}
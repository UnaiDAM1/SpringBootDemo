public class Equipo implements Runnable{
    private final int numEquipo;
    private final int numAtletas;

    private final Atleta[] atletas;
    private final Object testigo;

    public Equipo(int numEquipo, int numAtletas){
        this.numEquipo = numEquipo;
        this.numAtletas = numAtletas;
        this.atletas = new Atleta[numAtletas];
        this.testigo = new Object();

        for (int i = 0; i < numAtletas; i++) {
            atletas[i] = new Atleta(numEquipo, i + 1, this);
        }
    }

    @Override
    public void run() {
        for (Atleta atleta : atletas) {
            synchronized (testigo) {
                atleta.correr(testigo);
            }
        }
    }

    public void declararGanador(){
        Main.declararGanador(numEquipo);
    }
}

import java.util.Random;

public class Atleta {
    private final int numEquipo;
    private final int numAtleta;
    private final Equipo equipo;
    private final Random random = new Random();

    public Atleta(int numEquipo, int numAtleta, Equipo equipo) {
        this.numEquipo = numEquipo;
        this.numAtleta = numAtleta;
        this.equipo = equipo;
    }

    public void correr(Object testigo){
        try{
            System.out.println("Equipo " + numEquipo + ": el corredor " + numAtleta + " empieza a correr.");
            int tiempo = 9000 + random.nextInt(2000);
            Thread.sleep(tiempo);

            System.out.println("Equipo " + numEquipo + ": el atleta " + numAtleta + " termino de correr en " + tiempo / 1000.00);

            if (numAtleta == Main.ATLETAS_POR_EQUIPO){
                equipo.declararGanador();
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}

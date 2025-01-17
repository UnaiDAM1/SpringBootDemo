class HiloSumador implements Runnable {
    private final ContadorCompartido contadorCompartido;

    public HiloSumador(ContadorCompartido contadorCompartido) {
        this.contadorCompartido = contadorCompartido;
    }

    @Override
    public void run() {
        while (true) {
            if (!contadorCompartido.incrementar()) {
                break; // Salir del bucle si se alcanzó el límite
            }
            try {
                Thread.sleep(1000); // Pausa para simular trabajo y dar oportunidad a otros hilos
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                System.out.println(Thread.currentThread().getName() + " fue interrumpido.");
            }
        }
    }
}
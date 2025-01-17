class ContadorCompartido {
    private int contador = 0; // Contador inicial
    private final int LIMITE = 20; // Límite del contador

    // Método sincronizado para incrementar el contador
    public synchronized boolean incrementar() {
        if (contador < LIMITE) {
            contador++;
            System.out.println(Thread.currentThread().getName() + " incrementó el contador a: " + contador);
            return true; // Indica que el incremento se realizó
        } else {
            return false; // Indica que se alcanzó el límite
        }
    }
}

public class GanaleJuego {

    private static final int MAX_FICHAS = 6;
    private static final int MAX_TIRADAS = 10;

    private DadoLCR dado1;
    private DadoLCR dado2;
    private int fichas;
    private int tiradas;
    private boolean juegoTerminado;

    // Constructor
    public GanaleJuego() {
        dado1 = new DadoLCR();
        dado2 = new DadoLCR();
        fichas = 0;
        tiradas = 0;
        juegoTerminado = false;
    }

    /**
     * Lanza ambos dados y actualiza fichas según reglas del juego.
     * Si el juego ya terminó, no hace nada.
     */
    public void lanzarTirada() {
        if (juegoTerminado) return;

        tiradas++;
        char[] valores = {dado1.lanzar(), dado2.lanzar()};
        boolean yaDuplico = false;

        for (char valor : valores) {
            switch (valor) {
                case '*': fichas++; break;
                case 'L': fichas--; break;
                case 'R': fichas -= 2; break;
                case 'C': 
                    if (!yaDuplico) {
                        fichas *= 2;
                        yaDuplico = true;
                    }
                    break;
            }
        }

        // Evitar fichas negativas
        if (fichas < 0) fichas = 0;

        // Verificar fin del juego
        if (fichas >= MAX_FICHAS || tiradas >= MAX_TIRADAS) {
            juegoTerminado = true;
        }
    }

    // Getters de la API
    public int getFichas() { return fichas; }
    public int getTiradas() { return tiradas; }
    public boolean isJuegoTerminado() { return juegoTerminado; }
} 

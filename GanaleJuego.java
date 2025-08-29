
/**
 * Write a description of class GanaleJuego here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

public class GanaleJuego {
    private DadoLCR dado1;
    private DadoLCR dado2;
    private int fichas;
    private int tiradas;
    private boolean juegoTerminado;

    public GanaleJuego() {
        dado1 = new DadoLCR();
        dado2 = new DadoLCR();
        fichas = 0;
        tiradas = 0;
        juegoTerminado = false;
    }

    // Método para lanzar una tirada de los dos dados
    public void lanzarTirada() {
        if (juegoTerminado) {
            return; // no hace nada si el juego terminó
        }

        tiradas++;

        // Lanzar los dos dados y obtener sus valores
        char valor1 = dado1.lanzar();
        char valor2 = dado2.lanzar();

        boolean yaDuplico = false;

        // Primer dado
        if (valor1 == '*') fichas++;
        else if (valor1 == 'L') fichas--;
        else if (valor1 == 'R') fichas -= 2;
        else if (valor1 == 'C' && !yaDuplico) {
            fichas *= 2;
            yaDuplico = true;
        }

        // Segundo dado
        if (valor2 == '*') fichas++;
        else if (valor2 == 'L') fichas--;
        else if (valor2 == 'R') fichas -= 2;
        else if (valor2 == 'C' && !yaDuplico) {
            fichas *= 2;
            yaDuplico = true;
        }

        // Evitar fichas negativas
        if (fichas < 0) fichas = 0;

        // Verificar fin del juego
        if (fichas >= 6 || tiradas >= 10) {
            juegoTerminado = true;
        }
    }

    // Métodos públicos de la API
    public int getFichas() { return fichas; }
    public int getTiradas() { return tiradas; }
    public boolean isJuegoTerminado() { return juegoTerminado; }
}


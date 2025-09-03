
/**
 * Dado para el juego Left-Center-Right.
 *
 * @author (Cecilia Curlango Rosas)
 * @version (1.0 agosto 2025)
 */
public class DadoLCR {
    private Dado dado;

    public DadoLCR() {
        dado = new Dado();
    }

    // Devuelve el valor actual del dado sin lanzarlo
    public char getValor() {
        int elValorQueTieneElDado = dado.getValor();
        char valor;

        switch (elValorQueTieneElDado) {
            case 1:
            case 2:
            case 3:
                valor = '*';  // Punto
                break;
            case 4:
                valor = 'L';
                break;
            case 5:
                valor = 'C';
                break;
            case 6:
                valor = 'R';
                break;
            default:
                valor = 'C';  // Por seguridad
        }
        return valor;
    }

    // Lanza el dado y devuelve inmediatamente el valor resultante
    public char lanzar() {
        dado.lanzar();        // Lanza el dado
        return getValor();    // Devuelve el valor actualizado
    }
}

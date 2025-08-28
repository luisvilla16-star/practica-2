
/**
 * Write a description of class LCRDadoView here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class LCRDadoView{

    private Square fondo;
    private Circle punto;
    private int x;
    private int y;

    public LCRDadoView () {
        fondo = new Square();
        punto = new Circle();
    }

    private void posicionarFigura(Square s, int x, int y) {
        s.moveHorizontal(x);
        s.moveVertical(y);
    }

    private void posicionarFigura(Circle c, int x, int y) {
        c.moveHorizontal(x);
        c.moveVertical(y);
    }

    public void posicionar(int x, int y) {
        this.x = x;
        this.y = y;
        posicionarFigura(fondo, x, y);
        posicionarFigura(punto, x , y );
    }

    public void mostrar() {
        punto.makeInvisible();
    }

    public void mostrarPunto() {
        posicionarFigura(fondo, 0, 0);
        fondo.makeVisible();

        punto.moveHorizontal(20);
        punto.moveVertical(10);
        punto.makeVisible();
    }
}

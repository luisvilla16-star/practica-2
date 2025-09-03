import java.util.ArrayList;

public class LCRDadoView {
    private Square fondo;
    private ArrayList<Object> figuras;
    private int xFondo = -30, yFondo = -30;

    public LCRDadoView() {
        fondo = new Square();
        fondo.changeSize(100);
        fondo.makeVisible();
        figuras = new ArrayList<>();
    }

    private void ocultarFiguras() {
        for (Object o : figuras) {
            if (o instanceof Circle) ((Circle)o).makeInvisible();
            if (o instanceof Square) ((Square)o).makeInvisible();
        }
        figuras.clear();
    }

    private void crearCirculo(int dx, int dy) {
        Circle c = new Circle();
        c.changeSize(10);
        c.moveHorizontal(xFondo + dx);
        c.moveVertical(yFondo + dy);
        c.makeVisible();
        figuras.add(c);
    }

    private void crearCuadrado(int dx, int dy, String color) {
        Square s = new Square();
        s.changeSize(20);
        s.moveHorizontal(xFondo + dx);
        s.moveVertical(yFondo + dy);
        s.changeColor(color);
        s.makeVisible();
        figuras.add(s);
    }

    public void mostrarC() {
        ocultarFiguras();
        fondo.changeSize(100);
        fondo.makeVisible();

        int[][] pos = { {-160,-20},{-140,-20},{-120,-20},{-160,0}, {-160,20},{-160,40},{-140,40},{-120,40} };
        for (int[] p : pos) crearCirculo(p[0], p[1]);
    }

    public void mostrarL() {
        ocultarFiguras();
        fondo.changeSize(100);
        fondo.makeVisible();

        int[][] pos = { {-150,-10},{-150,10},{-150,30},{-130,30},{-110,30} };
        for (int[] p : pos) crearCirculo(p[0], p[1]);
    }

    public void mostrarR() {
        ocultarFiguras();
        fondo.changeSize(100);
        fondo.makeVisible();

        int[][] posC = {
            {-150,-10},{-150,10},{-150,30},{-150,50},
            {-130,-10},{-110,-10},{-90,-10},{-90,20},{-90,30},
            {-110,30},{-130,30},{-150,70},{-150,60}
        };
        for (int[] p : posC) crearCirculo(p[0], p[1]);

        int[][] posS = { {70,90},{90,110} };
        for (int[] p : posS) crearCuadrado(p[0], p[1], "BLUE");
    }

    public void mostrarPunto() {
        ocultarFiguras();
        fondo.changeSize(100);
        fondo.makeVisible();

        crearCirculo(-120,20);
    }

    public void mover(int dx, int dy) {
        xFondo += dx;
        yFondo += dy;
        fondo.moveHorizontal(dx);
        fondo.moveVertical(dy);

        for (Object o : figuras) {
            if (o instanceof Circle) {
                ((Circle)o).moveHorizontal(dx);
                ((Circle)o).moveVertical(dy);
            }
            if (o instanceof Square) {
                ((Square)o).moveHorizontal(dx);
                ((Square)o).moveVertical(dy);
            }
        }
    }
}

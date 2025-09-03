import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.*;

/**
 * Canvas is a class to allow for simple graphical drawing on a canvas.
 * This is a modification of the general purpose Canvas, specially made for
 * the BlueJ "shapes" example. 
 *
 * @author: Bruce Quig
 * @author: Michael Kölling
 *
 * @version 7.0 (modificado con drawString)
 */
public class Canvas
{
    private static Canvas canvasSingleton;

    public static Canvas getCanvas()
    {
        if(canvasSingleton == null) {
            canvasSingleton = new Canvas("BlueJ Picture Demo", 500, 300, Color.white);
        }
        canvasSingleton.setVisible(true);
        return canvasSingleton;
    }

    private JFrame frame;
    private CanvasPane canvas;
    private Graphics2D graphic;
    private Color backgroundColor;
    private Image canvasImage;
    private List<Object> objects;
    private HashMap<Object, Object> shapes; 

    private Canvas(String title, int width, int height, Color bgColor)
    {
        frame = new JFrame();
        canvas = new CanvasPane();
        frame.setContentPane(canvas);
        frame.setTitle(title);
        frame.setLocation(30,30);
        canvas.setPreferredSize(new Dimension(width, height));
        backgroundColor = bgColor;
        frame.pack();
        objects = new ArrayList<Object>();
        shapes = new HashMap<Object, Object>();
    }

    public void setVisible(boolean visible)
    {
        if(graphic == null) {
            Dimension size = canvas.getSize();
            canvasImage = canvas.createImage(size.width, size.height);
            graphic = (Graphics2D)canvasImage.getGraphics();
            graphic.setColor(backgroundColor);
            graphic.fillRect(0, 0, size.width, size.height);
            graphic.setColor(Color.black);
        }
        frame.setVisible(visible);
    }

    public void draw(Object referenceObject, String color, Shape shape)
    {
        objects.remove(referenceObject);
        objects.add(referenceObject);
        shapes.put(referenceObject, new ShapeDescription(shape, color));
        redraw();
    }

    public void drawString(Object referenceObject, String color, String text, int x, int y)
    {
        objects.remove(referenceObject);
        objects.add(referenceObject);
        shapes.put(referenceObject, new ShapeDescriptionText(text, color, x, y, 14));
        redraw();
    }

    public void drawString(Object referenceObject, String color, String text, int x, int y, int fontSize)
    {
        objects.remove(referenceObject);
        objects.add(referenceObject);
        shapes.put(referenceObject, new ShapeDescriptionText(text, color, x, y, fontSize));
        redraw();
    }

    public void drawString(String text, int x, int y)
    {
        Object referenceObject = new Object();
        shapes.put(referenceObject, new ShapeDescriptionText(text, "black", x, y, 14));
        objects.add(referenceObject);
        redraw();
    }

    public void erase(Object referenceObject)
    {
        objects.remove(referenceObject);
        shapes.remove(referenceObject);
        redraw();
    }

    public void setForegroundColor(String colorString)
    {
        if(colorString.equals("red")) {
            graphic.setColor(new Color(235, 25, 25));
        }
        else if(colorString.equals("black")) {
            graphic.setColor(Color.black);
        }
        else if(colorString.equals("blue")) {
            graphic.setColor(new Color(30, 75, 220));
        }
        else if(colorString.equals("yellow")) {
            graphic.setColor(new Color(255, 230, 0));
        }
        else if(colorString.equals("green")) {
            graphic.setColor(new Color(80, 160, 60));
        }
        else if(colorString.equals("magenta")) {
            graphic.setColor(Color.magenta);
        }
        else if(colorString.equals("white")) {
            graphic.setColor(Color.white);
        }
        else {
            graphic.setColor(Color.black);
        }
    }

    public void wait(int milliseconds)
    {
        try { Thread.sleep(milliseconds); } 
        catch (Exception e) { }
    }

    private void redraw()
    {
        erase();
        for(Object obj : objects) {
            Object desc = shapes.get(obj);
            if (desc instanceof ShapeDescription) {
                ((ShapeDescription)desc).draw(graphic);
            } else if (desc instanceof ShapeDescriptionText) {
                ((ShapeDescriptionText)desc).draw(graphic);
            }
        }
        canvas.repaint();
    }

    private void erase()
    {
        Color original = graphic.getColor();
        graphic.setColor(backgroundColor);
        Dimension size = canvas.getSize();
        graphic.fill(new Rectangle(0, 0, size.width, size.height));
        graphic.setColor(original);
    }

    private class CanvasPane extends JPanel
    {
        public void paint(Graphics g)
        {
            g.drawImage(canvasImage, 0, 0, null);
        }
    }

    private class ShapeDescription
    {
        private Shape shape;
        private String colorString;

        public ShapeDescription(Shape shape, String color)
        {
            this.shape = shape;
            colorString = color;
        }

        public void draw(Graphics2D graphic)
        {
            setForegroundColor(colorString);
            graphic.fill(shape);
        }
    }

    private class ShapeDescriptionText
    {
        private String text;
        private String colorString;
        private int x, y, fontSize;

        public ShapeDescriptionText(String text, String color, int x, int y, int fontSize)
        {
            this.text = text;
            this.colorString = color;
            this.x = x;
            this.y = y;
            this.fontSize = fontSize;
        }

        public void draw(Graphics2D graphic)
        {
            setForegroundColor(colorString);
            graphic.setFont(new Font("Arial", Font.BOLD, fontSize));
            graphic.drawString(text, x, y);
        }
    }
}




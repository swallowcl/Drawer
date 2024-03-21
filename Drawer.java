import ecs100.*;
import java.awt.Color;
import javax.swing.JColorChooser;
/**
 * Let user draw lines on graphic pane with the mouse
 *
 * @author Claire
 * @version 14.3
 */
public class Drawer
{
    // instance variables 
    private double startX, startY; // fields to remember 'pressed' position
    private Color currentColor = Color.black; // default colour is black
    private double currentSize = 50; // default element size
    private String currentElement = "line"; // default element is line

    /**
     * Constructor for objects of class LineDrawer
     */
    public Drawer()
    {
        // initialise instance variables
        UI.setLineWidth(10);
        UI.addButton("Random Colour",this::randomColor); // call back to change to random colours
        UI.addButton("Choose Colour", this::doChooseColor); // call back to chooose colour
        UI.addButton("Rectangle",this::drawRectangle); // call back to drawing rectangles
        UI.addButton("Circle",this::drawCircle); // call back to drawing circles
        UI.addButton("Line",this::drawLine); // call back to drawing lines
        UI.addTextField("Enter text",this::drawText); // adds text to screen
        UI.addSlider("Change Size", 1, 100, 30, this::changeSize); // changes the size of pen
        UI.addButton("Clear", this::clearScreen); // clears everything drawn on screen
        UI.addButton("Quit",UI::quit); // quits
        UI.setMouseListener(this::doMouse);
    }
    
    /**
     * Callback random colour changer
     */
    public void randomColor() {
        Color col = new Color((float) Math.random(), (float)Math.random(), (float) Math.random());
        UI.setColor(col); // new colour set to random colour
    }
    
    /**
     * Callback to colour picker
     */
    public void doChooseColor() {
        this.currentColor = JColorChooser.showDialog(null, "Choose Color",this.currentColor);
        UI.setColor(this.currentColor); // changes element colour to what user has chosen
        
    }
    
    /**
     * Call back for clearing graphics pane
     */
    public void clearScreen() {
        UI.clearGraphics(); // clears screen completely
        
    }
    
    
    /**
     * Call back for changing line size
     */
    public void changeSize(double newSize){
       UI.setLineWidth(newSize); 
       currentSize = (newSize); // changes size to new size on slider
    }
    
     /**
     * Call back method for rectangle
     */
    public void drawRectangle() {
        currentElement = "rectangle";
    }
    
     /**
     * Call back method for rectangle
     */
    public void drawLine() {
        currentElement = "line";
    }
    
    /**
     * Call back method for circle
     */
    public void drawCircle() {
        currentElement = "circle";
    }
    
     /**
     * Call back method for circle
     */
    public void drawText(String s) {
        currentElement = "text";
        UI.setFontSize(currentSize); // font size determind by slider
        UI.drawString(s, startX, startY); // draws where user last clicked
        

    }

    
      /**
     * Call back method for mouse
     * Draws a line/rectangle/circle
     * 
     */
    public void doMouse(String action, double x, double y) {
        if (action.equals("pressed")) {
            //store the pressed position
            this.startX = x;
            this.startY = y;
        // draw circle, line, or rectangle when mouse is released
        }else if (action.equals("released") && currentElement != "text"){ // does not run if user is entering text
            // draws rectangle
            if (currentElement.equals("rectangle")){
                // rectangle can be drawn despite start/end x & y positions
                if (x > startX && y > startY) {
                UI.fillRect(this.startX, this.startY, x-startX, y-startY);
                
                }else if (x < startX && y < startY) {
                UI.fillRect( x, y,this.startX - x,this.startY - y);
                
                }else if (x > startX && y < startY) {
                UI.fillRect(this.startX,y,x,this.startY - y);
                }
                else if (x < startX && y > startY) {
                UI.fillRect( x, this.startY,this.startX-x,y-this.startY);
                }
            }
            
            // draws line
            else if (currentElement.equals("line")){
                UI.drawLine(this.startX, this.startY, x, y);
        } 
            // draws circle        
            else if(currentElement.equals("circle")){
                // oval can be drawn despite start/end x & y positions
                if (x > startX && y > startY) {
                UI.fillOval(this.startX, this.startY, x-this.startX, y-this.startY);
                
                }else if (x < startX && y < startY) {
                UI.fillOval( x, y,this.startX - x,this.startY - y);
                
                }else if (x > startX && y < startY) {
                UI.fillOval( this.startX,y,x,this.startY - y);
                }
                else if (x < startX && y > startY) {
                UI.fillOval( x, this.startY,this.startX-x,y-this.startY);
                }
            }        
    }
    }

}

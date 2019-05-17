import java.awt.*;
import objectdraw.*;
/**
 * The KnightCell class remembers information about an individual cell.
 *
 * @author Amelia Tran
 * @version 04/24/2018
 */
public class KnightCell {
    private FilledRect cell;
    private FramedRect framedCell;
    private FilledOval[][] bomb;
    private FilledOval circle;
    private static final int CELL_SIZE = 40;    
    private int left;
    private int top;
    private DrawingCanvas canvas;
    private boolean visited;

    // draw a small cell
    public KnightCell( int left, int top, DrawingCanvas canvas) {
        this.left = left; 
        this.top = top; 
        this.canvas = canvas; 
        cell = new FilledRect( left, top, CELL_SIZE, CELL_SIZE, canvas); 
        cell.setColor( Color.WHITE); 
        framedCell = new FramedRect( left, top, CELL_SIZE, CELL_SIZE, canvas); 
    }

    // boolean to check if the cell has been visited
    public boolean isVisited() {   
        return visited;
    }        

    // place the number 
    public void placeNum( int count) {
        visited = true; 
        cell.setColor( Color.YELLOW); 
        new Text( count, left+15, top+15, canvas); 
    }
}

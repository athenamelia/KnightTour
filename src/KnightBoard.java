import java.awt.*;
import objectdraw.*;
/**
 * The KnightBoard class encapsulates the two-dimensional array that represents 
 * the 2D space of the game.
 * 
 * @author Amelia Tran
 * @version 04/24/2018
 */
public class KnightBoard {
    private KnightCell[][] cell;
    private static final int MAX_ROW = 8;
    private static final int MAX_COL = 8;
    private static final int CELL_SIZE = 40;  
    private DrawingCanvas canvas;   
    private Location lastPoint;
    private static final int LEFT = 20;
    private static final int TOP = 20;   
    private int currentRow, currentCol, newRow, newCol;
    private boolean isFirst = true;
    public boolean winKnight, loseKnight;

    // draw the grid 
    public KnightBoard( DrawingCanvas aCanvas) {
        cell = new KnightCell[MAX_ROW][MAX_COL]; 
        this.canvas = aCanvas; 
        createBoard();
    }

    // create the board
    private void createBoard() {  
        int top = TOP; 
        for ( int row = 0; row < MAX_ROW; row++) {
            int left = LEFT; 
            for ( int col = 0; col < MAX_COL; col++) {
                cell[row][col] = new KnightCell( left, top, canvas); 
                left = left + CELL_SIZE; 
            }
            top = top + CELL_SIZE; 
        }
    }

    // Converting from x pixel coordinates to row coordinates
    private int convertX( Location point) { 
        if (point.getX() < LEFT || point.getX() > LEFT + CELL_SIZE*MAX_COL) {
            return -1; 
        }
        else {
            return (int)(( point.getX() - LEFT) / CELL_SIZE);
        }
    }

    // Converting from y pixel coordinates to col coordinates
    private int convertY( Location point) {
        if (point.getY() < TOP || point.getY() > TOP + CELL_SIZE*MAX_ROW) {
            return -1; 
        }
        else {
            return (int)((point.getY() - TOP) / CELL_SIZE);
        }
    }

    // get the number of cells being placed
    private int getNumPlaced() {
        int count = 1;
        for ( int row = 0; row < MAX_ROW; row++) {
            for ( int col = 0; col < MAX_COL; col++) {
                if ( cell[row][col].isVisited()) {                    
                    count++;                 
                }
            }
        }
        return count; 
    }

    // boolean to check if the cell is valid
    private boolean isValid( Location point) {   
        int[] horizontal = new int[8]; 
        int[] vertical = new int[8];  

        horizontal[0] = 2; vertical[0] = -1;
        horizontal[1] = 1; vertical[1] = -2;
        horizontal[2] = -1; vertical[2] = -2;
        horizontal[3] = -2; vertical[3] = -1;
        horizontal[4] = -2; vertical[4] = 1;
        horizontal[5] = -1; vertical[5] = 2;
        horizontal[6] = 1; vertical[6] = 2;
        horizontal[7] = 2; vertical[7] = 1;

        for (int moveNumber = 0; moveNumber < 8; moveNumber++) {  
            if (newRow == currentRow + vertical[moveNumber] && newCol == currentCol + horizontal[moveNumber]) {      
                if (newRow >= 0 && newCol >= 0) {
                    if (!cell[newRow][newCol].isVisited()) {
                        return true;
                    }                                           
                }
                else {
                    if (getNumPlaced() != 64 ) {
                        loseKnight = true;      
                    }
                }
            }                                
        }

        return false;
    }

    // place the number 
    public void placeNum( Location point) {
        newRow = convertY(point); 
        
        if (isFirst == true) {
            if (newRow >= 0 && newCol >= 0) {
                cell[newRow][newCol].placeNum(getNumPlaced()); 
                currentRow = newRow;
                currentCol = newCol;
                isFirst = false;
            }
        }

        else {
            if (isValid(point)) {
                cell[newRow][newCol].placeNum(getNumPlaced()); 
                currentRow = newRow;
                currentCol = newCol;
                if ( getNumPlaced() == 64 ) {
                    winKnight = true;
                }
            }           
        }
    }
}
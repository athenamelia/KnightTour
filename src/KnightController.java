import java.awt.*;
import objectdraw.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * The KnightController class handles setting up a new game of Knight Tour. 
 * It creates the GUI and and game board and handles mouse events.
 * 
 * @author Amelia Tran
 * @version 04/24/2018
 * 
 */
public class KnightController extends WindowController 
                              implements ActionListener {
    private KnightBoard gameBoard;
    private JButton newGameButton;
    private JLabel instruction;
    private JPanel controlPanel, labelPanel;

    /**
     * Method that sets up the inital display (and GUI objects) and starts the game
     */
    public void begin() {
        //setup a new game board on the canvas
        gameBoard = new KnightBoard( canvas);       

        //create a new JPanel for the game 
        JPanel controlPanel = new JPanel();
        JPanel labelPanel = new JPanel();

        //create a new JButton for starting a new game (and add to controlPanel)
        newGameButton = new JButton ("Restart");
        controlPanel.add (newGameButton);
        //add the controlPanel to the JFrame
        add(controlPanel, BorderLayout.SOUTH);
        
         //create a new JLabel for starting a new game (and add to labelPanel)
        instruction = new JLabel ( "Try to complete the Knight's tour. Click a cell to start.");
        labelPanel.add (instruction);
        add(labelPanel, BorderLayout.NORTH);
        
        //add an action listener to the new game button
        newGameButton.addActionListener (this);
        resize( 360, 480); // resize the canvas
    }

    // Mouse pressed event handler. 
    public void onMouseClick(Location point) {
        //grab the x/y locations from the event and put them into a Location variable
        new Location (point.getX(), point.getY());        
        gameBoard.placeNum(point); // place the number of the visited cell
                
        // check if the user wins the game
        if (gameBoard.winKnight) {
            instruction.setText( "Congratulations, you completed the tour!");    
        }
        else if (gameBoard.loseKnight) {
            instruction.setText( "Sorry, no more valid moves. Click restart to try again");
        }
        // check if the board is clicked 
        else if (gameBoard != null) {
            instruction.setText( "Choose next cell");
        }
    }
    
    // Clear the canvas   
    public void actionPerformed(ActionEvent event) {
        if (gameBoard != null) {
            canvas.clear();
        }

        gameBoard = new KnightBoard(canvas);
        instruction.setText( "Click a cell to start.");
    }
}

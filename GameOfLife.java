import info.gridworld.actor.Actor;
import info.gridworld.actor.ActorWorld;
import info.gridworld.actor.Rock;
import info.gridworld.grid.Grid;
import info.gridworld.grid.BoundedGrid;
import info.gridworld.grid.Location;
import info.gridworld.gui.GUIController;
import java.util.*;

/**
 * Game of Life starter code. Demonstrates how to create and populate the game using the GridWorld framework.
 * Also demonstrates how to provide accessor methods to make the class testable by unit tests.
 * 
 * @author @gcschmit
 * @version 18 July 2014
 */ 
public class GameOfLife
{
    // the world comprised of the grid that displays the graphics for the game
    private ActorWorld world;
    
    // the game board will have 5 rows and 5 columns
    private final int ROWS = 19;
    private final int COLS = 19;
    
    /**
     * Default constructor for objects of class GameOfLife
     * 
     * @post    the game will be initialized and populated with the initial state of cells
     * 
     */
    public GameOfLife()
    {
        // create the grid, of the specified size, that contains Actors
        BoundedGrid<Actor> grid = new BoundedGrid<Actor>(ROWS, COLS);
        
        // create a world based on the grid
        world = new ActorWorld(grid);
        
        // populate the game
        populateGame();
        
        // display the newly constructed and populated world
        world.show();
        
        createNextGeneration();
        
    }
    
    /**
     * Creates the actors and inserts them into their initial starting positions in the grid
     *
     * @pre     the grid has been created
     * @post    all actors that comprise the initial state of the game have been added to the grid
     * 
     */
    private void populateGame()
    {
        // constants for the location of the three cells initially alive
        final int X1 = 1, Y1 = 0;Location loc1 = new Location(Y1, X1);
        final int X2 = 2, Y2 = 1;Location loc2 = new Location(Y2, X2);
        final int X3 = 0, Y3 = 2;Location loc3 = new Location(Y3, X3);
        final int X4 = 1, Y4 = 2;Location loc4 = new Location(Y4, X4);
        final int X5 = 2, Y5 = 2;Location loc5 = new Location(Y5, X5);

        // the grid of Actors that maintains the state of the game
        //  (alive cells contains actors; dead cells do not)
        Grid<Actor> grid = world.getGrid();
        
        // create and add rocks (a type of Actor) to the three intial locations
        grid.put(loc1, new Rock());grid.put(loc2, new Rock());grid.put(loc3, new Rock());
        grid.put(loc4, new Rock());grid.put(loc5, new Rock());
    }

    /**
     * Generates the next generation based on the rules of the Game of Life and updates the grid
     * associated with the world
     *
     * @pre     the game has been initialized
     * @post    the world has been populated with a new grid containing the next generation
     * 
     */
    private void createNextGeneration()
    {
        /** You will need to read the documentation for the World, Grid, and Location classes
         *      in order to implement the Game of Life algorithm and leverage the GridWorld framework.
         */
        
        // create the grid, of the specified size, that contains Actors
        Grid<Actor> grid = world.getGrid();
        
        // insert magic here...
        
        //runs continuously while run button is clicked
        boolean active = true;
        while(active)
        {
            //cells to kill
            List<Location> hitlist = new ArrayList<Location>();
            //cells to keep alive or make alive
            List<Location> newborns = new ArrayList<Location>();
            
            //cannot read cells and bring them alive/kill them at once or errors
            for(int x =0; x<19;x++)
            {
                for(int y=0; y<19;y++)
                {
                    Location change = new Location(x,y);
                    ArrayList<Location> neighbours = grid.getOccupiedAdjacentLocations(change);
                    if(neighbours.size()>3 || neighbours.size()<2){hitlist.add(change);}
                    else if(neighbours.size() == 3){newborns.add(change);}
                }
            }
            
            //kill or bring to life all cells at once after reading
            for(int i=0; i<hitlist.size(); i++){grid.remove(hitlist.get(i));}
            for(int i=0; i<newborns.size(); i++){grid.put(newborns.get(i), new Rock());}
            
            //waits 1/2 a second before next generation
            try{Thread.sleep(500);}catch(InterruptedException ex){Thread.currentThread().interrupt();}
        }
    
    }
    /**
     * Returns the actor at the specified row and column. Intended to be used for unit testing.
     *
     * @param   row the row (zero-based index) of the actor to return
     * @param   col the column (zero-based index) of the actor to return
     * @pre     the grid has been created
     * @return  the actor at the specified row and column
     */
    public Actor getActor(int row, int col)
    {
        Location loc = new Location(row, col);
        Actor actor = world.getGrid().get(loc);
        return actor;
    }

    /**
     * Returns the number of rows in the game board
     *
     * @return    the number of rows in the game board
     */
    public int getNumRows()
    {
        return ROWS;
    }
    
    /**
     * Returns the number of columns in the game board
     *
     * @return    the number of columns in the game board
     */
    public int getNumCols()
    {
        return COLS;
    }
    
    /**
     * Creates an instance of this class. Provides convenient execution.
     *
     */
    public static void main(String[] args)
    {
        GameOfLife game = new GameOfLife();
    }

}

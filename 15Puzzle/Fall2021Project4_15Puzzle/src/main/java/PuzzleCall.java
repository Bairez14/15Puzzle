import java.util.concurrent.Callable;
import java.util.ArrayList;

public class PuzzleCall implements Callable<ArrayList<Node>> {

    public ArrayList<Integer> puzzleState = new ArrayList<Integer>();
    public String heuristic; 

    public PuzzleCall(ArrayList<Integer> puzzleState, String heuristic){
        this.puzzleState = puzzleState;
        this.heuristic = heuristic;
    }

    @Override
    public ArrayList<Node> call() throws Exception {
        ArrayList<Node> solution;

        int [] puzzle = puzzleState.stream().mapToInt(i -> i).toArray();
        // ^^ need to convert ArrayList to int [] becuase that is the parameter the Node constructor takes in
        //https://stackoverflow.com/questions/718554/how-to-convert-an-arraylist-containing-integers-to-primitive-int-array
        //probably can look up another way to do it tho 
        
        Node startState = new Node(puzzle); //A_Star takes in a node in constructor so need to make a node with puzzle which is an int[]
        A_IDS_A_15solver ids = new A_IDS_A_15solver(); // instantiates instance of IDS class
        solution = ids.A_Star(startState, heuristic); // call method to A_Star

        //following code below from code given 
        // UserInterface puzzle = new UserInterface();			//class for reading in puzzle from user
		// Node startState = new Node(puzzle.getPuzzle());		//node contains the start state of puzzle
		// startState.setDepth(0);
        // System.out.println("\nStarting A* Search with heuristic #1....This may take a while\n\n");
        // A_Star(startState, "heuristicOne");							//A* search with heuristic 1 (misplaced tiles)
		// System.out.println("\nStarting A* Search with heuristic #2....This may take a while\n\n");
		// A_Star(startState, "heuristicTwo");							//A* search with heuristic 2 (manhattan)
		// System.out.println("\nThanks for using me to solve your 15 puzzle......Goodbye");
		// System.exit(1);

        return solution;
    }

    
}

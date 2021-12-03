import java.util.*;
import java.util.Random;
import javafx.scene.control.Button;


public class PuzzleLogic{
    
    final static ArrayList<Integer> puzzle1 = new ArrayList<Integer>(Arrays.asList(2, 6, 10, 3, 1, 4, 7, 11, 8, 5, 9, 15, 12, 13, 14, 0 ));// good
    final static ArrayList<Integer> puzzle2 = new ArrayList<Integer>(Arrays.asList(15, 2, 1, 12, 8, 5, 6, 11, 4, 9, 10, 7, 3, 14, 13, 0));  //good
    final static ArrayList<Integer> puzzle3 = new ArrayList<Integer>(Arrays.asList(13, 2, 9, 12, 8, 5, 6, 11, 4, 1, 10, 7, 3, 14, 15, 0));//good
    final static ArrayList<Integer> puzzle4 = new ArrayList<Integer>(Arrays.asList(1, 4, 8, 10, 5, 11, 7, 14, 13, 12, 6, 0, 9, 3, 15, 2 ));// 
    final static ArrayList<Integer> puzzle5 = new ArrayList<Integer>(Arrays.asList(12, 6, 10, 3, 1, 4, 7, 15, 8, 2, 9, 11, 14, 13, 5, 0));// good
    final static ArrayList<Integer> puzzle6 = new ArrayList<Integer>(Arrays.asList(9, 10, 2, 12, 8, 5, 6, 1, 11, 4, 13, 3, 14, 15, 7, 0));// good
    final static ArrayList<Integer> puzzle7 = new ArrayList<Integer>(Arrays.asList(10, 9, 12, 2, 8, 5, 1, 6, 11, 4, 3, 13, 14, 15, 7, 0)); //good
    final static ArrayList<Integer> puzzle8 = new ArrayList<Integer>(Arrays.asList(10, 8, 12, 2, 9, 5, 1, 6, 4, 11, 3, 15, 7, 13, 14, 0));  // good
    final static ArrayList<Integer> puzzle9 = new ArrayList<Integer>(Arrays.asList(13, 12, 9, 2, 8, 5, 6, 1, 4, 11, 10, 7, 3, 14, 15, 0));  // good
    final static ArrayList<Integer> puzzle10 = new ArrayList<Integer>(Arrays.asList(14, 6, 10, 3, 1, 4, 7, 15, 8, 5, 9, 11, 12, 13, 2, 0));  //good
    public static ArrayList<ArrayList<Integer>> puzzleOptions = new ArrayList<ArrayList<Integer>>();
    public static ArrayList<Integer> game = new ArrayList<Integer>();
    public static Integer xCord ;
    public static Integer yCord ;

    public static ArrayList<Integer> generatePuzzle() {
        // adds puzzles to arraylist
        puzzleOptions.add(puzzle1);
        puzzleOptions.add(puzzle2);
        puzzleOptions.add(puzzle3);
        puzzleOptions.add(puzzle4);
        puzzleOptions.add(puzzle5);
        puzzleOptions.add(puzzle6);
        puzzleOptions.add(puzzle7);
        puzzleOptions.add(puzzle8);
        puzzleOptions.add(puzzle9);
        puzzleOptions.add(puzzle10);
        // generate a random num from 0-9
        // give us a random puzzle for the user
        Random r = new Random();
        int random = r.nextInt((9 - 0) + 1) + 0;
        // generate the game board and we will return this
        for (int col = 0; col < 15; col++) {
            game.add(puzzleOptions.get(random).get(col));
        }

        return puzzleOptions.get(random);
	}

    public static boolean validMove(Button b, Button[][] temp) {
        System.out.println("in validMove method");
        //Button[][] temp = JavaFXTemplate.gameBoard;
        ArrayList<String> pressable = new ArrayList<String>();
        for (int c = 0; c < 4; c++) {//row
            for (int r = 0; r < 4; r++) { //column
                if (temp[c][r].getText() == "0") {
                    xCord = r;
                    yCord = c;
                }
            }
        }
        System.out.println("zero @ (" + xCord + ", " + yCord + ")");

        if ((xCord != 0) && (xCord != 3) && (yCord != 0) && (yCord != 3)) { //can check in all directions
            pressable.add((temp[xCord + 1][yCord].getText())); // right
            pressable.add((temp[xCord][yCord + 1].getText())); // down
            pressable.add((temp[xCord - 1][yCord].getText())); // left
            pressable.add((temp[xCord][yCord - 1].getText())); // up
        } else if (xCord == 0 && yCord == 0) {
            pressable.add((temp[xCord + 1][yCord].getText())); // right
            pressable.add((temp[xCord][yCord + 1].getText())); // down
        } else if (xCord == 0 && yCord == 3) {
            pressable.add((temp[xCord][yCord + 1].getText())); // down
            pressable.add((temp[xCord - 1][yCord].getText())); // left
        } else if (xCord == 3 && yCord == 0) {
            pressable.add((temp[xCord][yCord - 1].getText())); // up
            pressable.add((temp[xCord + 1][yCord].getText())); // right

        } else if (xCord == 3 && yCord == 3) {
            pressable.add((temp[xCord][yCord - 1].getText())); // up
            pressable.add((temp[xCord - 1][yCord].getText())); // left
        } else if (xCord == 0) {
            pressable.add((temp[xCord + 1][yCord].getText())); // right
            pressable.add((temp[xCord][yCord + 1].getText())); // down
            pressable.add((temp[xCord - 1][yCord].getText())); // left
        } else if (xCord == 3) {
            pressable.add((temp[xCord][yCord - 1].getText())); // up
            pressable.add(temp[xCord + 1][yCord].getText()); // right
            pressable.add((temp[xCord][yCord + 1].getText())); // down
        } else if (yCord == 0) {
            pressable.add((temp[xCord][yCord - 1].getText())); // up
            pressable.add((temp[xCord + 1][yCord].getText())); // right
            pressable.add((temp[xCord - 1][yCord].getText())); // left
        } else if (yCord == 3) {
            pressable.add((temp[xCord][yCord - 1].getText())); // up
            pressable.add((temp[xCord - 1][yCord].getText())); // left
            pressable.add((temp[xCord][yCord + 1].getText())); // down
        }

        //iterate through list and compare to button
        for (int i = 0; i < pressable.size(); i++) {
            if (b.getText().equals(pressable.get(i))) {
                return true;
            }
        }
        return false;
    }
    
    public static void swap(Button b, Button[][] temp) {
        System.out.println("in swap method");

        int bx = 0;
        int by = 0;
        for (int c = 0; c < 4; c++) {
            for (int r = 0; r < 4; r++) {
                if (temp[c][r].getText() == "0") {
                    bx = r;
                    by = c;
                }
            }
        }
        System.out.println("0 @ (" + bx + ", " + by + ")");
        System.out.println("pressed @ (" + xCord + ", " + yCord + ")");

        Button previous = temp[bx][by];
        Button zeroPrev = temp[xCord][yCord];
        temp[xCord][yCord] = previous; //swaps pressed with 0
        temp[xCord][yCord].setText("0");
        temp[bx][by] = zeroPrev; //swaps 
        temp[bx][by].setText(b.getText());
    }
    
    public boolean winningMove(Button[][] temp) {
        int counter = 0;
        for (int c = 0; c < 4; c++) {
            for (int r = 0; r < 4; r++) {
                if (temp[c][r].getText() == Integer.toString(counter)) {
                    counter++;
                }
            }
            return true;
        }
        return false;
    }

    //NOTES FOR CALLABLE CLASS
    //need class to implement callable
    //contain call method uses solver and returns solution so in javafx submit that method to use future.get() to get answer from


}
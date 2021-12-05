import java.util.*;

import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;

public class PuzzleLogic {

    final static ArrayList<Integer> puzzle1 = new ArrayList<Integer>(
            Arrays.asList(2, 6, 10, 3, 1, 4, 7, 11, 8, 5, 9, 15, 12, 13, 14, 0));// good
    final static ArrayList<Integer> puzzle2 = new ArrayList<Integer>(
            Arrays.asList(15, 2, 1, 12, 8, 5, 6, 11, 4, 9, 10, 7, 3, 14, 13, 0)); //good
    final static ArrayList<Integer> puzzle3 = new ArrayList<Integer>(
            Arrays.asList(13, 2, 9, 12, 8, 5, 6, 11, 4, 1, 10, 7, 3, 14, 15, 0));//good
    final static ArrayList<Integer> puzzle4 = new ArrayList<Integer>(
            Arrays.asList(1, 4, 8, 10, 5, 11, 7, 14, 13, 12, 6, 0, 9, 3, 15, 2));// 
    final static ArrayList<Integer> puzzle5 = new ArrayList<Integer>(
            Arrays.asList(12, 6, 10, 3, 1, 4, 7, 15, 8, 2, 9, 11, 14, 13, 5, 0));// good
    final static ArrayList<Integer> puzzle6 = new ArrayList<Integer>(
            Arrays.asList(9, 10, 2, 12, 8, 5, 6, 1, 11, 4, 13, 3, 14, 15, 7, 0));// good
    final static ArrayList<Integer> puzzle7 = new ArrayList<Integer>(
            Arrays.asList(10, 9, 12, 2, 8, 5, 1, 6, 11, 4, 3, 13, 14, 15, 7, 0)); //good
    final static ArrayList<Integer> puzzle8 = new ArrayList<Integer>(
            Arrays.asList(10, 8, 12, 2, 9, 5, 1, 6, 4, 11, 3, 15, 7, 13, 14, 0)); // good
    final static ArrayList<Integer> puzzle9 = new ArrayList<Integer>(
            Arrays.asList(13, 12, 9, 2, 8, 5, 6, 1, 4, 11, 10, 7, 3, 14, 15, 0)); // good
    final static ArrayList<Integer> puzzle10 = new ArrayList<Integer>(
            Arrays.asList(14, 6, 10, 3, 1, 4, 7, 15, 8, 5, 9, 11, 12, 13, 2, 0)); //good
    public static ArrayList<ArrayList<Integer>> puzzleOptions = new ArrayList<ArrayList<Integer>>();
    public static ArrayList<Integer> game = new ArrayList<Integer>();
    public static Integer xCord; //x-coordinate of 0
    public static Integer yCord; // y-coordinate of 0

// MOST OF THE OLD CODE IS AT THE BOTTOM, I JUST STARTED CLEAN BECAUSE I CONCENTRATE BETTER LOLLLL

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

        return puzzleOptions.get(random); // returns an arraylist but in JavaFX it needs to return an array... confusion
    }

    public static boolean validMove(int xcord, int ycord, ArrayList<Integer> buttons) {
        
        // finding the position of the current button pressed in a 1D array
        int index = (4 * ycord) + xcord;
        
        if (xcord != 0 && buttons.get(index - 1) == 0) { //left
            System.out.println("valid move");
            return true;
        }
        if (xcord != 3 && buttons.get(index + 1) == 0) { //right
            System.out.println("valid move");
            return true;
        }
        if (ycord != 0 && buttons.get(index - 4) == 0) {//up
            System.out.println("valid move");
            return true;
        }
        if (ycord != 3 && buttons.get(index + 4) == 0) {//down
            System.out.println("valid move");
            return true;
        }
        System.out.println("invalid move");
        return false;
    }
    // use the swap method using a button and zero
    public static void swap(Button b, ArrayList<Integer> buttons) {
        int i = Integer.parseInt(b.getText());
        Collections.swap(buttons, buttons.indexOf(0), buttons.indexOf(i)); 
        //Collections.swap(buttons, game.indexOf(0), buttons.indexOf(i)); 
    }
    
    // returns true if the array is in order from least to greatest
    // meaning the puzzle is solved and is valid
    public static boolean winningMove(ArrayList<Integer> buttons) {
        for (int i = 0; i < buttons.size(); i++) {
            if (buttons.get(i) != i) {
                return false;
            }
        }
        return true;
    }
}

//NOTES FOR CALLABLE CLASS
//need class to implement callable
//contain call method uses solver and returns solution so in javafx submit that method to use future.get() to get answer from

//valid move function
//iterate through list and compare to button
// for (int i = 0; i < pressable.size(); i++) {
//     System.out.println(pressable.get(i));
//     if (b.getText().equals(pressable.get(i))) {
//         return true;
//     }
// }
// return false;

// ---------------------------------
//     public static void swap(Button b, Integer[] buttons) {
// System.out.println("in swap method");

// int bx = 0; //x-coordinate of button being pressed
// int by = 0; //y-coordinate of button being pressed
// for (int c = 0; c < 4; c++) {
//     for (int r = 0; r < 4; r++) {
//         if (buttons[c][r].getText().equals(b.getText())) {
//             bx = c;
//             by = r;
//         }
//     }
// for(

// int c = 0;c<4;c++){// row
// for(
// int r = 0;r<4;r++)
// { // column
//     System.out.println(buttons[c][r].getText());

// }}

// Button zeroPrev = buttons[xCord][yCord];
// String buttontext = b
//         .getText();buttons[xCord][yCord].setText(buttontext);
//buttons[bx][by].setText("0");
//buttons[xCord][yCord]=b;
//xCord=bx; // setting
//                                                                                                                             // 0
//                                                                                                                             // xcoordinate
//                                                                                                                             // to
//                                                                                                                             // button
//                                                                                                                             // pressed
//                                                                                                                             // x
                                                                                                                        // cord
// yCord=by;buttons[bx][by]=zeroPrev; // swaps

// System.out.println("after swap zero @ "+"("+xCord+", "+yCord+")");for(
// int c = 0;c<4;c++)
// {//row
//             for (int r = 0; r < 4; r++) { //column
//                 System.out.println(buttons[c][r].getText());

//             }
//         }
// }
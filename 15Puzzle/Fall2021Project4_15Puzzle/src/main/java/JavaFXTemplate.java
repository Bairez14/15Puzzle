import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.stream.Collectors;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.*;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.BorderPane;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.Menu;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.scene.Node;
import javafx.util.Duration;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.animation.PauseTransition;
import java.lang.Thread;

public class JavaFXTemplate extends Application {

	public MenuItem AIH1, AIH2, exit, seeSolution, newGame, howTo;
	Text startGame;
	public GridPane gp;
	public Text moveNum;
	//public HashMap<String, Scene> sceneMap;
	//public ObservableList<String> stats;
	//public int[] buttons = new int[15];
	public ArrayList<Integer> buttons = new ArrayList<Integer>();
	EventHandler<ActionEvent> buttonpress;
	ArrayList<Puzzle> sol ;
//	int counter = 0; //counter for steps in solution
	int solutionSize = 10;
	PauseTransition solPause = new PauseTransition(Duration.seconds(2));
	int count = 0;
	boolean won = false;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		launch(args);
	}

	//feel free to remove the starter code from this method
	@Override
	public void start(Stage primaryStage) throws Exception {
		startGame = new Text("Welcome to 15 Puzzle");
		startGame.setFill(Color.WHITE);
		startGame.setFont(Font.font("Serif", 40));
		HBox welcomeBox = new HBox();
		welcomeBox.setAlignment(Pos.CENTER);
		welcomeBox.getChildren().add(startGame);
		Scene welcomeScene = new Scene(welcomeBox,500,500);
		welcomeScene.getRoot().setStyle("-fx-font-family: 'serif';"
				+ "-fx-background-image:url(https://www.unitedcenter.com/assets/1/7/MainFCKEditorDimension/United_Center_-_Michael_Jordan_Statue_3.png)");
		//start.setOnAction(primaryStage.setScene(puzzleScene()));
		primaryStage.setTitle("Welcome to 15 Puzzle");
		primaryStage.setScene(welcomeScene);
		primaryStage.show();
		PauseTransition welcomePause = new PauseTransition(Duration.seconds(3));
		welcomePause.setOnFinished(e -> {
				primaryStage.setScene(puzzleScene());
		});

		welcomePause.play();
		
		Thread t = new Thread(() -> {
			A_IDS_A_15solver ids = new A_IDS_A_15solver();
		});
		t.start();
	}

	public void addGrid(GridPane grid, ArrayList<Integer> currentButtons) {
		System.out.println("In grid method");
		//buttons = PuzzleLogic.generatePuzzle();
		buttons = currentButtons;
		int index = 0;
		for(int r = 0;  r < 4 ; r++) { //row
			for (int i = 0; i < 4; i++) { // collumn
				Button b1 = new Button(Integer.toString(buttons.get(index)));
				b1.setPrefSize(90, 90);
				
				b1.setOnAction(e->{
					int xbutton = GridPane.getColumnIndex(b1);
					int ybutton = GridPane.getRowIndex(b1);
					if (PuzzleLogic.validMove(xbutton, ybutton, buttons)) {
						PuzzleLogic.swap(b1, buttons);
						ObservableList<Node> gameButtons = grid.getChildren();
						for (Node node: gameButtons) {
							Button b = (Button) node;
							if (b.getText().equals("0")) {
								b.setText(b1.getText());
							}
						}
						b1.setText("0");
						if (PuzzleLogic.winningMove(buttons)) {
							won = true;
						}
					}
				});
				b1.setStyle("-fx-text-color: black;" + "-fx-background-color: white;" + "-fx-border-color: white;" + "-fx-font-size:35;" );
				grid.add(b1, i, r); // a bit confused, not sure how to go about this
				// we are supposed to place this button in the gridpane but we are only using a 1D array....
				index++;
			}
		}
	}

	

	public Scene puzzleScene() {
		gp = new GridPane();
		// setting up menubar
		MenuBar mBar = new MenuBar();
		Menu hMenu = new Menu("Heuristics");
		AIH1 = new MenuItem("Heuristic 1");
		AIH2 = new MenuItem("Heurostic 2");
		hMenu.getItems().addAll(AIH1, AIH2);
		Menu settingMenu = new Menu("Settings");
		exit = new MenuItem("Exit");
		newGame = new MenuItem("Play Again");
		settingMenu.getItems().addAll(exit, newGame);
		Menu solutionMenu = new Menu("Help");
		howTo = new MenuItem ("How To Play");
		seeSolution = new MenuItem("Solution");
		solutionMenu.getItems().add(seeSolution);
		solutionMenu.getItems().add(howTo);
		mBar.getMenus().addAll(hMenu, settingMenu, solutionMenu);
		// creating grid
		gp.setAlignment(Pos.CENTER);
		gp.setHgap(10);
		gp.setVgap(10);
		addGrid(gp, PuzzleLogic.generatePuzzle());
		HBox gameBox;
		if (won) {
			ListView<String> listView = new ListView<String>();
			listView.getItems().add("You Won!");
			gameBox = new HBox(mBar, gp, listView);
			newGame.setDisable(false);

		} else {
			gameBox = new HBox(mBar, gp);
		}
		newGame.setOnAction(e -> {
			newGame(gp);
		});
		Scene puzzleScene = new Scene(gameBox, 850, 850);
		BackgroundImage bg = new BackgroundImage(
				new Image("https://www.nba.com/bulls/sites/bulls/files/1920-generic-bullhead_0.png", 850, 850,
						false, true),
				BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT,
				BackgroundPosition.DEFAULT,
				new BackgroundSize(1.0, 1.0, true, true, false, false));
		puzzleScene.getRoot().setStyle("-fx-font-family: 'serif';");
		seeSolution.setDisable(true);
		newGame.setDisable(true);
		gameBox.setBackground(new Background(bg));

		// event handlers
		exit.setOnAction(e -> System.exit(0));
		AIH1.setOnAction(e -> {
			ExecutorService ex = Executors.newFixedThreadPool(1);
			ex.submit(() -> {
				ExecutorService ex2 = Executors.newFixedThreadPool(1);
				Future<ArrayList<Puzzle>> future = ex2.submit(new PuzzleCall(buttons, "heuristicOne"));
				//Node class vs java.util.scene.Node import
				try {
					sol = future.get();
					Platform.runLater(() -> {
						seeSolution.setDisable(false);
					});

				} catch (ExecutionException e1) {
					e1.printStackTrace();
				} catch (CancellationException e2) {
					e2.printStackTrace();
				} catch (InterruptedException e3) {
					e3.printStackTrace();
				}
			});
		});

		AIH2.setOnAction(e -> {
			ExecutorService ex = Executors.newFixedThreadPool(1);
			ex.submit(() -> {
				ExecutorService ex2 = Executors.newFixedThreadPool(1);
				Future<ArrayList<Puzzle>> future = ex2.submit(new PuzzleCall(buttons, "heuristicOne"));
				//Node class vs java.util.scene.Node import
				try {
					sol = future.get();
					Platform.runLater(() -> {
						seeSolution.setDisable(false);
					});

				} catch (ExecutionException e1) {
					e1.printStackTrace();
				} catch (CancellationException e2) {
					e2.printStackTrace();
				} catch (InterruptedException e3) {
					e3.printStackTrace();
				}
			});
		});

		seeSolution.setOnAction(e -> {
			if (sol.size() < 10) {
				solutionSize = sol.size();
			}
			int counter = 0;
			recursivePause(counter, sol);
			PuzzleLogic.updatePuzzle(buttons);
			won = PuzzleLogic.winningMove(buttons);
		});

		howTo.setOnAction(e -> {
			howToWindow();
		});
		
		return puzzleScene;
	}

// Basically, if you try putting transitions in for loops, it will cause issues. So instead of using a for loop, you would use something like this recursive function to display your grid. 
// I made a small typo in that post and you're supposed to display the UI change first and then do the if(condition) afterwards.

// So the idea is that you would pass some int index and Arraylist<node> you got from the algorithm into the function. 
// Then at that certain index, you would take the node at that index, which stores the whole puzzle for the one move made, 
// and then display it to the board using the pausetransition. Then if your condition is met, 
// meaning that you should display another round of moves, then you would recursive call with index+1 for the next Node, 
// which will include the next set of move.

	public void recursivePause(int counter, ArrayList<Puzzle> sol){
		PauseTransition s = new PauseTransition(Duration.seconds(2));
		s.setOnFinished(event -> {
			int count = counter;
			addGrid(gp, (ArrayList<Integer>) Arrays.stream(sol.get(count).getKey()).boxed().collect(Collectors.toList()));
			if (count < solutionSize) {
				System.out.println("In recursive");
				recursivePause(count+1, sol);
			} else {
				count++;
			}
			System.out.println(buttons.toString());
			
		});
		s.play();
	}
	
	// public Scene winScene() {

	// 	Text winText = new Text("Congrats! You won!");
	// 	HBox winBox = new HBox(winText);
	// 	Scene win = new Scene(winBox, 700, 700);
	// 	BackgroundImage bg = new BackgroundImage(
	// 			new Image("https://www.nba.com/bulls/sites/bulls/files/1920-generic-bullhead_0.png", 850, 850,
	// 					false, true),
	// 			BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT,
	// 			BackgroundPosition.DEFAULT,
	// 			new BackgroundSize(1.0, 1.0, true, true, false, false));
	// 	winBox.setBackground(new Background(bg));
	// 	win.getRoot().setStyle("-fx-font-family: 'serif';");
	// 	return win;
	// }

	public void newGame(GridPane grid) {
		addGrid(grid, PuzzleLogic.generatePuzzle());
	}

	public void howToWindow(){
		Stage newStage = new Stage();
		BorderPane pane = new BorderPane();
		pane.setPadding(new Insets(50));
		newStage.setTitle("How to Play");
		VBox box = new VBox();
		BackgroundImage bg = new BackgroundImage(
				new Image("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSGLIuYWmMlXPrV8YMb1BrsipsdDJjtD0OIGXSRfnxt5PQigITT2RMzN6NWCOTg743i7TQ&usqp=CAU", 700, 700,
						false, true),
				BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT,
				BackgroundPosition.DEFAULT,
				new BackgroundSize(1.0, 1.0, true, true, false, false));
		String instructions = ("Welcome to Puzzle15!\nThe goal is to swap the tiles with the empty tile until all buttons are in numerical order\n"
				+ " from lowest number to highest number with the blank spot in the upper left corner\nFor a hint, select help menu and a Heristic to "
				+ "solve the puzzle. Then select see Solution to see next ten moves be animated. ");
		Label label = new Label(instructions);
		label.setWrapText(true);
		label.setTextFill(Color.BLACK);
		label.setFont(Font.font("Verdana", 22));
		label.setPrefWidth(500);
		label.setPrefHeight(500);
		box.getChildren().add(label);
		box.setAlignment(Pos.CENTER);
		pane.setCenter(box);
		box.setBackground(new Background(bg));
		Scene stageScene = new Scene(pane, 600, 600);
		newStage.setScene(stageScene);
		newStage.show();
	}
}

import java.util.concurrent.Executors;
import java.util.concurrent.Future;
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
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.Node;
import javafx.util.Duration;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.animation.PauseTransition;

public class JavaFXTemplate extends Application {

	

	public MenuItem AIH1, AIH2, exit, seeSolution, newGame;
	Text startGame;
	public GridPane gp;
	public Text moveNum;
	//public HashMap<String, Scene> sceneMap;
	//public ObservableList<String> stats;
	//public int[] buttons = new int[15];
	public ArrayList<Integer> buttons = new ArrayList<Integer>();
	EventHandler<ActionEvent> buttonpress;
	ArrayList<Puzzle> sol ;

	//public static Button gameBoard[][] = new Button[4][4];

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		launch(args);
	}

	//feel free to remove the starter code from this method
	@Override
	public void start(Stage primaryStage) throws Exception {
		startGame = new Text("Welcome to 15 Puzzle");
		HBox welcomeBox = new HBox();
		welcomeBox.setAlignment(Pos.CENTER);
		welcomeBox.getChildren().add(startGame);
		Scene welcomeScene = new Scene(welcomeBox,1000,1000);
		welcomeScene.getRoot().setStyle("-fx-font-family: 'serif';"
				+ "-fx-background-image:url(https://cdn.shopify.com/s/files/1/0572/8311/7249/files/1991_Start_1024x1024.png?v=1626712496)");
		//start.setOnAction(primaryStage.setScene(puzzleScene()));
		primaryStage.setTitle("Welcome to 15 Puzzle");
		primaryStage.setScene(welcomeScene);
		primaryStage.show();
		PauseTransition welcomePause = new PauseTransition(Duration.seconds(3));
		welcomePause.setOnFinished(e->{
			primaryStage.setScene(puzzleScene());
		});

		welcomePause.play();
		
		Thread t = new Thread(() -> {
			A_IDS_A_15solver ids = new A_IDS_A_15solver();
		});
		t.start();
	}

	public void addGrid(GridPane grid) {
		buttons = PuzzleLogic.generatePuzzle();
		int index = 0;
		for(int r = 0;  r < 4 ; r++) { //row
			for (int i = 0; i < 4; i++) { // collumn
				Button b1 = new Button(Integer.toString(buttons.get(index)));
				//gameBoard[x][i] = b1;
				b1.setPrefSize(90, 90);
				//b1.setOnAction(buttonpress);
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
							// go to winner scene or let the user know they won
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
		newGame = new MenuItem("Reset");
		settingMenu.getItems().addAll(exit, newGame);
		Menu solutionMenu = new Menu("Help");
		seeSolution = new MenuItem("Solution");
		solutionMenu.getItems().add(seeSolution);
		mBar.getMenus().addAll(hMenu, settingMenu, solutionMenu);
		// creating grid
		gp.setAlignment(Pos.CENTER);
		gp.setHgap(10);
		gp.setVgap(10);
		addGrid(gp);
		HBox gameBox = new HBox(mBar, gp);
		Scene puzzleScene = new Scene(gameBox, 850, 850);
		BackgroundImage bg = new BackgroundImage(new Image("https://www.nba.com/bulls/sites/bulls/files/1920-generic-bullhead_0.png", 850, 850,
		false, true),
		BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT,
		BackgroundPosition.DEFAULT,
				new BackgroundSize(1.0, 1.0, true, true, false, false));
		gameBox.setBackground(new Background(bg));
		puzzleScene.getRoot().setStyle("-fx-font-family: 'serif';");
		
		// event handlers
		exit.setOnAction(e -> System.exit(0));
		AIH1.setOnAction(e -> {
			ExecutorService ex = Executors.newFixedThreadPool(1);
			ex.submit(()->{
				ExecutorService ex2 = Executors.newFixedThreadPool(1);
				Future<ArrayList<Puzzle>> future = ex2.submit(new PuzzleCall(buttons,"heuristicOne"));
				//Node class vs java.util.scene.Node import
				try{
					sol = future.get();

				}catch(ExecutionException e1){
					e1.printStackTrace();
				}catch(CancellationException e2){
					e2.printStackTrace();
				}catch(InterruptedException e3){
					e3.printStackTrace();
				}
			});
			
			// try{

			// }catch(ExecutionException e){
			// 	e.printStackTrace();
			// }

		});
		AIH2.setOnAction(e -> {

		});
		
		return puzzleScene;
	}

	// public void resetGame(GridPane grid) {
	// 	for (Node n : grid.getChildren()) {
	// 		Button temp = (Button) n;
	// 		temp.setText("");
	// 		temp.setDisable(false);
	// 		temp.setStyle("-fx-background-color: transparent;" + "-fx-border-color: white;");
	// 	}
	// }
}

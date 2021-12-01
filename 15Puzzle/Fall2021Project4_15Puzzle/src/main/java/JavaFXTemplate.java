import java.util.HashMap;
import java.util.*;
import javafx.scene.layout.BorderPane;
import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;

public class JavaFXTemplate extends Application {

	public MenuItem AIH1, AIH2, exit, seeSolution, newGame;
	Button start;
	public GridPane gp;
	public Text moveNum;
	//public HashMap<String, Scene> sceneMap;
	//public ObservableList<String> stats;
	//public Button[][] buttons = new Button[4][4];

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		launch(args);
	}

	//feel free to remove the starter code from this method
	@Override
	public void start(Stage primaryStage) throws Exception {
		start = new Button("Start Game");
		HBox welcomeBox = new HBox(start);
		welcomeBox.setAlignment(Pos.CENTER);
		welcomeBox.getChildren().add(start);
		Scene welcomeScene = new Scene(welcomeBox,1000,1000);
		welcomeScene.getRoot().setStyle("-fx-font-family: 'serif';"
				+ "-fx-background-image:url(https://cdn.shopify.com/s/files/1/0572/8311/7249/files/1991_Start_1024x1024.png?v=1626712496)");
		//start.setOnAction(primaryStage.setScene(puzzleScene()));
		primaryStage.setTitle("Welcome to 15 Puzzle");
		primaryStage.setScene(welcomeScene);
		primaryStage.show();
		Thread t = new Thread(() -> {
			A_IDS_A_15solver ids = new A_IDS_A_15solver();
		});
		t.start();
		// VBox welcomeVB = new VBox();
		// BackgroundImage bg = new BackgroundImage(new Image("welcome.jpg", 700, 700, false, true),
		// 		BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
		// 		new BackgroundSize(1.0, 1.0, true, true, false, false));
		// welcomeVB.setBackground(new Background(bg));

	}

	public void addGrid(GridPane grid) {
		for (int x = 0; x < 4; x++) { // column
			for (int i = 0; i < 4; i++) { // row
				Button b1 = new Button();
				// buttons[x][i] = b1; // populates buttons to 2d array of buttons
				b1.setPrefSize(90, 90);
				b1.setStyle("-fx-background-color: transparent;" + "-fx-border-color: white;");
				grid.add(b1, x, i); // adding buttons to grid
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
		Scene puzzleScene = new Scene(gameBox, 700, 700);
		puzzleScene.getRoot().setStyle("-fx-font-family: 'serif';"
				+ "-fx-background-image:url(https://www.nba.com/bulls/sites/bulls/files/1920-generic-bullhead_0.png)");

		// primaryStage.setScene(puzzleScene);
		// primaryStage.show();
		
		// event handlers
		exit.setOnAction(e -> System.exit(0));
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

	// public Scene gamePlayScene() {
	// 	//gp = new GridPane();
	// 	// setting up menubar
	// 	// MenuBar mBar = new MenuBar();
	// 	// Menu hMenu = new Menu("Heuristics");
	// 	// AIH1 = new MenuItem("Heuristic 1");
	// 	// AIH2 = new MenuItem("Heurostic 2");
	// 	// hMenu.getItems().addAll(AIH1,AIH2);
	// 	// Menu settingMenu = new Menu("Settings");
	// 	// exit = new MenuItem("Exit");
	// 	// newGame = new MenuItem("Reset");
	// 	// settingMenu.getItems().addAll(exit,newGame);
	// 	// Menu solutionMenu = new Menu("Help");
	// 	// seeSolution = new MenuItem("Solution");
	// 	// solutionMenu.getItems().add(seeSolution);
	// 	// mBar.getMenus().addAll(hMenu,settingMenu, solutionMenu);
	// 	start = new Button("Start Game");
	// 	//creating grid
	// 	// gp.setAlignment(Pos.CENTER);
	// 	// gp.setHgap(10);
	// 	// gp.setVgap(10);
	// 	// addGrid(gp);
	// 	HBox gameBox = new HBox(start);
	// 	Scene puzzleScene = new Scene(gameBox,700, 700);
	// 	// puzzleScene.getRoot().setStyle("-fx-font-family: 'serif';"
	// 	// 		+ "-fx-background-image: url(https://cdn.vox-cdn.com/thumbor/rh4HfDnP3aSyhDNC6wrRhqiYZzc=/1400x1400/filters:format(jpeg)/cdn.vox-cdn.com/uploads/chorus_asset/file/19246117/tyler_parker_5_guys_bulls_alyceatinoyan.jpg);");
	// 	// sceneMap.put("puzzleScene", puzzleScene);
	// 	return puzzleScene;
	// }

	// public Scene winScene(Stage primaryStage) {
	// 	return null;
	// }



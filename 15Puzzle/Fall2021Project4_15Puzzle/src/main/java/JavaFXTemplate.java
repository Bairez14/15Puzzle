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
	public HashMap<String, Scene> sceneMap;
	public BorderPane bPane;
	public ObservableList<String> stats;
	public Button[][] buttons = new Button[4][4];

	

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		launch(args);
	}

	//feel free to remove the starter code from this method
	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub
		//sceneMap.put("welcomeScene", welcomeScene(primaryStage));
		sceneMap.put("puzzleScene", gamePlayScene(primaryStage));
		primaryStage.setTitle("Welcome to 15 Puzzle");
		//Scene scene = new Scene(new VBox(), 700,700);
		primaryStage.setScene(sceneMap.get("puzzleScene"));
		//primaryStage.setScene(scene);
		primaryStage.show();
		
		Thread t = new Thread(()-> {A_IDS_A_15solver ids = new A_IDS_A_15solver();});
		t.start();

	}
	
	// public Scene welcomeScene(Stage primaryStage) {
	// 	Text welcome = new Text();
	// 	welcome.setText("Welcome to 15 Puzzle!");
	// 	welcome.setX(320);
	// 	welcome.setY(350);

	// 	//Scene scene = new Scene(, 700, 700);

	// 	return null;
	// }

	public Scene gamePlayScene(Stage primaryStage) {
		gp = new GridPane();
		// setting up menubar
		MenuBar mBar = new MenuBar();
		Menu hMenu = new Menu("Heuristics");
		AIH1 = new MenuItem("Heuristic 1");
		AIH2 = new MenuItem("Heurostic 2");
		hMenu.getItems().addAll(AIH1,AIH2);
		Menu settingMenu = new Menu("Settings");
		exit = new MenuItem("Exit");
		newGame = new MenuItem("Reset");
		settingMenu.getItems().addAll(exit,newGame);
		Menu solutionMenu = new Menu("Help");
		seeSolution = new MenuItem("Solution");
		solutionMenu.getItems().add(seeSolution);
		mBar.getMenus().addAll(hMenu,settingMenu, solutionMenu);
		start = new Button("Start Game");
		//creating grid
		gp.setAlignment(Pos.CENTER);
		gp.setHgap(10);
		gp.setVgap(10);
		addGrid(gp);
		HBox gameBox = new HBox(mBar, start,gp);
		Scene puzzleScene = new Scene(gameBox);
		puzzleScene.getRoot().setStyle("-fx-font-family: 'serif';"
				+ "-fx-background-image: url(https://cdn.vox-cdn.com/thumbor/rh4HfDnP3aSyhDNC6wrRhqiYZzc=/1400x1400/filters:format(jpeg)/cdn.vox-cdn.com/uploads/chorus_asset/file/19246117/tyler_parker_5_guys_bulls_alyceatinoyan.jpg);");

		return puzzleScene;
	}

	// public Scene winScene(Stage primaryStage) {
	// 	return null;
	// }
	
	public void addGrid(GridPane grid) {
		for (int x = 0; x < 4; x++) { // column
			for (int i = 0; i < 4; i++) { // row
				Button b1 = new Button();
				//buttons[x][i] = b1; // populates buttons to 2d array of buttons
				b1.setPrefSize(90, 90);
				b1.setStyle("-fx-background-color: transparent;" + "-fx-border-color: white;");
				grid.add(b1, x, i); // adding buttons to grid
			}
		}
	}

}

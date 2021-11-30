import java.util.HashMap;
import java.util.*;

import javafx.application.Application;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;

public class JavaFXTemplate extends Application {

	public Button AIH1, AIH2, start, exit, seeSolution, newGame;
	public GridPane gp;
	public Text moveNum;
	public HashMap<String, Scene> sceneMap;

	

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		launch(args);
	}

	//feel free to remove the starter code from this method
	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub
		primaryStage.setTitle("Welcome to JavaFX");
		
		
		
				
		Scene scene = new Scene(new VBox(), 700,700);
		primaryStage.setScene(scene);
		primaryStage.show();
		
		Thread t = new Thread(()-> {A_IDS_A_15solver ids = new A_IDS_A_15solver();});
		t.start();

	}
	
	public Scene welcomeScreen(Stage primaryStage){
		Text welcome = new Text();
		welcome.setText("Welcome to 15 Puzzle!");
		welcome.setX(320);
		welcome.setY(350);

		//Scene scene = new Scene(, 700, 700);

		return null;
	}

	public Scene gamePlayScene(Stage primaryStage){

		return null;
	}

	public Scene winScene(Stage primaryStage){
		return null;
	}

}

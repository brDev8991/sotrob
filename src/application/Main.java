package application;

import java.io.IOException;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;

public class Main extends Application {
	
	private double x, y;
	
	@Override
	public void start(Stage primaryStage) throws IOException {
		Parent rootParent = FXMLLoader.load(getClass().getResource("/controllers/Login.fxml"));
		Scene scene = new Scene(rootParent);
		
		rootParent.setOnMousePressed(new EventHandler<MouseEvent>(){
			@Override
			public void handle(MouseEvent event) {
				// TODO Auto-generated method stub
				x = event.getSceneX();
				y = event.getSceneY();
			}
		});
		
		rootParent.setOnMouseDragged(new EventHandler<MouseEvent>(){
			@Override
			public void handle(MouseEvent event) {
				// TODO Auto-generated method stub
				primaryStage.setX(event.getScreenX() - x);
				primaryStage.setY(event.getScreenY() - y);
			}
		});
		
		primaryStage.setScene(scene);
		primaryStage.initStyle(StageStyle.UNDECORATED);
		primaryStage.show();
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}

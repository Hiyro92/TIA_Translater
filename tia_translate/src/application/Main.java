package application;

import controller.MainController;
import javafx.application.Application;
import javafx.stage.Stage;


public class Main extends Application {
	
	
	@Override
	public void init() throws Exception {
		
		super.init();
	}

	@Override
	public void start(Stage primaryStage) {
		MainController mainController = new MainController();		
		primaryStage.setScene(mainController.getScene());
		primaryStage.show();
		primaryStage.setMinHeight(500);
		primaryStage.setMinWidth(500);
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}

package application;
	
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Properties;

import org.apache.logging.log4j.core.LoggerContext;
import org.apache.logging.log4j.core.config.properties.PropertiesConfiguration;

import controller.MainController;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;


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

package controller;

import java.io.File;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import Database.SQLiteDatabaseConnection;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TableView;
import javafx.scene.layout.StackPane;

public class MainController {
	
    @FXML
    private MenuItem mButtonNew;
    @FXML
    private MenuItem mButtonOpen;
    @FXML
    private MenuItem mButtonClose;
    @FXML
    private Label txtProjectName;
    @FXML
    private SplitPane splitView;
    @FXML
    private TableView<?> table;

	StackPane root;
	Scene scene;
	Logger LOGGER = LogManager.getLogger();
	
	public MainController() {		
		try {
			root = FXMLLoader.load(getClass().getResource("/fxml/MainView.fxml"));
			scene = new Scene(root);
		} catch (Exception e) {
			LOGGER.fatal("MainView  kann nicht geladen werden!");
		}
		

		SQLiteDatabaseConnection s = new SQLiteDatabaseConnection();
		s.executeStatment("");
		
	}
	

	public Scene getScene() {		
		return scene;
	}

}

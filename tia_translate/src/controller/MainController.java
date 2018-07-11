package controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.message.LoggerNameAwareMessage;

import Logger.LoggerConsoleController;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
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
	
	public MainController() {		
		try {
			root = FXMLLoader.load(getClass().getResource("/fxml/MainView.fxml"));
			scene = new Scene(root);
		} catch (Exception e) {
			e.printStackTrace();
		}		
		
		
		Logger logger = LogManager.getLogger();
		logger.fatal("fatal");
		logger.error("error");
		logger.info("Test");
		logger.trace("Test");
		logger.warn("Test");
		logger.trace("Test");
		Thread t1 = new Thread(new Runnable() {		
			@Override
			public void run() {
				while(true) {
					logger.debug("Das ist ein Test");
					try {
						Thread.currentThread().sleep(5000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				
			}
		});
		t1.start();
	}
	

	public Scene getScene() {		
		return scene;
	}

}

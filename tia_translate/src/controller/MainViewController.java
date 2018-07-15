package controller;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Date;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import application.Main;
import bean.ProjectHead;
import dao.ProjectHeadDAO;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TableView;
import javafx.scene.control.TextInputDialog;
import javafx.scene.layout.StackPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class MainViewController {	

	final private Logger LOGGER = LogManager.getLogger();
	
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

	
	
    @FXML
    void actionCloseApp(ActionEvent event) {
    	Platform.exit();
    }

    @FXML
    void actionLoadProject(ActionEvent event) {   	
    	Parent root = null;
    	MenuItem souce = (MenuItem)event.getSource();
		try {
			root = FXMLLoader.load(getClass().getResource("/fxml/OpenProjectView.fxml"));
		} catch (IOException e) {
			LOGGER.fatal("OpenProjectView kann nicht geladen werden!");
		}
    	Stage stage  = new Stage();
    	stage.setScene(new Scene(root, 600, 400));
    	stage.setResizable(false);
    	stage.initModality(Modality.WINDOW_MODAL);
    	stage.initOwner(souce.getParentPopup().getOwnerWindow());
    	stage.show();
    }

    @FXML
    void actionNewProject(ActionEvent event) {
    	TextInputDialog dialog = new TextInputDialog();
    	dialog.setTitle("Create new project");
    	dialog.setHeaderText("Create new project");
    	dialog.setContentText("Project name : " );
    	Optional<String> result = dialog.showAndWait();
    	if (result.isPresent()){
    	    new ProjectHeadDAO().add(new ProjectHead(result.get(), new Date() , 0));
    	}
    }

}

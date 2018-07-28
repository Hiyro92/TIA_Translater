package controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Date;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import bean.ProjectHead;
import dao.ProjectHeadDAO;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TableView;
import javafx.scene.control.TextInputDialog;
import javafx.scene.image.Image;
import javafx.stage.Modality;
import javafx.stage.Stage;
import loader.ImportLoader;

public class MainViewController {	

	final private Logger LOGGER = LogManager.getLogger();
	
	private ProjectHead showenData;	
	
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
    void initialize() {
    	
    }
	
	
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
    	stage.setTitle("Open Project");
    	stage.setResizable(false);
    	stage.initModality(Modality.APPLICATION_MODAL);
    	stage.getIcons().add(new Image("/res/translation.png"));
    	stage.showAndWait();
    	ProjectHead selectetProjectHead = (ProjectHead)stage.getUserData();
    	if(selectetProjectHead != null) {
    		LOGGER.debug("Loade project: " + selectetProjectHead.getName());
    	}

    }

    @FXML
    void actionNewProject(ActionEvent event) {
    	Parent root = null;
    	MenuItem souce = (MenuItem)event.getSource();
		try {
			root = FXMLLoader.load(getClass().getResource("/fxml/NewProjectView.fxml"));
		} catch (IOException e) {
			LOGGER.fatal("NewProjectView kann nicht geladen werden!");
		}
    	Stage stage  = new Stage();
    	stage.setScene(new Scene(root));
    	stage.setTitle("New Project");
    	stage.setResizable(false);
    	stage.initModality(Modality.APPLICATION_MODAL);
    	stage.getIcons().add(new Image("/res/translation.png"));
    	stage.showAndWait();   	
    	if(stage.getUserData() != null){
    		Object[] data =(Object[])stage.getUserData();
    		String projectName = (String)data[0];
        	File f = (File)data[1];
        	LOGGER.debug("Create Project: " + projectName);
    	}
    	
    }
    
    public void LoadProject(ProjectHead head) {
    	
    }
    
    private void updateTable() {
    	
    }
    private void createProject(String name, File file){
    	ProjectHead ph = new ProjectHead();
    	ph.setName(name);
    	ph.setCreatingDate(new Date());
    	try {
			ImportLoader loader = new ImportLoader(file);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
    	
    }

}

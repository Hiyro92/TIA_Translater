package controller;

import java.text.SimpleDateFormat;
import java.util.Date;

import bean.ProjectHead;
import dao.ProjectHeadDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Control;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class OpenProjectController {
	
    @FXML
    private TableView<ProjectHead> table;    
    @FXML
    private TableColumn<ProjectHead, String> tcName;
    @FXML
    private TableColumn<ProjectHead, Integer> tcContent;
    @FXML
    private TableColumn<ProjectHead, Date> tcDate;
    
    ProjectHeadDAO projectHeadDAO = new ProjectHeadDAO();

    @FXML
    void initialize() {
    	tcName.setCellValueFactory(new PropertyValueFactory<ProjectHead, String>("name"));
    	tcContent.setCellValueFactory(new PropertyValueFactory<ProjectHead, Integer>("content"));
    	tcDate.setCellValueFactory(new PropertyValueFactory<ProjectHead, Date>("creatingDate"));

		tcDate.setCellFactory((TableColumn<ProjectHead, Date> column) -> {
			return new TableCell<ProjectHead, Date>() {
				@Override
				protected void updateItem(Date item, boolean empty) {
					SimpleDateFormat datef = new SimpleDateFormat("EEE, dd.MM.yy / hh:mm:ss");
					super.updateItem(item, empty);
					if (item == null || empty) {
						setText(null);
					} else {
						setText(datef.format(item));
					}
				}
			};
		});
		tcContent.setStyle("-fx-alignment: CENTER");
		tcDate.setStyle("-fx-alignment: CENTER");
		
    	updateTable();
    }
    
    @FXML
    void actionDeleteButtton(ActionEvent event) {
    	ProjectHead head = null;
    	head = table.getSelectionModel().getSelectedItem();
    	if(head != null) {
    		projectHeadDAO.delete(head);
    	}
    	else {
    		Alert alert = new Alert(AlertType.INFORMATION);
    		alert.setTitle("No Selcetion!");
    		alert.setHeaderText(null);
    		alert.setContentText("Please select an Item");
    		alert.show();
    	}
    	updateTable();
    }
    
    @FXML
    void actionCancelButtton(ActionEvent event) {
    	Stage stage = (Stage)table.getScene().getWindow();
    	stage.close();
    }

    @FXML
    void actionOpenButtton(ActionEvent event) {
    	ProjectHead head = table.getSelectionModel().getSelectedItem();
    	if(head != null) {
    		Stage stage = (Stage)table.getScene().getWindow();
    		stage.setUserData(head);
        	stage.close();
    	}
    	else {
    		Alert alert = new Alert(AlertType.INFORMATION);
    		alert.setTitle("No Selcetion!");
    		alert.setHeaderText(null);
    		alert.setContentText("Please select an Item");
    		alert.show();
    	}
    	updateTable();
    }

    private void updateTable() {
    	table.setItems(FXCollections.observableArrayList(projectHeadDAO.getAll()));
    }
}
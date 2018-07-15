package controller;

import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import org.apache.poi.ss.usermodel.Table;

import bean.ProjectHead;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class OpenProjectController {
	
	ObservableList<ProjectHead> testData = FXCollections.observableArrayList(
			new ProjectHead("Test", new Date(), 50),
			new ProjectHead("Test1", new Date(), 53),
			new ProjectHead("Test2", new Date(), 41),
			new ProjectHead("Test3", new Date(), 52)
			);

    @FXML
    private TableView<ProjectHead> table;    
    @FXML
    private TableColumn<ProjectHead, String> tcName;
    @FXML
    private TableColumn<ProjectHead, Integer> tcContent;
    @FXML
    private TableColumn<ProjectHead, Date> tcDate;

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
		
    	table.setItems(testData);
    }
    
    @FXML
    void actionCancelButtton(ActionEvent event) {

    }

    @FXML
    void actionOpenButtton(ActionEvent event) {

    }

}
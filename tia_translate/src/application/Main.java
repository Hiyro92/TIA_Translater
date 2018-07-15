package application;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import bean.ProjectHead;
import controller.MainViewController;
import dao.ProjectHeadDAO;
import it.sauronsoftware.junique.AlreadyLockedException;
import it.sauronsoftware.junique.JUnique;
import it.sauronsoftware.junique.MessageHandler;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;


public class Main extends Application {
	
	final private Logger LOGGER = LogManager.getLogger();
	
	final private String appId = "TIA_Translater";
	final public static String DATABASE_PATH  = new File("Data.db").getAbsolutePath();
	

	@Override
	public void start(Stage primaryStage) {
			
		boolean alreadyRunning;
		//Prüft ob Programm schon läuft 
		try {
			JUnique.acquireLock(appId, new MessageHandler() {
				
				//args als Nachrichten der neuen Programms
				public String handle(String message) {			
					Platform.runLater(() -> {
						LogManager.getLogger().debug("Es kann nur eine instanz geöffnet werden!");
						primaryStage.setAlwaysOnTop(true);
						primaryStage.setAlwaysOnTop(false);
					});
					return null;
				}
			});
			alreadyRunning = false;
		} catch (AlreadyLockedException e) {
			alreadyRunning = true;
		}
		
		//Programm läuft noch nicht neue Instanz wird erzeugt
		if (!alreadyRunning) {
			Parent root = null;
			try {
				
				root = FXMLLoader.load(getClass().getResource("/fxml/MainView.fxml"));
			} catch (IOException e) {
				LOGGER.fatal("MainView kann nicht geladen werden!");
			}

			Scene scene = new Scene(root);
			primaryStage.setTitle("TIA Translater");
			primaryStage.setScene(scene);
			primaryStage.show();
		} 
		
		//Programm läuft bereits Sende args zu aktiver Instanz
		else {
			List<String> para = getParameters().getRaw();
			for (int i = 0; i < para.size(); i++) {
				JUnique.sendMessage(appId, para.get(i));
			}
			Platform.exit();
		}
	}
	
	
	public static void main(String[] args) {
		launch(args);
	}
}

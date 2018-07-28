package application;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Date;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import it.sauronsoftware.junique.AlreadyLockedException;
import it.sauronsoftware.junique.JUnique;
import it.sauronsoftware.junique.MessageHandler;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import loader.ImportLoader;


public class Main extends Application {
	
	final static private Logger LOGGER = LogManager.getLogger();	
	final private static String APP_ID = "TIA_Translater";
	final public static String DATABASE_PATH  = new File("Data.db").getAbsolutePath();
		

	@Override
	public void start(Stage primaryStage) {
				
		boolean alreadyRunning;
		//Prueft ob Programm schon laeuft 
		try {
			JUnique.acquireLock(APP_ID, new MessageHandler() {
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
		
		//Programm laeuft noch nicht -> neue Instanz wird erzeugt
		if (!alreadyRunning) {
			Parent root = null;
			try {
				
				root = FXMLLoader.load(getClass().getResource("/fxml/MainView.fxml"));
			} catch (IOException e) {
				LOGGER.fatal("MainView kann nicht geladen werden!");
			}

			Scene scene = new Scene(root);
			primaryStage.getIcons().add(new Image("/res/translation.png"));
			primaryStage.setTitle("TIA Translater");
			primaryStage.setScene(scene);
			primaryStage.show();
		} 
		
		//Programm laeuft bereits Sende args zu aktiver Instanz
		else {
			List<String> para = getParameters().getRaw();
			for (int i = 0; i < para.size(); i++) {
				JUnique.sendMessage(APP_ID, para.get(i));
			}
			Platform.exit();
		}
	}
	
	//Start des Programms
	public static void main(String[] args) {
		launch(args);
	}
}

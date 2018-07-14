package application;

import java.util.List;

import org.apache.logging.log4j.LogManager;

import controller.MainController;
import it.sauronsoftware.junique.AlreadyLockedException;
import it.sauronsoftware.junique.JUnique;
import it.sauronsoftware.junique.MessageHandler;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;


public class Main extends Application {
	
	private MainController mainController;	
	final private String appId = "TIA_Translater";

	@Override
	public void start(Stage primaryStage) {
		boolean alreadyRunning;
		
		try {
			JUnique.acquireLock(appId, new MessageHandler() {
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
		if (!alreadyRunning) {
			MainController mainController = new MainController();
			primaryStage.setScene(mainController.getScene());
			primaryStage.show();
		} else {
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

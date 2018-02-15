package core;

import javafx.application.Application;
import javafx.stage.Stage;

public class StartApp extends Application {

	public StartApp() {
	}

	public StartApp(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage stage) {
	
		Message mess = new Message();
		String answer = mess.menuMessage();

		if (answer.equalsIgnoreCase("Quit program")) {
			System.exit(0);
		} else {
			SubMessage subMess = new SubMessage();
			subMess.menuOptions(answer);
		
		}
		
	}
}

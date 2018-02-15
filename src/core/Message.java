package core;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class Message {
	String menuAnswer;
	public Message() {	}
	public String menuMessage() {
		Stage stage = new Stage();
		stage.initModality(Modality.APPLICATION_MODAL);
		stage.setMinWidth(250);
		stage.setMinHeight(250);

		ChoiceBox<String> menuBox = new ChoiceBox<>();
		menuBox.getItems().addAll("Origonal Image", "Blur Image", "Drawing Image", "Filter Image", "Sobel Image",
				"Transformation Image","Quit program");
		menuBox.setValue("Origonal Image");

		Button okButton = new Button("Submit");
		okButton.setOnAction(e -> getChose(menuBox, stage));

		VBox layout = new VBox(10);
		layout.getChildren().addAll(menuBox, okButton);
		layout.setAlignment(Pos.CENTER);

		Scene scene = new Scene(layout, 500, 500);
		stage.setScene(scene);
		stage.showAndWait();
		return menuAnswer;

	}

	private void getChose(ChoiceBox<String> menuBox, Stage stage) {
		menuAnswer = menuBox.getValue();
		stage.close();
		
	}
	
}

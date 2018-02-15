package transformation;

import java.io.IOException;

import org.opencv.core.Mat;
import org.opencv.imgproc.Imgproc;

import core.InterfaceImage;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class TransformationMenu implements InterfaceImage{
	String sobelAnswer;

	public String menuOptionBuilder() {
		Stage stage = new Stage();
		stage.initModality(Modality.APPLICATION_MODAL);
		stage.setMinWidth(250);
		stage.setMinHeight(250);
		
		ChoiceBox<String> menuBox = new ChoiceBox<>();
		menuBox.getItems().addAll("Scharr Operator","Sobel Operator");
		menuBox.setValue("Scharr Operator");
		
		Button okButton = new Button("Submit");
		okButton.setOnAction(e -> {
			getChoseOptions(menuBox, stage);
		});
		
		VBox layout = new VBox(10);
		layout.getChildren().addAll(menuBox, okButton);
		layout.setAlignment(Pos.CENTER);
		
		Scene scene = new Scene(layout, 500, 500);
		stage.setScene(scene);
		stage.showAndWait();
		return sobelAnswer;
	}

	private void getChoseOptions(ChoiceBox<String> menuBox,Stage stage) {
		sobelAnswer  = menuBox.getValue();		
		stage.close();		
		
	}
	
	public void printImage(String answer) throws IOException{
		Stage stage = new Stage();
		TransformationMenu obj = new TransformationMenu();
		WritableImage writableImage = obj.loadImage(answer);
		showImage(writableImage, stage, "Sobel Image");
		
	}

	private WritableImage loadImage(String answer) {
		
		Mat src = loadImageAndLibrary();
		Mat dst = new Mat();
		
		if (answer.equalsIgnoreCase("Scharr Operator")) {
			Imgproc.Scharr(src, dst, Imgproc.CV_SCHARR, 1,0);
		}
		else{
			Imgproc.Sobel(src, dst, -1, 1, 1);
		}
		
		WritableImage writableImage = buffImage(dst);
			return writableImage;
	}
	
}

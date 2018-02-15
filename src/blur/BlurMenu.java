package blur;

import java.io.IOException;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.Point;
import org.opencv.core.Size;
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

public class BlurMenu implements InterfaceImage {
	String answer;

	public String menuOptionBuilder() {
		Stage stage = new Stage();
		stage.initModality(Modality.APPLICATION_MODAL);
		stage.setMinWidth(250);
		stage.setMinHeight(250);
		
		ChoiceBox<String> menuBox = new ChoiceBox<>();
		menuBox.getItems().addAll("Blur","MedianBlur","BlurGaussian");
		menuBox.setValue("Blur");
		
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
		return answer;
	}

	private void getChoseOptions(ChoiceBox<String> menuBox,Stage stage) {
		answer  = menuBox.getValue();		
		stage.close();		
		
	}
	
	public void printImage(String answer) throws IOException{
		Stage stage = new Stage();
		BlurMenu obj = new BlurMenu();
		WritableImage writableImage = obj.loadImage(answer);
		showImage(writableImage, stage, "Blur Image");
		
	}

	private WritableImage loadImage(String answer) {
		
		Mat src = loadImageAndLibrary();
		Mat dst = new Mat();
		
		if (answer.equalsIgnoreCase("MedianBlur")) {
			Imgproc.medianBlur(src, dst, 15);
		}
		else if (answer.equalsIgnoreCase("BlurGaussian")) {
			Size size = new Size(45, 45);
			Imgproc.GaussianBlur(src, dst, size, 0);
		}
		else{
			Size size = new Size(45, 45);
			Point point = new Point(20, 30);

			Imgproc.blur(src, dst, size, point, Core.BORDER_DEFAULT);
		}
		
		WritableImage writableImage = buffImage(dst);
			return writableImage;
	}
}

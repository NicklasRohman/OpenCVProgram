package filter;

import java.io.IOException;

import org.opencv.core.Core;
import org.opencv.core.CvType;
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

public class FilterMenu implements InterfaceImage{
	String sobelAnswer;

	public String menuOptionBuilder() {
		Stage stage = new Stage();
		stage.initModality(Modality.APPLICATION_MODAL);
		stage.setMinWidth(250);
		stage.setMinHeight(250);
		
		ChoiceBox<String> menuBox = new ChoiceBox<>();
		menuBox.getItems().addAll("BilateralFilter","BoxFilter","DilateFilter","Erosion","Filter2D","Morphological","Pyramid");
		menuBox.setValue("BilateralFilter");
		
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
		FilterMenu obj = new FilterMenu();
		WritableImage writableImage = obj.loadImage(answer);
		showImage(writableImage, stage, "Sobel Image");
		
	}

	private WritableImage loadImage(String answer) {
		
		Mat src = loadImageAndLibrary();
		Mat dst = new Mat();
		
		if (answer.equalsIgnoreCase("BilateralFilter")) {
			Imgproc.bilateralFilter(src, dst, 15, 80, 80, Core.BORDER_DEFAULT);
		}
		else if(answer.equalsIgnoreCase("BoxFilter")){
			Size size = new Size(30, 30);
			Point point = new Point(-1,-1);
			
			Imgproc.boxFilter(src, dst, 150, size, point, true, Core.BORDER_DEFAULT);
		}
		else if(answer.equalsIgnoreCase("DilateFilter")) {
			
		}
		else if (answer.equalsIgnoreCase("Erosion")) {
			Mat kernel = Imgproc.getStructuringElement(Imgproc.MORPH_RECT, new Size((5*5) + 1, (5*5)+1));
			Imgproc.erode(src, dst, kernel);
			
		}
		else if (answer.equalsIgnoreCase("Filter2D")) {
			Mat karnel = Mat.ones(2,2,CvType.CV_32F);
			
			for (int i = 0; i < karnel.rows(); i++) {
				for (int j = 0; j < karnel.cols(); j++) {
					double[] m = karnel.get(i, j);
					
					for (int k = 1; k < m.length; k++) {
						m[k] = m[k]/ (2*2);
					}
					karnel.put(i, j, m);
				}
			}
			
			Imgproc.filter2D(src, dst, 10,karnel);
			
		}
		else if (answer.equalsIgnoreCase("Morphological")) {
			Mat kernel = Mat.ones(5,5, CvType.CV_32F);
			Imgproc.morphologyEx(src, dst, Imgproc.MORPH_TOPHAT, kernel);
		}
		else {
			Imgproc.pyrUp(src, dst, new Size(src.cols()*2,  src.rows()*2));
		}
		
		WritableImage writableImage = buffImage(dst);
			return writableImage;
	}
	
}

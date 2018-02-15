package shape;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.MatOfPoint;
import org.opencv.core.Point;
import org.opencv.core.RotatedRect;
import org.opencv.core.Scalar;
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

public class DrawingMenu implements InterfaceImage{
	String answer;

	public String menuOptionBuilder() {
		Stage stage = new Stage();
		stage.initModality(Modality.APPLICATION_MODAL);
		stage.setMinWidth(250);
		stage.setMinHeight(250);
		
		ChoiceBox<String> menuBox = new ChoiceBox<>();
		menuBox.getItems().addAll("Add Text","DrawLine","DrawCircle","DrawPolyLine","DrawConvexPoly","DrawEllipse","DrawRectangle","DrawArrow");
		menuBox.setValue("DrawLine");
		
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
		DrawingMenu obj = new DrawingMenu();
		WritableImage writableImage = obj.loadImage(answer);
		showImage(writableImage, stage, "Drawing Image");
		
	}

	private WritableImage loadImage(String answer) {

		Mat src = loadImageAndLibrary();
		Mat dst = new Mat();
		
		
		if (answer.equalsIgnoreCase("Add Text")) {
			Imgproc.putText(src, "Text Added", new Point(10,50), Core.FONT_HERSHEY_COMPLEX,1, new Scalar(0,0,0), 4);

		}
		else if (answer.equalsIgnoreCase("DrawLine")) {
			Imgproc.line(
					src, 
					new Point(10, 200), 
					new Point(300, 200), 
					new Scalar(0, 0, 255),
					5
				);
		}
		else if (answer.equalsIgnoreCase("DrawCircle")) {
			Imgproc.circle(src, new Point(530,360),100,new Scalar(0,0,250),10);
		}
		else if (answer.equalsIgnoreCase("DrawPolyLine")) {
			List <MatOfPoint> list = new ArrayList<MatOfPoint>();
			list.add(new MatOfPoint(new Point(75, 100), new Point(350, 100),
		            new Point(75, 150), new Point(350, 150),
		            new Point(75, 200), new Point(350, 200),
		            new Point(75, 250), new Point(350, 250)));
			
			
			Imgproc.polylines(src,list,false, new Scalar(0,0,255));
		}
		else if (answer.equalsIgnoreCase("DrawConvexPoly")) {
			 MatOfPoint matOfPoint = new MatOfPoint (
			         new Point(75, 100), new Point(350, 100),
			         new Point(75, 150), new Point(350, 150),
			         new Point(75, 200), new Point(350, 200),
			         new Point(75, 250), new Point(350, 250)
			      ); 
			
			Imgproc.fillConvexPoly(src,matOfPoint,new Scalar(0,0,255));
		}
		else if (answer.equalsIgnoreCase("DrawEllipse")) {
			Imgproc.ellipse(
					src, 
					new RotatedRect(
							new Point(200,150),
							new Size(260,180),180
							)
					,new Scalar(0,0,255),
					5);
		}
		else if (answer.equalsIgnoreCase("DrawRectangle")) {
			Imgproc.rectangle(src, new Point(130,50), new Point(380,280), new Scalar(0,0,255),5);

		}
		else{
			Imgproc.arrowedLine(src, new Point(10,200), new Point(590,200), new Scalar(0,100,255),10,0,0,0.2);
			
		}

		WritableImage writableImage = buffImage(src);
		return writableImage;
	}
}

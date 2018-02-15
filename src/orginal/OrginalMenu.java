package orginal;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.MatOfByte;
import org.opencv.imgcodecs.Imgcodecs;

import core.InterfaceImage;
import javafx.embed.swing.SwingFXUtils;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class OrginalMenu implements InterfaceImage {
	String answer;

	public String menuOptionBuilder() {
		Stage stage = new Stage();
		stage.initModality(Modality.APPLICATION_MODAL);
		stage.setMinWidth(250);
		stage.setMinHeight(250);
		
		ChoiceBox<String> menuBox = new ChoiceBox<>();
		menuBox.getItems().addAll("Orginal","GrayImage","RGB Scene");
		menuBox.setValue("Orginal");
		
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
		OrginalMenu obj = new OrginalMenu();
		WritableImage writableImage = obj.loadImage(answer);
		showImage(writableImage, stage, "Orginal Image");
		
	}

	private WritableImage loadImage(String answer) {
		System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
		String file = "apa.jpg";
		WritableImage writableImage = null;
		
		if (answer.equalsIgnoreCase("GrayImage")) {
			
			Mat grayImage = Imgcodecs.imread(file, Imgcodecs.IMREAD_GRAYSCALE);
			
			byte[] byteArray= new byte[ grayImage.rows()*grayImage.cols()*(int)(grayImage.elemSize())];
			grayImage.get(0, 0, byteArray);
			
			BufferedImage bufGrayImage = new BufferedImage(grayImage.cols(),grayImage.rows(), BufferedImage.TYPE_BYTE_GRAY);
			
			bufGrayImage.getRaster().setDataElements(0, 0, grayImage.cols(), grayImage.rows(), byteArray);
			
			writableImage = SwingFXUtils.toFXImage(bufGrayImage, null);
			
		
		}
		else if (answer.equalsIgnoreCase("RGB Scene")) {
			
			Mat rgbImage = Imgcodecs.imread(file, Imgcodecs.IMREAD_COLOR);
			
			byte[] byteArray = new byte[rgbImage.cols()*rgbImage.rows()*(int)(rgbImage.elemSize())];
			rgbImage.get(0, 0,byteArray);
			
			BufferedImage bufRgbImage = new BufferedImage(rgbImage.cols(), rgbImage.rows(), BufferedImage.TYPE_3BYTE_BGR); 
			bufRgbImage.getRaster().setDataElements(0,0,rgbImage.cols(), rgbImage.rows(), byteArray); 
			
			writableImage = SwingFXUtils.toFXImage(bufRgbImage, null);
			
		}
		else{
			
			Mat image = Imgcodecs.imread(file);
			MatOfByte matOfBite = new MatOfByte();
			Imgcodecs.imencode(".jpg", image, matOfBite);
			byte[] byteArray = matOfBite.toArray();
			InputStream in = new ByteArrayInputStream(byteArray);
			BufferedImage bufImage;
			try {
				bufImage = ImageIO.read(in);
				writableImage = SwingFXUtils.toFXImage(bufImage, null);
			} catch (IOException e) {
				e.printStackTrace();
			}

			

		}
		
			return writableImage;
	}
}

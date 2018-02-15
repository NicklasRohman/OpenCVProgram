package core;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.MatOfByte;
import org.opencv.imgcodecs.Imgcodecs;

import javafx.embed.swing.SwingFXUtils;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.stage.Stage;

public interface InterfaceImage {

	public default Mat loadImageAndLibrary() {
		System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
		String image = "apa.jpg";

		Mat src = Imgcodecs.imread(image);

		return src;
	}

	public default Mat loadImageAndLibraryPlusFlag() {
		System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
		String image = "apa.jpg";

		Mat src = Imgcodecs.imread(image,0);

		return src;
	}
	
	
	
	
	public default  WritableImage buffImage(Mat dst)  {
		MatOfByte matOfByte = new MatOfByte();
		Imgcodecs.imencode(".jpg", dst, matOfByte);
		
		byte[] byteArray = matOfByte.toArray();

		InputStream in = new ByteArrayInputStream(byteArray);
		BufferedImage bufImg = null;
			try {
				bufImg = ImageIO.read(in);
			} catch (IOException e) {
				e.printStackTrace();
			}

		WritableImage writableImage = SwingFXUtils.toFXImage(bufImg, null);
		return writableImage;

	}
	
	public default void showImage(WritableImage writableImage,Stage stage,String title){
		ImageView imageView = new ImageView(writableImage);
		imageView.setFitHeight(500);
		imageView.setFitWidth(500);
		imageView.setPreserveRatio(true);
		
		Group root = new Group(imageView);
		Scene scene = new Scene(root,600,600);
		
		stage.setTitle(title);
		stage.setScene(scene);
		stage.show();
	}
	
	
}

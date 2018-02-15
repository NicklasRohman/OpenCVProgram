package shape;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

import org.opencv.core.*;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;

import javafx.application.Application;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.stage.Stage;

public class DrawArrow extends Application{

	public DrawArrow() {
	}

	public DrawArrow(String[] args) {
	launch(args);
	}

	@Override
	public void start(Stage stage) throws Exception {
		
		DrawArrow obj = new DrawArrow();
		WritableImage writableImage = obj.loadImage();
		
		ImageView imageView = new ImageView(writableImage);
		imageView.setFitHeight(500);
		imageView.setFitWidth(500);
		
		Group root = new Group(imageView);
		Scene scene = new Scene(root,600,600);
		
		stage.setTitle("Arrow image");
		stage.setScene(scene);
		stage.show();
		
	}

	private WritableImage loadImage() throws IOException {

		System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
		
		String image = "apa.jpg";
		Mat matrix = Imgcodecs.imread(image);
		
		Imgproc.arrowedLine(matrix, new Point(10,200), new Point(590,200), new Scalar(0,100,255),10,0,0,0.2);
		
		MatOfByte manOfByte = new MatOfByte();
		Imgcodecs.imencode(".jpg", matrix, manOfByte);
		byte[]byteArray = manOfByte.toArray();
		
		InputStream in = new ByteArrayInputStream(byteArray);
		BufferedImage bufImg  = ImageIO.read(in);
		WritableImage writableImage = SwingFXUtils.toFXImage(bufImg, null);
		
		return writableImage;
	}

}

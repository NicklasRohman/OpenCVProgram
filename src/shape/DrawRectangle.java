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
import javafx.scene.transform.Scale;
import javafx.stage.Screen;
import javafx.stage.Stage;

public class DrawRectangle extends Application {
	
	public DrawRectangle(String[] args) {
		launch(args);
	}

	public DrawRectangle() {
	}

	@Override
	public void start(Stage stage) throws Exception {

		DrawRectangle obj = new DrawRectangle();
		WritableImage writableImage = obj.loadImage();

		ImageView imageView = new ImageView(writableImage);

		imageView.setFitHeight(500);
		imageView.setFitWidth(500);
		imageView.setPreserveRatio(true);

		Group root = new Group(imageView);

		Scene scene = new Scene(root, 600, 600);

		stage.setTitle("Rectangle Image");
		stage.setScene(scene);
		stage.show();

	}

	private WritableImage loadImage() throws IOException {
		System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
		String file = "apa.jpg";

		Mat matrix = Imgcodecs.imread(file);

		Imgproc.rectangle(matrix, new Point(130,50), new Point(380,280), new Scalar(0,0,255),5);
		
		MatOfByte matOfByte = new MatOfByte();
		Imgcodecs.imencode(".jpg",matrix,matOfByte);
		
		byte[] byteArray = matOfByte.toArray();
		
		InputStream in = new ByteArrayInputStream(byteArray);
		BufferedImage bufimg = ImageIO.read(in); 
		
		WritableImage writableImage = SwingFXUtils.toFXImage(bufimg, null);
		
		return writableImage;
	}

}

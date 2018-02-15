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

public class DrawLine extends Application {
	Mat matrix = null;
	
	public DrawLine(){}
	
	public DrawLine(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage stage) throws Exception {
		DrawLine obj = new DrawLine();
		WritableImage writableImage = obj.loadImage();

		ImageView imageView = new ImageView(writableImage);

		imageView.setFitHeight(600);
		imageView.setFitWidth(600);

		imageView.setPreserveRatio(true);

		Group root = new Group(imageView);

		Scene scene = new Scene(root, 600, 400);

		stage.setTitle("Draw Line");
		stage.setScene(scene);
		stage.show();

	}

	private WritableImage loadImage() throws IOException {
		System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
		
		String file = "apa.jpg";
		Mat matrix = Imgcodecs.imread(file);
		
		Imgproc.line(
				matrix, 
				new Point(10, 200), 
				new Point(300, 200), 
				new Scalar(0, 0, 255),
				5
			);
		
		MatOfByte matOfByte = new MatOfByte();
		Imgcodecs.imencode(".jpg", matrix, matOfByte);
		

		byte[] byteArray = matOfByte.toArray();

		InputStream in = new ByteArrayInputStream(byteArray);
		BufferedImage bufImage = ImageIO.read(in);
		this.matrix = matrix;
		
		WritableImage writableImage = SwingFXUtils.toFXImage(bufImage, null);
		return writableImage;
	}

}

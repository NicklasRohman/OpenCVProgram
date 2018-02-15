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

public class DrawCircle extends Application{

	public DrawCircle(){}
	
	public DrawCircle(String[] args) {
		launch(args);
	}
	
	@Override
	public void start(Stage stage) throws Exception {
		
		DrawCircle drawCircle = new DrawCircle();
		WritableImage writableImage = drawCircle.loadImage();

		ImageView imageView = new ImageView(writableImage);
		
		imageView.setFitHeight(500);;
		imageView.setFitWidth(500);
		imageView.setPreserveRatio(true);
		
		Group root = new Group(imageView);
		
		Scene scene = new Scene(root,800,800);
		
		stage.setTitle("DrawCircle");
		stage.setScene(scene);
		stage.show();
		
	}

	private WritableImage loadImage() throws IOException {

		System.loadLibrary(Core.NATIVE_LIBRARY_NAME);

		String file = "apa.jpg";
		Mat matrix = Imgcodecs.imread(file);
		
		Imgproc.circle(matrix, new Point(530,360),100,new Scalar(0,0,250),10);
		
		MatOfByte matOfByte = new MatOfByte();
		Imgcodecs.imencode(".jpg", matrix, matOfByte);

		byte[] byteArray = matOfByte.toArray();
		
		InputStream in = new ByteArrayInputStream(byteArray);
		BufferedImage bufImage = ImageIO.read(in);
		
		WritableImage writableImage = SwingFXUtils.toFXImage(bufImage, null);
		
		return writableImage;
	}

}

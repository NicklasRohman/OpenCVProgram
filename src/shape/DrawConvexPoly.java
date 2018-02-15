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

public class DrawConvexPoly extends Application {

	public DrawConvexPoly() {
	}

	public DrawConvexPoly(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage stage) throws Exception {
		DrawConvexPoly obj = new DrawConvexPoly();
		WritableImage writableImage = obj.loadImage();
		ImageView imageView = new ImageView(writableImage);

		imageView.setFitHeight(500);
		imageView.setFitWidth(500);

		imageView.setPreserveRatio(true);

		Group root = new Group(imageView);
		Scene scene = new Scene(root, 600, 400);

		stage.setTitle("Convex Poly Image");
		stage.setScene(scene);
		stage.show();

	}

	private WritableImage loadImage() throws IOException {
		System.loadLibrary(Core.NATIVE_LIBRARY_NAME);

		String file = "apa.jpg";
		Mat matrix = Imgcodecs.imread(file);
		
		
		 MatOfPoint matOfPoint = new MatOfPoint (
		         new Point(75, 100), new Point(350, 100),
		         new Point(75, 150), new Point(350, 150),
		         new Point(75, 200), new Point(350, 200),
		         new Point(75, 250), new Point(350, 250)
		      ); 
		
		Imgproc.fillConvexPoly(matrix,matOfPoint,new Scalar(0,0,255));
		MatOfByte matOfByte = new MatOfByte();
		Imgcodecs.imencode(".jpg", matrix, matOfByte);
		byte[]byteArray = matOfByte.toArray();
		
		InputStream in = new ByteArrayInputStream(byteArray);
		BufferedImage bufImg = ImageIO.read(in);
		WritableImage writableImage = SwingFXUtils.toFXImage(bufImg, null);
		
		return writableImage;
	}

}

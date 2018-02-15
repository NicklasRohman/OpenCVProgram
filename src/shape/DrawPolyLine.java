package shape;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;
import javax.imageio.stream.ImageInputStream;

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

public class DrawPolyLine  extends Application{

	public DrawPolyLine() {
	}
	
	public DrawPolyLine(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage stage) throws Exception {

		DrawPolyLine obj = new DrawPolyLine();
		WritableImage writableImage = obj.loadImage();
		
		ImageView imageView = new ImageView(writableImage);
		
		imageView.setFitHeight(500);
		imageView.setFitWidth(500);
		
		imageView.setPreserveRatio(true);
		
		Group root = new Group(imageView);
		Scene scene = new Scene(root,600,600);
		
		stage.setTitle("PolyLine Image");
		stage.setScene(scene);
		stage.show();
		
	}

	private WritableImage loadImage() throws IOException {
		System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
		String file = "apa.jpg";
		
		Mat matrix = Imgcodecs.imread(file);
		
		List <MatOfPoint> list = new ArrayList();
		list.add(new MatOfPoint(new Point(75, 100), new Point(350, 100),
	            new Point(75, 150), new Point(350, 150),
	            new Point(75, 200), new Point(350, 200),
	            new Point(75, 250), new Point(350, 250)));
		
		
		Imgproc.polylines(matrix,list,false, new Scalar(0,0,255));
		
		
		MatOfByte matOfByte = new MatOfByte();
		Imgcodecs.imencode(".jpg", matrix,matOfByte);
		byte[] byteArray = matOfByte.toArray();
		
		
		InputStream in = new ByteArrayInputStream(byteArray);
		BufferedImage bufImg = ImageIO.read(in); 
		WritableImage writableImage = SwingFXUtils.toFXImage(bufImg, null);
		
		return writableImage;
	}

}
